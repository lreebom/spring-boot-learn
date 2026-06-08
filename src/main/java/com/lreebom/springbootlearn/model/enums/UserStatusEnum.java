package com.lreebom.springbootlearn.model.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnum {
    DISABLED(0, "禁用"), ENABLED(1, "启用");

    private final Integer code;
    private final String name;

    UserStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean contains(Integer code) {
        if (code == null) {
            return false;
        }
        for (UserStatusEnum status : UserStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static String getNameByCode(Integer code) {
        for (UserStatusEnum status : UserStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status.getName();
            }
        }
        return null;
    }

}
