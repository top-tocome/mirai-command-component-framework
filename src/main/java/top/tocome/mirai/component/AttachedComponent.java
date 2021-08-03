package top.tocome.mirai.component;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.MessageEvent;

/**
 * 附加功能型组件
 */
public abstract class AttachedComponent extends CommandComponent {

    protected MessageEvent event;

    @Override
    protected boolean setEventType(Event event) {
        if (event instanceof MessageEvent) {
            this.event = (MessageEvent) event;
            return true;
        }
        return false;
    }

    @Override
    protected boolean commandNext(String commandMessage) {
        return false;
    }

    @Override
    protected boolean disable() {
        return false;
    }

    @Override
    protected Contact getSubject() {
        return event.getSubject();
    }
}
