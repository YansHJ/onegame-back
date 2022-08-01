package cn.yans.onegame.common.utils;

import java.util.Random;

public class ProbabilityUtils {

    public static int getProbability(){
        Random random = new Random();
        return random.nextInt(101);
    }
}
