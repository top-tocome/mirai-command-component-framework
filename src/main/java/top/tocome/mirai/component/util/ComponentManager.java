package top.tocome.mirai.component.util;

import top.tocome.mirai.component.AttachedComponent;
import top.tocome.mirai.component.ContactComponent;
import top.tocome.mirai.control.CommandSetBuilder;

public class ComponentManager extends AttachedComponent {

    ContactComponent component;

    public ComponentManager(ContactComponent component) {
        this.component = component;
    }

    @Override
    protected void commandInit() {
        commandSet = new CommandSetBuilder(this, "cm").describe("组件管理器")
                .newCommand("list", "列出已有组件", new String[]{"<level>[all,none]"},
                        params -> {
                            if (params[0].equals("all"))
                                getSubject().sendMessage(ComponentFactory.Instance.list());
                            else
                                getSubject().sendMessage(component.getComponents().toString());
                        })
                .newCommand("add", "添加<num>号组件", new String[]{"<num>"},
                        params -> {
                            component.getComponents().add(ComponentFactory.Instance.newComponent(Integer.parseInt(params[0])));
                            getSubject().sendMessage("添加成功");
                        })
                .newCommand("remove", "移除<num>号组件", new String[]{"<num>"},
                        params -> {
                            component.getComponents().remove(Integer.parseInt(params[0]));
                            getSubject().sendMessage("移除成功");
                        })
                .build();
    }

    @Override
    protected boolean common() {
        return false;
    }

    @Override
    protected boolean disable() {
        return false;
    }
}
