package in.sarangal.spannabletextview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import in.sarangal.lib.spantastic.SpanModel;
import in.sarangal.lib.spantastic.Spantastic;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String sentence;
        List<String> strings;

        /* Just Show Underline under specific word */
        sentence = "Spannable with single word underline.";
        TextView tvOne = findViewById(R.id.tv_one);
        new Spantastic.SpantasticBuilder(this, tvOne, sentence)
                .setSpan("underline")
                .showUnderline(true)
                .apply();

        /* Show Underline and make Text BOLD for Multiple words */
        sentence = "Spannable with underline and bold for multiple words.";
        strings = new ArrayList<>();
        strings.add("spannable");
        strings.add("underline");
        strings.add("bold");
        TextView tvTwo = findViewById(R.id.tv_two);
        new Spantastic.SpantasticBuilder(this, tvTwo, sentence)
                .setSpanList(strings)
                .showUnderline(true)
                .setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
                .apply();

        /* Set Click on Multiple Words with Bold Style */
        sentence = "Spannable with click, color, underline and bold for multiple words.";
        strings = new ArrayList<>();
        strings.add("click");
        strings.add("color");
        strings.add("underline");
        strings.add("bold");
        TextView tvThree = findViewById(R.id.tv_three);
        new Spantastic.SpantasticBuilder(this, tvThree, sentence)
                .setSpanList(strings)
                .showUnderline(true)
                .setSpanColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
                .setClickCallback(new Spantastic.SpannableCallBack() {
                    @Override
                    public void onSpanClick(String spanString, Object... object) {
                        Toast.makeText(MainActivity.this, String.format(Locale.ENGLISH, "\"%s\" Word Clicked", spanString), Toast.LENGTH_SHORT).show();
                    }
                })
                .apply();

        /* Set TextSize */
        sentence = "Spantastic with custom text size for words.";
        TextView tvFour = findViewById(R.id.tv_four);
        new Spantastic.SpantasticBuilder(this, tvFour, sentence)
                .setSpan("text size")
                .setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, this.getResources().getDisplayMetrics()))
                .apply();

        /* Set Custom Spannables with unique styling */
        sentence = "Spantastic with custom styles for multiple words.";
        TextView tvFive = findViewById(R.id.tv_five);
        List<SpanModel> spanModelList = new ArrayList<>();
        spanModelList.add(new SpanModel("custom", ContextCompat.getColor(this, R.color.colorPrimary), "custom", true, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, this.getResources().getDisplayMetrics())));
        spanModelList.add(new SpanModel("styles", ContextCompat.getColor(this, R.color.colorPrimaryDark), "styles", false, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, this.getResources().getDisplayMetrics())));
        spanModelList.add(new SpanModel("for", ContextCompat.getColor(this, R.color.colorAccent), "for", true, Typeface.create(Typeface.SERIF, Typeface.ITALIC), TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, this.getResources().getDisplayMetrics())));
        spanModelList.add(new SpanModel("multiple", Color.parseColor("#FFFF5722"), "multiple", null, Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC), TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, this.getResources().getDisplayMetrics())));
        spanModelList.add(new SpanModel("words", Color.parseColor("#FF9C27B0"), "words", true, Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD), null));
        new Spantastic.SpantasticBuilder(this, tvFive, sentence)
                .setCustomSpanModel(spanModelList)
                .setClickCallback(new Spantastic.SpannableCallBack() {
                    @Override
                    public void onSpanClick(String spanString, Object... object) {
                        Toast.makeText(MainActivity.this, String.format(Locale.ENGLISH, "\"%s\" Word Clicked", spanString), Toast.LENGTH_SHORT).show();
                    }
                })
                .apply();

        /* Developed By Sarangal */
        sentence = "Developed with ❤ by Sarangal";
        TextView tvCredit = findViewById(R.id.tv_credit);
        List<SpanModel> creditModelList = new ArrayList<>();
        creditModelList.add(new SpanModel("❤", Color.parseColor("#FFFF2F2F"), "Love", null, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), null));
        creditModelList.add(new SpanModel("Sarangal", Color.parseColor("#FF00BCD4"), "Sarangal", null, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), null));
        new Spantastic.SpantasticBuilder(this, tvCredit, sentence)
                .setCustomSpanModel(creditModelList)
                .apply();

    }

}
