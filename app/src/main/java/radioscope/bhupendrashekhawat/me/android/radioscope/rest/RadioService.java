package radioscope.bhupendrashekhawat.me.android.radioscope.rest;


import radioscope.bhupendrashekhawat.me.android.radioscope.rest.model.AllTalkshows;
import radioscope.bhupendrashekhawat.me.android.radioscope.rest.model.AllTracks;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bhupendra Shekhawat on 3/10/16.
 */

public class RadioService {
    public interface DARfmApi{



        //getting all currently playing tracks on radio

        @GET("/playlist.php")
        Call<AllTracks> getTracks(
                @Query("q") String q,
                @Query("callback") String json,
                @Query("partner_token") String ApiKey

        );

        //getting talkshows around the world

        @GET("/uberguide.php")
        Call<AllTalkshows> getTalkshows(
                @Query("q") String q,
                @Query("callback") String json,
                @Query("partner_token") String ApiKey

        );
    }
}
