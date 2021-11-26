package com.arash.altafi.mvp.NewsDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arash.altafi.mvp.R;
import com.arash.altafi.mvp.Utility.Custom_ImageView;
import com.arash.altafi.mvp.data.AppDataBase;
import com.arash.altafi.mvp.data.AppDataBase_Impl;
import com.arash.altafi.mvp.data.News;
import com.arash.altafi.mvp.data.Repository;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.jzvd.jzvideo.JZVideoA;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class NewsDetails_Activity extends AppCompatActivity {

    private News news;
    Custom_ImageView custom_imageView;
    Toolbar toolbar;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private int height = 254;
    private int weight = 400;

    private JzvdStd jzvdStd;
    private FrameLayout frameLayout;

    private SimpleExoPlayer player;
    private PlayerView playerView;
    private FrameLayout frameLayout_exo;

    private TextView txt_date;
    private ImageView img_bookmark;
    private HtmlTextView txt_content;

    private com.arash.altafi.mvp.data.DataSource dataSource;

    private AppDataBase appDataBase;
    private List<News> getTblBookMark = new ArrayList<>();
    private News info_News;
    private int idRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        dataSource = new Repository(this);

        FindView();
        Listener();
    }

    private void FindView()
    {
        collapsingToolbarLayout = findViewById(R.id.collaps_toolbar);
        news = getIntent().getParcelableExtra("details");
        custom_imageView = findViewById(R.id.img_news_details);
        toolbar = findViewById(R.id.toolbar_news_details);
        setSupportActionBar(toolbar);
        // برای قرار دادن اتوماتیک دکمه بک در تولبار
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        img_bookmark = findViewById(R.id.img_bookmark);
        txt_content = findViewById(R.id.txt_content_news);
        txt_date = findViewById(R.id.txt_date_news);

        appDataBase = AppDataBase.getInstance(this);
        dataSource = appDataBase.getLocalDataSource();
        getTblBookMark = dataSource.getAllBookMark_News();

    }

    private void Listener()
    {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setTitle(news.getTitle());

        collapsingToolbarLayout.setExpandedTitleTextColor(ContextCompat.getColorStateList(this,android.R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColorStateList(this,android.R.color.white));
        collapsingToolbarLayout.setTitle(news.getTitle());

        if (news.isVideoShow1())
        {
            jzvdStd();
//            exo_player();
        }
        else
        {
            custom_imageView.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext()).load(news.getImage()).into(custom_imageView);
        }

        txt_date.setText(news.getDate());
        String content = news.getDescription().replace("upload" , "https://novindevelopers.ir/mvp/upload");
        txt_content.setHtml(content , new HtmlHttpImageGetter(txt_content , null , true));


        for (int i = 0; i < getTblBookMark.size() ; i++) {
            info_News = getTblBookMark.get(i);
            if (news.getId() == info_News.getId())
            {
                idRoom = info_News.getId();
            }
        }

        if (idRoom == news.getId())
        {
            img_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24);
        }
        else
        {
            img_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
        }

        img_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (news.isBookMark())
//                {
//                    img_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
//                    news.setBookMark(!news.isBookMark());
//                    dataSource.deleteBookMark(news);
//                }
//                else
//                {
//                    news.setBookMark(!news.isBookMark());
//                    dataSource.bookmark(news);
//                    img_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24);
//                }

                if (idRoom == news.getId())
                {
                    img_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
                    news.setBookMark(!news.isBookMark());
                    dataSource.deleteBookMark(news);
                }
                else
                {
                    if (news.isBookMark())
                    {
                        img_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
                        news.setBookMark(!news.isBookMark());
                        dataSource.deleteBookMark(news);
                    }
                    else
                    {
                        news.setBookMark(!news.isBookMark());
                        dataSource.bookmark(news);
                        img_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24);
                    }
                }

            }
        });

//        if (news.isBookMark())
//        {
//            img_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24);
//        }
//        else
//        {
//            img_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
//        }

    }

    private void jzvdStd()
    {
        jzvdStd = findViewById(R.id.jz_Video);
        frameLayout = findViewById(R.id.frame_videos);
        frameLayout.setVisibility(View.VISIBLE);

        String v = "https://dl.geniusboys.ir/dark/web/part3.mp4";
        jzvdStd.setUp(v , news.getTitle() , Jzvd.SCREEN_NORMAL);
        Glide.with(getApplicationContext()).load(news.getImage()).into(jzvdStd.posterImageView);
        frameLayout.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams params = frameLayout.getLayoutParams();
                params.height = frameLayout.getWidth() * height / weight;
                frameLayout.setLayoutParams(params);
            }
        });
        jzvdStd.fullscreenButton.setVisibility(View.GONE);
        jzvdStd.batteryLevel.setVisibility(View.GONE);
        jzvdStd.tinyBackImageView.setVisibility(View.GONE);
        jzvdStd.backButton.setVisibility(View.GONE);
        jzvdStd.videoCurrentTime.setVisibility(View.GONE);
        jzvdStd.titleTextView.setVisibility(View.GONE);
    }

    private void exo_player()
    {
        playerView = findViewById(R.id.player_View);
        frameLayout_exo = findViewById(R.id.frame_videos_exo);
        frameLayout_exo.setVisibility(View.VISIBLE);
        String video = "https://dl.geniusboys.ir/dark/web/part3.mp4";

        frameLayout_exo.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams params = frameLayout_exo.getLayoutParams();
                params.height = frameLayout_exo.getWidth() * height / weight;
                frameLayout_exo.setLayoutParams(params);
            }
        });

        // Create a data source factory.
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory();
        // Create a progressive media source pointing to a stream uri.
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(video));
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