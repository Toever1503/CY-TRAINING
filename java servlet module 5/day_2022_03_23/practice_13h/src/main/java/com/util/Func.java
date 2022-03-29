package com.util;

public class Func {
    public static String getLastPath(String url) {
        String[] paths = url.split("/");
        String lastPath = paths[paths.length - 1];
        return lastPath.replaceAll("[?&]([\\w+]*=[\\w+]*)", "");
    }

}
