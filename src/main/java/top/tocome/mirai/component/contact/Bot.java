package top.tocome.mirai.component.contact;

import top.tocome.mirai.component.attached.IAttachedComponent;
import top.tocome.mirai.component.contact.manager.FriendManager;
import top.tocome.mirai.component.contact.manager.GroupManager;
import top.tocome.mirai.utils.Logger;

import java.util.ArrayList;

public class Bot extends ContactOrBot {

    public Bot(long id) {
        super(id);
        components.add(new GroupManager());
        components.add(new FriendManager());
    }

    @Override
    protected void commandSetting() {

    }

    private final ArrayList<IAttachedComponent> components = new ArrayList<>();

    @Override
    protected boolean common() {
        for (IAttachedComponent c : components) {
            if (c.invoke(getEvent(), commandMsg)) {
                return true;
            }
        }
        Logger.warning("no matched event:" + getEvent().getClass().getSimpleName());
        return false;
    }
}
