package top.tocome.mirai.component.attached;

import top.tocome.mirai.component.AttachedComponent;
import top.tocome.mirai.control.CommandSetBuilder;

public class Repeat extends AttachedComponent {

    String lastMessage = null;
    String lastRepeat = null;

    @Override
    protected void commandInit() {
        commandSet = new CommandSetBuilder(this, "repeat").describe("复读")
                .paramsHint(new String[]{"<content>"})
                .action(params -> {
                    getSubject().sendMessage(params[0]);
                }).build();

    }

    @Override
    protected boolean common() {
        String message = event.getMessage().serializeToMiraiCode();
        if (message.equals(lastMessage) && !message.equals(lastRepeat)) {
            event.getSubject().sendMessage(event.getMessage());
            lastRepeat = message;
        } else {
            lastMessage = message;
        }
        return false;
    }


    @Override
    protected boolean disable() {
        return false;
    }
}
