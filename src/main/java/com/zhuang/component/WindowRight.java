package com.zhuang.component;

import com.zhuang.constant.Constant;
import com.zhuang.enums.GameBtnEnum;
import com.zhuang.init.TetrisInit;
import com.zhuang.tetris.TetrisNode;
import com.zhuang.utils.TetrisType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 窗口右边组件
 *
 * @module
 * @author zxd
 * @date 2022/10/28  10:14
**/
public class WindowRight extends JPanel {
    //游戏初始化对象
    private TetrisInit tetrisInit;
    //开始按钮
    private JButton button;
    //开始按钮文字
    private Integer beginBtn;
    //分数
    private int score;
    //分数标签
    private JLabel scoreNum;
    //等级数标签
    private JLabel levelNum;
    //当前等级
    private int level;
    //消除行数标签
    private JLabel clearLineNum;
    //消除行数
    private int clearLine;

    public WindowRight(TetrisInit tetrisInit) {
        this.tetrisInit = tetrisInit;
        //初始化默认参数
        beginBtn = 0;
        score = 0;
        level = 1;
        //取消默认布局，自定义组件位置
        setLayout(null);
        setBackground(new Color(0xA484B0B3,true));
        setBounds(Constant.GAME_WIDTH, 0,Constant.WINDOW_WIDTH-Constant.GAME_WIDTH,Constant.WINDOW_HEIGHT);
        //设置开始按钮
        setBeginBtn();
        //设置分数标签
        setScoreLabel();
        //等级
        setLevelLabel();
        //消除行数
        setClearLineLabel();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //绘制分割线
        g.setColor(Color.black);
        g.fillRect(0,200,Constant.WINDOW_WIDTH-Constant.GAME_WIDTH,10);
        g.setColor(new Color(0xA484B0B3,true));

        g.fillRect(0,200,Constant.WINDOW_WIDTH-Constant.GAME_WIDTH,10);
        //绘制未来方块
        for (TetrisNode tetrisNode : tetrisInit.getFutureNodes()) {
            g.setColor(new Color(tetrisNode.getColor(),true));
            g.fillRect(tetrisNode.getX(),tetrisNode.getY(), 5,39);      //左边线
            g.fillRect(tetrisNode.getX()+5,tetrisNode.getY(),29,5);     //顶线
            g.fillRect(tetrisNode.getX()+34,tetrisNode.getY(),5,34);     //右边线
            g.fillRect(tetrisNode.getX()+5,tetrisNode.getY()+34,34,5); //底线
            g.fillRect(tetrisNode.getX()+10,tetrisNode.getY()+10,19,19);
        }

        g.setColor(new Color(0xA4C0C5C7,true));
        g.fillRect(0,300,Constant.WINDOW_WIDTH-Constant.GAME_WIDTH,50);
        g.fillRect(0,410,Constant.WINDOW_WIDTH-Constant.GAME_WIDTH,50);
        g.fillRect(0,520,Constant.WINDOW_WIDTH-Constant.GAME_WIDTH,50);
    }

    /**
     * 开始游戏按钮
     */
    public void setBeginBtn() {
        button = new JButton(GameBtnEnum.getByCode(beginBtn));                  //创建一个按钮
        //位置是以当前面板为准 ，而不是窗口
        button.setBounds(40, 600, 150, 40);               //设置按钮位置大小
        button.setFont(new Font("宋体", Font.PLAIN, 16));       //设置按钮字体的格式
        button.setBackground(new Color(0xA4C0C5C7));                   //设置按钮颜色
        add(button);                                         //把按钮添加到面板中
        button.addActionListener(new ActionListener() {                  //设置监听，当按钮被点击时就会执行此方法
            @Override
            public void actionPerformed(ActionEvent e) {

                //如果是点击的开始游戏，将isStart设置为true，并且启动定时器
                if (beginBtn.equals(GameBtnEnum.BEGIN.getCode())) {
                    tetrisInit.setIsStart(true);
                    tetrisInit.getTimer().start();
                    reBtn(GameBtnEnum.STOP.getCode());
                    //同时渲染重新开始按钮
                    setReStartBtn();
                } else if (beginBtn.equals(GameBtnEnum.STOP.getCode())) {
                    tetrisInit.getTimer().stop();
                    tetrisInit.setIsStart(false);
                    reBtn(GameBtnEnum.CONTINUE.getCode());
                } else if (beginBtn.equals(GameBtnEnum.CONTINUE.getCode())) {
                    tetrisInit.getTimer().start();
                    tetrisInit.setIsStart(true);
                    reBtn(GameBtnEnum.STOP.getCode());
                }
                tetrisInit.repaint();
                //取消按钮的聚焦，否则键盘监听会失效
                button.setFocusable(false);
            }
        });
    }

    /**
     * 重新开始按钮
     */
    public void setReStartBtn() {
        JButton reButton = new JButton(GameBtnEnum.RESTART.getText());                  //创建一个按钮
        //位置是以当前面板为准 ，而不是窗口
        reButton.setBounds(40, 680, 150, 40);               //设置按钮位置大小
        reButton.setFont(new Font("宋体", Font.PLAIN, 16));       //设置按钮字体的格式
        reButton.setBackground(new Color(0xA4C0C5C7));                   //设置按钮颜色
        add(reButton);                                         //把按钮添加到面板中
        reButton.addActionListener(new ActionListener() {                  //设置监听，当按钮被点击时就会执行此方法
            @Override
            public void actionPerformed(ActionEvent e) {
                //设置游戏未开始
                tetrisInit.setIsStart(false);
                //初始化下坠方块和沉积方块
                tetrisInit.getTetrisNodes().clear();
                tetrisInit.getDeposition().clear();
                //重新设置分数、等级、消除行数
                reLabel();
                tetrisInit.getGamePanel().setFail(false);
                TetrisType.randomBlock(tetrisInit);
                tetrisInit.getTimer().setDelay(Constant.SPEED);
                //设置游戏开始
                tetrisInit.setIsStart(true);
                tetrisInit.repaint();
                //取消按钮的聚焦，否则键盘监听会失效
                reButton.setFocusable(false);
            }
        });
    }

    /**
     * 重置标签
     */
    private void reLabel(){
        this.score = 0;
        remove(scoreNum);
        setScoreNum();

        this.level = 1;
        remove(levelNum);
        setLevelNum();

        this.clearLine = 0;
        remove(clearLineNum);
        setClearLineNum();
    }

    /**
     * 消除行数标签
     */
    private void setClearLineLabel(){
        JLabel jLabel = new JLabel("消除行数");          //创建一个标签
        jLabel.setBounds(40,470,200,40);     //标签位置和大小
        jLabel.setFont(new Font("宋体",Font.PLAIN,40));
        add(jLabel);                              //将标签添加到面板中
        setClearLineNum();
    }

    /**
     * 消除行数
     */
    private void setClearLineNum(){
        clearLineNum = new JLabel(Integer.toString(clearLine));
        clearLineNum.setBounds(70,520,200,40);
        clearLineNum.setFont(new Font("宋体",Font.BOLD,40));
        clearLineNum.setForeground(new Color(0xA40027FF,true));
        add(clearLineNum);
    }

    /**
     * 增加消除行数
     */
    public void addClearLine(int clearLine){
        this.clearLine+=clearLine;
        remove(clearLineNum);
        setClearLineNum();
        tetrisInit.repaint();
    }

    /**
     * 分数标签
     */
    private void setScoreLabel(){
        JLabel jLabel = new JLabel("当前分数");          //创建一个标签
        jLabel.setBounds(40,360,200,40);     //标签位置和大小
        jLabel.setFont(new Font("宋体",Font.PLAIN,40));
        add(jLabel);                              //将标签添加到面板中
        setScoreNum();
    }

    /**
     * 分数
     */
    private void setScoreNum(){
        scoreNum = new JLabel(Integer.toString(score));
        scoreNum.setBounds(70,410,200,40);
        scoreNum.setFont(new Font("宋体",Font.BOLD,40));
        scoreNum.setForeground(new Color(0xA40027FF,true));
        add(scoreNum);
    }

    /**
     * 增加分数
     * @param score 增加的分数
     */
    public void addScore(Integer score) {
        this.score+=score;
        remove(scoreNum);
        setScoreNum();
        tetrisInit.repaint();
    }

    /**
     * 等级标签
     */
    private void setLevelLabel(){
        JLabel jLabel = new JLabel("当前等级");
        jLabel.setBounds(35,250,200,40);
        jLabel.setFont(new Font("宋体",Font.PLAIN,40));
        add(jLabel);
        //设置等级数
        setLevelNum();
    }

    /**
     * 等级
     */
    private void setLevelNum(){
        levelNum = new JLabel(Integer.toString(level));
        levelNum.setBounds(70,300,200,40);
        levelNum.setFont(new Font("宋体",Font.BOLD,40));
        levelNum.setForeground(new Color(0xA40027FF,true));
        add(levelNum);
    }

    /**
     * 增加等级
     */
    public void addLevel(int level){
        this.level = level;
        remove(levelNum);
        setLevelNum();
        tetrisInit.repaint();
    }

    /**
     * 重置按钮
     */
    public void reBtn(Integer text){
        //查询是否有存在的按钮
        if (isAncestorOf(button)) {
            remove(button);
            this.beginBtn = text;
            setBeginBtn();
        }
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getClearLine() {
        return clearLine;
    }
}

