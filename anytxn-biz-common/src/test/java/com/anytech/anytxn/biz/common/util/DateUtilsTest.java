package com.anytech.anytxn.biz.common.util;//package jrx.anytxn.biz.common.uitl;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.time.LocalDate;
//import java.util.Date;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * DateUtils Tester.
// *
// * @author ${user}
// * @version 1.0
// * @since <pre>九月 19, 2018</pre>
// */
//
//public class DateUtilsTest {
//
//
//    @Test
//    public void testDateUtilsLocalDateConvertToDate() throws Exception {
//        LocalDate localDate=LocalDate.now();
//    }
//
//    /**
//     * Method: format(Date date)
//     */
//    @Test
//    public void testFormatDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: format(Date date, String pattern)
//     */
//    @Test
//    public void testFormatForDatePattern() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: date(int year, int month, int date)
//     */
//    @Test
//    public void testDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: localDateConvertToDate(LocalDate localDate)
//     */
//    @Test
//    public void testLocalDateConvertToDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: dateConvertToLocalDate(Date date)
//     */
//    @Test
//    public void testDateConvertToLocalDate() throws Exception {
//        /*
//        java.sql.Date sqlDate = new java.sql.Date(2019,9,9);
//
//        LocalDate date1 = DateUtils.dateConvertToLocalDate(sqlDate);
//        LocalDate date = DateUtils.dateConvertToLocalDate(new Date());
//        */
//    }
//
//    /**
//     * Method: compareDateWithLocalDate(Date date1, Date date2)
//     */
//    @Test
//    public void testCompareDateWithLocalDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: parse(String date)
//     */
//    @Test
//    public void testParse() throws Exception {
//        LocalDate parse = DateUtils.parse("2017-09-09");
//        assertEquals("日期解析", LocalDate.of(2017, 9, 9), parse);
//        assertEquals("时间解析", LocalDate.of(2017, 9, 9),
//                DateUtils.parse("     2017-09-09 " + "23:04:34"));
//    }
//
//    /**
//     * Method: format(String str)
//     */
//    @Test
//    public void testFormatStr() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getBeforeDay(Date date)
//     */
//    @Test
//    public void testGetBeforeDay() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: compareDate(Date date, Date date1)
//     */
//    @Test
//    public void testCompareDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: formatDate(Date date, String pattern)
//     */
//    @Test
//    public void testFormatDateForDatePattern() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: formatDateTime(Date date)
//     */
//    @Test
//    public void testFormatDateTimeDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: formatDateTime()
//     */
//    @Test
//    public void testFormatDateTime() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: formatDateTime2()
//     */
//    @Test
//    public void testFormatDateTime2() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: formatDate(Date date)
//     */
//    @Test
//    public void testFormatDateDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: formatDate2()
//     */
//    @Test
//    public void testFormatDate2() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: formatDate2(Date date)
//     */
//    @Test
//    public void testFormatDate2Date() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: formatTime(Date date)
//     */
//    @Test
//    public void testFormatTimeDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: formatTime()
//     */
//    @Test
//    public void testFormatTime() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: formatTime2()
//     */
//    @Test
//    public void testFormatTime2() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: now()
//     */
//    @Test
//    public void testNow() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: nowLocalDate()
//     */
//    @Test
//    public void testNowLocalDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: nowLocalDateTime()
//     */
//    @Test
//    public void testNowLocalDateTime() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: parseDateTime(String datetime)
//     */
//    @Test
//    public void testParseDateTime() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: parseDate(String date, String format)
//     */
//    @Test
//    public void testParseDateForDateFormat() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: parseDate(String date)
//     */
//    @Test
//    public void testParseDateDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: parseDate(Date datetime)
//     */
//    @Test
//    public void testParseDateDatetime() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: formatDate(Object o)
//     */
//    @Test
//    public void testFormatDateO() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: formatDateTime(Object o)
//     */
//    @Test
//    public void testFormatDateTimeO() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: add(Date date, int field, int amount)
//     */
//    @Test
//    public void testAdd() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: addMilliSecond(Date date, int amount)
//     */
//    @Test
//    public void testAddMilliSecond() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: addSecond(Date date, int amount)
//     */
//    @Test
//    public void testAddSecond() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: addMiunte(Date date, int amount)
//     */
//    @Test
//    public void testAddMiunte() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: addHour(Date date, int amount)
//     */
//    @Test
//    public void testAddHour() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: addDay(Date date, int amount)
//     */
//    @Test
//    public void testAddDay() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: addMonth(Date date, int amount)
//     */
//    @Test
//    public void testAddMonth() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: addYear(Date date, int amount)
//     */
//    @Test
//    public void testAddYear() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getDate()
//     */
//    @Test
//    public void testGetDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getDateTime()
//     */
//    @Test
//    public void testGetDateTime() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: between(Date date, int offset, TimeUnit unit)
//     */
//    @Test
//    public void testBetween() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getDate(Date date, int interval)
//     */
//    @Test
//    public void testGetDateForDateInterval() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getDateByMonth(Date date, int interval)
//     */
//    @Test
//    public void testGetDateByMonth() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getDateByMinute(Date date, int interval)
//     */
//    @Test
//    public void testGetDateByMinute() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: compare(Date date1, Date date2)
//     */
//    @Test
//    public void testCompare() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getDaysBetween(Date date1, Date date2)
//     */
//    @Test
//    public void testGetDaysBetween() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getDiffDays(Date startdate, Date enddate)
//     */
//    @Test
//    public void testGetDiffDays() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getYearAndMonthAndDay(Date date)
//     */
//    @Test
//    public void testGetYearAndMonthAndDay() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getYear(Date date)
//     */
//    @Test
//    public void testGetYear() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getMonth(Date date)
//     */
//    @Test
//    public void testGetMonth() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getDay(Date date)
//     */
//    @Test
//    public void testGetDay() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: sameYear(long thisTime, long thatTime)
//     */
//    @Test
//    public void testSameYear() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: sameMonth(long thisTime, long thatTime)
//     */
//    @Test
//    public void testSameMonth() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: sameDate(long thisTime, long thatTime)
//     */
//    @Test
//    public void testSameDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: toYear(Date date, int year)
//     */
//    @Test
//    public void testToYear() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getMonthStartTime(Date date)
//     */
//    @Test
//    public void testGetMonthStartTime() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getMonthEndTime(Date date)
//     */
//    @Test
//    public void testGetMonthEndTime() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getMonthStartEnd(Date d1, int months)
//     */
//    @Test
//    public void testGetMonthStartEnd() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getWorkingDate()
//     */
//    @Test
//    public void testGetWorkingDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getDateByCurrDateAndDays(Date currDate, int days)
//     */
//    @Test
//    public void testGetDateByCurrDateAndDays() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getDayTerm(Date date)
//     */
//    @Test
//    public void testGetDayTermDate() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: getDayTerm(Date d1, Date d2)
//     */
//    @Test
//    public void testGetDayTermForD1D2() throws Exception {
//        //TODO: Test goes here...
//    }
//
//    /**
//     * Method: beforeDay(Date date1, Date date2)
//     */
//    @Test
//    public void testBeforeDay() throws Exception {
//        //TODO: Test goes here...
//    }
//
//
//}
