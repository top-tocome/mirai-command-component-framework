package top.tocome.mirai.component.contact;

import top.tocome.mirai.component.contact.manager.AttachedToBot;
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

    private final ArrayList<AttachedToBot> components = new ArrayList<>();

    @Override
    protected boolean common() {
        for (AttachedToBot c : components) {
            if (c.invoke(getEvent(), commandMsg)) {
                return true;
            }
        }
        Logger.warning("no matched event:" + getEvent().getClass().getSimpleName());
        return false;
    }
}
