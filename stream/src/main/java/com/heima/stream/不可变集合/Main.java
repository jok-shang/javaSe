package com.heima.stream.不可变集合;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/20 15:31
 */
public class Main {
    public static void main(String[] args) {
        // 将 "20230601" 转换为 LocalDate 对象
        String timeString = "202306";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        LocalDate currentTime = LocalDate.parse(timeString, formatter);

        // 计算同比时间（一年前的时间）
        LocalDate sameTimeLastYear = currentTime.minusYears(1);

        // 计算环比时间（前一个时间段的时间）
        LocalDate sameTimeLastMonth = currentTime.minusMonths(1);

        // 格式化输出结果
        String currentFormatted = currentTime.format(formatter);
        String sameTimeLastYearFormatted = sameTimeLastYear.format(formatter);
        String sameTimeLastMonthFormatted = sameTimeLastMonth.format(formatter);

        // 输出结果
        System.out.println("当前时间：" + currentFormatted);
        System.out.println("同比时间：" + sameTimeLastYearFormatted);
        System.out.println("环比时间：" + sameTimeLastMonthFormatted);
    }
}
