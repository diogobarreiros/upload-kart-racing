package br.com.kartRacing.shared.utils;

import java.time.LocalTime;

/**
 * 
 * @author diogo
 *
 */
public class LocalTimeUtils {

	/**
	 * Format string for localtime
	 * @param time
	 * @return LocalTime
	 */
	public static LocalTime formatTime(String time) {

		LocalTime localTime = LocalTime.of(
				00,
				Integer.valueOf(time.substring(0, 1)),
				Integer.valueOf(time.substring(2, 4)),
				Integer.valueOf(time.substring(5, 8))
				);

		return localTime;
	}
}
