package top.tocome.mirai.plugin;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import top.tocome.mirai.FrameworkEntry;
import top.tocome.mirai.utils.Logger;

/**
 * 作为插件使用框架
 */
public final class JavaPluginMain extends JavaPlugin {
    public static final JavaPluginMain INSTANCE = new JavaPluginMain(); // 必须 public static, 必须名为 INSTANCE

    private JavaPluginMain() {
        super(new JvmPluginDescriptionBuilder("top.tocome.mirai.plugin", "0.0.3")
                .author("tocome")
                .build()
        );
    }

    @Override
    public void onEnable() {
        Logger.info("top.tocome.mirai.plugin load");
        FrameworkEntry.Instance
//                .loadComponent(c1)
//                .loadComponent(c2)
               .run();
    }
}
