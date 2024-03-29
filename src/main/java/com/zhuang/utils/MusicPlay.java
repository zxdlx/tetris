package com.zhuang.utils;

import com.zhuang.constant.MusicPath;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * 音乐工具类
 *
 * @author zxd
 * @date 2024/3/30 2:03
 */
public class MusicPlay {
    //背景音乐对象
    private final Clip backgroundMusicClip;

    //点击音效对象
    private final Clip clickClip;

    //旋转音效对象
    private final Clip revolveClip;

    //下坠音效对象
    private final Clip sinkClip;

    //目前播放到第几个音频
    private Integer index;

    //初始化所有
    public MusicPlay() {
        this.index = 0;
        try {
            backgroundMusicClip = AudioSystem.getClip();
            clickClip = AudioSystem.getClip();
            revolveClip = AudioSystem.getClip();
            sinkClip = AudioSystem.getClip();
            //加载点击音效
            AudioInputStream clickSteam = AudioSystem.getAudioInputStream(
                    new File(Objects.requireNonNull(TetrisUtil.class.getClassLoader().getResource("audio/sound/click.wav")).toURI()));
            clickClip.open(clickSteam);
            //加载旋转音效
            AudioInputStream revolveSteam = AudioSystem.getAudioInputStream(
                    new File(Objects.requireNonNull(TetrisUtil.class.getClassLoader().getResource("audio/sound/revolve.wav")).toURI()));
            revolveClip.open(revolveSteam);
            //加载下坠音效
            AudioInputStream sinkSteam = AudioSystem.getAudioInputStream(
                    new File(Objects.requireNonNull(TetrisUtil.class.getClassLoader().getResource("audio/sound/sink.wav")).toURI()));
            sinkClip.open(sinkSteam);
        } catch (LineUnavailableException | IOException | URISyntaxException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
        playBackgroundMusic();
        //设置监听，一个音频播放完后接着下一个
        backgroundMusicClip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) {
                    playBackgroundMusic();
                }
            }
        });
    }

    /**
     * 播放背景音乐
     *
     * @author zxd
     * @date 2024/3/30 2:27
     */
    private void playBackgroundMusic() {
        if (backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
        }
        if (backgroundMusicClip.isOpen()) {
            backgroundMusicClip.close();
        }
        try {
            //加载背景音乐
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(Objects.requireNonNull(
                            TetrisUtil.class.getClassLoader().getResource(MusicPath.backGroundMusicPrefix + MusicPath.backGroundMusic[index])).toURI()
                    ));
            backgroundMusicClip.open(audioInputStream);
        } catch (LineUnavailableException | IOException | URISyntaxException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
        backgroundMusicClip.start();
        //把音频指向下一首，如果是最后一首就回到开头
        if (index == MusicPath.backGroundMusic.length - 1) {
            index = 0;
        } else {
            index++;
        }
    }

    /**
      * 点击音效
      *
      * @author zxd
      * @date 2024/3/30 3:02
      */
    public void clickSound(){
        playClickSound(clickClip);
    }

    /**
      * 旋转音效
      *
      * @author zxd
      * @date 2024/3/30 3:02
      */
    public void revolveSound(){
        playClickSound(revolveClip);
    }

    /**
      * 下坠音效
      *
      * @author zxd
      * @date 2024/3/30 3:03
      */
    public void sinkSound(){
        playClickSound(sinkClip);
    }

    /**
      * 播放音效
      *
      * @author zxd
      * @date 2024/3/30 2:56
      */
    private void playClickSound(Clip clip){
        clip.setFramePosition(0);
        clip.start();
    }
}
