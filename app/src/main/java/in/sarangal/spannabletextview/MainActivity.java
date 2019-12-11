package in.sarangal.spannabletextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.sarangal.lib.spantastic.Spantastic;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String sentence = "Simple spannable with .";

        List<String> strings = new ArrayList<>();
        strings.add("spannable");
        strings.add("underline");

        TextView tvOne = findViewById(R.id.tv_one);
        new Spantastic.SpantasticBuilder(this, tvOne, sentence)
                .setSpanList(strings)
                .showUnderline(true)
                .apply();

        TextView tvTwo = findViewById(R.id.tv_two);
        new Spantastic.SpantasticBuilder(this, tvTwo, sentence)
                .setSpanList(strings)
                .showUnderline(true)
                .apply();

        TextView tvThree = findViewById(R.id.tv_three);

        TextView tvFour = findViewById(R.id.tv_four);

        TextView tvFive = findViewById(R.id.tv_five);

        TextView tvSix = findViewById(R.id.tv_six);

    }
}
