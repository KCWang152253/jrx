#!/usr/bin/env bash

#利用pwd命令获取当前工程目录，实际获取到的是该shell脚本的目录。再利用sed命令将/bin替换为空
PROJECT_HOME=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd | sed 's/\/bin//')
SHELL_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd)

source ${SHELL_DIR}/env.sh
source ${SHELL_DIR}/JAVA_ENV.sh

echo "work directory: ${PROJECT_HOME}"


TPID=0

getPID(){
    TPID=`pgrep -f ${APPLICATION_MAIN}`
}


#tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
getPID
if [[ ${TPID} -ne 0 ]]; then
        echo "check: ${APPLICATION_MAIN} is running, ps info: ${TPID}"
else
        echo "check: ${APPLICATION_MAIN} is not running."
fi
