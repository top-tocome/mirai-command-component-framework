package top.tocome.mirai.component.attached;

import top.tocome.mirai.component.contact.Contact;
import top.tocome.mirai.component.utils.ComponentFactory;
import top.tocome.mirai.control.CommandSetBuilder;

public class ComponentManager extends AttachedComponent {

    Contact component;

    public ComponentManager(Contact component) {
        this.component = component;
    }

    @Override
    protected void commandInit() {
        commandSet = new CommandSetBuilder(this, "cm").describe("组件管理器")
                .newCommand("list", "列出组件", new String[]{"<level>[all,has]"},
                        params -> {
                            if (params[0].equals("all"))
                                getSubject().sendMessage(ComponentFactory.Instance.list());
                            else
                                getSubject().sendMessage(component.list());
                        })
                .newCommand("add", "添加<num>号组件", new String[]{"<num>"},
                        params -> {
                            int i;
                            try {
                                i = Integer.parseInt(params[0]);
                            } catch (Exception e) {
                                getSubject().sendMessage("参数错误");
                                return;
                            }
                            AttachedComponent attachedComponent = ComponentFactory.Instance.newComponent(i);
                            if (attachedComponent == null)
                                getSubject().sendMessage("添加失败");
                            else {
                                component.add(attachedComponent);
                                getSubject().sendMessage("添加成功");
                            }
                        })
                .newCommand("remove", "移除<num>号组件", new String[]{"<num>"},
                        params -> {
                            int i;
                            try {
                                i = Integer.parseInt(params[0]);
                            } catch (Exception e) {
                                getSubject().sendMessage("参数错误");
                                return;
                            }
                            if (component.remove(i))
                                getSubject().sendMessage("移除成功");
                            else getSubject().sendMessage("移除失败");
                        })
                .build();
    }

    @Override
    protected boolean common() {
        return false;
    }
}
