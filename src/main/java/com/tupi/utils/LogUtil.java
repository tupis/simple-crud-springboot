package com.tupi.utils;

import java.lang.reflect.Field;

public class LogUtil<T> {
    public void logTudo(T obj) {
        Class<?> classLog = obj.getClass();
        Field[] fields = classLog.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                System.out.println(field.getName() + ": " + value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
