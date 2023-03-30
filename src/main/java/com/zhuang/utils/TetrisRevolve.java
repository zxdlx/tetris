package com.zhuang.utils;

import com.zhuang.constant.Constant;
import com.zhuang.enums.DirectionEnum;
import com.zhuang.enums.TetrisTypeEnum;
import com.zhuang.init.TetrisInit;
import com.zhuang.tetris.TetrisNode;

import java.util.List;

/**
 * 方块旋转工具类
 *
 * @module
 * @author zxd
 * @date 2022/10/31  16:03
**/
public class TetrisRevolve {
    /**
     * 旋转方块
     *
     * @author zxd
     * @date 2022/10/27 16:37
     * @param tetrisNodes 方块集合
     * @param tetrisInit 初始化对象，用于获取类型和方向
     * @return void
     */
    public static void revolve(List<TetrisNode> tetrisNodes, TetrisInit tetrisInit){
        //旋转后的方向
        Integer direction = null;
        //I形
        if (tetrisInit.getTetrisType().equals(TetrisTypeEnum.I.getCode())) {
            //旋转
            direction = revolveI(tetrisNodes,tetrisInit.getDeposition(),tetrisInit.getDirection());
        }
        //S形
        if (tetrisInit.getTetrisType().equals(TetrisTypeEnum.S.getCode())) {
            direction = revolveS(tetrisNodes,tetrisInit.getDeposition(),tetrisInit.getDirection());
        }
        //Z形
        if (tetrisInit.getTetrisType().equals(TetrisTypeEnum.Z.getCode())) {
            direction = revolveZ(tetrisNodes,tetrisInit.getDeposition(),tetrisInit.getDirection());
        }
        //L形
        if (tetrisInit.getTetrisType().equals(TetrisTypeEnum.L.getCode())) {
            direction = revolveL(tetrisNodes,tetrisInit.getDeposition(),tetrisInit.getDirection());
        }
        //J形
        if (tetrisInit.getTetrisType().equals(TetrisTypeEnum.J.getCode())) {
            direction = revolveJ(tetrisNodes,tetrisInit.getDeposition(),tetrisInit.getDirection());
        }
        //反T形
        if (tetrisInit.getTetrisType().equals(TetrisTypeEnum.T.getCode())) {
            direction = revolveT(tetrisNodes,tetrisInit.getDeposition(),tetrisInit.getDirection());
        }
        tetrisInit.setDirection(direction);
    }

    /**
     * I形旋转，返回设置后的方向
     */
    private static Integer revolveI(List<TetrisNode> tetrisNodes,List<TetrisNode> deposition,Integer direction){
        TetrisNode tetrisNode = tetrisNodes.get(2); //下落方块旋转轴

        //如果此时的方向是上下，就切换到左右
        if (direction.equals(DirectionEnum.UP.getCode()) || direction.equals(DirectionEnum.DOWN.getCode())) {
            //左边第一个是否有阻挡
            boolean left1 = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() - 40) && v.getY().equals(tetrisNode.getY()));
            //左边第二个是否有阻挡
            boolean left2 = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() - 80) && v.getY().equals(tetrisNode.getY()));
            //右边第一格是否有阻挡
            boolean right1 = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() + 40) && v.getY().equals(tetrisNode.getY()));
            //右边第2格是否有阻挡
            boolean right2 = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() + 80) && v.getY().equals(tetrisNode.getY()));
            //左边一格，右边两格，方块想右移动一格
            if (left2 && !left1 && !right1 && !right2){
                tetrisNodes.forEach(v -> v.setX(v.getX()+40));
            } else if (!left1 && !left2 && !right1){
                //如果左边距离边界只有一格，并且右边第二格有空位，就向右移动一格
                if (tetrisNode.getX()-40==Constant.LEFT_LINE && !right2){
                    tetrisNodes.forEach(v -> v.setX(v.getX()+40));
                }
                //如果贴在两侧的墙上，则无法旋转
                if (tetrisNode.getX() == Constant.LEFT_LINE || tetrisNode.getX() == Constant.RIGHT_LINE) {
                    return DirectionEnum.UP.getCode();
                }
            }else {
                //如果都不匹配，就无法旋转
                return DirectionEnum.UP.getCode();
            }

            //进行旋转，I形轴是从下往上第3格节点，所以第3个节点不动
            for (int i = 0; i < 4; i++) {
                if (i == 0 || i == 1) {
                    //将第一格和第二格设置为第三个左边
                    tetrisNodes.get(i).setX(tetrisNode.getX()-(80-i*40));
                    tetrisNodes.get(i).setY(tetrisNode.getY());
                } else if (i == 3) {
                    //第4格设置为第三个右边
                    tetrisNodes.get(i).setX(tetrisNode.getX()+40);
                    tetrisNodes.get(i).setY(tetrisNode.getY());
                }
            }
            return DirectionEnum.LEFT.getCode();
        }else {
            //如果下方只有一格空间或者贴这沉积方块，或者上方贴这沉积方块，或者底下一格是游戏面板底部，或者贴着游戏面板底部，无法旋转
            for (TetrisNode node : deposition) {
                if (node.getX().equals(tetrisNode.getX()) && node.getY().equals(tetrisNode.getY() + 40)
                        || node.getX().equals(tetrisNode.getX()) && node.getY().equals(tetrisNode.getY() + 80)
                        || node.getX().equals(tetrisNode.getX()) && node.getY().equals(tetrisNode.getY() - 40)
                        || tetrisNode.getY().equals(Constant.BOT_LINE)
                        || tetrisNode.getY().equals(Constant.BOT_LINE - Constant.MOVE)) {
                    return DirectionEnum.LEFT.getCode();
                }
            }
            //I形轴是从下往上第3格节点，所以第3个节点不动
            for (int i = 0; i < 4; i++) {
                if (i == 0 || i == 1) {
                    //将第一格和第二格设置为第三个下边
                    tetrisNodes.get(i).setX(tetrisNode.getX());
                    tetrisNodes.get(i).setY(tetrisNode.getY()+(80-i*40));
                } else if (i == 3) {
                    //第4格设置为第三格上边
                    tetrisNodes.get(i).setX(tetrisNode.getX());
                    tetrisNodes.get(i).setY(tetrisNode.getY()-40);
                }
            }
            return DirectionEnum.UP.getCode();
        }
    }

    /**
     * S形旋转
     */
    private static Integer revolveS(List<TetrisNode> tetrisNodes,List<TetrisNode> deposition,Integer direction){
        //旋转轴第一格
        TetrisNode tetrisNode = tetrisNodes.get(0);
        //如果此时的方向是左右，就切换到上下
        if (direction.equals(DirectionEnum.LEFT.getCode()) || direction.equals(DirectionEnum.RIGHT.getCode())) {
            //如果左边，或者左上方有沉积方块，就无法旋转
            for (TetrisNode node : deposition) {
                if (node.getX().equals(tetrisNode.getX() - 40) && node.getY().equals(tetrisNode.getY())
                        || node.getX().equals(tetrisNode.getX() - 40) && node.getY().equals(tetrisNode.getY() - 40)) {
                    return DirectionEnum.LEFT.getCode();
                }
            }
            //旋转轴第一格，将第2格设置为第1格下边
            tetrisNodes.get(1).setX(tetrisNode.getX());
            tetrisNodes.get(1).setY(tetrisNode.getY()+40);
            //第3、4格设置为第1个左上边
            for (int i = 0; i < 2; i++) {
                tetrisNodes.get(i+2).setX(tetrisNode.getX()-40);
                tetrisNodes.get(i+2).setY(tetrisNode.getY()-((1-i)*40));
            }
            return DirectionEnum.UP.getCode();
        }else {
            //如果右边边，或者左下方有沉积方块，就无法旋转
            for (TetrisNode node : deposition) {
                if (node.getX().equals(tetrisNode.getX() + 40) && node.getY().equals(tetrisNode.getY())
                        || node.getX().equals(tetrisNode.getX() - 40) && node.getY().equals(tetrisNode.getY() + 40)) {
                    return DirectionEnum.UP.getCode();
                }
            }
            //将第2格设置为第1格右边
            tetrisNodes.get(1).setX(tetrisNode.getX()+40);
            tetrisNodes.get(1).setY(tetrisNode.getY());
            //第3、4格设置为第1个左下边
            for (int i = 0; i < 2; i++) {
                tetrisNodes.get(i+2).setX(tetrisNode.getX()-((1-i)*40));
                tetrisNodes.get(i+2).setY(tetrisNode.getY()+40);
            }
            return DirectionEnum.LEFT.getCode();
        }
    }

    /**
     * Z形旋转
     */
    private static Integer revolveZ(List<TetrisNode> tetrisNodes,List<TetrisNode> deposition,Integer direction){
        //第一个为旋转轴
        TetrisNode tetrisNode = tetrisNodes.get(0);
        //如果此时的方向是左右，就切换到上下
        if (direction.equals(DirectionEnum.LEFT.getCode()) || direction.equals(DirectionEnum.RIGHT.getCode())) {
            //如果右边，或者右上方有沉积方块，就无法旋转
            for (TetrisNode node : deposition) {
                if (node.getX().equals(tetrisNode.getX() + 40) && node.getY().equals(tetrisNode.getY())
                        || node.getX().equals(tetrisNode.getX() + 40) && node.getY().equals(tetrisNode.getY() - 40)) {
                    return DirectionEnum.LEFT.getCode();
                }
            }
            //旋转轴第一格，将第2格设置为第1格下边
            tetrisNodes.get(1).setX(tetrisNode.getX());
            tetrisNodes.get(1).setY(tetrisNode.getY()+40);
            //第3、4格设置为第1个左上边
            for (int i = 0; i < 2; i++) {
                tetrisNodes.get(i+2).setX(tetrisNode.getX()+40);
                tetrisNodes.get(i+2).setY(tetrisNode.getY()-((1-i)*40));
            }
            return DirectionEnum.UP.getCode();
        }else {
            //如果左边，或者右下方有沉积方块，就无法旋转
            for (TetrisNode node : deposition) {
                if (node.getX().equals(tetrisNode.getX() - 40) && node.getY().equals(tetrisNode.getY())
                        || node.getX().equals(tetrisNode.getX() + 40) && node.getY().equals(tetrisNode.getY() + 40)) {
                    return DirectionEnum.UP.getCode();
                }
            }
            //将第2格设置为第1格下边
            tetrisNodes.get(1).setX(tetrisNode.getX()-40);
            tetrisNodes.get(1).setY(tetrisNode.getY());
            //第3、4格设置为第1个左上边
            for (int i = 0; i < 2; i++) {
                tetrisNodes.get(i+2).setX(tetrisNode.getX()+((1-i)*40));
                tetrisNodes.get(i+2).setY(tetrisNode.getY()+40);
            }
            return DirectionEnum.LEFT.getCode();
        }
    }

    /**
     * L形旋转
     */
    private static Integer revolveL(List<TetrisNode> tetrisNodes,List<TetrisNode> deposition,Integer direction){
        //旋转轴第二格
        TetrisNode tetrisNode = tetrisNodes.get(1);
        //四个方向公用的判断，左边有无沉积方块
        boolean left = deposition.stream().anyMatch(v->v.getX().equals(tetrisNode.getX()-40) && v.getY().equals(tetrisNode.getY()));
        //右边有无沉积方块
        boolean right = deposition.stream().anyMatch(v->v.getX().equals(tetrisNode.getX()+40) && v.getY().equals(tetrisNode.getY()));
        //方块上边是否有沉积方块
        boolean up = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX()) && v.getY().equals(tetrisNode.getY() - 40));
        //下边是否有沉积方块
        boolean down = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX()) && v.getY().equals(tetrisNode.getY() + 40));

        //如果此时的方向是上，就切换到右
        if (direction.equals(DirectionEnum.UP.getCode())) {
            //左下方有无沉积方块
            boolean left2 = deposition.stream().anyMatch(v->v.getX().equals(tetrisNode.getX()-40) && v.getY().equals(tetrisNode.getY()+40));
            //右边2格有无沉积方块
            boolean right2 = deposition.stream().anyMatch(v->v.getX().equals(tetrisNode.getX()+80) && v.getY().equals(tetrisNode.getY()));
            //如果左边一格被档，而右边两格都有位置，则向右边移动一格
            if ((left2 || left ||tetrisNode.getX() == Constant.LEFT_LINE) && !right && !right2) {
                tetrisNodes.forEach(v->v.setX(v.getX()+40));
            } else if (!(!left && !left2 && !right && tetrisNode.getX() > Constant.LEFT_LINE)) {
                //这是正常旋转，如果不符合正常旋转的就不旋转
                return DirectionEnum.UP.getCode();
            }
            //旋转
            tetrisNodes.get(0).setX(tetrisNode.getX()+40);
            tetrisNodes.get(0).setY(tetrisNode.getY());
            for (int i = 0; i < 2; i++) {
                tetrisNodes.get(i+2).setX(tetrisNode.getX()-40);
                tetrisNodes.get(i+2).setY(tetrisNode.getY()+i*40);
            }
            return DirectionEnum.RIGHT.getCode();
        }else if (direction.equals(DirectionEnum.RIGHT.getCode())){
            //上左边是否有沉积方块
            boolean up2 = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() - 40) && v.getY().equals(tetrisNode.getY() - 40));
            //如果有一块不满足条件，就无法旋转
            if (!(!up && !up2 && !down)) {
                return DirectionEnum.RIGHT.getCode();
            }

            tetrisNodes.get(0).setX(tetrisNode.getX());
            tetrisNodes.get(0).setY(tetrisNode.getY()+40);
            for (int i = 0; i < 2; i++) {
                tetrisNodes.get(i+2).setX(tetrisNode.getX()-i*40);
                tetrisNodes.get(i+2).setY(tetrisNode.getY()-40);
            }
            return DirectionEnum.DOWN.getCode();
        }else if (direction.equals(DirectionEnum.DOWN.getCode())){
            //右上方有无沉积方块
            boolean right2 = deposition.stream().anyMatch(v->v.getX().equals(tetrisNode.getX()+40) && v.getY().equals(tetrisNode.getY()-40));
            //左边2格有无沉积方块
            boolean left2 = deposition.stream().anyMatch(v->v.getX().equals(tetrisNode.getX()-80) && v.getY().equals(tetrisNode.getY()));
            //如果右边1、2格被档，或者触碰到边缘而左边两格都有位置，则向右边移动一格
            if ((right || right2 ||tetrisNode.getX() == Constant.RIGHT_LINE) && !left && !left2) {
                tetrisNodes.forEach(v->v.setX(v.getX()-40));
            } else if (!(!left && !right && !right2 && tetrisNode.getX()<Constant.RIGHT_LINE)) {
                //这是正常旋转，如果不符合正常旋转的就不旋转
                return DirectionEnum.DOWN.getCode();
            }
            tetrisNodes.get(0).setX(tetrisNode.getX()-40);
            tetrisNodes.get(0).setY(tetrisNode.getY());
            for (int i = 0; i < 2; i++) {
                tetrisNodes.get(i+2).setX(tetrisNode.getX()+40);
                tetrisNodes.get(i+2).setY(tetrisNode.getY()-i*40);
            }
            return DirectionEnum.LEFT.getCode();
        }else {
            //下右边是否有沉积方块
            boolean down2 = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() + 40) && v.getY().equals(tetrisNode.getY() + 40));
            //如果有一块不满足条件，就无法旋转
            if (!(!up && !down && !down2)) {
                return DirectionEnum.LEFT.getCode();
            }
            tetrisNodes.get(0).setX(tetrisNode.getX());
            tetrisNodes.get(0).setY(tetrisNode.getY()-40);
            for (int i = 0; i < 2; i++) {
                tetrisNodes.get(i+2).setX(tetrisNode.getX()+i*40);
                tetrisNodes.get(i+2).setY(tetrisNode.getY()+40);
            }
            return DirectionEnum.UP.getCode();
        }
    }

    /**
     * 生成J形
     */
    private static Integer revolveJ(List<TetrisNode> tetrisNodes,List<TetrisNode> deposition,Integer direction){
        //旋转轴第二格
        TetrisNode tetrisNode = tetrisNodes.get(1);
        //四个方向公用的判断，左边有无沉积方块
        boolean left = deposition.stream().anyMatch(v->v.getX().equals(tetrisNode.getX()-40) && v.getY().equals(tetrisNode.getY()));
        //右边有无沉积方块
        boolean right = deposition.stream().anyMatch(v->v.getX().equals(tetrisNode.getX()+40) && v.getY().equals(tetrisNode.getY()));
        //方块上边是否有沉积方块
        boolean up = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX()) && v.getY().equals(tetrisNode.getY() - 40));
        //下边是否有沉积方块
        boolean down = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX()) && v.getY().equals(tetrisNode.getY() + 40));
        //右下方有无沉积方块
        boolean rightDown = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() + 40) && v.getY().equals(tetrisNode.getY() + 40));
        //右上是否有沉积方块
        boolean rightUp = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() + 40) && v.getY().equals(tetrisNode.getY() - 40));
        //左下方是否有沉积方块
        boolean leftDown = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() - 40) && v.getY().equals(tetrisNode.getY() + 40));

        //如果此时的方向是上，就切换到右
        if (direction.equals(DirectionEnum.UP.getCode())) {
            //左上方有无沉积方块
            boolean leftUp = deposition.stream().anyMatch(v->v.getX().equals(tetrisNode.getX()-40) && v.getY().equals(tetrisNode.getY()-40));
            //左2格上放有无沉积方块
            boolean left2Up = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() - 80) && v.getY().equals(tetrisNode.getY() - 40));
            //左边2格有无沉积方块
            boolean left2 = deposition.stream().anyMatch(v->v.getX().equals(tetrisNode.getX()-80) && v.getY().equals(tetrisNode.getY()));
            //如果右边一格被档，而左边1、2、2上位置都空着，就可以往左移动一格进行旋转
            if ((right || tetrisNode.getX() == Constant.RIGHT_LINE) && !left && !left2 && !left2Up) {
                tetrisNodes.forEach(v->v.setX(v.getX()-40));
            } else if (leftUp && !left && !rightDown){
                //如果左上角被阻挡了，只要左边和右下角没有阻挡，就往下移动一格
                tetrisNodes.forEach(v->v.setY(v.getY()+40));
            } else if (!(!left && !leftUp && !right)) {
                //这是正常旋转，如果不符合正常旋转的就不旋转
                return DirectionEnum.UP.getCode();
            }

            tetrisNodes.get(0).setX(tetrisNode.getX()+40);
            tetrisNodes.get(0).setY(tetrisNode.getY());
            for (int i = 0; i < 2; i++) {
                tetrisNodes.get(i+2).setX(tetrisNode.getX()-40);
                tetrisNodes.get(i+2).setY(tetrisNode.getY()-i*40);
            }
            return DirectionEnum.RIGHT.getCode();
        }else if (direction.equals(DirectionEnum.RIGHT.getCode())){
            //如果右上方被阻挡，并且上方和坐下方空余，就向左边移动一格
            if (rightUp && !up && !leftDown) {
                tetrisNodes.forEach(v->v.setX(v.getX()-40));
            } else if (!(!up && !down && !rightUp)) {
                //如果有一块不满足就无法旋转
                return DirectionEnum.RIGHT.getCode();
            }
            tetrisNodes.get(0).setX(tetrisNode.getX());
            tetrisNodes.get(0).setY(tetrisNode.getY()+40);
            for (int i = 0; i < 2; i++) {
                tetrisNodes.get(i+2).setX(tetrisNode.getX()+i*40);
                tetrisNodes.get(i+2).setY(tetrisNode.getY()-40);
            }
            return DirectionEnum.DOWN.getCode();
        }else if (direction.equals(DirectionEnum.DOWN.getCode())){
            //右边2格是否有沉积方块
            boolean right2 = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() + 80) && v.getY().equals(tetrisNode.getY()));
            //右边2格下方是否有沉积方块
            boolean right2Down = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() + 80) && v.getY().equals(tetrisNode.getY() + 40));
            //如果左边一格被阻挡，右边，右边2格，右边2格下方没有阻挡时，可以向右边移动一格
            if ((left || tetrisNode.getX().equals(Constant.LEFT_LINE)) && !right && !right2 && !right2Down) {
                tetrisNodes.forEach(v->v.setX(v.getX()+40));
            } else if (!(!left && !right && !rightDown)) {
                return DirectionEnum.DOWN.getCode();
            }
            tetrisNodes.get(0).setX(tetrisNode.getX()-40);
            tetrisNodes.get(0).setY(tetrisNode.getY());
            for (int i = 0; i < 2; i++) {
                tetrisNodes.get(i+2).setX(tetrisNode.getX()+40);
                tetrisNodes.get(i+2).setY(tetrisNode.getY()+i*40);
            }
            return DirectionEnum.LEFT.getCode();
        }else {
            if (leftDown && !down && !rightUp) {
                //如果坐下方被挡住，而下方和右上方没有被挡住，则向右移动一格
                tetrisNodes.forEach(v->v.setX(v.getX()+40));
            }else if (!(!up && !down && !leftDown)) {
                //如果不满足条件无法旋转
                return DirectionEnum.LEFT.getCode();
            }
            tetrisNodes.get(0).setX(tetrisNode.getX());
            tetrisNodes.get(0).setY(tetrisNode.getY()-40);
            for (int i = 0; i < 2; i++) {
                tetrisNodes.get(i+2).setX(tetrisNode.getX()-i*40);
                tetrisNodes.get(i+2).setY(tetrisNode.getY()+40);
            }
            return DirectionEnum.UP.getCode();
        }
    }

    /**
     * 旋转反T形
     */
    private static Integer revolveT(List<TetrisNode> tetrisNodes,List<TetrisNode> deposition,Integer direction){
        //旋转轴第二格
        TetrisNode tetrisNode = tetrisNodes.get(1);
        //四个方向公用的判断，左边有无沉积方块
        boolean left = deposition.stream().anyMatch(v->v.getX().equals(tetrisNode.getX()-40) && v.getY().equals(tetrisNode.getY()));
        //右边有无沉积方块
        boolean right = deposition.stream().anyMatch(v->v.getX().equals(tetrisNode.getX()+40) && v.getY().equals(tetrisNode.getY()));
        //方块上边是否有沉积方块
        boolean up = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX()) && v.getY().equals(tetrisNode.getY() - 40));
        //下边是否有沉积方块
        boolean down = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX()) && v.getY().equals(tetrisNode.getY() + 40));
        //如果此时的方向是上，就切换到右
        if (direction.equals(DirectionEnum.UP.getCode())) {
            //如果下方有沉积方块，就无法旋转
            if (down || tetrisNode.getY().equals(Constant.BOT_LINE)) {
                return DirectionEnum.UP.getCode();
            }
            //旋转轴第二格，只需要让第一格往下就可以
            tetrisNodes.get(0).setX(tetrisNode.getX());
            tetrisNodes.get(0).setY(tetrisNode.getY()+40);
            return DirectionEnum.RIGHT.getCode();
        }else if (direction.equals(DirectionEnum.RIGHT.getCode())){
            //右边2格是否有方块
            boolean right2 = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() + 80));
            //右下方是否有方块
            boolean rightDown = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() + 40) && v.getY().equals(tetrisNode.getY() + 40));
            //如果左边贴墙，而右边2格和右下角为空余，则向右边移动一格
            if ((left || tetrisNode.getX().equals(Constant.LEFT_LINE)) && !right2 && !rightDown) {
                tetrisNodes.forEach(v->v.setX(v.getX()+40));
            }else if (left){
                //如果不满足旋转条件，则无法旋转
                return DirectionEnum.RIGHT.getCode();
            }
            //让第三个往左
            tetrisNodes.get(2).setX(tetrisNode.getX()-40);
            tetrisNodes.get(2).setY(tetrisNode.getY());
            return DirectionEnum.DOWN.getCode();
        }else if (direction.equals(DirectionEnum.DOWN.getCode())){
            //如果上方有沉积方块，就无法旋转
            if (up) {
                return DirectionEnum.DOWN.getCode();
            }
            //让第4格往上
            tetrisNodes.get(3).setX(tetrisNode.getX());
            tetrisNodes.get(3).setY(tetrisNode.getY()-40);
            return DirectionEnum.LEFT.getCode();
        }else {
            //左边2格是否有方块
            boolean left2 = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() - 80) && v.getY().equals(tetrisNode.getY()));
            //左上方是否有方块
            boolean leftUp = deposition.stream().anyMatch(v -> v.getX().equals(tetrisNode.getX() - 40) && v.getY().equals(tetrisNode.getY() - 40));
            //如果右边贴墙，而左边2格和左上角为空余，则向左边移动一格
            if ((right || tetrisNode.getX().equals(Constant.RIGHT_LINE)) && !left2 && !leftUp) {
                tetrisNodes.forEach(v->v.setX(v.getX()-40));
            } else if (right) {
                return DirectionEnum.LEFT.getCode();
            }
            //最后回正就需要初始化原来的位置和下标了，否则会出问题
            tetrisNodes.get(3).setX(tetrisNode.getX()+40);
            tetrisNodes.get(3).setY(tetrisNode.getY());

            tetrisNodes.get(2).setX(tetrisNode.getX());
            tetrisNodes.get(2).setY(tetrisNode.getY()-40);

            tetrisNodes.get(0).setX(tetrisNode.getX()-40);
            tetrisNodes.get(0).setY(tetrisNode.getY());
            return DirectionEnum.UP.getCode();
        }
    }
}
