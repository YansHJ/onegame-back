package cn.yans.onegame.conmon.utils;

import java.util.Random;

public class ProbabilityUtils {

    public static int getProbability(){
        Random random = new Random();
        return random.nextInt(101);
    }
}
