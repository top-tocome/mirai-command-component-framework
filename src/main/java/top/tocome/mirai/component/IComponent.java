package top.tocome.mirai.component;

import net.mamoe.mirai.event.Event;

/**
 * 基础组件
 */
public interface IComponent {
    /**
     * 调用该组件
     *
     * @param event mirai事件
     * @return 调用结果：成功or失败
     */
    boolean invoke(Event event);
}
