package top.tocome.mirai.component.contact;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.FriendEvent;
import top.tocome.mirai.component.ContactComponent;

public class Friend extends ContactComponent {

    public Friend(long id) {
        super(id);
    }

    @Override
    protected void init() {

    }

    private FriendEvent event;

    @Override
    protected boolean setEventType(Event event) {
        this.event = (FriendEvent) event;
        return true;
    }

    @Override
    protected boolean disable() {
        return false;
    }

    @Override
    public Contact getSubject() {
        return event.getFriend();
    }

    @Override
    protected Event getEvent() {
        return event;
    }
}
