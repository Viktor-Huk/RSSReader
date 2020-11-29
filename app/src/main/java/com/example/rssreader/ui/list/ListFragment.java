package com.example.rssreader.ui.list;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rssreader.R;
import com.example.rssreader.databinding.ListFragmentBinding;
import com.example.rssreader.model.Article;
import com.google.android.material.snackbar.Snackbar;

public class ListFragment extends Fragment {

    private static final String TAG = ListFragment.class.getSimpleName();

    private ListViewModel listViewModel;
    private ListFragmentBinding binding;
    private NewsAdapter newsAdapter;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ListFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);

        initRecyclerView();
        initObserves();
        refreshData();

        listViewModel.getFreshArticles();
    }

    private void refreshData() {

        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            listViewModel.getFreshArticles();

            binding.swipeRefreshLayout.postDelayed(() -> {
                binding.swipeRefreshLayout.setRefreshing(false);
            }, 2000L);
        });
    }

    private void initObserves() {
        listViewModel.getArticles().observe(getViewLifecycleOwner(), articles -> {
            newsAdapter.submitList(articles);
            Log.i(TAG, "list articles size: " + newsAdapter.getCurrentList().size());
        });

        listViewModel.getErrorState().observe(getViewLifecycleOwner(), errorState -> {
            if (errorState) {
                showSnackBar();
            }
        });
    }

    private void showSnackBar() {
        Log.i(TAG, "Snack bar");

        Snackbar.make(
                binding.getRoot(),
                getString(R.string.connection_error),
                Snackbar.LENGTH_LONG
        ).show();
    }

    private void initRecyclerView() {
        newsAdapter = new NewsAdapter(article -> {
            openArticleInWebView(article);
        });

        RecyclerView recyclerView = binding.mainRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsAdapter);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        getContext(),
                        GridLayoutManager.VERTICAL
                )
        );
    }

    private void openArticleInWebView(Article article) {
        String link = article.getLink();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}