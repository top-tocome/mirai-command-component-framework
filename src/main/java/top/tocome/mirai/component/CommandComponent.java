package top.tocome.mirai.component;

import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.MessageEvent;
import top.tocome.control.command.Command;
import top.tocome.control.command.CommandSet;
import top.tocome.control.command.CommandSetBuilder;
import top.tocome.mirai.utils.Logger;

/**
 * 包含消息指令的组件
 */
public abstract class CommandComponent extends Component implements ICommandComponent {

    /**
     * 组件的名称
     */
    protected String name = getClass().getSimpleName();

    /**
     * 该组件的指令集
     * 默认已含有 `key-stop` 和 `key-help`
     * 默认key为{@link #name}
     */
    protected CommandSet commandSet;

    /**
     * 指令消息内容
     * mirai码形式
     */
    protected String commandMsg;
    /**
     * 触发指令的消息事件
     */
    private MessageEvent messageEvent;

    protected CommandComponent() {
        commandSet = new CommandSetBuilder(name).onMatchedEvent(params -> {
            messageEvent.getSubject().sendMessage(commandSet.getAllUsage());
            return Command.MatchResult.Success;
        })
                .newCommand("help", params -> {
                    messageEvent.getSubject().sendMessage(commandSet.getAllUsage());
                    return Command.MatchResult.Success;
                })
                .newCommand("stop", params -> {
                    setActive(false);
                    messageEvent.getSubject().sendMessage(name + "停用成功");
                    return Command.MatchResult.Success;
                }).build();
        commandSetting();
    }

    /**
     * 使用
     * {@link CommandSetBuilder#CommandSetBuilder CommandSetBuilder}({@link #commandSet})
     * 进行指令的设置
     * 优先更改key,默认key为{@link #name}
     *
     * @implSpec 添加指令到指令集
     */
    protected abstract void commandSetting();

    @Override
    public boolean invoke(Event event, String commandMsg) {
        this.commandMsg = commandMsg;
        if (commandMsg != null) messageEvent = (MessageEvent) event;
        return super.invoke(event);
    }

    @Override
    public boolean invoke(Event event) {
        return invoke(event, null);
    }

    @Override
    protected boolean enable() {
        if (commandMsg != null && commandSet != null) {
            Command.MatchResult result = commandSet.match(commandMsg);
            if (result != Command.MatchResult.Failed) {
                if (result == Command.MatchResult.Success)
                    Logger.info(commandMsg + result.errorHint);
                else
                    messageEvent.getSubject().sendMessage(result.errorHint);
                return true;
            }
        }
        return common();
    }

    /**
     * 该组件的普通功能
     */
    protected abstract boolean common();

    @Override
    protected boolean disable() {
        if (commandMsg != null) {
            if (commandMsg.equals(commandSet.getKey() + Command.Prefix + "start")) {
                setActive(true);
                messageEvent.getSubject().sendMessage(commandSet.getKey() + "启动成功");
                return true;
            } else if (commandMsg.equals(commandSet.getKey())) {
                messageEvent.getSubject().sendMessage("该组件已停用，请输入\n"
                        + commandSet.getKey() + Command.Prefix + "start\n" + "重新启用");
                return true;
            }
        }
        return false;
    }
}
