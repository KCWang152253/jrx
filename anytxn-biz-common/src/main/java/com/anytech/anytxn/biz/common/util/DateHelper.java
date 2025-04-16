package com.anytech.anytxn.biz.common.util;

import com.anytech.anytxn.biz.common.constant.AnyTxnCommonRespCode;
import com.anytech.anytxn.biz.common.exception.AnyTxnCommonException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * 时间工具类
 * @author fhp
 *
 */
public class DateHelper {

	private static DateTimeFormatter YMD_HMS_MS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
	private static DateTimeFormatter YMD_HMS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static DateTimeFormatter YMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
	private static DateTimeFormatter HMS_MS = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
	private static DateTimeFormatter HMS = DateTimeFormatter.ofPattern("HH:mm:ss");

	private DateHelper() {

	}

	/**
	 * 将TemporalAccessor的子类实例格式化成yyyy-MM-dd
	 * @param ta TemporalAccessor(LocalDateTime/LocalDate/LocalTime为其实现类)
	 * @return yyyy-MM-dd格式字符串
	 */
	public static String format2Ymd (TemporalAccessor ta) {
		return YMD.format(ta);
	}

	public static String formatYmd (TemporalAccessor ta) {
		return YYYYMMDD.format(ta);
	}
	/**
	 * 将TemporalAccessor的子类实例格式化成yyyy-MM-dd HH:mm:ss
	 * @param ta TemporalAccessor(LocalDateTime/LocalDate/LocalTime为其实现类)
	 * @return yyyy-MM-dd HH:mm:ss格式字符串
	 */
	public static String format2YmdHms (TemporalAccessor ta) {
		return YMD_HMS.format(ta);
	}
	/**
	 * 将TemporalAccessor的子类实例格式化成yyyy-MM-dd HH:mm:ss,SSS
	 * @param ta TemporalAccessor(LocalDateTime/LocalDate/LocalTime为其实现类)
	 * @return yyyy-MM-dd HH:mm:ss,SSS格式字符串
	 */
	public static String format2YmdHmsMs (TemporalAccessor ta) {
		return YMD_HMS_MS.format(ta);
	}
	/**
	 * 将TemporalAccessor的子类实例格式化成HH:mm:ss
	 * @param ta TemporalAccessor(LocalDateTime/LocalDate/LocalTime为其实现类)
	 * @return HH:mm:ss格式字符串
	 */
	public static String format2Hms (TemporalAccessor ta) {
		return HMS.format(ta);
	}
	/**
	 * 将TemporalAccessor的子类实例格式化成HH:mm:ss,SSS
	 * @param ta TemporalAccessor(LocalDateTime/LocalDate/LocalTime为其实现类)
	 * @return HH:mm:ss,SSS格式字符串
	 */
	public static String format2HmsMs (TemporalAccessor ta) {
		return HMS_MS.format(ta);
	}

	/**
	 * 将字符串转换为LocalDateTime
	 * @param dateStr 格式必须为[yyyy-MM-dd HH:mm:ss,SSS/yyyy-MM-dd HH:mm:ss]中一种
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime (String dateStr) {
		if (StringUtils.isNotBlank(dateStr)) {
			if (dateStr.length() > 19) {
				return  LocalDateTime.parse(dateStr, YMD_HMS_MS);
			}
			if (dateStr.length() > 10) {
				return  LocalDateTime.parse(dateStr, YMD_HMS);
			}
		}
		throw new AnyTxnCommonException(AnyTxnCommonRespCode.P_ERR);
	}

	/**
	 * 将字符串转换为LocalDateTime
	 * @param dateStr 格式必须为[yyyy-MM-dd HH:mm:ss,SSS/yyyy-MM-dd HH:mm:ss]中一种
	 * @return LocalDateTime
	 */
	public static LocalDateTime toLocalDateTime (String dateStr, String pattern) {
		if (StringUtils.isBlank(pattern)) {
			return toLocalDateTime(dateStr);
		}

		if (StringUtils.isNotBlank(dateStr)) {
			DateTimeFormatter self = DateTimeFormatter.ofPattern(pattern);
			return  LocalDateTime.parse(dateStr, self);
		}
		throw new AnyTxnCommonException(AnyTxnCommonRespCode.P_ERR);
	}


	/**
	 * 将字符串转换为LocalDate
	 * @param dateStr 格式必须为[yyyy-MM-dd HH:mm:ss,SSS/yyyy-MM-dd HH:mm:ss/yyyy-MM-dd]中一种
	 * @return LocalDate
	 */
	public static LocalDate toLocalDate (String dateStr) {
		if (StringUtils.isNotBlank(dateStr)) {
			if (dateStr.length() > 19) {
				return  LocalDate.parse(dateStr, YMD_HMS_MS);
			}
			if (dateStr.length() > 10) {
				return  LocalDate.parse(dateStr, YMD_HMS);
			}
			return  LocalDate.parse(dateStr, YMD);
		}
		throw new AnyTxnCommonException(AnyTxnCommonRespCode.P_ERR);
	}

	/**
	 * 将字符串转换为LocalTime
	 * @param dateStr 格式必须为[yyyy-MM-dd HH:mm:ss,SSS/yyyy-MM-dd HH:mm:ss/HH:mm:ss,SSS/HH:mm:ss]中一种
	 * @return LocalTime
	 */
	public static LocalTime toLocalTime (String dateStr) {
		if (StringUtils.isNotBlank(dateStr)) {
			if (dateStr.length() > 19) {
				return  LocalTime.parse(dateStr, YMD_HMS_MS);
			}
			if (dateStr.length() > 12) {
				return  LocalTime.parse(dateStr, YMD_HMS);
			}
			if (dateStr.length() > 8) {
				return  LocalTime.parse(dateStr, HMS_MS);
			}
			return  LocalTime.parse(dateStr, HMS);
		}
		throw new AnyTxnCommonException(AnyTxnCommonRespCode.P_ERR);
	}

	/**
	 * 两日期相隔天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long dateDiff(LocalDate date1, LocalDate date2){
		return Math.abs(date1.toEpochDay() - date2.toEpochDay());
	}

	public static String formatLodalDate(TemporalAccessor temporalAccessor, String s) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(s);
		return dateTimeFormatter.format(temporalAccessor);
	}


}
