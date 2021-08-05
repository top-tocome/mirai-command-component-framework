package top.tocome.mirai.control;

import top.tocome.mirai.component.CommandComponent;

import java.util.ArrayList;

/**
 * 指令集
 */
public class CommandSet extends Command {

    protected CommandSet(CommandComponent component, String key) {
        super(component, key, null);
        commands = new ArrayList<>();
        action = params -> {
            component.getSubject().sendMessage(getDefaultHint());
        };
        addCommand(new CommandBuilder(component, "help", params -> {
            StringBuilder help = new StringBuilder(getHelp());
            for (Command c : commands) {
                help.append(c.getHelp());
            }
            component.getSubject().sendMessage(help.toString());
        }).describe("获取帮助").build());
        addCommand(new CommandBuilder(component, "stop", params -> {
            component.setActive(false);
            component.getSubject().sendMessage("停用成功");
        }).describe("停用该组件").build());
    }

    protected final ArrayList<Command> commands;

    public void addCommand(Command command) {
        command.parentSet = this;
        commands.add(command);
    }

    @Override
    protected void matchAction(String message) {
        if (message.startsWith(secondRegex)) {
            message = message.replaceFirst(Command.secondRegex, "").trim();
            for (Command c : commands) {
                if (c.match(message))
                    return;
            }
            component.getSubject().sendMessage(Command.startRegex + "error:no such command");
        } else {
            super.matchAction(message);
        }
    }

    /**
     * @return 默认帮助提示
     */
    public String getDefaultHint() {
        return getHelp() + "输入 " + getTotalKey() + Command.secondRegex + "help获取帮助";
    }
}
