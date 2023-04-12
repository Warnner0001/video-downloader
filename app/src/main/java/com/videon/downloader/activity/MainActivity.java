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
import com.videon.downloader.databinding.ActivityMainBinding;
import com.videon.downloader.fragment.AboutFragment;
import com.videon.downloader.fragment.DownloaderFragment;
import com.videon.downloader.fragment.MoreFragment;
import com.videon.downloader.fragment.RateFragment;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import me.ibrahimsn.lib.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity {
    private Function function;
    @SuppressLint("StaticFieldLeak")
    public static ActivityMainBinding binding;
    boolean doubleBackToExitPressedOnce = false;
    private NavController navController;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        OnClick onClick = (position, title, type, id) -> {

        };
        function = new Function(MainActivity.this, onClick);
        function.startMediation();
        navController = Navigation.findNavController(this, R.id.fragmentContainer);
        function.setStatusBarGradiant(MainActivity.this);
        setSupportActionBar(binding.toolbar.toolbarMain);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.menu_home, R.id.menu_about, R.id.menu_rate, R.id.menu_more)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        binding.toolbar.toolbarMain.setTitle(getString(R.string.app_name));
        setupBottomMenu();
        Function.showProgressDialog(MainActivity.this);

        binding.bottomNav.setOnItemSelectedListener((OnItemSelectedListener) i -> {
            switch (i) {
                case 0:
                    setFragment(new DownloaderFragment());
                    binding.toolbar.toolbarMain.setTitle(getString(R.string.app_name));
                    break;
                case 1:
                    setFragment(new AboutFragment());
                    break;
                case 2:
                    setFragment(new RateFragment());
                    break;
                case 3:
                    setFragment(new MoreFragment());
                    break;
            }
            return false;
        });

        setFragment(new DownloaderFragment());
        function.mediationBanner();
    }

    private void setupBottomMenu() {
        PopupMenu popupMenu = new PopupMenu(this, null);
        popupMenu.inflate(R.menu.bottom_menu);
        Menu menu = popupMenu.getMenu();
        binding.bottomNav.setupWithNavController(menu, navController);
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, fragment, getResources().getString(R.string.menu_home)).commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull android.view.MenuItem item) {
        if (item.getItemId() == R.id.menu_more) {
            Uri uri = Uri.parse(yourVideoWebsite);
            startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, uri), "Choose Browser"));
        } else if (item.getItemId() == R.id.menu_help) {
            function.shareApp();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
        }
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            String title = getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getBackStackEntryCount() - 1).getTag();
            if (title != null) {
                binding.toolbar.toolbarMain.setTitle(title);
                getWindow().clearFlags(1024);
            }
            super.onBackPressed();
        } else {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getString(R.string.click_back), Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        }
    }

}