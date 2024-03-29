package com.zhuang.enums;

/**
 * 游戏按钮文字
 *
 * @author zxd
 * @date 2022/10/24  17:20
**/
public enum GameBtnEnum {
    /**
     * 开始游戏
     */
    BEGIN(0,"开始游戏"),

    /**
     * 暂停游戏
     */
    STOP(1,"暂停游戏"),

    /**
     * 继续游戏
     */
    CONTINUE(2,"继续游戏"),

    /**
     * 重新开始
     */
    RESTART(3,"重新开始");

    private final Integer code;
    private final String text;

    GameBtnEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static String getByCode(Integer code){
        for (GameBtnEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value.text;
            }
        }
        return "";
    }
}
