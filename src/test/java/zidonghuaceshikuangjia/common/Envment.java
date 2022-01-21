package zidonghuaceshikuangjia.common;

import java.util.HashMap;
import java.util.Map;

public class Envment {
    private static Map<String, Object> env = new HashMap<>();

    public static void addEnv(String key, Object value) {
        env.put(key, value);
    }

    public static Object getEnv(String key) {
        return env.get(key);
    }

    public static Map<String, Object> getEnv() {
        return env;
    }

    public static void setEnv(Map<String, Object> env) {
        Envment.env = env;
    }
}

