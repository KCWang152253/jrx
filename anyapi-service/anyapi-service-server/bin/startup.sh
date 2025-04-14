#!/usr/bin/env bash

SHELL_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd)
source ${SHELL_DIR}/check.sh
. /etc/profile
. ~/.bash_profile
LOG_DIR=${PROJECT_HOME}/logs
if [[ ${CLASSPATH} ]]; then
    CLASSPATH="${CLASSPATH}:${PROJECT_HOME}/config:${PROJECT_HOME}/lib/*"
else
    CLASSPATH="${PROJECT_HOME}/config:${PROJECT_HOME}/lib/*"
fi

#环境变量
if [[ -n "$1" ]]; then
    PROFILE=$1
fi


#GC日志参数
#-XX:+PrintGC 输出GC日志
#-XX:+PrintGCDetails 输出GC的详细日志
#-XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
#-XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
#-XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
#-Xloggc:../logs/gc.log 日志文件的输出路径
# -XX:+DisableExplicitGC 禁止显示的调用gc发方法
GC_OPTS="-XX:+DisableExplicitGC"
GC_OPTS="${GC_OPTS} -XX:+PrintGCDetails"
GC_OPTS="${GC_OPTS} -XX:+PrintGCDateStamps"
GC_OPTS="${GC_OPTS} -XX:GCLogFileSize=10M"
GC_OPTS="${GC_OPTS} -XX:NumberOfGCLogFiles=10"
GC_OPTS="${GC_OPTS} -XX:+UseGCLogFileRotation"
GC_OPTS="${GC_OPTS} -XX:ErrorFile=${LOG_DIR}/hs_err_pid<pid>.log"
GC_OPTS="${GC_OPTS} -Xloggc:${LOG_DIR}/gc.log"


#内存溢出记录dump文件
#HEAP_DUMP="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOG_DIR}/heap_dump_`date '+%Y-%m-%d—%H%M%S'`.hprof"
HEAP_DUMP="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOG_DIR}/"


JAVA_OPT="-Dspring.profiles.active=${PROFILE}"
JAVA_OPT="${JAVA_OPT} -Duser.timezone=GMT+8 -XX:+PrintCommandLineFlags"



#JMX参数, 生产环境不开启
JMX_OPTS=""
if [[ "${JMX_ENABLED}" = "true" ]]; then
    JMX_OPTS="-Dcom.sun.management.jmxremote.port=${JMX_PORT}"
    JMX_OPTS="${JMX_OPTS} -Djava.rmi.server.hostname=${JMX_HOSTNAME}"
    JMX_OPTS="${JMX_OPTS} -Dcom.sun.management.jmxremote=true"
    JMX_OPTS="${JMX_OPTS} -Dcom.sun.management.jmxremote.ssl=false"
    JMX_OPTS="${JMX_OPTS} -Dcom.sun.management.jmxremote.authenticate=false"
    JAVA_OPT="${JAVA_OPT} ${JMX_OPTS}"
fi

#如果为空
if [[ ! "${JAVA_MEM_OPTS}" ]]; then
    JAVA_MEM_OPTS="-Xms1g -Xmx4g -Xss256k"
fi

#JAVA_OPT="${JAVA_OPT} -javaagent:/home/jrxany/skywalking-agent/skywalking-agent.jar -Dskywalking.agent.service_name=AnyEST管平台 -Dskywalking.collector.backend_service=172.16.101.29:11800"


#===========================================================================================
# JVM Configuration 按开发环境，测试环境，集成环境，生产环境区分配置参数
# poc 和生产环境一套，生成和poc按最小内存8g计算
# 测试，sit，uat一套环境
# 开发一套环境（默认）
# 根据实际的配置调整内存大小和xss的大小值
#===========================================================================================
# 管理平台
if [[ "${MODE}" = "ADMIN" ]]; then
    #JAVA_MEM_OPTS="-Xms512m -Xmx2g"
    JAVA_OPT="${JAVA_OPT} -XX:ParallelGCThreads=8 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC"
    JAVA_OPT="${JAVA_OPT} -XX:+CMSParallelRemarkEnabled"
else # service api
    JAVA_OPT="${JAVA_OPT} -XX:+UseG1GC"
    JAVA_OPT="${JAVA_OPT} -XX:MaxGCPauseMillis=100"
    JAVA_OPT="${JAVA_OPT} -XX:MaxMetaspaceSize=512m"
    JAVA_OPT="${JAVA_OPT} -XX:MetaspaceSize=512m"
fi

JAVA_OPT="${JAVA_OPT} -XX:+UseFastAccessorMethods"
JAVA_OPT="${JAVA_OPT} -XX:+ParallelRefProcEnabled"

echo -e "\033[31m 注意：当前使用的是${PROFILE}环境，请根据系统实际情况设置最大最小内存参数,当前默认值：${JAVA_MEM_OPTS} \033[0m"

JAVA_OPT="${JAVA_MEM_OPTS} ${JAVA_OPT} ${GC_OPTS} ${HEAP_DUMP}"

# print out env properties
echo "工程目录: ${PROJECT_HOME}"
echo "配置文件变量：\$PROFILE=${PROFILE}"
echo "服务模式：\$MODE=${MODE}"
echo "日志目录：\$LOG_DIR=${LOG_DIR}"
echo "控制台日志：\$STARTUP_LOG=${STARTUP_LOG}"
echo "JMX启用：\$JMX_ENABLED=${JMX_ENABLED}"
echo \$JAVA_OPT="${JAVA_OPT}"

#标准日志输出，生成环境不开启
LOG_FILE="start.out"
TID_FILE="${PROJECT_HOME}/tpid"

JAVA_CMD="${JAVA_OPT} -cp ${CLASSPATH} ${APPLICATION_MAIN}"

#创建日志文件和目录
logDir(){
    if [[ ! -d "${LOG_DIR}" ]]; then
        mkdir "${LOG_DIR}"
    fi
    if [[ ! -f "${LOG_DIR}/${LOG_FILE}" ]]; then
        touch "${LOG_DIR}/${LOG_FILE}"
    fi
}

#查找记录的日志
findLogFile(){
    if [[ "${PROFILE}" = "prd" || "${PROFILE}" = "poc" ]]; then
        ftime=1;
        echo -n "find log file."
        while [[ ${ftime} < 7 ]]; do
            for logFile in `ls ${LOG_DIR}`
            do
                if [[ ${logFile} =~ \.log$ ]];then # [[ $file =~ \.log$ ]] 匹配以.log结尾的文件
                    LOG_FILE=${logFile}
                    ftime=8
                    break
                fi
            done
            echo -n "."
            ((ftime++))
            sleep 1
        done
        echo -n ", find file: ${LOG_FILE}"
    fi
}

#执行主命令
executeMain(){
    if [[ "${STARTUP_LOG}" = "true" ]]; then
        JAVA_CMD="${JAVA_CMD} > ${LOG_DIR}/${LOG_FILE} 2>&1 &"
        echo "执行命令: ${JAVA_HOME}/bin/java --DATA_ID=${DATA_ID} --NAMESPACE=${NAMESPACE} --SERVER_ADDR=${SERVER_ADDR} --DATA_PARTITION=${DATA_PARTITION} ${JAVA_CMD}"
        nohup ${JAVA_HOME}/bin/java ${JAVA_OPT}  -cp ${CLASSPATH} ${APPLICATION_MAIN} --DATA_PARTITION=${DATA_PARTITION} --DATA_ID=${DATA_ID} --NAMESPACE=${NAMESPACE} --SERVER_ADDR=${SERVER_ADDR} > ${LOG_DIR}/${LOG_FILE} 2>&1 &
    else
        JAVA_CMD="${JAVA_CMD} > /dev/null 2>&1 &"
        echo "执行命令: ${JAVA_HOME}/bin/java --DATA_ID=${DATA_ID} --NAMESPACE=${NAMESPACE} --SERVER_ADDR=${SERVER_ADDR} ${JAVA_CMD}"
        nohup ${JAVA_HOME}/bin/java ${JAVA_OPT}  -cp ${CLASSPATH} ${APPLICATION_MAIN} --DATA_ID=${DATA_ID} --NAMESPACE=${NAMESPACE} --SERVER_ADDR=${SERVER_ADDR} --DATA_PARTITION=${DATA_PARTITION} > /dev/null 2>&1 &
    fi
}

#记录pid到文件
writePid(){
    if [[ ! -f ${TID_FILE} ]]; then
        touch "${TID_FILE}"
    fi
    echo "write pid file: $!"
    echo $! > "${TID_FILE}"
}

#移除pid文件
rmPid(){
  if [[ -e ${TID_FILE} ]]; then
    rm -f "${TID_FILE}"
fi
}

startup(){
    getPID
    logDir
    echo "================================================================================================================"
    if [[ ${PROFILE} -ne 0 ]]; then
        echo "${APPLICATION_MAIN} already started (PID=${TPID})"
        echo "================================================================================================================"
    else
        echo "Starting ${APPLICATION_MAIN}"
        executeMain
        sleep 1
        getPID
        findLogFile
        writePid
        if [[ ${TPID} -ne 0 ]]; then
            echo "${APPLICATION_MAIN} (PID=${TPID})...[Success]"
            echo "================================================================================================================"
            echo "tail log file : ${LOG_DIR}/${LOG_FILE}"
            tail -f "${LOG_DIR}/${LOG_FILE}"
        else
            echo -e "\033[31m ${APPLICATION_MAIN} boot failed. [Failed] \033[0m"
            echo "================================================================================================================"
            tail -200 "${LOG_DIR}/${LOG_FILE}"
            rmPid
        fi
    fi
}

rmPid

#服务启动
startup