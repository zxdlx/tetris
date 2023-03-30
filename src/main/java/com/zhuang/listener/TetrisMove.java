package com.zhuang.listener;

import com.zhuang.constant.Constant;
import com.zhuang.init.TetrisInit;
import com.zhuang.tetris.TetrisNode;
import com.zhuang.utils.TetrisType;
import com.zhuang.utils.TetrisUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 方块移动定时类
 *
 * @module
 * @author zxd
 * @date 2022/10/26  11:58
**/
public class TetrisMove implements ActionListener {
    //游戏初始化对象
    private TetrisInit tetrisInit;
    //消除沉积方块还是生成下落方块,0消除  1生成
    private int isClear;
    //被删除的y坐标集合
    private List<Integer> listY;

    public TetrisMove(TetrisInit tetrisInit) {
        this.tetrisInit = tetrisInit;
        this.isClear = 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //是否可以往下一格
        boolean isMove = true;
        if (isClear == 1) {
            //判断是否有碰到底部或者碰到沉积方块
            for (TetrisNode tetrisNode : tetrisInit.getTetrisNodes()) {
                //判断是否碰到底部
                if (tetrisNode.getY() >= Constant.BOT_LINE) {
                    isMove = false;
                    tetrisInit.getTimer().stop();
                    break;
                }
                //循环沉积方块，判断是否有触碰到
                for (TetrisNode node : tetrisInit.getDeposition()) {
                    if (tetrisNode.getX().equals(node.getX()) && tetrisNode.getY().equals(node.getY() - 40)) {
                        isMove = false;
                        tetrisInit.getTimer().stop();
                        break;
                    }
                }
            }
        }else {
            if (listY.size()>0) {
                TetrisUtil.sink(tetrisInit.getDeposition(), listY);
            }
            isMove = false;
            isClear = 1;
        }
        //如果没有碰到，就添加
        if (isMove) {
            for (TetrisNode tetrisNode : tetrisInit.getTetrisNodes()) {
                tetrisNode.setY(tetrisNode.getY() + 40);
            }
        }else {
            //碰到了就添加到沉积方块，并将原来的节点集合清空,并再次生成方块，启动定时器
            tetrisInit.getDeposition().addAll(tetrisInit.getTetrisNodes());
            tetrisInit.getTetrisNodes().clear();
            tetrisInit.getTimer().setDelay(new Double(Constant.SPEED*(0.2*(6-tetrisInit.getWindowBot().getLevel()))).intValue());
            tetrisInit.getTimer().start();
            //消除方块，并设置下一个动作为消除
            listY = TetrisUtil.clearLine(tetrisInit.getDeposition());
            if (listY.size() > 0) {
                //添加分数，消除1行10，2行30分，3行60分，4行100分
                if (listY.size() == 1) {
                    tetrisInit.getWindowBot().addScore(10);
                } else if (listY.size() == 2) {
                    tetrisInit.getWindowBot().addScore(30);
                } else if (listY.size() == 3) {
                    tetrisInit.getWindowBot().addScore(60);
                } else if (listY.size() == 4) {
                    tetrisInit.getWindowBot().addScore(100);
                }
                //分为5个等级，初始为1级，每200分增加一个等级，每+1等级速度加快，第五级约等于原来的5倍
                int score = tetrisInit.getWindowBot().getScore();
                for (int i = 2; i < 9; i+=2) {
                    if (score >= i * 100 && score < i * 100 + 200) {
                        //如果是这个分数区间，就进来设置等级
                        tetrisInit.getWindowBot().addLevel(i/2+1);
                        tetrisInit.getTimer().setDelay(new Double(Constant.SPEED*(0.1*i)).intValue());
                    }
                }
                //设置消除行数
                tetrisInit.getWindowBot().addClearLine(listY.size());
                isClear = 0;
            }else {
                //判断沉积方块是否已堆积到顶部，抽取出当前沉积方块的所有y坐标
                List<Integer> collect = tetrisInit.getDeposition().stream().map(TetrisNode::getY).collect(Collectors.toList());
                //如果有小于或等于0的y坐标就是游戏结束

                if (collect.size()>0 && Collections.min(collect) <= 0) {
                    tetrisInit.getTimer().stop();
                    tetrisInit.setIsStart(false);
                    tetrisInit.getGamePanel().setFail(true);
                }else {
                    //如果生成动作才会调用生成，根据未来方块生成当前方块和生成新的未来方块
                    TetrisType.randomBlock(tetrisInit);
                }
            }
        }

        tetrisInit.repaint();
    }
}
