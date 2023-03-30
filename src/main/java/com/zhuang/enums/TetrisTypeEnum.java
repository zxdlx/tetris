package com.zhuang.enums;

/**
 * 方块类型枚举类，用于辨别此时是什么类型的方块
 *
 * @module
 * @author zxd
 * @date 2022/10/26  12:05
**/
public enum TetrisTypeEnum {
    /**
     * O形
     */
    O(0),

    /**
     * I形
     */
    I(1),

    /**
     * S形
     */
    S(2),

    /**
     * Z形
     */
    Z(3),

    /**
     * L形
     */
    L(4),

    /**
     * J形
     */
    J(5),

    /**
     * 反T形
     */
    T(6);

    private final Integer code;

    TetrisTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
