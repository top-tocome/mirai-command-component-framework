package top.tocome.mirai.component.manager;

import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import top.tocome.mirai.component.AbstractComponent;
import top.tocome.mirai.component.contact.manager.BotManager;
import top.tocome.mirai.control.Command;

public final class EventManager extends AbstractComponent {

    public static final EventManager Instance = new EventManager();

    private EventManager() {
    }

    private Event event;

    @Override
    protected boolean setEventType(Event event) {
        this.event = event;
        return true;
    }

    @Override
    protected boolean enable() {
        if (!(event instanceof BotEvent)) return false;
        if (event instanceof MessageEvent) {
            String message = ((MessageEvent) event).getMessage().serializeToMiraiCode().trim();
            if (message.startsWith(Command.startRegex))
                return BotManager.Instance.invoke(event,
                        message.replaceFirst(Command.startRegex, "").trim());
        }
        return BotManager.Instance.invoke(event);
    }

    @Override
    protected boolean disable() {
        return false;
    }
}
