package top.tocome.mirai.component.contact.manager;

import net.mamoe.mirai.event.events.FriendEvent;
import net.mamoe.mirai.event.events.GroupEvent;
import top.tocome.mirai.component.contact.ContactOrBot;
import top.tocome.mirai.component.contact.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.BotEvent;
import top.tocome.mirai.util.Logger;

public final class BotManager extends ContactOrBot.Manager {

    public static final BotManager Instance = new BotManager();

    private BotManager() {
    }

    @Override
    protected void commandInit() {

    }

    private BotEvent event;

    @Override
    protected boolean setEventType(Event event) {
        this.event = (BotEvent) event;
        return true;
    }

    @Override
    protected boolean common() {
        return getContact(event.getBot().getId()).invoke(event, commandMessage);
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
    public ContactOrBot add() {
        Bot bot = new Bot(event.getBot().getId());
        contacts.add(bot);
        Logger.info("add new Bot:" + bot.getId());
        return bot;
    }
}
