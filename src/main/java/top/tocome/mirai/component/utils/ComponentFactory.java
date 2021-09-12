package top.tocome.mirai.component.utils;

import top.tocome.mirai.component.attached.IAttachedComponent;
import top.tocome.mirai.utils.Logger;

import java.util.ArrayList;

public final class ComponentFactory {
    public static final ComponentFactory Instance = new ComponentFactory();

    private ComponentFactory() {
    }

    protected final ArrayList<Class<? extends IAttachedComponent>> componentsToAll = new ArrayList<>();

    public void load(Class<? extends IAttachedComponent> c) {
        for (Class<? extends IAttachedComponent> cc : componentsToAll) {
            if (cc.getName().equals(c.getName())) {
                Logger.warning("try to load the same attachedComponent:" + c.getName());
                return;
            }
        }
        componentsToAll.add(c);
        Logger.info("load attachedComponent ok:" + c.getName());
    }

    public IAttachedComponent newComponent(int i) {
        try {
            return componentsToAll.get(i).newInstance();
        } catch (Exception e) {
            Logger.error(e.toString());
        }
        return null;
    }

    public String list() {
        StringBuilder stringBuilder = new StringBuilder("所有组件:\n");
        int i = 0;
        for (Class<? extends IAttachedComponent> c : componentsToAll) {
            stringBuilder.append(i).append(" : ").append(c.getSimpleName()).append("\n");
            i++;
        }
        return stringBuilder.toString();
    }
}
