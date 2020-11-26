package com.example.rssreader.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rssreader.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

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

        mainViewModel.getArticles().observe(this, articles -> {
            newsAdapter.submitList(articles);
        });

        mainViewModel.getFreshArticles();
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