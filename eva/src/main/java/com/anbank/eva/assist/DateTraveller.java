package com.anbank.eva.assist;

import java.util.ArrayList;
import java.util.List;

public class DateTraveller {
    
    public static List<String> getDateList(String startDateString, String endDateString) {
        List<String> dateList = new ArrayList<String>();
        // start date
        int startDate = Integer.parseInt(startDateString);
        int startYear = startDate / 10000;
        int startMonth = startDate / 100 % 100;
        int startDay = startDate % 100;
        // end date
        int endDate = Integer.parseInt(endDateString);
        int endYear = endDate / 10000;
        int endMonth = endDate / 100 % 100;
        int endDay = endDate % 100;
        // begin
        int y = startYear;
        int m = startMonth;
        int d = startDay;
        while (y < endYear || y == endYear && m < endMonth || y == endYear && m == endMonth && d <= endDay) {
//          System.out.println(y + "-" + m + "-" + d + " vs. " + endYear + "-" + endMonth + "-" + endDay + " : " + 
//                  (y < endYear) + "," +  (y == endYear && m < endMonth) + "," + (y == endYear && m == endMonth && d <= endDay)
//                  );
            String tmpDateString = String.format("%d%02d%02d", y, m, d);
            dateList.add(tmpDateString);
            boolean isRunNian = (y % 400 == 0 || y % 4 == 0 && y % 100 != 0);
            int lastDay = 31;
            if (m == 2) {
                if (isRunNian) lastDay = 29;
                else lastDay = 28;
            } 
            else if (m <= 7 && m % 2 == 0 || m > 7 && m % 2 == 1) {
                lastDay = 30;
            }
            if (d >= lastDay) {
                m ++;
                d = 1;
                if (m > 12) {
                    y ++;
                    m = 1;
                }
            }
            else {
                d ++;
            }
        }
        return dateList;
    }
    
    // test
    public static void main(String[] args) {
        List<String> dateList = getDateList("20151203", "20160301");
        for (String dateS : dateList) {
            System.out.println(dateS);
        }
    }
    
}
