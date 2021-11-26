package com.arash.altafi.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class play_video extends AppCompatActivity {

    String video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        video = getIntent().getStringExtra("video");

        exo_player(video);
//        jzvdStd(video);
    }

    private void jzvdStd(String video)
    {
            JzvdStd jzvdStd;
            FrameLayout frameLayout_jzvd;

            jzvdStd = findViewById(R.id.jz_Video);
            frameLayout_jzvd = findViewById(R.id.frame_jzvd);
            frameLayout_jzvd.setVisibility(View.VISIBLE);

//            String video = "https://dl.geniusboys.ir/dark/web/part3.mp4";
            jzvdStd.setUp(video , "فیلم اول" , Jzvd.SCREEN_NORMAL);
            Glide.with(getApplicationContext()).load(R.drawable.arash).into(jzvdStd.posterImageView);

            jzvdStd.fullscreenButton.setVisibility(View.GONE);
            jzvdStd.batteryLevel.setVisibility(View.GONE);
            jzvdStd.tinyBackImageView.setVisibility(View.GONE);
            jzvdStd.backButton.setVisibility(View.GONE);
            jzvdStd.videoCurrentTime.setVisibility(View.GONE);
            jzvdStd.titleTextView.setVisibility(View.GONE);
    }

    private void exo_player(String video)
    {
        SimpleExoPlayer player;
        PlayerView playerView;
        FrameLayout frameLayout_exo;

        playerView = findViewById(R.id.player_View);
        frameLayout_exo = findViewById(R.id.frame_exo);
        frameLayout_exo.setVisibility(View.VISIBLE);

//        String video = "https://dl.geniusboys.ir/dark/web/part3.mp4";
        // Create a data source factory.
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory();
        // Create a progressive media source pointing to a stream uri.
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(video));
        // Create a player instance.
        player = new SimpleExoPlayer.Builder(this).build();
        // Set the media source to be played.
        player.setMediaSource(mediaSource);
        // Prepare the player.
        player.prepare();
        player.setPlayWhenReady(true);
        playerView.setPlayer(player);
    }

}