package top.tocome.mirai;

import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.BotPassiveEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import top.tocome.mirai.component.Component;
import top.tocome.mirai.component.contact.manager.BotManager;
import top.tocome.mirai.utils.Logger;

/**
 * mirai事件管理
 */
public final class EventManager implements Component {

    public static final EventManager Instance = new EventManager();

    private EventManager() {
    }

    public static String CommandStartRegex = ">";

    @Override
    public boolean invoke(Event event) {
        if (event instanceof BotPassiveEvent) {
            if (event instanceof MessageEvent) {//检测指令系统
                String message = ((MessageEvent) event).getMessage().serializeToMiraiCode();
                if (message.startsWith(CommandStartRegex)) {
                    return BotManager.Instance.invoke(event,
                            message.replaceFirst(CommandStartRegex, ""));
                }
            }
            return BotManager.Instance.invoke(event);
        } else {
            Logger.warning("no deal event:" + event.getClass().getSimpleName());
        }
        return false;
    }

}
