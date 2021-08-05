package top.tocome.mirai.component;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.MessageEvent;
import top.tocome.mirai.control.Command;

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
    public Contact getSubject() {
        return event.getSubject();
    }

    @Override
    protected boolean disable() {
        if (commandMessage == null) return false;
        if (commandMessage.equals(commandSet.key + Command.secondRegex + "start")) {
            setActive(true);
            getSubject().sendMessage(commandSet.key + "启动成功");
            return true;
        } else if (commandMessage.equals(commandSet.key)) {
            getSubject().sendMessage("该组件已停用，请输入\n"
                    + commandSet.key + Command.secondRegex + "start\n" + "重新启用");
        }
        return false;
    }
}
