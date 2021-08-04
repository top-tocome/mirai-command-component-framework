package top.tocome.mirai.component.attached;

import net.mamoe.mirai.message.code.MiraiCode;
import top.tocome.mirai.component.AttachedComponent;
import top.tocome.mirai.control.Command;
import top.tocome.mirai.control.CommandSetBuilder;

import java.util.ArrayList;

public final class KeyWord extends AttachedComponent {

    public final static class KeyValue {
        String key;
        String value;

        public KeyValue(String key, String value) {
            this.key = key;
            this.value = value;
        }

        boolean equals(String key) {
            return this.key.equals(key);
        }

        @Override
        public String toString() {
            return key + Command.paramRegex + value + "\n";
        }
    }

    ArrayList<KeyValue> keyValues = new ArrayList<>();

    @Override
    protected void commandInit() {
        commandSet = new CommandSetBuilder(this, "keyword").describe("匹配整句并回复")
                .newCommand("add", "添加一对<key>::<return>", new String[]{"<key>", "<return>"},
                        params -> {
                            keyValues.add(new KeyValue(params[0].trim(), params[1].trim()));
                            getSubject().sendMessage("添加成功");
                        })
                .newCommand("remove", "删除一对<key>::<return>", new String[]{"<key>"},
                        params -> {
                            for (KeyValue keyValue : keyValues)
                                if (keyValue.equals(params[0].trim())) {
                                    keyValues.remove(keyValue);
                                    getSubject().sendMessage("移除成功");
                                    return;
                                }
                            event.getSubject().sendMessage(Command.startRegex + "error:no such key");
                        })
                .newCommand("list", "列出所有<key>::<return>",
                        params -> {
                            StringBuilder sb = new StringBuilder("list:\n");
                            for (KeyValue keyValue : keyValues) {
                                sb.append(keyValue.toString());
                            }
                            getSubject().sendMessage(sb.toString());
                        })
                .build();
    }

    @Override
    protected boolean common() {
        String message = event.getMessage().serializeToMiraiCode();
        for (KeyValue keyValue : keyValues) {
            if (keyValue.equals(message)) {
                event.getSubject().sendMessage(MiraiCode.deserializeMiraiCode(keyValue.value));
                return true;
            }
        }
        return false;
    }
}
