package top.tocome.mirai.component.contact;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.FriendEvent;
import net.mamoe.mirai.event.events.GroupEvent;
import net.mamoe.mirai.event.events.MessagePreSendEvent;
import top.tocome.mirai.component.contact.manager.FriendManager;
import top.tocome.mirai.component.contact.manager.GroupManager;
import top.tocome.mirai.util.Logger;

public class Bot extends ContactOrBot {

    public Bot(long id) {
        super(id);
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

    private final GroupManager groupManager = new GroupManager();
    private final FriendManager friendManager = new FriendManager();

    @Override
    protected boolean common() {
        if (event instanceof GroupEvent)
            return groupManager.invoke(event, commandMessage);
        else if (event instanceof FriendEvent)
            return friendManager.invoke(event, commandMessage);
        else if (event instanceof MessagePreSendEvent) {
            Logger.info("机器人正在发送消息...");
        } else {
            Logger.error("no matched Event:" + event.getClass().getSimpleName());
        }
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
    protected boolean disable() {
        return false;
    }
}
