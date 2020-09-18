package com.swarnimodi.itunessearch;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converter {

    @TypeConverter
    public SongList storedStringToSongs(String value) {

        if(value == null) {
            return null;
        }
        else {
            List<String> values = Arrays.asList(value.split("`"));
            ArrayList<Song> s = new ArrayList<>(values.size()/3);
            int i = 0;
            while(i<values.size()) {
                Song song = new Song();
                song.setTrackName(values.get(i++));
                song.setArtistName(values.get(i++));
                song.setCollectionName(values.get(i++));
                s.add(song);
            }
            return new SongList(s);
        }
    }

    @TypeConverter
    public String songsToStoredString(SongList songList) {
        if(songList == null) {
            return null;
        }
        else {
            String value = "";
            for(int i=0; i<songList.getSongList().size(); i++) {
                value += songList.getSongList().get(i).getTrackName() + "`";
                value += songList.getSongList().get(i).getArtistName() + "`";
                value += songList.getSongList().get(i).getCollectionName() + "`";
            }
            value = value.substring(value.length()-1);
            return value;
        }
    }
}
