package com.maison.demo.services;

import org.slf4j.LoggerFactory;

public class Log {

    public static void info(Object clazz, String message) {
        LoggerFactory.getLogger(_getTargetClass(clazz)).info(message);
    }

    public static void error(Object clazz, String message) {
        LoggerFactory.getLogger(_getTargetClass(clazz)).error(message);
    }

    public static void warn(Object clazz, String message) {
        LoggerFactory.getLogger(_getTargetClass(clazz)).warn(message);
    }

    public static void debug(Object clazz, String message) {
        LoggerFactory.getLogger(_getTargetClass(clazz)).debug(message);
    }

    public static void trace(Object clazz, String message) {
        LoggerFactory.getLogger(_getTargetClass(clazz)).trace(message);
    }

    private static String _getTargetClass(Object clazz) {
        if (clazz instanceof String) {
            return (String) clazz;
        }
        return clazz.getClass().getName();
    }
}
