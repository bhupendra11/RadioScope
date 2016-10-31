package radioscope.bhupendrashekhawat.me.android.radioscope.core;

/**
 * Created by Bhupendra Shekhawat on 31/10/16.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import radioscope.bhupendrashekhawat.me.android.radioscope.R;
import radioscope.bhupendrashekhawat.me.android.radioscope.rest.model.Talkshow;


/**
 * Created by Bhupendra Shekhawat on 26/10/16.
 */

public class TalkshowAdapter extends BaseAdapter {

    private List<Talkshow> mTalkshowList;
    private LayoutInflater mInflater;

    public TalkshowAdapter(Activity context, List<Talkshow> talkshows){
        super();
        mTalkshowList = talkshows;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Talkshow talkshow = (Talkshow) getItem(position);
        ViewHolder viewHolder ;

        //Log.e("DEBUG", "Track name : "+track.getTitle());

        if (convertView == null) {
            //  Log.e("DEBUG", "Inside convertView = null");
            convertView = mInflater.inflate(
                    R.layout.talkshow_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.trackTitleView = (TextView) convertView.findViewById(R.id.track_title_view);
            viewHolder.albumArtView = (ImageView) convertView.findViewById(R.id.album_art_small);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.albumArtView.setAdjustViewBounds(true);
        viewHolder.albumArtView.setPadding(0,0,0,0);

        viewHolder.trackTitleView.setText(talkshow.getmShowname());
        //viewHolder.trackAlbumView.setText(talkshow.getmImageurl());


        return convertView;
    }

    @Override
    public int getCount() {
        // Log.d("TrackAdapter","Item count = "+mTracksList.size() );
        return mTalkshowList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTalkshowList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Implement viewHolder inorder to avoid expensive findViewVyId calls by every list item

    static class ViewHolder{
        ImageView albumArtView;
        TextView trackTitleView;



    }
}

