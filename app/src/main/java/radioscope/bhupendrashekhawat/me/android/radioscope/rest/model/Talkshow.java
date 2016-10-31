package radioscope.bhupendrashekhawat.me.android.radioscope.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhupendra Shekhawat on 31/10/16.
 */

public class Talkshow {



    @SerializedName("showgenre")
    private String mShowGenre;

    @SerializedName("showid")
    private String mShowid;

    @SerializedName("showname")
    private String mShowname;

    public Talkshow(String showGenre, String showid, String showname, String imageurl) {
        this.mShowGenre = showGenre;
        this.mShowid = showid;
        this.mShowname = showname;
        this.mImageurl = imageurl;
    }

    @SerializedName("imageurl")
    private String mImageurl;



    public String getmShowGenre() {
        return mShowGenre;
    }

    public void setmShowGenre(String mShowGenre) {
        this.mShowGenre = mShowGenre;
    }

    public String getmShowid() {
        return mShowid;
    }

    public void setmShowid(String mShowid) {
        this.mShowid = mShowid;
    }

    public String getmShowname() {
        return mShowname;
    }

    public void setmShowname(String mShowname) {
        this.mShowname = mShowname;
    }

    public String getmImageurl() {
        return mImageurl;
    }

    public void setmImageurl(String mImageurl) {
        this.mImageurl = mImageurl;
    }
}
