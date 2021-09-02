package top.tocome.mirai.component;

import net.mamoe.mirai.event.Event;

public interface ICommandComponent extends IComponent {
    /**
     * 调用指令组件
     *
     * @param event      mirai事件
     * @param commandMsg 指令消息,null为普通调用,非空为指令匹配
     * @return 调用结果
     */
    boolean invoke(Event event, String commandMsg);
}
