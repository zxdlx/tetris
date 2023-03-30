package com.zhuang.tetris;

/**
 * 方块节点类
 *
 * @module
 * @author zxd
 * @date 2022/10/25  10:07
**/
public class TetrisNode {
    /**
     * x轴
     */
    private Integer x;

    /**
     * y轴
     */
    private Integer y;

    /**
     * 宽度
     */
    private Integer width;

    /**
     * 高度
     */
    private Integer height;

    /**
     * 方块颜色
     */
    private Integer color;

    public TetrisNode(Integer x, Integer y, Integer width, Integer height, Integer color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
}
