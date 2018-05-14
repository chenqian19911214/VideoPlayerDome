package com.example.chenq.videoplayerdome;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NestedScrollView post_detail_nested_scroll;
    private LandLayoutVideo detailPlayer;
    private Button inputUrl;
    private RelativeLayout activity_detail_player;
    private boolean isPlay;
    private boolean isPause;
    private boolean cache;
    private String url, name;
    private Intent intent;
    private ImageView imageView;
    private OrientationUtils orientationUtils;
    private GSYVideoOptionBuilder gsyVideoOptionBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = getIntent();
        url = intent.getStringExtra("url");
        name = intent.getStringExtra("name");
        ActionBar actionBar = getSupportActionBar();
        if (!TextUtils.isEmpty(name)) {
            actionBar.setTitle(name);
            Log.i("","");
       }
        if (TextUtils.isEmpty(url)) {
            url = "http://110.53.223.51/staticResource/hls_vipZone/A3661/A3661.m3u8";
        }
        initView();
        resolveNormalVideoUI();
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        gsyVideoOptionBuilder = new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setIsTouchWiget(true)
                .setRotateViewAuto(true)
                .setLockLand(false)
                .setShowFullAnimation(true)
                .setNeedLockFull(true)
                .setSeekRatio(1)
                .setUrl(url)
                .setCacheWithPlay(cache)
                .setVideoTitle(name)
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    //开始加载，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onStartPrepared(String url, Object... objects) {
                        DialogView.show(MainActivity.this, true);

                        Log.i("chenqian", "开始加载");
                    }

                    //加载成功，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        //开始播放了才能旋转和全屏
                        Log.i("chenqian", "加载成功");
                        DialogView.dismiss(MainActivity.this);
                        orientationUtils.setEnable(true);
                        isPlay = true;
                    }

                    //点击了开始按键播放，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onClickStartIcon(String url, Object... objects) {
                        Log.i("chenqian", "点击了开始按键播放");
                    }

                    //点击了错误状态下的开始按键，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onClickStartError(String url, Object... objects) {
                        Log.i("chenqian", "点击了错误状态下的开始按键");

                    }

                    //点击了播放状态下的开始按键--->停止，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onClickStop(String url, Object... objects) {
                        Log.i("chenqian", "点击了播放状态下的开始按键--->停止");

                    }

                    //点击了全屏播放状态下的开始按键--->停止，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onClickStopFullscreen(String url, Object... objects) {
                        Log.i("chenqian", "点击了全屏播放状态下的开始按键--->停止");

                    }

                    //点击了暂停状态下的开始按键--->播放，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onClickResume(String url, Object... objects) {
                        Log.i("chenqian", "点击了暂停状态下的开始按键--->播放");

                    }

                    //点击了全屏暂停状态下的开始按键--->播放，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onClickResumeFullscreen(String url, Object... objects) {
                        Log.i("chenqian", "点击了全屏暂停状态下的开始按键--->播放");

                    }

                    //点击了空白弹出seekbar，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onClickSeekbar(String url, Object... objects) {
                        Log.i("chenqian", "点击了空白弹出seekbar");

                    }

                    //点击了全屏的seekbar，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onClickSeekbarFullscreen(String url, Object... objects) {
                        Log.i("chenqian", "点击了全屏的seekbar");

                    }

                    //播放完了，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onAutoComplete(String url, Object... objects) {
                        Log.i("chenqian", "//播放完了");

                    }

                    //进去全屏，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        Log.i("chenqian", "进去全屏");

                    }

                    //退出全屏，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo();
                        }
                        Log.i("chenqian", "退出全屏");

                    }

                    //进入小窗口，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onQuitSmallWidget(String url, Object... objects) {
                        Log.i("chenqian", "进入小窗口");

                    }

                    //退出小窗口，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onEnterSmallWidget(String url, Object... objects) {
                        Log.i("chenqian", "退出小窗口");

                    }

                    //触摸调整声音，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onTouchScreenSeekVolume(String url, Object... objects) {
                        Log.i("chenqian", "触摸调整声音");

                    }

                    //触摸调整进度，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onTouchScreenSeekPosition(String url, Object... objects) {
                        Log.i("chenqian", "触摸调整进度");

                    }

                    //触摸调整亮度，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onTouchScreenSeekLight(String url, Object... objects) {
                        Log.i("chenqian", "触摸调整亮度");

                    }

                    //播放错误，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onPlayError(String url, Object... objects) {
                        Log.i("chenqian", "播放错误");

                    }

                    //点击了空白区域开始播放，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onClickStartThumb(String url, Object... objects) {
                        Log.i("chenqian", "点击了空白区域开始播放");

                    }

                    //点击了播放中的空白区域，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onClickBlank(String url, Object... objects) {
                        Log.i("chenqian", "点击了播放中的空白区域");

                    }

                    //点击了全屏播放中的空白区域，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                    @Override
                    public void onClickBlankFullscreen(String url, Object... objects) {
                        Log.i("chenqian", "点击了全屏播放中的空白区域");

                    }
                });
        gsyVideoOptionBuilder.build(detailPlayer);

        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(MainActivity.this, true, true);
            }
        });

        detailPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });
        detailPlayer.postDelayed(new Runnable() {
            //  @Override
            public void run() {
                ImageView testImage = findViewById(R.id.test_image_view);
                Glide.with(MainActivity.this.getApplicationContext())
                        .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525708180755&di=078af5aedf4b44268425be46bf25e407&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F203fb80e7bec54e78494e3a3bb389b504fc26a17.jpg")
                        .into(testImage);
            }
        }, 5000);
        playVideo();
    }

    @Override
    public void onBackPressed() {
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        getCurPlay().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        getCurPlay().onVideoResume();
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPlay) {
            getCurPlay().release();
        }
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            detailPlayer.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
        }
    }

    private void resolveNormalVideoUI() {
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.GONE);
    }

    private void initView() {
        post_detail_nested_scroll = findViewById(R.id.post_detail_nested_scroll);
        detailPlayer = findViewById(R.id.detail_player);
        inputUrl = (Button) findViewById(R.id.inputUrl);
        activity_detail_player = (RelativeLayout) findViewById(R.id.activity_detail_player);
        //  url = "https://res.exexm.com/cw_145225549855002";

        //增加封面
        imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.xxx1);
        inputUrl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inputUrl:
                //  showInputDialog();
                break;
        }
    }

    private void playVideo() {
        detailPlayer.release();
        gsyVideoOptionBuilder.setUrl(url)
                .setCacheWithPlay(cache)
                .setVideoTitle(name)
                .build(detailPlayer);
        gsyVideoOptionBuilder.build(detailPlayer);
        detailPlayer.postDelayed(new Runnable() {
            @Override
            public void run() {
                detailPlayer.startPlayLogic();
            }
        }, 1000);
    }

    private GSYVideoPlayer getCurPlay() {
        if (detailPlayer.getFullWindowPlayer() != null) {
            return detailPlayer.getFullWindowPlayer();
        }
        return detailPlayer;
    }
}
