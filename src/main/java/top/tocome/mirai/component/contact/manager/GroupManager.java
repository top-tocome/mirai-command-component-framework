package top.tocome.mirai.component.contact.manager;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.GroupEvent;
import top.tocome.mirai.component.contact.ContactOrBot;
import top.tocome.mirai.component.contact.Group;

public class GroupManager extends ContactOrBot.Manager {

    @Override
    protected void commandInit() {

    }

    private GroupEvent event;

    @Override
    protected boolean setEventType(Event event) {
        if (event instanceof GroupEvent) {
            this.event = (GroupEvent) event;
            return true;
        }
        return false;
    }


    @Override
    protected boolean common() {
        return getContact(getSubject().getId()).invoke(event, commandMessage);
    }

    @Override
    protected boolean disable() {
        return false;
    }

    @Override
    public Contact getSubject() {
        return event.getGroup();
    }

    @Override
    public ContactOrBot add() {
        Group group = new Group(getSubject().getId());
        contacts.add(group);
        return group;
    }
}
