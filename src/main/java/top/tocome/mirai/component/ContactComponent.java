package top.tocome.mirai.component;

import top.tocome.mirai.component.util.ComponentManager;

import java.util.ArrayList;

/**
 * 联系人组件
 * Bot或User
 */
public abstract class ContactComponent extends CommandComponent {

    public ContactComponent(long id) {
        this.id = id;
        attachedComponents = new ArrayList<>();
        attachedComponents.add(new ComponentManager(this));
    }

    /**
     * 联系人账号id
     */
    private final long id;

    public long getId() {
        return id;
    }

    /**
     * 附着的组件
     */
    protected final ArrayList<AttachedComponent> attachedComponents;

    public ArrayList<AttachedComponent> getComponents() {
        return attachedComponents;
    }

    @Override
    protected boolean common() {
        return commandNext(null);
    }

    /**
     * 联系人管理型组件
     * 记录每个用户对此bot的使用状态
     */
    public abstract static class Manager extends CommandComponent {

        /**
         * 已使用的联系人名单
         */
        protected final ArrayList<ContactComponent> contacts = new ArrayList<>();

        /**
         * 获取联系人
         *
         * @param id 联系人id
         * @return 联系人，不存在则创建新联系人
         */
        protected ContactComponent getContact(long id) {
            for (ContactComponent contact : contacts) {
                if (contact.getId() == id) {
                    return contact;
                }
            }
            return add();
        }

        /**
         * 添加新联系人
         */
        protected abstract ContactComponent add();

        @Override
        protected boolean common() {
            return commandNext(null);
        }
    }
}


