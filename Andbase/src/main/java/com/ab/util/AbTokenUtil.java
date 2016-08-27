package com.ab.util;

/**
 * Created by youarenotin on 16/8/27.
 */
public class AbTokenUtil {
    public static String getToken(String userId) {
        String str = String.format("%010d", Integer.valueOf(userId));
        int token1 = 6989 / 1000;
        int token2 = 69 % 10;
        int token3 = 698 % 10;
        int token4 = 6989 % 10;
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        int index = 0;
        while (i < 14) {
            int index2;
            if (i == 0) {
                stringBuffer.append(token1);
                index2 = index;
            } else if (i == 3) {
                stringBuffer.append(token2);
                index2 = index;
            } else if (i == 8) {
                stringBuffer.append(token3);
                index2 = index;
            } else if (i == 11) {
                stringBuffer.append(token4);
                index2 = index;
            } else {
                index2 = index + 1;
                stringBuffer.append(str.charAt(index));
            }
            i++;
            index = index2;
        }
        return stringBuffer.toString();
    }
}
