package radioscope.bhupendrashekhawat.me.android.radioscope.rest.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONStringer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhupendra Shekhawat on 8/10/16.
 */

public class CustomJsonDeserializer implements JsonDeserializer<ArrayList<Track> > {

    @Override
    public ArrayList<Track> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.d("Test/Deserializer", "Using a custom deserializer for Login WS");

        Gson gson = new Gson();

        //String clean = JSON
       /* ArrayList<Track>  loginResponse = gson.fromJson(json, ArrayList<Track> .class);

        if (loginResponse.getStatus() == 1) {
            // The full response as a json object
            final JsonObject jsonObject = json.getAsJsonObject();
            // The response attribute in the JSON received
            final JsonElement jsonElement = jsonObject.get("response");

            // The response will be parsed because the status was 1
            UserAuthenticated userAuthenticated = gson.fromJson(jsonElement, UserAuthenticated.class);
            loginResponse.setResponse(userAuthenticated);
        }*/

        return null;
    }
}
