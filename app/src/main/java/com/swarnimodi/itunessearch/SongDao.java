package com.swarnimodi.itunessearch;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface SongDao {

    @Insert(onConflict = REPLACE)
    void insert(SongData songData);

    @Query("DELETE FROM table_name")
    void reset();

    @Query("SELECT * FROM table_name WHERE search_term=:search_term")
    SongData getSongs(String search_term);
}
