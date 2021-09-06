package top.tocome.mirai.component.contact.manager;

import net.mamoe.mirai.event.events.BotEvent;
import top.tocome.mirai.component.contact.Bot;
import top.tocome.mirai.utils.Logger;

public final class BotManager extends ContactOrBotManager<Bot> {

    public static final BotManager Instance = new BotManager();

    private BotManager() {
    }

    @Override
    protected void commandSetting() {

    }

    private BotEvent event;

    @Override
    protected boolean common() {
        if (getEvent() instanceof BotEvent) {
            event = (BotEvent) getEvent();
            return getContact(event.getBot().getId()).invoke(event, commandMsg);
        } else return false;
    }

    @Override
    public Bot add() {
        Bot bot = new Bot(event.getBot().getId());
        contacts.add(bot);
        Logger.info("add new Bot:" + bot.getId());
        return bot;
    }
}
