package com.zhuang.init;

import com.zhuang.component.GamePanel;
import com.zhuang.component.WindowRight;
import com.zhuang.constant.Constant;
import com.zhuang.listener.TetrisKeyListener;
import com.zhuang.listener.TetrisMove;
import com.zhuang.tetris.TetrisNode;
import com.zhuang.utils.MusicPlay;
import com.zhuang.utils.TetrisType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 俄罗斯方块初始化类
 *
 * @author zxd
 * @date 2022/10/25  9:52
 **/
public class TetrisInit extends JFrame {
    //游戏面板
    private final GamePanel gamePanel;
    //底部面板
    private final WindowRight windowRight;
    //方块节点集合
    private List<TetrisNode> tetrisNodes;
    //下一个方块的节点集合
    private List<TetrisNode> futureNodes;
    //定时器
    private final Timer timer;
    //当前方块类型
    private Integer tetrisType;
    //下一个方块的类型
    private Integer futureType;
    //当前方块方向
    private Integer direction;
    //底部的沉积方块集合
    private final List<TetrisNode> deposition;
    //音频
    private final MusicPlay musicPlay;

    //是否开始游戏
    private boolean isStart;

    public TetrisInit() {
        //初始化窗口
        super("俄罗斯方块");
        windowInit();
        //初始化键盘监听
        addKeyListener(new TetrisKeyListener());

        TetrisType.randomBlock(this);
        deposition = new ArrayList<>();

        //初始化游戏面板
        gamePanel = new GamePanel(this);
        add(gamePanel);

        //初始化右边面板
        windowRight = new WindowRight(this);
        add(windowRight);

        //创建定时器
        //定时器事件类
        TetrisMove tetrisMove = new TetrisMove(this);
        timer = new Timer(Constant.SPEED, tetrisMove);

        //播放音乐
        musicPlay = new MusicPlay();

        //显示窗口
        setVisible(true);
    }


    /**
     * 初始化窗口
     */
    private void windowInit() {
        setSize(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);   //设置窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置点击右上角关闭按钮后程序会停止，不设置的话关闭了程序不会停止
        setLocationRelativeTo(null);                     //设置启动时默认位置为屏幕居中
        setResizable(false);                             //设置窗口不可变大或缩小
        setLayout(null);
        //设置窗口的图标，先获取图片再设置
        BufferedImage read = null;
        try {
            URL url = this.getClass().getClassLoader().getResource(Constant.IMG_PATH);
            if (url != null) {
                read = ImageIO.read(url);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setIconImage(read);

        setFocusable(true);         //设置聚焦
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public List<TetrisNode> getTetrisNodes() {
        return tetrisNodes;
    }

    public void setTetrisNodes(List<TetrisNode> tetrisNodes) {
        this.tetrisNodes = tetrisNodes;
    }

    public Timer getTimer() {
        return timer;
    }

    public List<TetrisNode> getDeposition() {
        return deposition;
    }

    public Integer getTetrisType() {
        return tetrisType;
    }

    public void setTetrisType(Integer tetrisType) {
        this.tetrisType = tetrisType;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public boolean getIsStart() {
        return isStart;
    }

    public void setIsStart(boolean start) {
        isStart = start;
    }

    public WindowRight getWindowBot() {
        return windowRight;
    }

    public Integer getFutureType() {
        return futureType;
    }

    public void setFutureType(Integer futureType) {
        this.futureType = futureType;
    }

    public List<TetrisNode> getFutureNodes() {
        return futureNodes;
    }

    public void setFutureNodes(List<TetrisNode> futureNodes) {
        this.futureNodes = futureNodes;
    }

    public MusicPlay getMusicPlay() {
        return musicPlay;
    }
}
