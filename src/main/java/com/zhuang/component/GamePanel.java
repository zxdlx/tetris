package com.zhuang.component;

import com.zhuang.constant.Constant;
import com.zhuang.init.TetrisInit;
import com.zhuang.tetris.TetrisNode;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 游戏面板类
 *
 * @author zxd
 * @date 2022/10/25  9:59
 **/
public class GamePanel extends JPanel {
    //游戏初始化对象
    private final TetrisInit tetrisInit;
    //是否失败
    private boolean isFail;

    public GamePanel(TetrisInit tetrisInit) {
        setBounds(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        this.tetrisInit = tetrisInit;
    }

    @Override
    public void paint(Graphics g) {
        //初始化背景
        super.paint(g);
        //绘制背景色
        g.setColor(Color.black);
        g.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        //绘制边框
        g.setColor(new Color(0xA484B0B3, true));
        g.fillRect(0, 0, 10, Constant.GAME_HEIGHT);    //左边线
        g.fillRect(Constant.GAME_WIDTH - 10, 0, 10, Constant.GAME_HEIGHT);  //右边线
        g.fillRect(10, Constant.GAME_HEIGHT - 10, Constant.GAME_WIDTH - 20, 10);   //底线
        //绘制方块
        drawBlocks(tetrisInit.getTetrisNodes(), g);
        //绘制底部沉积方块
        drawBlocks(tetrisInit.getDeposition(), g);
        //失败总结
        if (isFail) {
            g.setColor(new Color(0xA429B116, true));
            g.setFont(new Font("宋体", Font.PLAIN, 40));
            g.drawString("游戏结束", 170, 120);
            g.drawString("您的分数为：" + tetrisInit.getWindowBot().getScore(), 130, 180);
        }
    }

    /**
     * 绘制方块
     *
     * @param tetrisNodes 方块节点
     * @param g           画笔
     * @author zxd
     * @date 2024/3/30 4:36
     */
    static void drawBlocks(List<TetrisNode> tetrisNodes, Graphics g) {
        for (TetrisNode tetrisNode : tetrisNodes) {
            g.setColor(new Color(tetrisNode.getColor(), true));
            g.fillRect(tetrisNode.getX(), tetrisNode.getY(), 5, 39);      //左边线
            g.fillRect(tetrisNode.getX() + 5, tetrisNode.getY(), 29, 5);     //顶线
            g.fillRect(tetrisNode.getX() + 34, tetrisNode.getY(), 5, 34);     //右边线
            g.fillRect(tetrisNode.getX() + 5, tetrisNode.getY() + 34, 34, 5); //底线
            g.fillRect(tetrisNode.getX() + 10, tetrisNode.getY() + 10, 19, 19);
        }
    }

    public void setFail(boolean fail) {
        isFail = fail;
    }
}
