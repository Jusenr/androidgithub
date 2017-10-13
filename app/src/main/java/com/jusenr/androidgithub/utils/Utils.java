package com.jusenr.androidgithub.utils;

import android.text.TextUtils;
import android.util.Base64;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mingjun on 16/7/20.
 */
public class Utils {

    public static String replaceAllBlank(String str) {
        if (TextUtils.isEmpty(str)) return "";

        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(str);
        String dest = m.replaceAll("");
        return dest;
    }

    public static String trimNewLine(String str) {
        if (TextUtils.isEmpty(str)) return "";

        str = str.trim();
        Pattern p = Pattern.compile("\t|\r|\n");
        Matcher m = p.matcher(str);
        String dest = m.replaceAll("");
        return dest;
    }

    public static String base64Decode(String originalString) {
        if (TextUtils.isEmpty(originalString)) return "";

        return new String(Base64.decode(originalString, 0));
    }

    /**
     * Github时间字符串格式化
     *
     * @param githubTime 2017-10-13T08:11:48Z
     * @return 时间字符串
     */
    public static String formatDateGithub(String githubTime) {
        githubTime = githubTime.replace("Z", " UTC");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z");
            Date date = formatter.parse(githubTime);
            return date.toLocaleString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
