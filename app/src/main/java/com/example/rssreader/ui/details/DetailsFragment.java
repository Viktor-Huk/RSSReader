package com.example.rssreader.ui.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rssreader.databinding.DetailsFragmentBinding;

public class DetailsFragment extends Fragment {

    public static final String LINK = "ling";
    private static final String TAG = DetailsFragment.class.getSimpleName();

    private DetailsFragmentBinding binding;
    private WebView webView;

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DetailsFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        webView = binding.webView;

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());

        String link = getArguments().getString(LINK);
        webView.loadUrl(link);

        initOnClickListeners();
        initSwipe();
    }

    private void initOnClickListeners() {

        binding.include.arrowBack.setOnClickListener(view -> {
            if (webView.canGoBack()) {
                webView.goBack();
            }
        });

        binding.include.arrowForward.setOnClickListener(view -> {
            if (webView.canGoForward()) {
                webView.goForward();
            }
        });

        binding.include.openInBrowser.setOnClickListener(view -> {
            String link = webView.getUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));

            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        });

        binding.include.refresh.setOnClickListener(view -> {
            webView.reload();
        });

        binding.include.stop.setOnClickListener(view -> {
            webView.stopLoading();
        });

    }

    private void initSwipe() {

        binding.detailsSwipeRefreshLayout.setOnRefreshListener(() -> {
            webView.reload();

            binding.detailsSwipeRefreshLayout.postDelayed(() -> {
                binding.detailsSwipeRefreshLayout.setRefreshing(false);
            }, 2000L);
        });
    }
}