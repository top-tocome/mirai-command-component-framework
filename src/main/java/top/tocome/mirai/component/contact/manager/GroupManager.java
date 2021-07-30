package top.tocome.mirai.component.contact.manager;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.GroupEvent;
import top.tocome.mirai.component.ContactComponent;
import top.tocome.mirai.component.contact.Group;

public class GroupManager extends ContactComponent.Manager {

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
    protected boolean disable() {
        return false;
    }

    @Override
    public Contact getSubject() {
        return event.getGroup();
    }

    @Override
    protected Event getEvent() {
        return event;
    }

    @Override
    public ContactComponent add(long id) {
        Group group = new Group(id);
        contacts.add(group);
        return group;
    }
}
