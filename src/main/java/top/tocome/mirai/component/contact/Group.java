package top.tocome.mirai.component.contact;

import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.GroupEvent;

public class Group extends Contact {

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
    public net.mamoe.mirai.contact.Contact getSubject() {
        return event.getGroup();
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
