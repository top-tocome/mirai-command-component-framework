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
                    if (params[0].equals(""))
                        getSubject().sendMessage(commandSet.getDefaultHint());
                    else
                        getSubject().sendMessage(params[0]);
                }).build();

    }

    @Override
    protected boolean common() {
        String message = event.getMessage().serializeToMiraiCode();
        if (message.equals(lastMessage) && !message.equals(lastRepeat)) {
            event.getSubject().sendMessage(event.getMessage());
            lastRepeat = message;
            return true;
        } else if (message.equals(lastRepeat)) {
            event.getSubject().sendMessage("打断复读");
            lastMessage = null;
            lastRepeat = null;
            return true;
        } else {
            lastMessage = message;
            return false;
        }
    }
}
