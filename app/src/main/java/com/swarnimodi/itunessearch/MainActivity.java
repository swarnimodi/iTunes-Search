package com.swarnimodi.itunessearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private MainActivityViewModel mMainActivityViewModel;
    private GridAdapter adapter;
    private EditText searchBar;
    private Button searchButton;
    private String searchTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);
        searchBar = findViewById(R.id.search_bar);
        searchButton = findViewById(R.id.search_button);

        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.init();

        initGridView();

        mMainActivityViewModel.getSongs().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                adapter.notifyDataSetChanged();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTerm = searchBar.getText().toString();
                //Call the searching method over here and pass the searchTerm
                mMainActivityViewModel.newSearch(MainActivity.this, searchTerm);
                gridView.smoothScrollToPosition(0);
            }
        });
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