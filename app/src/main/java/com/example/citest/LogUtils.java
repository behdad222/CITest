package com.example.citest;

import android.util.Log;
import androidx.annotation.NonNull;

public class LogUtils {
    private static final String LOG_PREFIX = "CI";

    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;


    private static final int MAX_LOG_SIZE = 1000;
    // Since logging is disabled for release, we can set our logging level to DEBUG.
    private static final int LOG_LEVEL = Log.DEBUG;

    private static boolean isLoggingEnabled() {
        return BuildConfig.DEBUG;
    }

    private static String fixTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }

        return LOG_PREFIX + str;
    }

    /**
     * Don't use this when obfuscating class names.
     */
    private static String makeTag(Class cls) {
        return fixTag(cls.getSimpleName());
    }

    private static String makeTag() {
        return fixTag("");
    }


    public static void logD(
            boolean isProtected, @NonNull String message) {
        fixLog(isProtected, Log.DEBUG, makeTag(), message);
    }

    public static void logD(
            boolean isProtected, @NonNull Class tag, @NonNull String message) {
        fixLog(isProtected, Log.DEBUG, makeTag(tag), message);
    }

    public static void logD(
            boolean isProtected, @NonNull String tag, @NonNull String message) {
        fixLog(isProtected, Log.DEBUG, fixTag(tag), message);
    }

    public static void logD(
            boolean isProtected, @NonNull String message, @NonNull Throwable cause) {
        fixLog(isProtected, Log.DEBUG, makeTag(), message, cause);
    }

    public static void logD(
            boolean isProtected, @NonNull Class tag,
            @NonNull String message, @NonNull Throwable cause) {
        fixLog(isProtected, Log.DEBUG, makeTag(tag), message, cause);
    }

    public static void logD(
            boolean isProtected, @NonNull String tag,
            @NonNull String message, @NonNull Throwable cause) {
        fixLog(isProtected, Log.DEBUG, fixTag(tag), message, cause);
    }


    public static void logV(
            boolean isProtected, @NonNull String message) {
        fixLog(isProtected, Log.VERBOSE, makeTag(), message);
    }

    public static void logV(
            boolean isProtected, @NonNull Class tag, @NonNull String message) {
        fixLog(isProtected, Log.VERBOSE, makeTag(tag), message);
    }

    public static void logV(
            boolean isProtected, @NonNull String tag, @NonNull String message) {
        fixLog(isProtected, Log.VERBOSE, fixTag(tag), message);
    }

    public static void logV(
            boolean isProtected, @NonNull Class tag,
            @NonNull String message, @NonNull Throwable cause) {
        fixLog(isProtected, Log.VERBOSE, makeTag(tag), message, cause);
    }

    public static void logV(
            boolean isProtected, @NonNull String message, @NonNull Throwable cause) {
        fixLog(isProtected, Log.VERBOSE, makeTag(), message, cause);
    }

    public static void logV(
            boolean isProtected, @NonNull String tag,
            @NonNull String message, @NonNull Throwable cause) {
        fixLog(isProtected, Log.VERBOSE, fixTag(tag), message, cause);
    }


    public static void logI(
            boolean isProtected, @NonNull String message) {
        fixLog(isProtected, Log.INFO, makeTag(), message);
    }

    public static void logI(
            boolean isProtected, @NonNull Class tag, @NonNull String message) {
        fixLog(isProtected, Log.INFO, makeTag(tag), message);
    }

    public static void logI(
            boolean isProtected, @NonNull String tag, @NonNull String message) {
        fixLog(isProtected, Log.INFO, fixTag(tag), message);
    }

    public static void logI(
            boolean isProtected, @NonNull String message, @NonNull Throwable cause) {
        fixLog(isProtected, Log.INFO, makeTag(), message, cause);
    }

    public static void logI(
            boolean isProtected, @NonNull Class tag,
            @NonNull String message, @NonNull Throwable cause) {
        fixLog(isProtected, Log.INFO, makeTag(tag), message, cause);
    }

    public static void logI(
            boolean isProtected, @NonNull String tag,
            @NonNull String message, @NonNull Throwable cause) {
        fixLog(isProtected, Log.INFO, fixTag(tag), message, cause);
    }

    public static void logW(
            @NonNull String message) {
        fixLog(false, Log.WARN, makeTag(), message);
    }

    public static void logW(@NonNull Class tag, @NonNull String message) {
        fixLog(false, Log.WARN, makeTag(tag), message);
    }

    public static void logW(
            @NonNull String tag, @NonNull String message) {
        fixLog(false, Log.WARN, fixTag(tag), message);
    }

    public static void logW(
            @NonNull String message, @NonNull Throwable cause) {
        fixLog(false, Log.WARN, makeTag(), message, cause);
    }

    public static void logW(
            @NonNull Class tag, @NonNull String message, @NonNull Throwable cause) {
        fixLog(false, Log.WARN, makeTag(tag), message, cause);
    }

    public static void logW(
            @NonNull String tag, @NonNull String message, @NonNull Throwable cause) {
        fixLog(false, Log.WARN, fixTag(tag), message, cause);
    }


    public static void logE(
            @NonNull String message) {
        fixLog(false, Log.ERROR, makeTag(), message);
    }

    public static void logE(
            @NonNull Class tag, @NonNull String message) {
        fixLog(false, Log.ERROR, makeTag(tag), message);
    }

    public static void logE(
            @NonNull String tag, @NonNull String message) {
        fixLog(false, Log.ERROR, fixTag(tag), message);
    }

    public static void logE(
            @NonNull String message, @NonNull Throwable cause) {
        fixLog(false, Log.ERROR, makeTag(), message, cause);
    }

    public static void logE(
            @NonNull Class tag, @NonNull String message, @NonNull Throwable cause) {
        fixLog(false, Log.ERROR, makeTag(tag), message, cause);
    }

    public static void logE(
            @NonNull String tag, @NonNull String message, @NonNull Throwable cause) {
        fixLog(false, Log.ERROR, fixTag(tag), message, cause);
    }


    private static boolean checkPrint(boolean isProtected, int logLevel) {
        if (logLevel >= Log.WARN) {
            return true;
        }

        if (!isLoggingEnabled()) {
            return false;
        }

        if (isProtected && !BuildConfig.DEBUG) {
            return false;
        }


        if (LOG_LEVEL <= logLevel) {
            return true;
        }

        return false;
    }

    private static void fixLog(boolean isProtected, int logLevel, String tag, String message) {
        fixLog(isProtected, logLevel, tag, message, null);
    }

    private static void fixLog(
            boolean isProtected, int logLevel, String tag, String message, Throwable cause) {
        if (!checkPrint(isProtected, logLevel)) {
            return;
        }

        for (int i = 0; i <= message.length() / MAX_LOG_SIZE; i++) {
            int start = i * MAX_LOG_SIZE;
            int end = (i + 1) * MAX_LOG_SIZE;
            end = Math.min(end, message.length());
            printLog(logLevel, tag, message.substring(start, end), cause);
        }
    }

    private static void printLog(int logLevel, String tag, String message, Throwable cause) {

        switch (logLevel) {
            case Log.VERBOSE:
                Log.v(tag, message, cause);
                break;

            case Log.DEBUG:
                Log.d(tag, message, cause);
                break;

            case Log.INFO:
                Log.i(tag, message, cause);
                break;

            case Log.WARN:
                Log.w(tag, message, cause);
                break;

            case Log.ERROR:
                Log.e(tag, message, cause);
                break;

            default:
                Log.e(tag, message, cause);
                break;
        }
    }
}
