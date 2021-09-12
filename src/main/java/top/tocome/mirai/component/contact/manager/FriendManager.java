package top.tocome.mirai.component.contact.manager;

import net.mamoe.mirai.event.events.FriendEvent;
import top.tocome.mirai.component.attached.IAttachedComponent;
import top.tocome.mirai.component.contact.Friend;
import top.tocome.mirai.utils.Logger;

public class FriendManager extends ContactOrBotManager<Friend> implements IAttachedComponent {

    @Override
    protected void commandSetting() {

    }

    private FriendEvent event;

    @Override
    protected boolean common() {
        if (getEvent() instanceof FriendEvent) {
            event = (FriendEvent) getEvent();
            return getContact(event.getFriend().getId()).invoke(event, commandMsg);
        } else return false;
    }

    @Override
    public Friend add() {
        Friend friend = new Friend(event.getFriend().getId());
        contacts.add(friend);
        Logger.info("add new Friend:" + friend.getId() + "\nNick:" + event.getFriend().getNick()
                + "\nbelong to Bot:" + event.getBot().getId());
        return friend;
    }
}
