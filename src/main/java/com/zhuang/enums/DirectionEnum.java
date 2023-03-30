package com.zhuang.enums;

/**
 * 方向枚举
 *
 * @module
 * @author zxd
 * @date 2022/10/27  16:44
**/
public enum DirectionEnum {
    /**
     * 左
     */
    LEFT(0),

    /**
     * 上
     */
    UP(1),

    /**
     * 右
     */
    RIGHT(2),

    /**
     * 下
     */
    DOWN(3);

    private final Integer code;

    DirectionEnum(Integer code){
        this.code=code;
    }

    public Integer getCode(){
        return this.code;
    }
}
