package com.jusenr.androidgithub;

import com.jusenr.androidgithub.utils.Utils;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2017/10/13
 * Time       : 17:05
 * Project    ：androidgithub.
 */
public class MainTest {

    public static void main(String[] args) {
        String githubTime="2017-10-13T08:11:48Z";
        String s = Utils.formatDateGithub(githubTime);
        System.out.println(s);
    }
}
