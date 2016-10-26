package radioscope.bhupendrashekhawat.me.android.radioscope.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Bhupendra Shekhawat on 24/10/16.
 */

public class AllTracks {

    @SerializedName("success")
    private String mSuccess;

    @SerializedName("result")
    ArrayList<Track> trackList;

    public ArrayList<Track> getTrackList() {
        return trackList;
    }


}
