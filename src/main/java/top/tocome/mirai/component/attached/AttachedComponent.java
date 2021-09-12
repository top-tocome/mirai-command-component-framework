package top.tocome.mirai.component.attached;


import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import top.tocome.mirai.component.CommandComponent;

import java.util.function.Consumer;

/**
 * 附加功能型组件
 */
public abstract class AttachedComponent<T extends BotEvent> extends CommandComponent implements IAttachedComponent {
    /**
     * 当前处理的事件
     */
    protected T event;

    /**
     * 设置该组件可接收和处理的事件类型
     *
     * @implNote 该函数实现方式固定如下：将 T 替换成你所设置的事件类型<br>
     * <code>if (getEvent() instanceof T){<br>
     * event = (T) getEvent();<br>
     * return true;<br>
     * }<br>
     * return false;<br></code>
     * @see MessageComponent#setEventType()
     */
    protected abstract boolean setEventType();

    @Override
    protected boolean common() {
        if (setEventType()) return dealt();
        return false;
    }

    /**
     * 普通事件处理,此刻同等于<br>
     * {@link GlobalEventChannel#subscribeAlways(Class, Consumer)
     * GlobalEventChannel.INSTANCE.subscribeAlways(T.class, event -> commonly());}
     *
     * @return false-将事件传递给下一个组件处理
     * true-结束该事件的处理
     */
    protected abstract boolean dealt();
}
