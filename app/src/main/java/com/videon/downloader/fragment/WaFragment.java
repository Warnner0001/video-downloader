package com.videon.downloader.fragment;

import static com.videon.downloader.include.Constant.DownloadName;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.videon.downloader.R;
import com.videon.downloader.activity.MainActivity;
import com.videon.downloader.adapter.WAppStatusAdapter;
import com.videon.downloader.databinding.FragmentWaBinding;
import com.videon.downloader.include.Function;
import com.videon.downloader.include.LocalPref;
import com.videon.downloader.interfaces.OnClick;
import com.videon.downloader.list.DataModel;

import java.util.ArrayList;
import java.util.Objects;

public class WaFragment extends Fragment {
    private Function function;
    private Activity activity;
    private FragmentWaBinding binding;
    private ArrayList<DataModel> statusImageList;
    private WAppStatusAdapter mAdapter;
    private loadDataAsync async;
    int REQUEST_ACTION_OPEN_DOCUMENT_TREE = 101;

    public WaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        activity = requireActivity();
        statusImageList = new ArrayList<>();
        OnClick onClick = (position, title, type, id) -> {

        };
        function = new Function(activity, onClick);
        function.startMediation();
        MainActivity.binding.toolbar.toolbarMain.setTitle(getString(R.string.whatsapp_status));
        binding.allow.cardPermission.setVisibility(View.VISIBLE);
        binding.rvDownloader.setHasFixedSize(true);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rvDownloader.setLayoutManager(gridLayoutManager);
        binding.allow.btnAllow.setOnClickListener(view1 -> {
            PackageManager pm = activity.getPackageManager();
            if (function.isPackageInstalled("com.whatsapp", pm)) {
                StorageManager sm = (StorageManager) requireActivity().getSystemService(Context.STORAGE_SERVICE);
                String statusDir = function.getWaFolder();
                Intent intent = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    intent = sm.getPrimaryStorageVolume().createOpenDocumentTreeIntent();
                    Uri uri = intent.getParcelableExtra("android.provider.extra.INITIAL_URI");
                    String scheme = uri.toString();
                    scheme = scheme.replace("/root/", "/document/");
                    scheme += "%3A" + statusDir;
                    uri = Uri.parse(scheme);
                    intent.putExtra("android.provider.extra.INITIAL_URI", uri);
                } else {
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                    intent.putExtra("android.provider.extra.INITIAL_URI", Uri.parse("content://com.android.externalstorage.documents/document/primary%3A" + statusDir));
                }
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                startActivityForResult(intent, REQUEST_ACTION_OPEN_DOCUMENT_TREE);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ACTION_OPEN_DOCUMENT_TREE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            Log.e("onActivityResult: ", "" + data.getData());
            try {
                requireContext().getContentResolver()
                        .takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } catch (Exception e) {
                e.printStackTrace();
            }

            LocalPref.setWATree(getActivity(), uri.toString());

            populateGrid();
        }

    }

    @SuppressLint("StaticFieldLeak")
    public class loadDataAsync extends AsyncTask<Void, Void, Void> {
        DocumentFile[] allFiles;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Function.showProgressDialog(activity);
            binding.rvDownloader.setVisibility(View.GONE);
            binding.allow.cardPermission.setVisibility(View.GONE);
            binding.noData.conNoInternet.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            allFiles = null;
            statusImageList = new ArrayList<>();
            allFiles = getFromSdcard();
            for (DocumentFile allFile : Objects.requireNonNull(allFiles)) {
                if (!allFile.getUri().toString().contains(".nomedia")) {
                    statusImageList.add(new DataModel(allFile.getUri().toString(), allFile.getName()));
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new Handler().postDelayed(() -> {
                if (getActivity() != null) {
                    mAdapter = new WAppStatusAdapter(getActivity(), statusImageList, true);
                    binding.rvDownloader.setAdapter(mAdapter);
                    Function.hideProgressDialog(activity);
                    binding.rvDownloader.setVisibility(View.VISIBLE);
                }
            }, 300);

            if (statusImageList == null || statusImageList.size() == 0) {
                binding.noData.conNoInternet.setVisibility(View.VISIBLE);
            } else {
                binding.noData.conNoInternet.setVisibility(View.GONE);
            }
        }
    }

    private DocumentFile[] getFromSdcard() {
        String treeUri = LocalPref.getWATree(getActivity());
        DocumentFile fromTreeUri = DocumentFile.fromTreeUri(requireContext().getApplicationContext(), Uri.parse(treeUri));
        if (fromTreeUri != null && fromTreeUri.exists() && fromTreeUri.isDirectory()
                && fromTreeUri.canRead() && fromTreeUri.canWrite()) {

            return fromTreeUri.listFiles();
        } else {
            return null;
        }
    }

    public void populateGrid() {
        async = new loadDataAsync();
        async.execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!LocalPref.getWATree(getActivity()).equals("")) {
            populateGrid();
        }
    }

    @Override
    public void onDestroy() {
        if (async != null) {
            async.cancel(true);
        }
        MainActivity.binding.toolbar.toolbarMain.setTitle(activity.getString(R.string.app_name));
        super.onDestroy();
    }
}