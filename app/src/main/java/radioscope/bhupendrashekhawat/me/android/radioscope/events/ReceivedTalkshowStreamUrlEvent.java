package radioscope.bhupendrashekhawat.me.android.radioscope.events;

/**
 * Created by Bhupendra Shekhawat on 1/11/16.
 */

public class ReceivedTalkshowStreamUrlEvent {


    public String getUrl() {
        return url;
    }

    public final String url;

        public ReceivedTalkshowStreamUrlEvent(String url) {
            this.url = url;
        }


}
