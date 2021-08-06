package top.tocome.mirai.plugin;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import top.tocome.mirai.component.attached.KeyWord;
import top.tocome.mirai.component.attached.Repeat;
import top.tocome.mirai.component.contact.manager.BotManager;
import top.tocome.mirai.component.util.ComponentFactory;
import top.tocome.mirai.control.Command;
import top.tocome.mirai.util.Logger;

public final class JavaPluginMain extends JavaPlugin {
    public static final JavaPluginMain INSTANCE = new JavaPluginMain(); // 必须 public static, 必须名为 INSTANCE

    private JavaPluginMain() {
        super(new JvmPluginDescriptionBuilder("top.tocome.mirai.plugin", "0.0.3")
                .author("tocome")
                .build()
        );
        ComponentFactory.Instance.load(Repeat.class);
        ComponentFactory.Instance.load(KeyWord.class);
    }

    @Override
    public void onEnable() {
        Logger.info("top.tocome.mirai.plugin load");
        GlobalEventChannel.INSTANCE.subscribeAlways(BotEvent.class,
                event -> {
                    if (event instanceof MessageEvent) {//检测指令系统
                        String message = ((MessageEvent) event).getMessage().serializeToMiraiCode().trim();
                        if (message.startsWith(Command.startRegex)) {
                            BotManager.Instance.invoke(event,
                                    message.replaceFirst(Command.startRegex, "").trim());
                            return;
                        }
                    }
                    BotManager.Instance.invoke(event);
                });
    }
}
