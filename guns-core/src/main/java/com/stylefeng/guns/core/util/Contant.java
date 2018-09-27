package com.stylefeng.guns.core.util;

public class Contant {
    public static String commonStringUtil(String str) {
        String ss = "";
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if(charArray[i] >= 'A' && charArray[i] <= 'Z'){
                ss += "_"+charArray[i];
            } else {
                ss += charArray[i];
            }
        }
        return ss.toLowerCase();
    }
}
