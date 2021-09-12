package top.tocome.mirai.component.contact;

import top.tocome.mirai.component.attached.IAttachedComponent;
import top.tocome.mirai.component.attached.ComponentManager;
import top.tocome.mirai.utils.Logger;

import java.util.ArrayList;

public abstract class Contact extends ContactOrBot {

    public Contact(long id) {
        super(id);
        components.add(new ComponentManager(this));
    }

    /**
     * 附着的组件
     */
    protected final ArrayList<IAttachedComponent> components = new ArrayList<>();

    @Override
    protected boolean common() {
        for (IAttachedComponent c : components) {
            if (c.invoke(getEvent(), commandMsg)) {
                Logger.info(c.getClass().getName() + " run success");
                return true;
            }
        }
        return false;
    }

    /**
     * 添加一个组件
     */
    public void add(IAttachedComponent c) {
        components.add(c);
    }

    /**
     * 移除组件
     */
    public boolean remove(int i) {
        if (i >= components.size()) return false;
        components.remove(i);
        return true;
    }

    /**
     * @return 已有组件列表
     */
    public String list() {
        StringBuilder stringBuilder = new StringBuilder("已有组件:\n");
        int i = 0;
        for (IAttachedComponent c : components) {
            stringBuilder.append(i).append(" : ").append(c.getClass().getSimpleName()).append("\n");
            i++;
        }
        return stringBuilder.toString();
    }
}
