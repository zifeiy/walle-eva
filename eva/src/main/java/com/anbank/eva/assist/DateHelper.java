package com.anbank.eva.assist;

public class DateHelper {
	
	public static String getDateNDaysAgo(String date, int n) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6, 8));
		while (n > 0) {
			n --;
			if (day > 1) day --;
			else {
				month --;
				if (month < 1) { month = 12; year --; }
				if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) day = 31;
				else if (month == 4 || month == 6 || month == 9 || month == 11) day = 30;
				else {
					if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) day = 29;
					else day = 28;
				}
			}
		}
		return String.format("%04d%02d%02d", year, month, day);
	}
	
	public static boolean checkTheLastDayOfTheMonth(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6, 8));
		if (month == 2) {
			if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) return day == 29;
			else return day == 28;
		}
		else if (month < 8 && month % 2 == 1 || month > 7 && month % 2 == 0) return day == 31;
		else return day == 30;
	}
	
	public static int getDeltaDays(String beforeDate, String endDate) {
		int cnt = 0;
		while (beforeDate.equals(endDate) == false && cnt <= 10) {
			endDate = getDateNDaysAgo(endDate, 1);
			cnt ++;
		}
		return cnt;
	}
}
