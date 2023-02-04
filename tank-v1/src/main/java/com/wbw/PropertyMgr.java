package com.wbw;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;


public class PropertyMgr {

    private static Properties properties = new Properties();
    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Object get(String key) {
        Optional<Properties> properties = Optional.of(PropertyMgr.properties);
        if (properties.isPresent()) {
                return properties.get().get(key);
        }
        return properties;
    }

    public static void main(String[] args) {

        Object initTankCount = PropertyMgr.get("initTanCount");
        System.out.println(initTankCount);
    }
}
