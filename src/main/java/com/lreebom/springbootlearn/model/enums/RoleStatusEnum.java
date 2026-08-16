package com.lreebom.springbootlearn.model.enums;

import lombok.Getter;

@Getter
public enum RoleStatusEnum {
    DISABLED(0, "禁用"),
    ENABLED(1, "启用");

    private final Integer code;
    private final String name;

    RoleStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean contains(Integer code) {
        if (code == null) {
            return false;
        }
        for (RoleStatusEnum status : RoleStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static String getNameByCode(Integer code) {
        for (RoleStatusEnum status : RoleStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status.getName();
            }
        }
        return null;
    }
}
