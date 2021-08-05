package top.tocome.mirai.component.contact;

import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.GroupEvent;
import top.tocome.mirai.component.AttachedComponent;

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
    protected boolean common() {
        for (AttachedComponent attachedComponent : attachedComponents) {
            if (attachedComponent.invoke(event, commandMessage)) return true;
        }
        return false;
    }

    @Override
    public net.mamoe.mirai.contact.Contact getSubject() {
        return event.getGroup();
    }

    @Override
    protected boolean disable() {
        return false;
    }
}
