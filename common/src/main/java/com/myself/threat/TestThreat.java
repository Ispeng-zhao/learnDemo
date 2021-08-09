package com.myself.threat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: hanmm
 * @Date: 2021/04/23/14:46
 * @Description:
 */
public class TestThreat {
    public static void main(String[] args) {
        System.out.println(numberToIp(-1068957685L));
        System.out.println(getOrderIdByTime());
    }


    private static String numberToIp(Long number) {
        //等价上面
        String ip = "";
        for (int i = 3; i >= 0; i--) {
            ip += String.valueOf((number & 0xff));
            if (i != 0) {
                ip += ".";
            }
            number = number >> 8;
        }

        return ip;
    }

    public static String getOrderIdByTime() {
        DateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
        return sdf.format(new Date());
    }
}
