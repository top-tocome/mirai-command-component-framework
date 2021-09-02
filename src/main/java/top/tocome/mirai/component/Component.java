package top.tocome.mirai.component;

import net.mamoe.mirai.event.Event;

/**
 * 基础组件
 * 可开关组件
 */
public abstract class Component implements IComponent {

    /**
     * 组件的开关
     * 默认为打开状态
     */
    private boolean active = true;

    public final void setActive(boolean active) {
        this.active = active;
    }

    /**
     * mirai事件
     */
    private Event event;

    protected Event getEvent() {
        return event;
    }

    @Override
    public boolean invoke(Event event) {
        this.event = event;
        return active ? enable() : disable();
    }

    /**
     * 该组件打开时运行
     */
    protected abstract boolean enable();

    /**
     * 该组件关闭后运行
     */
    protected abstract boolean disable();
}
