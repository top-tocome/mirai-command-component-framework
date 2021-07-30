package top.tocome.mirai.component.contact.manager;

import net.mamoe.mirai.contact.Contact;
import top.tocome.mirai.component.ContactComponent;
import top.tocome.mirai.component.contact.Friend;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.FriendEvent;

public class FriendManager extends ContactComponent.Manager {

    @Override
    protected void commandInit() {

    }

    private FriendEvent event;

    @Override
    protected boolean setEventType(Event event) {
        if (event instanceof FriendEvent) {
            this.event = (FriendEvent) event;
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
        return event.getFriend();
    }

    @Override
    protected Event getEvent() {
        return event;
    }

    @Override
    public ContactComponent add(long id) {
        Friend friend = new Friend(id);
        contacts.add(friend);
        return friend;
    }
}
