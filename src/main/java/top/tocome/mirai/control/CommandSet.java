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
            StringBuilder help = new StringBuilder(getHelp());
            for (Command c : commands) {
                help.append(c.getHelp());
            }
            component.getSubject().sendMessage(help.toString());
        };
        addCommand(new CommandBuilder(component, "help", action)
                .describe("获取帮助")
                .build());
        addCommand(new CommandBuilder(component, "stop", params -> component.setActive(false))
                .describe("停用该组件")
                .build());
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
}
