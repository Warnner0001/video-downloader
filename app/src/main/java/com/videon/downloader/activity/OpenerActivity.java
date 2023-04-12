package com.videon.downloader.activity;

import static com.videon.downloader.include.Constant.MEDIATION_INIT;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.videon.downloader.BuildConfig;
import com.videon.downloader.R;
import com.videon.downloader.databinding.ActivityOpenerBinding;
import com.videon.downloader.include.Function;
import com.videon.downloader.interfaces.OnClick;

import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class OpenerActivity extends AppCompatActivity {

    private Function function;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        changeStatusBarColor();
        ActivityOpenerBinding binding = ActivityOpenerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        OnClick onClick = (position, title, type, id) -> {

        };
        function = new Function(OpenerActivity.this, onClick);
        function.startMediation();
        binding.appVersion.setText(getResources().getString(R.string.app_version) + BuildConfig.VERSION_NAME);
        splashScreen();
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void splashScreen() {
        if (function.isNetworkAvailable()) {
            int splashTimeOut = 1000;
            new Handler().postDelayed(this::openSplash, splashTimeOut);
        } else {
            function.alertMessage(getString(R.string.internet_connection));
        }
    }

    public void call_Activity() {
        startActivity(new Intent(OpenerActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void changeStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void openSplash() {
        int apiVersion = android.os.Build.VERSION.SDK_INT;
        if (apiVersion >= Build.VERSION_CODES.TIRAMISU) {
            Dexter.withActivity(this)
                    .withPermissions(
                            Manifest.permission.READ_MEDIA_IMAGES,
                            Manifest.permission.READ_MEDIA_VIDEO,
                            Manifest.permission.READ_MEDIA_AUDIO
                    )
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if (report.areAllPermissionsGranted()) {
                                if (!MEDIATION_INIT) {
                                    call_Activity();
                                    finish();
                                } else {
                                    Function.showProgressDialog(OpenerActivity.this);
                                    new Handler().postDelayed(() -> {
                                        Function.hideProgressDialog(OpenerActivity.this);
                                        if (MEDIATION_INIT) {
                                            call_Activity();
                                            finish();
                                        } else {
                                            function.alertMessage(getString(R.string.internet_connection));
                                        }
                                    }, 2000);
                                }
                            } else {
                                Toast.makeText(OpenerActivity.this, getString(R.string.accept_permission), Toast.LENGTH_SHORT).show();
                            }
                            if (report.isAnyPermissionPermanentlyDenied()) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.fromParts("package", getPackageName(), null));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
        } else {
            Dexter.withActivity(this)
                    .withPermissions(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if (report.areAllPermissionsGranted()) {
                                if (!MEDIATION_INIT) {
                                    call_Activity();
                                    finish();
                                } else {
                                    Function.showProgressDialog(OpenerActivity.this);
                                    new Handler().postDelayed(() -> {
                                        Function.hideProgressDialog(OpenerActivity.this);
                                        if (MEDIATION_INIT) {
                                            call_Activity();
                                            finish();
                                        } else {
                                            function.alertMessage(getString(R.string.internet_connection));
                                        }
                                    }, 2000);
                                }
                            } else {
                                Toast.makeText(OpenerActivity.this, getString(R.string.accept_permission), Toast.LENGTH_SHORT).show();
                            }
                            if (report.isAnyPermissionPermanentlyDenied()) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.fromParts("package", getPackageName(), null));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
        }
    }

}


