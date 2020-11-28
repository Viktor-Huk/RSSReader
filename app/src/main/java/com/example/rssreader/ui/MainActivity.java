package com.example.rssreader.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rssreader.R;
import com.example.rssreader.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initRecyclerView();
        initObserves();
        refreshData();

        mainViewModel.getFreshArticles();
    }

    private void refreshData() {

        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            mainViewModel.getFreshArticles();

            binding.swipeRefreshLayout.postDelayed(() -> {
                binding.swipeRefreshLayout.setRefreshing(false);
            }, 2000L);
        });
    }

    private void initObserves() {
        mainViewModel.getArticles().observe(this, articles -> {
            newsAdapter.submitList(articles);
            Log.i(TAG, "list articles size: " + newsAdapter.getCurrentList().size());
        });

        mainViewModel.getErrorState().observe(this, errorState -> {
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
        newsAdapter = new NewsAdapter();
        RecyclerView recyclerView = binding.mainRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsAdapter);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        this,
                        LinearLayoutManager.VERTICAL
                )
        );
    }
}