package top.tocome.mirai.utils;

import net.mamoe.mirai.utils.MiraiLogger;

public class Logger {
    private static final MiraiLogger logger = MiraiLogger.create("tocome");

    public static void info(String info) {
        logger.info(info);
    }

    public static void error(String error) {
        logger.error(error);
    }

    public static void warning(String warning) {
        logger.warning(warning);
    }
}
