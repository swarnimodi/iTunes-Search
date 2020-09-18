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
            StringBuilder value = new StringBuilder();
            for(int i=0; i<songList.getSongList().size(); i++) {
                value.append(songList.getSongList().get(i).getTrackName()).append("`");
                value.append(songList.getSongList().get(i).getArtistName()).append("`");
                value.append(songList.getSongList().get(i).getCollectionName()).append("`");
            }
            value = new StringBuilder(value.substring(value.length() - 1));
            return value.toString();
        }
    }
}
