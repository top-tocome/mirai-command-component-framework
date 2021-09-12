package top.tocome.mirai.component.attached;

import top.tocome.control.command.Command;
import top.tocome.control.command.CommandSetBuilder;
import top.tocome.mirai.component.contact.Contact;
import top.tocome.mirai.component.utils.ComponentFactory;
import top.tocome.mirai.component.utils.Message;
import top.tocome.utils.Strings;

public class ComponentManager extends MessageComponent {

    protected Contact contact;

    public ComponentManager(Contact contact) {
        this.contact = contact;
    }

    @Override
    protected void commandSetting() {
        commandSet.setKey("cm");
        new CommandSetBuilder(commandSet).describe("组件管理器")
                .newCommand("list", new String[]{"<level>[all,has]"}, "列出组件",
                        params -> {
                            if (params[0].equals("all"))
                                return new Message(ComponentFactory.Instance.list());
                            else
                                return new Message(contact.list());
                        })
                .newCommand("add", new String[]{"<num>"}, "添加<num>号组件",
                        params -> {
                            int i = Strings.parseInt(params[0], -1);
                            if (i == -1) return Command.MatchResult.ParamError;

                            IAttachedComponent c = ComponentFactory.Instance.newComponent(i);
                            if (c == null)
                                return new Message("添加失败");
                            else {
                                contact.add(c);
                                return new Message("添加成功");
                            }
                        })
                .newCommand("remove", new String[]{"<num>"}, "移除<num>号组件",
                        params -> {
                            int i = Strings.parseInt(params[0], -1);
                            if (i == -1) return Command.MatchResult.ParamError;

                            if (contact.remove(i))
                                return new Message("移除成功");
                            else return new Message("移除失败");
                        })
                .build();
    }

    @Override
    protected boolean dealt() {
        return false;
    }
}
