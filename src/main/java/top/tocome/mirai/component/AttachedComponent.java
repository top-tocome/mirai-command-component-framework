package top.tocome.mirai.component;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.Event;
import top.tocome.mirai.control.CommandSet;

/**
 * 附加功能型组件
 */
public abstract class AttachedComponent extends AbstractComponent {

    public AttachedComponent() {
        commandInit();
    }

    /**
     * 组件的初始化
     *
     * @implSpec 指令集的初始化
     */
    protected abstract void commandInit();

    /**
     * 指令集
     */
    public CommandSet commandSet;

    /**
     * 指令消息
     */
    protected String commandMessage;

    /**
     * 调用该组件
     *
     * @param commandMessage null为普通功能,非空为指令
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
     * 调用该组件的普通功能
     */
    protected abstract boolean common();

    /**
     * 调用该组件的指令集
     */
    protected boolean command() {
        if (commandSet != null && commandSet.match(commandMessage)) return true;
        return commandNext();
    }

    /**
     * 调用下一个命令集
     */
    protected abstract boolean commandNext();

    /**
     * 获取消息来源
     */
    public abstract Contact getSubject();

    /**
     * 获取消息事件
     */
    protected abstract Event getEvent();
}
