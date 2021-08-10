package top.tocome.mirai.component.contact;

import net.mamoe.mirai.event.events.BotEvent;
import top.tocome.mirai.component.attached.AttachedComponent;
import top.tocome.mirai.component.attached.ComponentManager;
import top.tocome.mirai.utils.Logger;

import java.util.ArrayList;

public abstract class Contact extends ContactOrBot {

    public Contact(long id) {
        super(id);
        attachedComponents = new ArrayList<>();
        attachedComponents.add(new ComponentManager(this));
    }

    /**
     * 附着的组件
     */
    protected final ArrayList<AttachedComponent> attachedComponents;

    /**
     * 添加一个组件
     */
    public void add(AttachedComponent attachedComponent) {
        attachedComponents.add(attachedComponent);
    }

    /**
     * 移除组件
     */
    public boolean remove(int i) {
        if (i >= attachedComponents.size()) return false;
        attachedComponents.remove(i);
        return true;
    }

    /**
     * @return 已有组件列表
     */
    public String list() {
        StringBuilder stringBuilder = new StringBuilder("已有组件:\n");
        int i = 0;
        for (AttachedComponent c : attachedComponents) {
            stringBuilder.append(i).append(" : ").append(c.getClass().getSimpleName()).append("\n");
            i++;
        }
        return stringBuilder.toString();
    }

    @Override
    protected boolean common() {
        for (AttachedComponent attachedComponent : attachedComponents) {
            if (attachedComponent.invoke(getEvent(), commandMessage)) {
                Logger.info(attachedComponent.getClass().getName() + " run success");
                return true;
            }
        }
        return false;
    }

    protected abstract BotEvent getEvent();
}
