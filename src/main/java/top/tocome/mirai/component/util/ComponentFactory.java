package top.tocome.mirai.component.util;

import top.tocome.mirai.component.AttachedComponent;
import top.tocome.mirai.util.Logger;

import java.util.ArrayList;

public final class ComponentFactory {
    public static final ComponentFactory Instance = new ComponentFactory();

    private ComponentFactory() {
    }

    protected final ArrayList<Class<? extends AttachedComponent>> classs = new ArrayList<>();

    public void load(Class<? extends AttachedComponent> c) {
        for (Class<? extends AttachedComponent> cc : classs) {
            if (cc.getName().equals(c.getName())) {
                Logger.warning("try to load the same attachedComponent:" + c.getName());
                return;
            }
        }
        classs.add(c);
    }

    protected AttachedComponent newComponent(int i) {
        try {
            return classs.get(i).newInstance();
        } catch (Exception e) {
            Logger.error(e.toString());
        }
        return null;
    }

    protected String list() {
        StringBuilder stringBuilder = new StringBuilder("所有组件:\n");
        int i = 0;
        for (Class<? extends AttachedComponent> c : classs) {
            stringBuilder.append(i).append(" : ").append(c.getSimpleName()).append("\n");
            i++;
        }
        return stringBuilder.toString();
    }
}
