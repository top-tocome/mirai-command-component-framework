package top.tocome.mirai.component.contact.manager;

import net.mamoe.mirai.event.events.GroupEvent;
import top.tocome.mirai.component.contact.Group;
import top.tocome.mirai.utils.Logger;

public class GroupManager extends ContactOrBotManager<Group> {

    @Override
    protected void commandSetting() {

    }

    private GroupEvent event;

    @Override
    protected boolean common() {
        if (getEvent() instanceof GroupEvent) {
            event = (GroupEvent) getEvent();
            return getContact(event.getGroup().getId()).invoke(event, commandMsg);
        } else return false;
    }

    @Override
    public Group add() {
        Group group = new Group(event.getGroup().getId());
        contacts.add(group);
        Logger.info("add new Group:" + group.getId() + "\nName:" + event.getGroup().getName()
                + "\nbelong to Bot:" + event.getBot().getId());
        return group;
    }
}
