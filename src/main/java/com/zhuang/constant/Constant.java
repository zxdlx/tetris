package com.zhuang.constant;

public interface Constant {
    /**
     * 窗口宽度
     */
    int WINDOW_WIDTH = 740;

    /**
     * 窗口高度
     */
    int WINDOW_HEIGHT = 799;

    /**
     * 游戏面板宽度
     */
    int GAME_WIDTH = WINDOW_WIDTH-240;

    /**
     * 游戏面板高度
     */
    int GAME_HEIGHT = WINDOW_HEIGHT-29;

    /**
     * 每次移动做标数
     */
    int MOVE = 40;

    /**
     * 边框宽度
     */
    int BORDER = 10;

    /**
     * 游戏左边线判断X坐标
     */
    int LEFT_LINE = BORDER;

    /**
     * 游戏右边线判断X坐标
     */
    int RIGHT_LINE = GAME_WIDTH - BORDER - MOVE;

    /**
     * 游戏底边线判断Y坐标
     */
    int BOT_LINE = GAME_HEIGHT - BORDER - MOVE;

    /**
     * 横向格数
     */
    int HORIZONTAL = 12;

    /**
     * 纵向格数
     */
    int VERTICAL = 19;

    /**
     * 初始速度
     */
    int SPEED = 1000;

    /**
     * 最高等级
     */
    int LEVEL_MAX = 5;

    /**
     * 多少分增加一格等级
     */
    int ADD_LEVEL = 200;

    /**
     * 图标路径
     */
    String IMG_PATH = "image/tetris.png";



}
