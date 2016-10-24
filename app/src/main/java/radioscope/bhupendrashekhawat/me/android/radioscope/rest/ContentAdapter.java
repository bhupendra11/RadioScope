/*
package radioscope.bhupendrashekhawat.me.android.radioscope.rest;

import android.util.Log;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import radioscope.bhupendrashekhawat.me.android.radioscope.rest.model.AllTracks;

*/
/**
 * Created by Bhupendra Shekhawat on 9/10/16.
 *//*


public class ContentAdapter extends TypeAdapter<AllTracks> {

    @Override
    public void write(JsonWriter out, AllTracks value) throws IOException {
        // TODO: Writer implementation
    }

    @Override
    public AllTracks read(JsonReader in) throws IOException {

        Log.d("ContentAdapter", "Data is \n\n\n" + in.toString());
        String isNull  = (in.peek() != JsonToken.NULL)?"true":"false";
        Log.d("ContentAdapter", isNull);

        if(in.peek() != JsonToken.NULL) {
            Log.d("ContentAdapter", "Json not null");
            return fromJson(in.nextString());
        } else {
            Log.d("ContentAdapter", "Json is null");
            in.nextNull();
            return null;
        }
    }

}
*/
