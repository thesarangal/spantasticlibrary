package in.sarangal.lib.spantastic;

import android.graphics.Typeface;

import androidx.annotation.NonNull;

public class SpanModel {

    private String mSpanString;
    private Integer mColorId;
    private String mCallbackKey;
    private Boolean showUnderline;
    private Typeface mTypeface;

    public Typeface getTypeface() {
        return mTypeface;
    }
    public String getSpanString() {
        return mSpanString;
    }

    public Integer getColorId() {
        return mColorId;
    }

    public String getCallbackKey() {
        return mCallbackKey;
    }

    public Boolean getShowUnderline() {
        return showUnderline;
    }

    public SpanModel(@NonNull String mSpanString, Integer mColorId, @NonNull String mCallbackKey, Boolean showUnderline, Typeface mTypeface) {
        this.mSpanString = mSpanString;
        this.mColorId = mColorId;
        this.mCallbackKey = mCallbackKey;
        this.showUnderline = showUnderline;
        this.mTypeface = mTypeface;
    }

}
