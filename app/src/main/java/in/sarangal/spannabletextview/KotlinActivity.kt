package `in`.sarangal.spannabletextview

import `in`.sarangal.lib.spantastic.SpanModel
import `in`.sarangal.lib.spantastic.Spantastic
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.*
import kotlin.collections.ArrayList

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Just Show Underline under specific word */
        var sentence = "Spannable with single word underline."
        val tvOne = findViewById<TextView>(R.id.tv_one)
        Spantastic.Builder(tvOne, sentence)
            .setSpan("underline")
            .showUnderline(true)
            .apply()

        /* Show Underline and make Text BOLD for Multiple words */
        sentence = "Spannable with underline and bold for multiple words."
        var strings: ArrayList<String> = ArrayList()
        strings.add("spannable")
        strings.add("underline")
        strings.add("bold")
        val tvTwo = findViewById<TextView>(R.id.tv_two)
        Spantastic.Builder(tvTwo, sentence)
            .setSpanList(strings)
            .showUnderline(true)
            .setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
            .apply()

        /* Set Click on Multiple Words with Bold Style */
        sentence =
            "Spannable with click, color, underline and bold for multiple words."
        strings = ArrayList()
        strings.add("click")
        strings.add("color")
        strings.add("underline")
        strings.add("bold")
        val tvThree = findViewById<TextView>(R.id.tv_three)
        Spantastic.Builder(tvThree, sentence)
            .setSpanList(strings)
            .showUnderline(true)
            .setSpanColor(ContextCompat.getColor(this, R.color.colorAccent))
            .setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
            .setClickCallback(object : Spantastic.SpannableCallBack {
                override fun onSpanClick(spanString: String?, vararg `object`: Any?) {
                    Toast.makeText(
                        this@KotlinActivity, String.format(
                            Locale.ENGLISH,
                            "\"%s\" Word Clicked", spanString
                        ), Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })
            .apply()

        /* Set TextSize */
        sentence = "Spantastic with custom text size for words."
        val tvFour = findViewById<TextView>(R.id.tv_four)
        Spantastic.Builder(tvFour, sentence)
            .setSpan("text size")
            .setTextSize(
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP, 20f,
                    this.resources.displayMetrics
                )
            )
            .apply()

        /* Set Custom Spannables with unique styling */
        sentence =
            "Spantastic with custom styles for multiple words."
        val tvFive = findViewById<TextView>(R.id.tv_five)
        val spanModelList: MutableList<SpanModel> = ArrayList()
        spanModelList.add(
            SpanModel(
                "custom",
                ContextCompat.getColor(this, R.color.colorPrimary), "custom",
                true, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL),
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP, 15f,
                    this.resources.displayMetrics
                )
            )
        )
        spanModelList.add(
            SpanModel(
                "styles",
                ContextCompat.getColor(this, R.color.colorPrimaryDark), "styles",
                false, Typeface.create(Typeface.DEFAULT, Typeface.BOLD),
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP, 10f,
                    this.resources.displayMetrics
                )
            )
        )
        spanModelList.add(
            SpanModel(
                "for",
                ContextCompat.getColor(this, R.color.colorAccent), "for",
                true, Typeface.create(Typeface.SERIF, Typeface.ITALIC),
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP, 18f,
                    this.resources.displayMetrics
                )
            )
        )
        spanModelList.add(
            SpanModel(
                "multiple",
                Color.parseColor("#FFFF5722"), "multiple", null,
                Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC),
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP, 13f,
                    this.resources.displayMetrics
                )
            )
        )
        spanModelList.add(
            SpanModel(
                "words",
                Color.parseColor("#FF9C27B0"), "words", true,
                Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD), null
            )
        )
        Spantastic.Builder(tvFive, sentence)
            .setCustomSpanModel(spanModelList)
            .setClickCallback(object : Spantastic.SpannableCallBack {
                override fun onSpanClick(spanString: String?, vararg `object`: Any?) {
                    Toast.makeText(
                        this@KotlinActivity, String.format(
                            Locale.ENGLISH, "\"%s\" Word Clicked", spanString
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
            .apply()

        /* Developed By Sarangal */
        sentence = "Developed with ❤ by Sarangal"
        val tvCredit = findViewById<TextView>(R.id.tv_credit)
        val creditModelList: MutableList<SpanModel> = ArrayList<SpanModel>()
        creditModelList.add(
            SpanModel(
                "❤", Color.parseColor("#FFFF2F2F"),
                "Love", null,
                Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), null
            )
        )
        creditModelList.add(
            SpanModel(
                "Sarangal",
                Color.parseColor("#FF00BCD4"), "Sarangal", null,
                Typeface.create(Typeface.DEFAULT, Typeface.BOLD), null
            )
        )
        Spantastic.Builder(tvCredit, sentence)
            .setCustomSpanModel(creditModelList)
            .apply()
    }
}