package top.tocome.mirai.component;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.Event;
import top.tocome.mirai.control.CommandSet;

/**
 * 包含消息指令的组件
 */
public abstract class CommandComponent extends AbstractComponent {

    public CommandComponent() {
        commandInit();
    }

    /**
     * 该组件的指令集
     */
    protected CommandSet commandSet;

    /**
     * @implSpec 指令集的初始化
     */
    protected abstract void commandInit();


    /**
     * 指令消息内容
     */
    private String commandMessage;

    /**
     * 调用该组件
     *
     * @param event          mirai事件
     * @param commandMessage null为普通调用,非空为指令匹配
     * @return 调用结果
     */
    public boolean invoke(Event event, String commandMessage) {
        this.commandMessage = commandMessage;
        return super.invoke(event);
    }

    @Override
    public boolean invoke(Event event) {
        return invoke(event, null);
    }

    @Override
    protected boolean enable() {
        return commandMessage != null ? command() : common();
    }

    /**
     * 该组件的普通功能
     */
    protected abstract boolean common();

    /**
     * 匹配该组件的指令集
     */
    private boolean command() {
        if (commandSet != null && commandSet.match(commandMessage)) return true;
        return commandNext(commandMessage);
    }

    /**
     * 指令集匹配失败后调用
     */
    protected abstract boolean commandNext(String commandMessage);

    /**
     * 获取消息来源
     *
     * @apiNote 主要供指令action发送bot消息
     */
    public abstract Contact getSubject();
}
