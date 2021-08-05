package top.tocome.mirai.component.contact.manager;

import net.mamoe.mirai.contact.Contact;
import top.tocome.mirai.component.contact.ContactOrBot;
import top.tocome.mirai.component.contact.Friend;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.FriendEvent;
import top.tocome.mirai.util.Logger;

public class FriendManager extends ContactOrBot.Manager {

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
    protected boolean common() {
        return getContact(getSubject().getId()).invoke(event, commandMessage);
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
    public ContactOrBot add() {
        Friend friend = new Friend(getSubject().getId());
        contacts.add(friend);
        Logger.info("add new Friend:" + friend.getId() + "\nNick:" + event.getFriend().getNick()
                + "\nbelong to Bot:" + getSubject().getBot().getId());
        return friend;
    }
}
