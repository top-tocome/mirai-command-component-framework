package top.tocome.mirai.control;

import top.tocome.mirai.component.AttachedComponent;

/**
 * 指令构建器
 */
public class CommandBuilder {

    private final Command command;

    public CommandBuilder(AttachedComponent component, String key) {
        command = new Command(component, key);
    }

    public CommandBuilder params(String[] param) {
        command.paramsHint = param;
        return this;
    }

    public CommandBuilder describe(String describe) {
        command.describe = describe;
        return this;
    }

    public CommandBuilder action(Command.Action action) {
        command.action = action;
        return this;
    }

    public Command build() {
        return command;
    }

}
