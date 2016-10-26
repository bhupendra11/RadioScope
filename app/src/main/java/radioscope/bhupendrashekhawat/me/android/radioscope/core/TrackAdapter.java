package radioscope.bhupendrashekhawat.me.android.radioscope.core;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import radioscope.bhupendrashekhawat.me.android.radioscope.R;
import radioscope.bhupendrashekhawat.me.android.radioscope.rest.model.Track;

/**
 * Created by Bhupendra Shekhawat on 26/10/16.
 */

public class TrackAdapter extends BaseAdapter{

    private List<Track> mTracksList;
    private LayoutInflater mInflater;

    public TrackAdapter(Activity context, List<Track> tracks){
        super();
        mTracksList = tracks;
        mInflater = LayoutInflater.from(context);



    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Track track = (Track) getItem(position);
        ViewHolder viewHolder ;

        Log.e("DEBUG", "Track name : "+track.getTitle());

        if (convertView == null) {
            Log.e("DEBUG", "Inside convertView = null");
            convertView = mInflater.inflate(
                    R.layout.track_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.trackTitleView = (TextView) convertView.findViewById(R.id.track_title_view);
            viewHolder.trackArtistView = (TextView) convertView.findViewById(R.id.track_artist_view);
            viewHolder.trackAlbumView = (TextView) convertView.findViewById(R.id.track_album_view);

            viewHolder.albumArtView = (ImageView) convertView.findViewById(R.id.album_art_small);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.albumArtView.setAdjustViewBounds(true);
        viewHolder.albumArtView.setPadding(0,0,0,0);

        viewHolder.trackTitleView.setText(track.getTitle());
        viewHolder.trackArtistView.setText(track.getArtist());
        viewHolder.trackAlbumView.setText(track.getCallSign());


        return convertView;
    }

    @Override
    public int getCount() {
        Log.d("TrackAdapter","Item count = "+mTracksList.size() );
        return mTracksList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTracksList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Implement viewHolder inorder to avoid expensive findViewVyId calls by every list item

    static class ViewHolder{
        ImageView albumArtView;
        TextView trackTitleView;
        TextView trackAlbumView;
        TextView trackArtistView;


    }
}

