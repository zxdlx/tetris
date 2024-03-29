package com.zhuang.listener;

import com.zhuang.constant.Constant;
import com.zhuang.init.TetrisInit;
import com.zhuang.tetris.TetrisNode;
import com.zhuang.utils.TetrisRevolve;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 键盘监听
 *
 * @module
 * @author zxd
 * @date 2022/10/26  14:18
**/
public class TetrisKeyListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        TetrisInit tetrisInit = (TetrisInit) e.getSource();
        //如果游戏已开始
        if (tetrisInit.getIsStart() && tetrisInit.getTimer().getDelay()!=10) {
            //是否碰到左右的墙
            boolean isWall = false;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    for (TetrisNode tetrisNode : tetrisInit.getTetrisNodes()) {
                        if (tetrisNode.getX() <= 10) {
                            isWall = true;
                            break;
                        }
                        //判断左边是否被沉积方块挡住
                        for (TetrisNode node : tetrisInit.getDeposition()) {
                            if (tetrisNode.getX().equals(node.getX()+40) && tetrisNode.getY().equals(node.getY())) {
                                isWall = true;
                                break;
                            }
                        }
                    }
                    //如果没有碰到墙
                    if (!isWall) {
                        //所有方块往左边移动一格
                        for (TetrisNode tetrisNode : tetrisInit.getTetrisNodes()) {
                            tetrisNode.setX(tetrisNode.getX() - 40);
                        }
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    for (TetrisNode tetrisNode : tetrisInit.getTetrisNodes()) {
                        if (tetrisNode.getX() + 40 >= Constant.GAME_WIDTH - 10) {
                            isWall = true;
                            break;
                        }

                        //判断左边是否被沉积方块挡住
                        for (TetrisNode node : tetrisInit.getDeposition()) {
                            if (tetrisNode.getX().equals(node.getX()-40) && tetrisNode.getY().equals(node.getY())) {
                                isWall = true;
                                break;
                            }
                        }
                    }
                    //如果没有碰到墙
                    if (!isWall) {
                        //所有方块往右边移动一格
                        for (TetrisNode tetrisNode : tetrisInit.getTetrisNodes()) {
                            tetrisNode.setX(tetrisNode.getX() + 40);
                        }
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    //先停掉定时器，设置一下启动延迟为0，在设置一下后续的延时，最后再启动定时器，就可以再按下按键的瞬间立即执行定时器了
                    tetrisInit.getTimer().stop();
                    tetrisInit.getTimer().setInitialDelay(0);
                    tetrisInit.getTimer().setDelay(10);
                    tetrisInit.getTimer().start();
                    break;
                case KeyEvent.VK_UP:
                    //翻转方块
                    TetrisRevolve.revolve(tetrisInit.getTetrisNodes(), tetrisInit);
                    tetrisInit.getMusicPlay().revolveSound();
                    break;
            }
            tetrisInit.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
