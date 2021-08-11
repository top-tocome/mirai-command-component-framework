package top.tocome.mirai;

import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import top.tocome.mirai.component.attached.AttachedComponent;
import top.tocome.mirai.component.utils.ComponentFactory;

/**
 * 运行框架
 * 封装初始化及事件监听
 */
public final class FrameworkEntry {
    public static final FrameworkEntry Instance = new FrameworkEntry();

    private FrameworkEntry() {
    }

    public FrameworkEntry loginBot(long qq, String password) {
        BotFactory.INSTANCE.newBot(qq, password).login();
        return this;
    }

    public FrameworkEntry loadComponent(Class<? extends AttachedComponent> c) {
        ComponentFactory.Instance.load(c);
        return this;
    }

    public void run() {
        GlobalEventChannel.INSTANCE.subscribeAlways(BotEvent.class, EventManager.Instance::invoke);
    }
}
