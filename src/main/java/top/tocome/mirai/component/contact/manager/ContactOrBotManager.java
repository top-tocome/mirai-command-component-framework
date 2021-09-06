package top.tocome.mirai.component.contact.manager;

import top.tocome.mirai.component.CommandComponent;
import top.tocome.mirai.component.contact.ContactOrBot;

import java.util.ArrayList;

/**
 * 联系人管理型组件
 * 记录每个用户对此bot的使用状态
 */
public abstract class ContactOrBotManager<T extends ContactOrBot> extends CommandComponent {

    /**
     * 已使用的联系人名单
     */
    protected final ArrayList<T> contacts = new ArrayList<>();

    /**
     * 获取联系人
     *
     * @param id 联系人id
     * @return 联系人，不存在则创建新联系人
     */
    protected T getContact(long id) {
        for (T contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return add();
    }

    /**
     * 添加新联系人
     */
    protected abstract T add();

}
