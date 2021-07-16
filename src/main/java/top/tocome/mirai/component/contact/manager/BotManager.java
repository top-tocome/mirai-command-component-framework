package top.tocome.mirai.component.contact.manager;

import net.mamoe.mirai.event.events.FriendEvent;
import net.mamoe.mirai.event.events.GroupEvent;
import top.tocome.mirai.component.ContactComponent;
import top.tocome.mirai.component.contact.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.BotEvent;

public final class BotManager extends ContactComponent.Manager {

    public static final BotManager Instance = new BotManager();

    private BotManager() {
    }

    @Override
    protected void init() {

    }

    private BotEvent event;

    @Override
    protected boolean setEventType(Event event) {
        this.event = (BotEvent) event;
        return true;
    }

    @Override
    protected boolean common() {
        return getContact(event.getBot().getId()).invoke(getEvent());
    }

    @Override
    protected boolean commandNext() {
        return getContact(event.getBot().getId()).invoke(getEvent(), commandMessage);
    }

    @Override
    protected boolean disable() {
        return false;
    }

    @Override
    public Contact getSubject() {
        if (event instanceof GroupEvent)
            return ((GroupEvent) event).getGroup();
        else if (event instanceof FriendEvent)
            return ((FriendEvent) event).getFriend();
        else return null;
    }

    @Override
    protected Event getEvent() {
        return event;
    }

    @Override
    public ContactComponent add(long id) {
        Bot bot = new Bot(id);
        contacts.add(bot);
        return bot;
    }
}
