package cn.yans.onegame.common.utils;

import java.util.UUID;

public class UUIDUtils {

    public static String get16Uuid(){
        return UUID.randomUUID().toString().replace("-","").substring(5,21);
    }
}
