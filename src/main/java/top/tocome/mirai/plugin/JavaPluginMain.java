package top.tocome.mirai.plugin;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.GlobalEventChannel;
import top.tocome.mirai.component.manager.EventManager;

public final class JavaPluginMain extends JavaPlugin {
    public static final JavaPluginMain INSTANCE = new JavaPluginMain(); // 必须 public static, 必须名为 INSTANCE

    private JavaPluginMain() {
        super(new JvmPluginDescriptionBuilder("top.tocome.mirai.plugin", "0.0.0")
                .author("tocome")
                .build()
        );
    }

    @Override
    public void onEnable() {
        GlobalEventChannel.INSTANCE.subscribeAlways(Event.class, EventManager.Instance::invoke);
    }
}
