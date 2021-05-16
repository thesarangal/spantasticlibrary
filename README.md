# Spantastic Library
Advance Spannable Customization

Current Version: [![](https://jitpack.io/v/thesarangal/spantasticlibrary.svg)](https://jitpack.io/#thesarangal/spantasticlibrary)


### Features

- Custom Typeface
- Custom Color
- Click Callbacks with Key Value and Object
- Custom TextSize
and more.

### How to To get a Git project into your build

#### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

#### Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.thesarangal:spantasticlibrary:VERSION_CODE'
	}

Done! The first time you request a project JitPack checks out the code, builds it and serves the build artifacts (jar, aar).

### How to use Spantastic
#### I. Just Show Underline under specific word

	String sentence = "Spannable with single word underline.";
	TextView tvOne = findViewById(R.id.tv_one);
	new Spantastic.Builder(tvOne, sentence)
                    .setSpan("underline")
                    .showUnderline(true)
                    .apply();
		
#### II. Show Underline and make Text BOLD for Multiple words

	String sentence = "Spannable with underline and bold for multiple words.";
	List<String> strings = new ArrayList<>();
	strings.add("spannable");
	strings.add("underline");
	strings.add("bold");
	TextView tvTwo = findViewById(R.id.tv_two);
	new Spantastic.Builder(tvTwo, sentence)
                    .setSpanList(strings)
                    .showUnderline(true)
                    .setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
                    .apply();

#### III. Set Click on Multiple Words with Bold Style

	String sentence = "Spannable with click, color, underline and bold for multiple words.";
	List<String> strings = new ArrayList<>();
	strings.add("click");
	strings.add("color");
	strings.add("underline");
	strings.add("bold");
	TextView tvThree = findViewById(R.id.tv_three);
	new Spantastic.Builder(tvThree, sentence)
                    .setSpanList(strings)
                    .showUnderline(true)
                    .setSpanColor(ContextCompat.getColor(this, R.color.colorAccent))
                    .setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
                    .setClickCallback(new Spantastic.SpannableCallBack() {
                        @Override
                        public void onSpanClick(String spanString, Object... object) {
                            Toast.makeText(MainActivity.this, String.format(Locale.ENGLISH,
                                    "\"%s\" Word Clicked", spanString), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .apply();
		
#### IV. Change TextSize

	String sentence = "Spantastic with custom text size for words.";
        TextView tvFour = findViewById(R.id.tv_four);
        new Spantastic.Builder(tvFour, sentence)
                        .setSpan("text size")
                        .setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20,
                                this.getResources().getDisplayMetrics()))
                        .apply();
		
#### V. sentence = "Spantastic with custom styles for multiple words.";

	TextView tvFive = findViewById(R.id.tv_five);
	List<SpanModel> spanModelList = new ArrayList<>();
	spanModelList.add(new SpanModel("custom", ContextCompat.getColor(this, R.color.colorPrimary), "custom", true, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, this.getResources().getDisplayMetrics())));
	spanModelList.add(new SpanModel("styles", ContextCompat.getColor(this, R.color.colorPrimaryDark), "styles", false, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, this.getResources().getDisplayMetrics())));
	spanModelList.add(new SpanModel("for", ContextCompat.getColor(this, R.color.colorAccent), "for", true, Typeface.create(Typeface.SERIF, Typeface.ITALIC), TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, this.getResources().getDisplayMetrics())));
	spanModelList.add(new SpanModel("multiple", Color.parseColor("#FFFF5722"), "multiple", null, Typeface.create(Typeface.MONOSPACE, Typeface.BOLD_ITALIC), TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, this.getResources().getDisplayMetrics())));
	spanModelList.add(new SpanModel("words", Color.parseColor("#FF9C27B0"), "words", true, Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD), null));
	new Spantastic.Builder(tvFive, sentence)
                    .setCustomSpanModel(spanModelList)
                    .setClickCallback(new Spantastic.SpannableCallBack() {
                        @Override
                        public void onSpanClick(String spanString, Object... object) {
                            Toast.makeText(MainActivity.this,
                                    String.format(Locale.ENGLISH, "\"%s\" Word Clicked", spanString),
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .apply();

#### Changelog:

v2.1.0
^ Migrate to Kotlin
^ "SpantasticBuilder" class is deprecated, use "Builder" class.

#### Above Example's Output

![spantastic_library_custom_spannable_textview](https://user-images.githubusercontent.com/46309117/95648356-09f0b500-0af4-11eb-8f85-e719b3c340e9.png)

### Test Demo

Download DEMO APK: https://github.com/thesarangal/spantasticlibrary/raw/master/SpantasticDemo.apk



#### Developed with ❤ by Sarangal
