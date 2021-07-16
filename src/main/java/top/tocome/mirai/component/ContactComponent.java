package top.tocome.mirai.component;

import java.util.ArrayList;

/**
 * 联系人组件
 */
public abstract class ContactComponent extends AttachedComponent {

    public ContactComponent(long id) {
        this.id = id;
    }

    /**
     * 联系人账号id
     */
    protected long id;

    public long getId() {
        return id;
    }

    /**
     * 附着的组件
     */
    protected ArrayList<AttachedComponent> attachedComponents = new ArrayList<>();

    @Override
    protected boolean common() {
        for (AttachedComponent attachedComponent : attachedComponents) {
            if (attachedComponent.invoke(getEvent())) return true;
        }
        return false;
    }

    @Override
    protected boolean commandNext() {
        for (AttachedComponent attachedComponent : attachedComponents) {
            if (attachedComponent.invoke(getEvent(), commandMessage)) return true;
        }
        return false;
    }

    /**
     * 联系人管理型组件
     * 记录每个用户对此bot的使用状态
     */
    public abstract static class Manager extends AttachedComponent {
        /**
         * 已使用该机器人的名单
         */
        protected ArrayList<ContactComponent> contacts = new ArrayList<>();

        protected ContactComponent getContact(long id) {
            for (ContactComponent contact : contacts) {
                if (contact.getId() == id) {
                    return contact;
                }
            }
            return add(id);
        }

        /**
         * 添加新使用者
         */
        public abstract ContactComponent add(long id);

        @Override
        protected boolean common() {
            return getContact(getSubject().getId()).invoke(getEvent());
        }

        @Override
        protected boolean commandNext() {
            return getContact(getSubject().getId()).invoke(getEvent(), commandMessage);
        }

    }
}


