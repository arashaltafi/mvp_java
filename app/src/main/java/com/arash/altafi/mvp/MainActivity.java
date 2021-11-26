package com.arash.altafi.mvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.arash.altafi.mvp.base.BaseActivity;
import com.arash.altafi.mvp.bookmark.BookMark_Fragment;
import com.arash.altafi.mvp.category.Category_Fragment;
import com.arash.altafi.mvp.home.Home_Fragment;
import com.arash.altafi.mvp.video.Video_Fragment;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

public class MainActivity extends BaseActivity {

    private View progressBar;
    private BottomNavigation bottomNavigation;
    private Home_Fragment home_fragment;
    private Category_Fragment category_fragment;
    private BookMark_Fragment bookMark_fragment;
    private Video_Fragment video_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindView();
    }

    private void FindView() {
        progressBar = findViewById(R.id.frameLayout_ProgressBar);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setDefaultItem(3);
        Typeface typeface = Typeface.createFromAsset(getAssets() , "vazir.ttf");
        bottomNavigation.setTypeface(typeface);
        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int i) {
                switch (i)
                {
                    case R.id.tab_home:
                        if (home_fragment == null)
                        {
                            home_fragment = new Home_Fragment();
                        }
                        replaceTransaction(home_fragment);
                        break;
                    case R.id.tab_category:
                        if (category_fragment == null)
                        {
                            category_fragment = new Category_Fragment();
                        }
                        replaceTransaction(category_fragment);
                        break;
                    case R.id.tab_bookmark:
                        if (bookMark_fragment == null)
                        {
                            bookMark_fragment = new BookMark_Fragment();
                        }
                        replaceTransaction(bookMark_fragment);
                        break;
                    case R.id.tab_videos:
                        if (video_fragment == null)
                        {
                            video_fragment = new Video_Fragment();
                        }
                        replaceTransaction(video_fragment);
                        break;
                }
            }
        });
    }

    private void replaceTransaction(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void setProgressBar(boolean isProgress) {
        if (isProgress)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
        else
        {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (bottomNavigation.getSelectedItem() == 3)
        {
            finish();
        }
        else
        {
            bottomNavigation.setSelectedItem(3);
        }
    }
}