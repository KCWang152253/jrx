package com.anytech.anytxn.biz.common.util;

import com.anytech.anytxn.biz.common.constant.AnyTxnCommonRespCode;
import com.anytech.anytxn.biz.common.constant.CommonRepDetail;
import com.anytech.anytxn.biz.common.exception.AnyTxnCommonException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * PartitionKey生成工具类
 * @author peidong.meng
 * @date 2020/7/17
 */
public class PartitionKeyUtil {

    public static int BASE = 8192;

    /**
     * 获取long类型的partitionKey
     * @param source
     * @return
     */
    public static Long partitionKey(String source){
        if (StringUtils.isEmpty(source)) {
            throw new NullPointerException("partitionKey source is null");
        }

        try {
            return Long.parseLong(source) % BASE;
        } catch (Exception e){
            throw new AnyTxnCommonException(AnyTxnCommonRespCode.P_CLASS_CAST_ERR, CommonRepDetail.PARTITIONKEY_LONG, source);
        }
    }

    /**
     * 获取int类型的partitionKey
     * @param source
     * @return
     */
    public static Integer IntPartitionKey(String source){
        return partitionKey(source).intValue();
    }

    /**
     * 获取String类型的partitionKey
     * @param source
     * @return
     */
    public static String StrPartitionKey(String source){
        return String.valueOf(partitionKey(source));
    }


    /**
     * batch按照partition分组用。
     * 如job.partitionKey=0-1023，gridSize=8.则：0-127，128-255，...，896-1023
     * 如job.partitionKey=2-2，gridSize=8则只生成一个2-2的结果
     * @param from: 起始partition值
     * @param to : 结束partition值
     * @param n：需要分的个数
     */
    public static List<Pair<Integer, Integer>> partitionGroup(int from, int to, int n) {
        // 共size个partitionKey需要分组
        int size = to - from + 1;
        // 分组结果
        List<Pair<Integer, Integer>> partitionGroupList = new ArrayList<>();

        // 平均分后溢出partitionKey个数
        int remainder = size % n;
        // 每组partitionKey个数
        int number = size / n;
        int i = from;

        while (i <= to) {
            int v = i + number - 1;

            // 有溢出个数则多分配一个
            if (remainder > 0) {
                //多放一個
                v = i + number;
                partitionGroupList.add(Pair.of(i,v));
                // 下一次开始也 + 1
                i = i + 1;
                remainder--;
            } else {
                partitionGroupList.add(Pair.of(i,v));
            }

            i = i + number;
        }
        return partitionGroupList;
    }
}
