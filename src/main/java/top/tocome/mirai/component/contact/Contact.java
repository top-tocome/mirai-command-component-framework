package top.tocome.mirai.component.contact;

import net.mamoe.mirai.event.events.BotEvent;
import top.tocome.mirai.component.AttachedComponent;
import top.tocome.mirai.component.util.ComponentManager;
import top.tocome.mirai.util.Logger;

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

    public ArrayList<AttachedComponent> getComponents() {
        return attachedComponents;
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
