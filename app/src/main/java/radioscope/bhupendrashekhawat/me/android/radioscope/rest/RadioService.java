package radioscope.bhupendrashekhawat.me.android.radioscope.rest;

import java.util.List;


import radioscope.bhupendrashekhawat.me.android.radioscope.rest.model.AllTracks;
import radioscope.bhupendrashekhawat.me.android.radioscope.rest.model.Track;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bhupendra Shekhawat on 3/10/16.
 */

public class RadioService {
    public interface DARfmApi{




        @GET("/playlist.php")
        Call<AllTracks> getTracks(
                @Query("q") String q,
                @Query("callback") String questionMark,
                @Query("partner_token") String ApiKey

        );

    }
}
