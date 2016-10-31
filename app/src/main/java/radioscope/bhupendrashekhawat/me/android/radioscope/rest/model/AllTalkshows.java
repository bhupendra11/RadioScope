package radioscope.bhupendrashekhawat.me.android.radioscope.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Bhupendra Shekhawat on 31/10/16.
 */

public class AllTalkshows {

    @SerializedName("stations")
    ArrayList<Talkshow> talkshowList;

    public ArrayList<Talkshow> getTalkshowList() {
        return talkshowList;
    }

}
