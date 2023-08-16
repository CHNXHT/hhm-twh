package com.idata.hhmtwh.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IdCardUtils {
    // 获取性别
    public static int getGender(String idCardNumber) {
        if (idCardNumber.length() == 18) {
            int genderDigit = Integer.parseInt(idCardNumber.substring(16, 17));
            return genderDigit % 2 == 0 ? 2 : 1;
        } else if (idCardNumber.length() == 15) {
            int genderDigit = Integer.parseInt(idCardNumber.substring(14, 15));
            return genderDigit % 2 == 0 ? 2 : 1;
        }
        //error
        return -1;
    }

    // 获取年龄
    public static String getAge(String idCardNumber) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);

            Date birthDate;
            if (idCardNumber.length() == 18) {
                birthDate = sdf.parse(idCardNumber.substring(6, 14));
            } else if (idCardNumber.length() == 15) {
                birthDate = sdf.parse("19" + idCardNumber.substring(6, 12));
            } else {
                return "-1"; // 身份证号长度不合法
            }

            calendar.setTime(birthDate);
            int birthYear = calendar.get(Calendar.YEAR);

            return String.valueOf(currentYear - birthYear);
        } catch (ParseException e) {
            e.printStackTrace();
            return "-1"; // 解析出生日期失败
        }
    }

}
