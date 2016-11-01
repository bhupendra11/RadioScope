package radioscope.bhupendrashekhawat.me.android.radioscope.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Bhupendra Shekhawat on 1/11/16.
 */

public class TalkshowStream {

    @SerializedName("success")
    private String mSuccess;

    @SerializedName("result")
    ArrayList<TStream> talkshowList;

    public ArrayList<TStream> getTalkshowList() {
        return talkshowList;
    }

    public 

    class TStream{

        public TStream(String url, String encoding, String callsign, String websiteurl, String timeleft, String timeplayed) {
            this.mUrl = url;
            this.mEncoding = encoding;
            this.mCallsign = callsign;
            this.mWebsiteurl = websiteurl;
            this.mTimeleft = timeleft;
            this.mTimeplayed = timeplayed;
        }

        public String getmUrl() {
            return mUrl;
        }

        @SerializedName("url")
        private String mUrl;

        @SerializedName("encoding")
        private String mEncoding;

        @SerializedName("callsign")
        private String mCallsign;

        @SerializedName("websiteurl")
        private String mWebsiteurl;

        @SerializedName("timeleft")
        private String mTimeleft;

        @SerializedName("timeplayed")
        private String mTimeplayed;


    }
}
