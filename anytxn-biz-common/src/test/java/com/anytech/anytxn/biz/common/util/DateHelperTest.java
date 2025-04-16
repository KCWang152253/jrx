package com.anytech.anytxn.biz.common.util;

import org.junit.Test;

import java.time.LocalDate;

/**
 * @author peidong.meng
 * @date 2020/6/18
 */
public class DateHelperTest {

    @Test
    public void toLocalDateTime(){

        LocalDate a1 = DateHelper.toLocalDateTime("20200518000000", "yyyyMMddHHmmss").toLocalDate();
        LocalDate a2 = DateHelper.toLocalDateTime("20200619000000", "yyyyMMddHHmmss").toLocalDate();

    }
}
