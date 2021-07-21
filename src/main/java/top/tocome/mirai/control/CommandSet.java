package top.tocome.mirai.control;

import top.tocome.mirai.component.AttachedComponent;

import java.util.ArrayList;

/**
 * 指令集
 */
public class CommandSet extends Command {

    public CommandSet(AttachedComponent component, String key, String describe) {
        super(component, key);
        this.describe = describe;
        action = params -> {
            StringBuilder help = new StringBuilder(getHelp());
            for (Command c : commands) {
                help.append(c.getHelp());
            }
            component.getSubject().sendMessage(help.toString());
        };

        add(new CommandBuilder(component, "help")
                .describe("获取帮助")
                .action(action)
                .build())
                .add(new CommandBuilder(component, "stop")
                        .describe("停用该组件")
                        .action(params -> component.setActive(false))
                        .build())
        ;
    }

    protected ArrayList<Command> commands = new ArrayList<>();

    public CommandSet add(Command command) {
        command.parentSet = this;
        commands.add(command);
        return this;
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
