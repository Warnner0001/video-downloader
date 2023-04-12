package com.videon.downloader.activity;

import static com.videon.downloader.include.Constant.yourVideoWebsite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.videon.downloader.R;
import com.videon.downloader.databinding.ActivityGalleryBinding;
import com.videon.downloader.databinding.ActivityMainBinding;
import com.videon.downloader.fragment.AboutFragment;
import com.videon.downloader.fragment.DownloaderFragment;
import com.videon.downloader.fragment.MoreFragment;
import com.videon.downloader.fragment.RateFragment;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import me.ibrahimsn.lib.OnItemSelectedListener;

public class GalleryActivity extends AppCompatActivity {
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
        function = new Function(GalleryActivity.this, onClick);
        function.setStatusBarGradiant(GalleryActivity.this);
        function.mediationBanner();
        setSupportActionBar(binding.toolbar.toolbarMain);
        binding.toolbar.toolbarMain.setTitle(getString(R.string.video_gal));

        Function.showProgressDialog(GalleryActivity.this);
    }

}