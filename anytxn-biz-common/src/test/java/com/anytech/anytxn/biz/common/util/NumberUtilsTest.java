package com.anytech.anytxn.biz.common.util;//package jrx.anytxn.biz.common.uitl;
//
//import org.junit.Test;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
///**
// * NumberUtils Tester.
// *
// * @author ${user}
// * @version 1.0
// * @since <pre>九月 15, 2018</pre>
// */
//
//
//public class NumberUtilsTest {
//    private BigDecimal bigDecimal = new BigDecimal("3");
//    private BigDecimal bigDecimal1 = new BigDecimal("9");
//
//
//    /**
//     * Method: compareBigDecimal(BigDecimal num1, BigDecimal num2)
//     */
//    @Test
//    public void testCompareToBigDecimal() {
//        assertTrue(NumberUtils.compareBigDecimal(bigDecimal1, bigDecimal));
//        assertFalse(NumberUtils.compareBigDecimal(bigDecimal, bigDecimal1));
//        try {
//            NumberUtils.compareBigDecimal(null, bigDecimal);
//        } catch (NullPointerException e) {
//            assertEquals("null异常", e.getMessage(), "num1");
//        }
//        try {
//            NumberUtils.compareBigDecimal(bigDecimal1, null);
//        } catch (NullPointerException e) {
//            assertEquals("null异常", e.getMessage(), "num2");
//        }
//
//    }
//
//    /**
//     * Method: sum(Collection<BigDecimal> collection)
//     */
//    @Test
//    public void testSumCollection() {
//        List<BigDecimal> list = null;
//        try {
//            NumberUtils.sum(list);
//        } catch (UnsupportedOperationException e) {
//            assertEquals("测试异常", "不支持空集合操作", e.getMessage());
//        }
//        list = new ArrayList<>();
//        list.add(bigDecimal);
//        list.add(bigDecimal1);
//        assertEquals("测试求和", new BigDecimal("12"), NumberUtils.sum(list));
//    }
//
//    /**
//     * Method: sum(BigDecimal... bigDecimals)
//     */
//    @Test
//    public void testSumBigDecimals() {
//        BigDecimal[] bigDecimals = null;
//        try {
//            NumberUtils.sum(bigDecimals);
//        } catch (UnsupportedOperationException e) {
//            assertEquals("测试异常", "不支持空数组操作", e.getMessage());
//        }
//        bigDecimals = new BigDecimal[]{bigDecimal, bigDecimal1};
//        assertEquals("数组求和", new BigDecimal("12"), NumberUtils.sum(bigDecimals));
//    }
//
//
//}
