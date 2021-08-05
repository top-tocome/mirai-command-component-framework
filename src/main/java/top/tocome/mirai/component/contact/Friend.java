package top.tocome.mirai.component.contact;


import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.FriendEvent;

public class Friend extends Contact {

    public Friend(long id) {
        super(id);
    }

    @Override
    protected void commandInit() {

    }

    private FriendEvent event;

    @Override
    protected boolean setEventType(Event event) {
        this.event = (FriendEvent) event;
        return true;
    }

    @Override
    public net.mamoe.mirai.contact.Contact getSubject() {
        return event.getFriend();
    }

    @Override
    protected boolean disable() {
        return false;
    }

    @Override
    protected BotEvent getEvent() {
        return event;
    }
}
