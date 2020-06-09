package com.caochf.spider.util;

import java.util.UUID;

public class IDGenerate {
    public static String getUUID32() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-","");
    }
}
