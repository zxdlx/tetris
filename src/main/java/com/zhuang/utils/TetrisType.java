package com.zhuang.utils;

import com.zhuang.enums.DirectionEnum;
import com.zhuang.enums.TetrisTypeEnum;
import com.zhuang.init.TetrisInit;
import com.zhuang.tetris.TetrisNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 方块的7种类型生成工具类
 *
 * @module
 * @author zxd
 * @date 2022/10/25  10:10
**/
public class TetrisType {

    /**
     * 生成O形
     */
    public static List<TetrisNode> getO(Integer type){
        List<TetrisNode> tetrisNodes = new ArrayList<>();
        if (type.equals(0)) {
            //每次循环横向绘制2格，向下循环2次
            for (int i = 0; i < 2; i++) {
                tetrisNodes.add(new TetrisNode(210, i * 40 - 40, 39, 39, 0xA4D0C238));
                tetrisNodes.add(new TetrisNode(250, i * 40 - 40, 39, 39, 0xA4D0C238));
            }
        }else {
            for (int i = 0; i < 2; i++) {
                tetrisNodes.add(new TetrisNode(80, 60+i*40, 39, 39, 0xA4D0C238));
                tetrisNodes.add(new TetrisNode(120, 60+i*40, 39, 39, 0xA4D0C238));
            }
        }
        return tetrisNodes;
    }

    /**
     * 生成I形
     */
    public static List<TetrisNode> getI(Integer type){
        List<TetrisNode> tetrisNodes = new ArrayList<>();
        if (type.equals(0)) {
            //每次循环绘制一格，向上循环4次
            for (int i = 0; i < 4; i++) {
                tetrisNodes.add(new TetrisNode(210, 0 - i * 40, 39, 39, 0xA44AAC3A));
            }
        }else {
            for (int i = 0; i < 4; i++) {
                tetrisNodes.add(new TetrisNode(100, 20+i*40, 39, 39, 0xA44AAC3A));
            }
        }
        return tetrisNodes;
    }

    /**
     * 生成S形
     */
    public static List<TetrisNode> getS(Integer type){
        List<TetrisNode> tetrisNodes = new ArrayList<>();
        if (type.equals(0)) {
            //每次循环横向绘制两个，向下循环两次
            for (int i = 0; i < 2; i++) {
                tetrisNodes.add(new TetrisNode(210 - (i * 40), i * 40 - 40, 39, 39, 0xA4B31233));
                tetrisNodes.add(new TetrisNode(250 - (i * 40), i * 40 - 40, 39, 39, 0xA4B31233));
            }
        }else {
            for (int i = 0; i < 2; i++) {
                tetrisNodes.add(new TetrisNode(100 - (i * 40), 60+i*40, 39, 39, 0xA4B31233));
                tetrisNodes.add(new TetrisNode(140 - (i * 40), 60+i*40, 39, 39, 0xA4B31233));
            }
        }
        return tetrisNodes;
    }

    /**
     * 生成Z形
     */
    public static List<TetrisNode> getZ(Integer type){
        List<TetrisNode> tetrisNodes = new ArrayList<>();
        if (type.equals(0)) {
            //每次循环横向绘制两个，向下循环两次
            for (int i = 0; i < 2; i++) {
                tetrisNodes.add(new TetrisNode(210 + (i * 40), i * 40 - 40, 39, 39, 0xA42732B8));
                tetrisNodes.add(new TetrisNode(170 + (i * 40), i * 40 - 40, 39, 39, 0xA42732B8));
            }
        }else {
            for (int i = 0; i < 2; i++) {
                tetrisNodes.add(new TetrisNode(100 + (i * 40), 60+i*40, 39, 39, 0xA42732B8));
                tetrisNodes.add(new TetrisNode(60 + (i * 40), 60+i*40, 39, 39, 0xA42732B8));
            }
        }
        return tetrisNodes;
    }

    /**
     * 生成L形
     */
    public static List<TetrisNode> getL(Integer type){
        List<TetrisNode> tetrisNodes = new ArrayList<>();
        if (type.equals(0)) {
            //每次循环横向绘制一个，向下循环3次，第三次往右额外绘制一格
            for (int i = 0; i < 3; i++) {
                tetrisNodes.add(new TetrisNode(210, i * 40 - 80, 39, 39, 0xA42EB8CB));
                if (i == 2) {
                    tetrisNodes.add(new TetrisNode(250, i * 40 - 80, 39, 39, 0xA42EB8CB));
                }
            }
        }else {
            for (int i = 0; i < 3; i++) {
                tetrisNodes.add(new TetrisNode(80, 40+i*40, 39, 39, 0xA42EB8CB));
                if (i == 2) {
                    tetrisNodes.add(new TetrisNode(120, 40+i*40, 39, 39, 0xA42EB8CB));
                }
            }
        }
        return tetrisNodes;
    }

    /**
     * 生成J形
     */
    public static List<TetrisNode> getJ(Integer type){
        List<TetrisNode> tetrisNodes = new ArrayList<>();
        if (type.equals(0)) {
            //每次循环横向绘制一个，向下循环3次，第三次往左额外绘制一格
            for (int i = 0; i < 3; i++) {
                tetrisNodes.add(new TetrisNode(210, i * 40 - 80, 39, 39, 0xA49517B6));
                if (i == 2) {
                    tetrisNodes.add(new TetrisNode(170, i * 40 - 80, 39, 39, 0xA49517B6));
                }
            }
        }else {
            for (int i = 0; i < 3; i++) {
                tetrisNodes.add(new TetrisNode(120, 40+i*40, 39, 39, 0xA49517B6));
                if (i == 2) {
                    tetrisNodes.add(new TetrisNode(80, 40+i*40, 39, 39, 0xA49517B6));
                }
            }
        }
        return tetrisNodes;
    }

    /**
     * 生成反T形
     */
    public static List<TetrisNode> getT(Integer type){
        List<TetrisNode> tetrisNodes = new ArrayList<>();
        if (type.equals(0)) {
            //每次循环竖向绘制一个，向右循环3次，第二次是往上多绘制一格
            for (int i = 0; i < 3; i++) {
                tetrisNodes.add(new TetrisNode((i * 40) + 170, 0, 39, 39, 0xFF663A2B));
                //第二次循环是多出来一格
                if (i == 1) {
                    tetrisNodes.add(new TetrisNode((i * 40) + 170, -40, 39, 39, 0xFF663A2B));
                }
            }
        }else {
            for (int i = 0; i < 3; i++) {
                tetrisNodes.add(new TetrisNode((i * 40) + 60, 100, 39, 39, 0xFF663A2B));
                //第二次循环是多出来一格
                if (i == 1) {
                    tetrisNodes.add(new TetrisNode((i * 40) + 60, 60, 39, 39, 0xFF663A2B));
                }
            }
        }
        return tetrisNodes;
    }

    /**
     * 随机生成方块
     */
    public static void randomBlock(TetrisInit tetrisInit){
        Random random = new Random();
        //如果是游戏刚开始
        if (!tetrisInit.getIsStart()) {
            //先生成当前方块
            tetrisInit.setTetrisNodes(generateBlocks(tetrisInit,0,random.nextInt(7)));
            //生成未来方块
            tetrisInit.setFutureNodes(generateBlocks(tetrisInit, 1, random.nextInt(7)));
            return;
        }else {
            //如果游戏已经开始了，根据未来方块生成当前方块
            tetrisInit.setTetrisNodes(generateBlocks(tetrisInit,0,tetrisInit.getFutureType()));
            //再重新生成未来方块
            tetrisInit.setFutureNodes(generateBlocks(tetrisInit, 1, random.nextInt(7)));
        }
    }

    /**
     * 生成当前或者未来方块
     *
     * @author zxd
     * @date 2022/10/28 16:07
     * @param tetrisInit 初始化对象
     * @param nowOrFuture 0当前方块 1未来方块
     * @param type 方块类型
     * @return void
     */
    private static List<TetrisNode> generateBlocks(TetrisInit tetrisInit,Integer nowOrFuture,Integer type){
        //根据未来方块进行生成
        List<TetrisNode> tetrisNodes = new ArrayList<>();
        switch (type){
            case 0:
                tetrisNodes = getO(nowOrFuture);
                if (nowOrFuture.equals(0)) {
                    //O形不需要设置方向
                    tetrisInit.setTetrisType(TetrisTypeEnum.O.getCode());
                }else {
                    tetrisInit.setFutureType(TetrisTypeEnum.O.getCode());
                }
                break;
            case 1:
                tetrisNodes=getI(nowOrFuture);
                tetrisInit.setDirection(DirectionEnum.UP.getCode());
                //设置类型和初始方向
                if (nowOrFuture.equals(0)) {
                    tetrisInit.setTetrisType(TetrisTypeEnum.I.getCode());
                }else {
                    tetrisInit.setFutureType(TetrisTypeEnum.I.getCode());
                }
                break;
            case 2:
                tetrisNodes=getS(nowOrFuture);
                tetrisInit.setDirection(DirectionEnum.RIGHT.getCode());
                if (nowOrFuture.equals(0)) {
                    tetrisInit.setTetrisType(TetrisTypeEnum.S.getCode());
                }else {
                    tetrisInit.setFutureType(TetrisTypeEnum.S.getCode());
                }
                break;
            case 3:
                tetrisNodes=getZ(nowOrFuture);
                tetrisInit.setDirection(DirectionEnum.LEFT.getCode());
                if (nowOrFuture.equals(0)) {
                    tetrisInit.setTetrisType(TetrisTypeEnum.Z.getCode());
                }else {
                    tetrisInit.setFutureType(TetrisTypeEnum.Z.getCode());
                }
                break;
            case 4:
                tetrisNodes=getL(nowOrFuture);
                tetrisInit.setDirection(DirectionEnum.UP.getCode());
                if (nowOrFuture.equals(0)) {
                    tetrisInit.setTetrisType(TetrisTypeEnum.L.getCode());
                }else {
                    tetrisInit.setFutureType(TetrisTypeEnum.L.getCode());
                }
                break;
            case 5:
                tetrisNodes=getJ(nowOrFuture);
                tetrisInit.setDirection(DirectionEnum.UP.getCode());
                if (nowOrFuture.equals(0)) {
                    tetrisInit.setTetrisType(TetrisTypeEnum.J.getCode());
                }else {
                    tetrisInit.setFutureType(TetrisTypeEnum.J.getCode());
                }
                break;
            case 6:
                tetrisNodes=getT(nowOrFuture);
                tetrisInit.setDirection(DirectionEnum.UP.getCode());
                if (nowOrFuture.equals(0)) {
                    tetrisInit.setTetrisType(TetrisTypeEnum.T.getCode());
                }else {
                    tetrisInit.setFutureType(TetrisTypeEnum.T.getCode());
                }
                break;
        }
        return tetrisNodes;
    }

}
