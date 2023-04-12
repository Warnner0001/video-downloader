package com.videon.downloader.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

import com.videon.downloader.R;
import com.videon.downloader.databinding.ActivityPreviewVideoBinding;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class VideoPlayerActivity extends AppCompatActivity {

    public ActivityPreviewVideoBinding binding;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityPreviewVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        OnClick onClick = (position, title, type, id) -> {

        };
        Function function = new Function(VideoPlayerActivity.this, onClick);
        function.setStatusBarGradiant(VideoPlayerActivity.this);
        function.mediationBanner();
        setSupportActionBar(binding.toolbar.toolbarMain);
        binding.toolbar.toolbarMain.setTitle(getString(R.string.video_play));

        String videoPath = getIntent().getStringExtra("videoPath");
        binding.videoPlayer.setVideoPath(videoPath);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(binding.videoPlayer);
        binding.videoPlayer.setMediaController(mediaController);
        binding.videoPlayer.start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}