package top.tocome.mirai.control;

import top.tocome.mirai.component.CommandComponent;

/**
 * 指令集构建器
 */
public class CommandSetBuilder {

    private final CommandSet commandSet;

    public CommandSetBuilder(CommandComponent component, String key) {
        commandSet = new CommandSet(component, key);
    }

    public CommandSetBuilder paramsHint(String[] paramsHint) {
        commandSet.paramsHint = paramsHint;
        return this;
    }

    public CommandSetBuilder describe(String describe) {
        commandSet.describe = describe;
        return this;
    }

    public CommandSetBuilder action(Command.Action action) {
        commandSet.action = action;
        return this;
    }

    public CommandSetBuilder newCommand(String key, Command.Action action) {
        return newCommand(key, "", new String[0], action);
    }

    public CommandSetBuilder newCommand(String key, String describe, Command.Action action) {
        return newCommand(key, describe, new String[0], action);
    }

    public CommandSetBuilder newCommand(String key, String describe, String[] paramsHint, Command.Action action) {
        Command command = new CommandBuilder(commandSet.component, key, action)
                .describe(describe)
                .paramsHint(paramsHint)
                .build();
        command.parentSet = commandSet;
        commandSet.commands.add(command);
        return this;
    }

    public CommandSet build() {
        return commandSet;
    }
}
