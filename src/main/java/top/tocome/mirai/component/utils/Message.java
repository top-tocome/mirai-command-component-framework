package top.tocome.mirai.component.utils;

import top.tocome.utils.Error;

/**
 * Error别名
 * 供指令系统反馈消息
 */
public class Message extends Error {
    public Message(String message) {
        super(message);
    }
}
