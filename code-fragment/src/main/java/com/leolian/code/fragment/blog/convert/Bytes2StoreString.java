package com.leolian.code.fragment.blog.convert;

import javax.swing.text.JTextComponent;
import java.text.DecimalFormat;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/20 18:31
 */
public class Bytes2StoreString {
    
    private static long TB_LONG = 1024L * 1024L * 1024L * 1024L;
    private static long GB_LONG = 1024L * 1024L * 1024L;
    private static long MB_LONG = 1024 * 1024;
    
    public static String getNetFileSizeDescription(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.00");
        if(size >= TB_LONG){
            double i = (size * 1.0) / TB_LONG;
            bytes.append(format.format(i)).append("TB");
        } else if (size >= GB_LONG) {
            double i = (size * 1.0) / GB_LONG;
            bytes.append(format.format(i)).append("GB");
        } else if (size >= MB_LONG) {
            double i = (size * 1.0) / MB_LONG;
            bytes.append(format.format(i)).append("MB");
        } else if (size >= 1024) {
            double i = (size * 1.0) / 1024;
            bytes.append(format.format(i)).append("KB");
        } else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            } else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }

    public static void main(String[] args) {
        System.out.println(getNetFileSizeDescription(20667685123L));
    }
}
