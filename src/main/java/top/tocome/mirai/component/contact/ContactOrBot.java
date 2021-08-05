package top.tocome.mirai.component.contact;

import top.tocome.mirai.component.CommandComponent;

import java.util.ArrayList;

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

    /**
     * 联系人管理型组件
     * 记录每个用户对此bot的使用状态
     */
    public abstract static class Manager extends CommandComponent {

        /**
         * 已使用的联系人名单
         */
        protected final ArrayList<ContactOrBot> contacts = new ArrayList<>();

        /**
         * 获取联系人
         *
         * @param id 联系人id
         * @return 联系人，不存在则创建新联系人
         */
        protected ContactOrBot getContact(long id) {
            for (ContactOrBot contact : contacts) {
                if (contact.getId() == id) {
                    return contact;
                }
            }
            return add();
        }

        /**
         * 添加新联系人
         */
        protected abstract ContactOrBot add();

    }
}


