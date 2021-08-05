package top.tocome.mirai.component.contact;

import top.tocome.mirai.component.AttachedComponent;
import top.tocome.mirai.component.util.ComponentManager;

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
}
