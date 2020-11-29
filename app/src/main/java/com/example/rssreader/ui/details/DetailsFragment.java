package com.example.rssreader.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rssreader.databinding.DetailsFragmentBinding;

public class DetailsFragment extends Fragment {

    public static final String LINK = "ling";
    private static final String TAG = DetailsFragment.class.getSimpleName();

    private DetailsFragmentBinding binding;
    private DetailsViewModel mViewModel;

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
        mViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        binding.webView.getSettings().setJavaScriptEnabled(true);

        String link = getArguments().getString(LINK);
        binding.webView.loadUrl(link);

        binding.include.arrowBack.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "arrow back", Toast.LENGTH_LONG).show();
        });
    }
}