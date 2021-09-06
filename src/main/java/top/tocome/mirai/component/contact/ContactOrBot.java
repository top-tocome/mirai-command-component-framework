package top.tocome.mirai.component.contact;

import top.tocome.mirai.component.CommandComponent;

/**
 * 联系人组件
 * Bot或User
 */
public abstract class ContactOrBot extends CommandComponent {

    public ContactOrBot(long id) {
        this.id = id;
    }

    /**
     * 联系人账号id
     */
    private final long id;

    public long getId() {
        return id;
    }

}


