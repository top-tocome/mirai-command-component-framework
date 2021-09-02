package top.tocome.mirai.component;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.Event;
import top.tocome.control.command.Command;
import top.tocome.control.command.CommandSet;
import top.tocome.mirai.utils.Logger;

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
     * mirai码形式
     */
    protected String commandMessage;

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
        if (commandMessage != null && commandSet != null) {
            Command.MatchResult result = commandSet.match(commandMessage);
            if (result != Command.MatchResult.Failed) {
                if (result == Command.MatchResult.Success)
                    Logger.info(commandMessage + result.errorHint);
                else
                    getSubject().sendMessage(result.errorHint);
                return true;
            }
        }
        return common();
    }

    /**
     * 该组件的普通功能
     */
    protected abstract boolean common();

    /**
     * 获取消息来源
     *
     * @apiNote 主要供指令action发送bot消息
     */
    public abstract Contact getSubject();
}
