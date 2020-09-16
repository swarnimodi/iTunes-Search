package com.swarnimodi.itunessearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.GridView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    private MainActivityViewModel mMainActivityViewModel;
    GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.grid_view);

        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.init();

        mMainActivityViewModel.getSongs().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                adapter.notifyDataSetChanged();
            }
        });

        initGridView();
    }

    private void initGridView() {
        adapter = new GridAdapter(MainActivity.this, mMainActivityViewModel.getSongs().getValue());
        gridView.setAdapter(adapter);
    }
}