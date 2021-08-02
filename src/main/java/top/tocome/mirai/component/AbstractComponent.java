package top.tocome.mirai.component;

import net.mamoe.mirai.event.Event;

/**
 * 抽象基础组件
 * 可开关组件
 */
public abstract class AbstractComponent implements Component {

    /**
     * 组件的开关
     * 默认为打开状态
     */
    private boolean active = true;

    public final void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean invoke(Event event) {
        if (!active) return disable();
        else if (setEventType(event))
            return enable();
        return false;
    }

    /**
     * 设置该组件的事件类型
     */
    protected abstract boolean setEventType(Event event);

    /**
     * 该组件打开时运行
     */
    protected abstract boolean enable();

    /**
     * 该组件关闭后运行
     */
    protected abstract boolean disable();
}
