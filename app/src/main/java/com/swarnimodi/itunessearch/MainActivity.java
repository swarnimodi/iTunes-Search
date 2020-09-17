package com.swarnimodi.itunessearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private MainActivityViewModel mMainActivityViewModel;
    private GridAdapter adapter;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);
        searchBar = findViewById(R.id.search_bar);

        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.init();

        mMainActivityViewModel.getSongs().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                adapter.notifyDataSetChanged();
                gridView.smoothScrollToPosition(0);
            }
        });

        initGridView();
    }

    public void onClick(View v) {

        String searchTerm = searchBar.getText().toString();
        searchTerm = searchTerm.replace(" ", "+");
        //Call the searching method over here and pass the searchTerm
        mMainActivityViewModel.newSearch(MainActivity.this, searchTerm);
    }

    private void initGridView() {

        try {
            adapter = new GridAdapter(MainActivity.this, mMainActivityViewModel.getSongs().getValue());
        } catch (Exception e){
            adapter = new GridAdapter(MainActivity.this);
        }

        gridView.setAdapter(adapter);
    }
}