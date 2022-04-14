package com.practice_2022_04_06.util;

public class StringUtil {
    public static String getLastPath(String url) {
        String[] paths = url.split("/");
        String lastPath = paths[paths.length - 1];
        return lastPath.replaceAll("[?&]([\\w+]*=[\\w+]*)", "");
    }

}
