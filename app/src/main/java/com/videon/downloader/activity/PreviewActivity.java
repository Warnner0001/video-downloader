package com.videon.downloader.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.videon.downloader.R;
import com.videon.downloader.adapter.FullPreviewAdapter;
import com.videon.downloader.databinding.ActivityGalleryBinding;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;
import com.videon.downloader.list.DataModel;

import java.util.ArrayList;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class PreviewActivity extends AppCompatActivity {
    private Function function;

    public static ActivityGalleryBinding binding;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        OnClick onClick = (position, title, type, id) -> {

        };
        function = new Function(PreviewActivity.this, onClick);
        function.setStatusBarGradiant(PreviewActivity.this);
        function.mediationBanner();
        setSupportActionBar(binding.toolbar.toolbarMain);
        binding.toolbar.toolbarMain.setTitle(getString(R.string.preview_items));

        ArrayList<DataModel> imageList = getIntent().getParcelableArrayListExtra("images");
        int position = getIntent().getIntExtra("position", 0);

        FullPreviewAdapter fullPreviewAdapter = new FullPreviewAdapter(PreviewActivity.this, imageList);
        binding.viewpager.setAdapter(fullPreviewAdapter);
        binding.viewpager.setCurrentItem(position);
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                function.onDownClick(1, "", "", "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}