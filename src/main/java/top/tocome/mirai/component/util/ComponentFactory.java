package top.tocome.mirai.component.util;

import top.tocome.mirai.component.AttachedComponent;

import java.util.ArrayList;

public final class ComponentFactory {
    public static final ComponentFactory Instance = new ComponentFactory();

    private ComponentFactory() {
    }

    protected final ArrayList<Class<? extends AttachedComponent>> classs = new ArrayList<>();

    public void load(Class<? extends AttachedComponent> c) {
        classs.add(c);
    }

    protected AttachedComponent newComponent(int i) {
        try {
            return classs.get(i).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String list() {
        StringBuilder stringBuilder = new StringBuilder("Components:\n");
        for (Class<? extends AttachedComponent> c : classs) {
            stringBuilder.append(c.getName()).append("\n");
        }
        return stringBuilder.toString();
    }
}
