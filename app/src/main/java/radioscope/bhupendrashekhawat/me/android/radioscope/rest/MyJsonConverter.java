/*
package radioscope.bhupendrashekhawat.me.android.radioscope.rest;

;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

*/
/**
 * Created by Bhupendra Shekhawat on 9/10/16.
 *//*


public class MyJsonConverter extends Converter.Factory {

    public static MyJsonConverter create() {
        Log.d("MyJsonConverter " , "Create  called");
        return create(new Gson());
    }

    public static MyJsonConverter create(Gson gson) {
        return new MyJsonConverter(gson);
    }

    private final Gson gson;

    private MyJsonConverter(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonResponseBodyConverter<>(gson, adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }


    final class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        private final Charset UTF_8 = Charset.forName("UTF-8");

        private final Gson gson;
        private final TypeAdapter<T> adapter;

        GsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
            JsonWriter jsonWriter = gson.newJsonWriter(writer);
            adapter.write(jsonWriter, value);
            jsonWriter.close();
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        }
    }

    final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private final Gson gson;
        private final TypeAdapter<T> adapter;

        GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            String dirty = value.string();

            String cleanMid = dirty.substring(2);
            String clean = cleanMid.substring(0,-1);

            Log.d("MyJsonConvertor", "Clean json is " +clean);

            */
/*String clean = dirty.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" +
                    "<string xmlns=\"http://tempuri.org/\">","").replace("</string>","");*//*

            try {
                return adapter.fromJson(clean);
            } finally {
                value.close();
            }
        }
    }


}*/
