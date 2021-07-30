package top.tocome.mirai.component.contact;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.GroupEvent;
import top.tocome.mirai.component.ContactComponent;

import java.util.ArrayList;

public class Group extends ContactComponent {

    public Group(long id) {
        super(id);
    }

    @Override
    protected void commandInit() {

    }

    private GroupEvent event;

    @Override
    protected boolean setEventType(Event event) {
        this.event = (GroupEvent) event;
        return true;
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

}
