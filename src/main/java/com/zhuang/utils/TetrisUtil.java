package com.zhuang.utils;

import com.zhuang.constant.Constant;
import com.zhuang.tetris.TetrisNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 工具类
 *
 * @author zxd
 * @date 2022/10/27  10:34
**/
public class TetrisUtil {
    /**
     * 消除完整的行
     *
     * @author zxd
     * @date 2022/10/27 16:37
     * @param deposition 沉积方块
     * @return java.util.List<java.lang.Integer>
     */
    public static List<Integer> clearLine(List<TetrisNode> deposition){
        //抽取出所有对象的Y坐标
        List<Integer> collect = deposition.stream().map(TetrisNode::getY).collect(Collectors.toList());
        //创建一个map存储Y坐标出现的次数
        Map<Integer,Integer> map = new HashMap<>();
        //被删除的y坐标
        List<Integer> y = new ArrayList<>();
        for (Integer integer : collect) {
            //次数
            int i = 1;
            //如果map里已有这个值，就+1并覆盖
            if (map.get(integer) != null) {
                i = map.get(integer)+1;
            }
            map.put(integer,i);
        }
        //循环出map
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            //如果重复次数等于12  代表占据了一行，应该清除这个坐标上的所有对象
            if (entry.getValue().equals(Constant.HORIZONTAL)) {
                //添加到被删除的y坐标
                y.add(entry.getKey());
                //删除掉集合里大于12格的行
                deposition.removeIf(v -> v.getY().equals(entry.getKey()));
            }
        }
        return y;
    }

    /**
     * 将浮空的沉积方块下沉
     *
     * @author zxd
     * @date 2022/10/27 15:37
     * @param deposition 未下沉的沉积方块集合
     * @param clearY 被消除的y坐标集合
     */
    public static void sink(List<TetrisNode> deposition,List<Integer> clearY){
        //先给y坐标进行排序，让y最大值放第一个，也就是最下行进行判断
        List<Integer> listY = clearY.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        //循环被消除的y坐标
        for (Integer integer : listY) {
            //是否有未下沉的沉积方块
            boolean isSink = false;
            //循环未下沉的沉积方块
            for (TetrisNode tetrisNode : deposition) {
                //如果未下沉的沉积方块大于被消除的y坐标，就往下移动一格，然后大于这个y坐标的y坐标也往下移动一格，方便后面进行判断
                if (tetrisNode.getY() < integer) {
                    tetrisNode.setY(tetrisNode.getY()+40);
                    //有下沉方块
                    isSink = true;
                }
            }
            //如果有下降方块，二次循环y坐标，大于当前循环的y坐标的全部下降一个
            if (isSink) {
                for (Integer y : listY) {
                    if (y < integer) {
                        int i = listY.indexOf(y);
                        listY.set(i, y + 40);
                    }
                }
            }
        }
    }
}
