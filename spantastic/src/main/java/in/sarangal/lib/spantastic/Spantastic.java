package in.sarangal.lib.spantastic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Developed with Love by Sarangal
 */

public class Spantastic {

    private Context mContext;
    private TextView mTextView;
    private String mFullString;
    private Integer mColorID;
    private SpannableCallBack mSpannableCallBack;
    private List<SpanModel> mSpanModelList;
    private Typeface mTypeface;
    private Float mTextSize;
    private boolean showUnderline;
    private Object mObject;

    /**
     * Method Params:
     *
     * @param spantasticBuilder SpantasticBuilder Object with multiple params
     *                          for Custom Spannable Styling.
     */

    private Spantastic(SpantasticBuilder spantasticBuilder) {
        mContext = spantasticBuilder.mContext;
        mTextView = spantasticBuilder.mTextView;
        mFullString = spantasticBuilder.mFullString;
        mColorID = spantasticBuilder.mColorID;
        mSpannableCallBack = spantasticBuilder.mSpannableCallBack;
        showUnderline = spantasticBuilder.showUnderline;
        mSpanModelList = spantasticBuilder.mSpanModelList;
        mTypeface = spantasticBuilder.mTypeface;
        mTextSize = spantasticBuilder.mTextSize;
        mObject = spantasticBuilder.mObject;

        /* Set Styling to the TextView */
        setSpannable();
    }

    /* Set Styling to the TextView */
    private void setSpannable() {
        SpannableString spString = new SpannableString(mFullString);
        if (mContext != null && mTextView != null && mFullString != null && !mFullString.isEmpty()
                && mSpanModelList != null && !mSpanModelList.isEmpty()) {
            for (final SpanModel spanModel : mSpanModelList) {
                try {
                    if (spanModel.getSpanString() != null && !spanModel.getSpanString().isEmpty()) {
                        String spanString = spanModel.getSpanString();

                        /* Check DOLLAR ($) Sign in String */
                        if (spanString.contains("$")) {
                            spanString = spanString.replace("$", "\\$");
                        }

                        Pattern pattern = Pattern.compile(spanString);
                        Matcher matcher = pattern.matcher(mFullString);
                        while (matcher.find()) {
                            int start = matcher.start();
                            int end = matcher.end();
                            final String finalSpanString = spanString;
                            ClickableSpan clickSpan = new ClickableSpan() {
                                @Override
                                public void onClick(@NonNull View view) {
                                    if (mSpannableCallBack != null) {

                                        /* Callback with Interface for ACTION on span clicks */
                                        mSpannableCallBack.onSpanClick(
                                                spanModel.getCallbackKey() != null
                                                        ? spanModel.getCallbackKey()
                                                        : finalSpanString, mObject);
                                    }
                                }

                                @Override
                                public void updateDrawState(@NonNull TextPaint textPaint) {
                                    super.updateDrawState(textPaint);

                                    /* Set Span UnderLine */
                                    textPaint.setUnderlineText(spanModel.getShowUnderline() != null
                                            ? spanModel.getShowUnderline() : showUnderline);

                                    /* Set Span Color */
                                    try {
                                        if (spanModel.getColorId() != null || mColorID != null)
                                            textPaint.setColor(spanModel.getColorId() != null
                                                    ? spanModel.getColorId() : mColorID);
                                        else
                                            textPaint.setColor(mTextView.getCurrentTextColor());
                                    } catch (Exception ignored) {
                                    }

                                    /* Set Span Typeface for Styling */
                                    if (spanModel.getTypeface() != null || mTypeface != null) {
                                        textPaint.setTypeface(spanModel.getTypeface() != null
                                                ? spanModel.getTypeface() : mTypeface);
                                    }

                                    /* Set Span TextSize */
                                    if (spanModel.getTextSize() != null || mTextSize != null) {
                                        textPaint.setTextSize(spanModel.getTextSize() != null
                                                ? spanModel.getTextSize() : mTextSize);
                                    }
                                }
                            };

                            /* Set Span on SpannableString */
                            spString.setSpan(clickSpan, start, end, 0);
                        }
                    }
                } catch (Exception ignored) {
                }
            }

            /* Set Spannable to TextView */
            mTextView.setText(spString);

            /* Support to Clickable */
            mTextView.setMovementMethod(LinkMovementMethod.getInstance());

            mTextView.setHighlightColor(Color.TRANSPARENT);
        }
    }

    /**
     * Interface for Spannable Click CallBack
     */
    public interface SpannableCallBack {
        void onSpanClick(String spanString, Object... object);
    }

    /**
     * Builder Class for Handling Spannable Operations
     */
    public static class SpantasticBuilder {

        private Context mContext;
        private TextView mTextView;
        private String mFullString;
        private Integer mColorID;
        private SpannableCallBack mSpannableCallBack;
        private List<SpanModel> mSpanModelList;
        private Typeface mTypeface;
        private boolean showUnderline;
        private Float mTextSize;
        private Object mObject;

        /**
         * Method Params:
         *
         * @param mContext    Instance or Reference
         * @param mTextView   TextView on which spannable text will be applied
         * @param mFullString Complete string value from which span(s) will be found.
         */
        public SpantasticBuilder(@NonNull Context mContext, @NonNull TextView mTextView,
                                 @NonNull String mFullString) {
            this.mContext = mContext;
            this.mTextView = mTextView;
            this.mFullString = mFullString;
        }

        /**
         * Method Params:
         *
         * @param singleString Single string which will be convert to spannable.
         */
        public SpantasticBuilder setSpan(String singleString) {
            List<String> spanList = new ArrayList<>();
            if (singleString != null && !singleString.isEmpty())
                spanList.add(singleString);
            return setSpanList(spanList);
        }

        /**
         * Method Params:
         *
         * @param spanList List of strings which will be convert to spannable.
         */
        public SpantasticBuilder setSpanList(List<String> spanList) {
            List<SpanModel> spanModelList = new ArrayList<>();
            if (spanList != null && !spanList.isEmpty())
                for (String spanString : spanList)
                    if (spanString != null && !spanString.isEmpty())
                        spanModelList.add(new SpanModel(spanString, null, spanString,
                                null, null, null));
            return setCustomSpanModel(spanModelList);
        }

        /**
         * Method Params:
         *
         * @param colorId The desired resource identifier, as generated by the aapt
         *                tool. This integer encodes the package, type, and resource
         *                entry. The value 0 is an invalid identifier.
         */
        public SpantasticBuilder setSpanColor(int colorId) {
            mColorID = colorId;
            return this;
        }

        /**
         * Method Params:
         *
         * @param showUnderline true: underline will be display under span text,
         */
        public SpantasticBuilder showUnderline(boolean showUnderline) {
            this.showUnderline = showUnderline;
            return this;
        }

        /**
         * Method Params:
         *
         * @param spannableCallBack Interface reference to get click callback of specific
         */
        public SpantasticBuilder setClickCallback(@NonNull SpannableCallBack spannableCallBack) {
            mSpannableCallBack = spannableCallBack;
            return this;
        }

        /**
         * Method Params:
         *
         * @param typeface Typeface for custom styling.
         */
        public SpantasticBuilder setTypeface(@NonNull Typeface typeface) {
            mTypeface = typeface;
            return this;
        }

        /**
         * Method Params:
         *
         * @param textSize PIXEL value in Float type for TextSize.
         */
        public SpantasticBuilder setTextSize(float textSize) {
            mTextSize = textSize;
            return this;
        }

        /**
         * Method Params:
         *
         * @param spanModelList List of custom model of strings with custom
         *                      styling params which will be convert to spannable.
         */
        public SpantasticBuilder setCustomSpanModel(List<SpanModel> spanModelList) {
            if (spanModelList != null) {
                mSpanModelList = spanModelList;
            } else {
                mSpanModelList = new ArrayList<>();
            }

            return this;
        }

        /**
         * Method Params:
         *
         * @param object May object any object which will be return on span click via
         *               spannableCallBack interface.
         */
        public SpantasticBuilder setObject(Object object) {
            mObject = object;
            return this;
        }

        /**
         * Implement Spantastic Constructor method.
         */
        public void apply() {
            new Spantastic(this);
        }
    }
}
