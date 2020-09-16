package com.swarnimodi.itunessearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Song> song;

    public GridAdapter(Context c, List<Song> song) {
        context = c;
        this.song = song;
    }

    @Override
    public int getCount() {
        return song.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, null);
        }

        TextView Name = convertView.findViewById(R.id.name);
        TextView Artist = convertView.findViewById(R.id.artist);
        TextView Album = convertView.findViewById(R.id.album);

        Name.setText(song.get(position).getTrackName());
        Artist.setText(song.get(position).getArtistName());
        Album.setText(song.get(position).getCollectionName());

        return convertView;
    }
}
