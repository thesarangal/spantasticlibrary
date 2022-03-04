package `in`.sarangal.lib.spantastic

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import java.util.*
import java.util.regex.Pattern

/**
 * Developed with Love by Sarangal
 */
class Spantastic private constructor(builder: Builder) {

    /*
    * Data Members
    * */
    private val textView: TextView = builder.textView
    private val fullString: String = builder.completeString
    private val colorID: Int? = builder.colorID
    private val spannableCallBack: SpannableCallBack? = builder.spannableCallBack
    private val spanModelList: List<SpanModel>? = builder.spanModelList
    private val typeface: Typeface? = builder.typeface
    private val textSize: Float? = builder.textSize
    private val showUnderline: Boolean = builder.showUnderline
    private val objectAny: Any? = builder.objectAny

    init {

        /* Set Styling to the TextView */
        setSpannable()
    }

    /**
     * Set Styling to the TextView
     * */
    private fun setSpannable() {

        if (fullString.isBlank()) {
            return
        }

        if (spanModelList.isNullOrEmpty()) {
            return
        }

        val spannableString = SpannableString(fullString)

        /* Iterate Span List */
        spanModelList.forEach { spanModel ->

            if (spanModel.spanString.isEmpty()) {
                return@forEach
            }

            var spanString = spanModel.spanString

            try {
                /* Check DOLLAR ($) Sign in String */
                if (spanString.contains("$")) {
                    spanString = spanString.replace("$", "\\$")
                }

                val pattern = Pattern.compile(spanString)
                val matcher = pattern.matcher(fullString)
                while (matcher.find()) {
                    val start = matcher.start()
                    val end = matcher.end()
                    val clickSpan: ClickableSpan = object : ClickableSpan() {
                        override fun onClick(view: View) {
                            spannableCallBack?.onSpanClick(
                                spanModel.callbackKey, objectAny
                            )
                        }

                        override fun updateDrawState(textPaint: TextPaint) {
                            super.updateDrawState(textPaint)

                            /* Set Span UnderLine */
                            textPaint.isUnderlineText = spanModel.showUnderline ?: showUnderline

                            /* Set Span Color */
                            try {
                                textPaint.color = spanModel.colorId ?: colorID
                                        ?: textView.currentTextColor
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }

                            /* Set Span Typeface for Styling */
                            (spanModel.typeface ?: typeface)?.let {
                                textPaint.typeface = it
                            }

                            /* Set Span TextSize */
                            (spanModel.textSize ?: textSize)?.let {
                                textPaint.textSize = it
                            }
                        }
                    }

                    /* Set Span on SpannableString */
                    spannableString.setSpan(clickSpan, start, end, 0)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        /* Set Spannable to TextView */
        textView.text = spannableString

        /* Support to Clickable */
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.highlightColor = Color.TRANSPARENT
    }

    /**
     * Interface for Spannable Click CallBack
     */
    interface SpannableCallBack {
        fun onSpanClick(spanString: String?, vararg `object`: Any?)
    }

    /**
     * Builder Class for Handling Spannable Operations
     *
     * Method Params:
     *
     * @param textView   TextView on which spannable text will be applied
     * @param completeString Complete string value from which span(s) will be found.
     */
    open class Builder(internal val textView: TextView, internal val completeString: String) {
        internal var colorID: Int? = null
        internal var spannableCallBack: SpannableCallBack? = null
        internal var spanModelList: List<SpanModel>? = null
        internal var typeface: Typeface? = null
        internal var showUnderline = false
        internal var textSize: Float? = null
        internal var objectAny: Any? = null

        /**
         * Method Params:
         *
         * @param singleString Single string which will be convert to spannable.
         */
        fun setSpan(singleString: String): Builder {
            val spanList: MutableList<String> = ArrayList()
            if (singleString.isNotEmpty()) {
                spanList.add(singleString)
            }
            return setSpanList(spanList)
        }

        /**
         * Method Params:
         *
         * @param spanList List of strings which will be convert to spannable.
         */
        fun setSpanList(spanList: List<String>): Builder {
            val spanModelList: MutableList<SpanModel> = ArrayList()

            /* Iterate Span List */
            spanList.forEach { spanString ->

                if (spanString.isEmpty()) {
                    return@forEach
                }

                spanModelList.add(SpanModel(spanString, callbackKey = spanString))
            }

            return setCustomSpanModel(spanModelList)
        }

        /**
         * Method Params:
         *
         * @param colorId The desired resource identifier, as generated by the aapt
         * tool. This integer encodes the package, type, and resource
         * entry. The value 0 is an invalid identifier.
         */
        fun setSpanColor(colorId: Int): Builder {
            colorID = colorId
            return this
        }

        /**
         * Method Params:
         *
         * @param showUnderline true: underline will be display under span text,
         */
        fun showUnderline(showUnderline: Boolean): Builder {
            this.showUnderline = showUnderline
            return this
        }

        /**
         * Method Params:
         *
         * @param spannableCallBack Interface reference to get click callback of specific
         */
        fun setClickCallback(spannableCallBack: SpannableCallBack): Builder {
            this.spannableCallBack = spannableCallBack
            return this
        }

        /**
         * Method Params:
         *
         * @param typeface Typeface for custom styling.
         */
        fun setTypeface(typeface: Typeface): Builder {
            this.typeface = typeface
            return this
        }

        /**
         * Method Params:
         *
         * @param textSize PIXEL value in Float type for TextSize.
         */
        fun setTextSize(textSize: Float): Builder {
            this.textSize = textSize
            return this
        }

        /**
         * Method Params:
         *
         * @param spanModelList List of custom model of strings with custom
         * styling params which will be convert to spannable.
         */
        fun setCustomSpanModel(spanModelList: List<SpanModel>): Builder {
            this.spanModelList = spanModelList
            return this
        }

        /**
         * Method Params:
         *
         * @param object May object any object which will be return on span click via
         * spannableCallBack interface.
         */
        fun setObject(`object`: Any): Builder {
            objectAny = `object`
            return this
        }

        /**
         * Implement Spantastic Constructor method.
         */
        fun apply() {
            Spantastic(this)
        }
    }
}