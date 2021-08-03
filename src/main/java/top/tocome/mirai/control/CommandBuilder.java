package top.tocome.mirai.control;

import top.tocome.mirai.component.CommandComponent;

/**
 * 指令构建器
 */
public class CommandBuilder {

    private final Command command;

    public CommandBuilder(CommandComponent component, String key, Command.Action action) {
        command = new Command(component, key, action);
    }

    public CommandBuilder paramsHint(String[] paramsHint) {
        command.paramsHint = paramsHint;
        return this;
    }

    public CommandBuilder describe(String describe) {
        command.describe = describe;
        return this;
    }

    public Command build() {
        return command;
    }
}