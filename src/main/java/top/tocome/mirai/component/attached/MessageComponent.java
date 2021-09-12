package top.tocome.mirai.component.attached;

import net.mamoe.mirai.event.events.MessageEvent;

/**
 * 最常用的组件类型
 */
public abstract class MessageComponent extends AttachedComponent<MessageEvent> {
    /**
     * 接受到的消息的mirai码
     */
    protected String message;

    @Override
    protected boolean setEventType() {
        if (getEvent() instanceof MessageEvent) {
            event = (MessageEvent) getEvent();
            message = event.getMessage().serializeToMiraiCode();
            return true;
        }
        return false;
    }
}
