package com.tomhurry.utils;

import java.util.UUID;

/**
 * ID生成工具类
 *
 * @author taozhi
 * @date 2022/5/19 12:21
 * @since 1.0.0
 */
public class IdGenerator {

    private static final String RANDOM_STRING = "abcdefghijklmnopqrstuvwxyz";

    private IdGenerator() {
    }

    /**
     * 生成UUID
     *
     * @return 返回uuid
     */
    public static String genUUID() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        return id.replace("-", "");
    }

    public static String genRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        int len = RANDOM_STRING.length();
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM_STRING.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }

    private static int getRandom(int count) {
        return (int) Math.round(Math.random() * count);
    }
}
