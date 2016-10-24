
package radioscope.bhupendrashekhawat.me.android.radioscope.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


/**
 * Created by Bhupendra Shekhawat on 4/10/16.
 */



public class Track  {

    @SerializedName("callsign")
    private String mCallSign;

    @SerializedName("genre")
    private String mGenre;

    @SerializedName("artist")
    private String mArtist;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("songstamp")
    private String mSongstamp;

    @SerializedName("seconds_remaining")
    private String mSecondsRemaining;

    @SerializedName("station_id")
    private String mStaionId;

    private ArrayList<Track> mTrackList;

    public Track(String mStaionId, String mGenre, String mCallSign, String mArtist, String mSongstamp, String mTitle, String mSecondsRemaining) {
        this.mStaionId = mStaionId;
        this.mGenre = mGenre;
        this.mCallSign = mCallSign;
        this.mArtist = mArtist;
        this.mSongstamp = mSongstamp;
        this.mTitle = mTitle;
        this.mSecondsRemaining = mSecondsRemaining;
    }

    public String getmCallSign() {
        return mCallSign;
    }

    public void setmCallSign(String mCallSign) {
        this.mCallSign = mCallSign;
    }

    public String getmGenre() {
        return mGenre;
    }

    public void setmGenre(String mGenre) {
        this.mGenre = mGenre;
    }

    public String getmArtist() {
        return mArtist;
    }

    public void setmArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    public String getmSongstamp() {
        return mSongstamp;
    }

    public void setmSongstamp(String mSongstamp) {
        this.mSongstamp = mSongstamp;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmSecondsRemaining() {
        return mSecondsRemaining;
    }

    public void setmSecondsRemaining(String mSecondsRemaining) {
        this.mSecondsRemaining = mSecondsRemaining;
    }

    public String getmStaionId() {
        return mStaionId;
    }

    public void setmStaionId(String mStaionId) {
        this.mStaionId = mStaionId;
    }



    /*protected Track(Parcel in) {
        mCallSign = in.readString();
        mGenre = in.readString();
        mArtist = in.readString();
        mTitle = in.readString();
        mSongstamp = in.readString();
        mSecondsRemaining = in.readString();
        mStaionId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCallSign);
        dest.writeString(mGenre);
        dest.writeString(mArtist);
        dest.writeString(mTitle);
        dest.writeString(mSongstamp);
        dest.writeString(mSecondsRemaining);
        dest.writeString(mStaionId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };*/
}
