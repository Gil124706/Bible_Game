package com.example.bible_game;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Questions extends AppCompatActivity  {
    private int currentQuestionIndex = 0;
    public List<Question> questions;
     private int score1 =0;
    JSONObject fromServer;
    JSONObject jsonRecieved;
    private void showQuestion(int questionIndex) {
        // Get the question object from the list
        Question question = questions.get(questionIndex);

        // Update the question text view
        TextView textViewQuestion = findViewById(R.id.text_view_question);
        textViewQuestion.setText(question.getQuestionText());

        // Update the options radio buttons
        RadioButton radioButtonOptionA = findViewById(R.id.radio_button_option_a);
        radioButtonOptionA.setText(question.getOptionA());

        RadioButton radioButtonOptionB = findViewById(R.id.radio_button_option_b);
        radioButtonOptionB.setText(question.getOptionB());

        RadioButton radioButtonOptionC = findViewById(R.id.radio_button_option_c);
        radioButtonOptionC.setText(question.getOptionC());
        RadioButton radioButtonOptionD = findViewById(R.id.radio_button_option_d);
        radioButtonOptionD.setText(question.getOptionD());

        // Clear the selection of the options radio group
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        radioGroup.clearCheck();
    }
    private void showFinalScore(int score) {
        // Hide the question UI elements
        TextView textViewQuestion = findViewById(R.id.text_view_question);
        textViewQuestion.setVisibility(View.GONE);

        RadioButton radioButtonOptionA = findViewById(R.id.radio_button_option_a);
        radioButtonOptionA.setVisibility(View.GONE);

        RadioButton radioButtonOptionB = findViewById(R.id.radio_button_option_b);
        radioButtonOptionB.setVisibility(View.GONE);

        RadioButton radioButtonOptionC = findViewById(R.id.radio_button_option_c);
        radioButtonOptionC.setVisibility(View.GONE);

        RadioGroup radioGroup = findViewById(R.id.radio_group);
        radioGroup.setVisibility(View.GONE);

        Button buttonNextQuestion = findViewById(R.id.button_next_question);
        buttonNextQuestion.setVisibility(View.GONE);

        // Show the final score
        TextView textViewFinalScore = findViewById(R.id.text_view_final_score);
        String finalScoreText = "Your final score is: " + score + " out of " + questions.size();
        textViewFinalScore.setText(finalScoreText);
        textViewFinalScore.setVisibility(View.VISIBLE);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        Button buttonNextQuestion = findViewById(R.id.button_next_question);
        buttonNextQuestion.setEnabled(false);

        RadioGroup radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Enable the button when the user selects an answer
                buttonNextQuestion.setEnabled(true);
            }
        });

        LinearLayout question = findViewById(R.id.question);
        questions = new ArrayList<>();
        Intent intent = getIntent();
        if(intent.getStringExtra("button").equals("green")){
            questions.add(new Question(
                    "ממי קנה אברהם אבינו את מערת המכפלה?",
                    Arrays.asList("מלך מצרים", "לוט", "אבימלך", "עפרון החתי"),
                    3
            ));

            questions.add(new Question(
                    "מדוע נכתב שמו של עפרון החתי בתורה ללא ו׳?",
                    Arrays.asList("כי עשה הרבה ודיבר מעט", "כי לעגו לעפרון", "כי אמר הרבה ואפילו מעט לא עשה", "כי לא כתבו בתקופה הזאת עם ו"),
                    2
            ));
            questions.add(new Question(
                    "כיצד ידע אברהם היכן הוא המקום לעקוד את יצחק?",
                    Arrays.asList("חיפש הר עם ברקים מעליו", "הוא ראה ענן קשור על ההר", "הוא בדק במפה מה ההר הכי גבוה", "אלוהים אמר לו"),
                    1
            ));
            questions.add(new Question(
                    "מהו הניסיון העשירי שנתנסה בו אברהם אבינו?",
                    Arrays.asList("לך לך מארצך וממולדתך ומבית אביך", "הבצורת בארץ", "סדום ועמורה", "עקידת יצחק"),
                    3
            ));
            questions.add(new Question(
                    "מה עשה אברהם במלחמתו נגד עובדי האלילים?",
                    Arrays.asList("שבר את כול הפסלים בחנות של אביו", "הקים מזבחות ליד הערים שלהם", "גרש את כול עובדי האלילים", "קנה את אדמותיהם"),
                    0
            ));
            questions.add(new Question(
                    "איזה נס ארע לאברהם באור כשדים?",
                    Arrays.asList("נחנק וברגע האחרון הצליח לבלוע", "נולד לו ילד", "הוא כמעט נדקר וניצל", "הוא הושלך לכבשן ויצא ממנו חי"),
                    3
            ));
            questions.add(new Question(
                    "מה עשה אברהם כאשר נודע לו כי לוט נפל בשבי?",
                    Arrays.asList("ברח כמה שיותר רחוק", "נלחם בארבעת המלכים", "שילם כופר לשחררו", "ביקש מאלוהים לעזור ללוט"),
                    1
            ));
            questions.add(new Question(
                    "כמה אנשים השתתפו עם אברהם במלחמתו במלכים לפי חזל?",
                    Arrays.asList("אלף", "עשר", "מאה", "אחד"),
                    3
            ));
            questions.add(new Question(
                    "מה עשו ארבעת המלכים המנצחים ללוט?",
                    Arrays.asList("שבו אותו", "לקחו את אשתו", "גירשו אותו לשטח של אברהם", "הרגו אותו"),
                    0
            ));
            questions.add(new Question(
                    "מה ארע מיד לאחר שברך יצחק את יעקב ויעקב יצא?",
                    Arrays.asList("עשו נכנס", "רבקה נכנסה", "יצחק דיבר עם אלוהים", "רבקה הכינה עוד אוכל"),
                    0
            ));
            questions.add(new Question(
                    "מיהו האדם הראשון והוא בן שמונה ימים?",
                    Arrays.asList("יעקב", "עשו", "אברהם", "יצחק"),
                    3
            ));
            questions.add(new Question(
                    "מה פירוש השם יצחק?",
                    Arrays.asList("אברהם הצחיק אותה", "היא יכולה לצחוק על הגר", "אלוהים עשה לה צחוק", "היא שמחה שהוא נולד"),
                    2
            ));
            questions.add(new Question(
                    "מה עשתה רבקה כאשר ראתה לראשונה את יצחק אבינו?",
                    Arrays.asList("היא נפלה מהגמל", "היא השתחווה בפניו", "היא אכלה לחם", "היא העניקה לו מתנות"),
                    0
            ));
            questions.add(new Question(
                    "מדוע היו נשות עשו למורת רוח ליצחק ורבקה?",
                    Arrays.asList("כי הן היו רעות", "כי הן עבדו עבודה זרה", "כי הן היו חמדניות", "כי הן לא יכלו להביא ילדים לעולם"),
                    1
            ));
            questions.add(new Question(
                    "מדוע כהו עיניו של יצחק מראות?",
                    Arrays.asList("כי הוא חטא", "כי הוא חלה", "כי נעקרו לו העיניים", "כי בזמן שנעקד בכו המלאכים אל תוך עיניו"),
                    3
            ));
            questions.add(new Question(
                    "מדוע קרא יצחק את שם העיר באר שבע?",
                    Arrays.asList("על שם הבארות שבה", "כי שבע הוא מספר טופולוגי", "על שם שבע האנשים שגרו שם", "על שם השבועה שנשבע לאבימלך"),
                    3
            ));
            questions.add(new Question(
                    "מה ציוותה רבקה על יעקב כשנודע לה שעשו זומם להרגו?",
                    Arrays.asList("שייקח סכין ויהרוג את עשו", "שיברח לחרן אל קרובי משפחתה", "שיברח למצרים אל בני ישמאל", "שיתנצל בפני עשו"),
                    1
            ));
            questions.add(new Question(
                    "בן כמה היה יצחק במותו?",
                    Arrays.asList("180 שנה", "150 שנה", "130 שנה", "140 שנה"),
                    0
            ));
            questions.add(new Question(
                    "בן כמה היה יצחק אבינו בזמן העקידה?",
                    Arrays.asList("40 שנה", "36 שנה", "37 שנה", "34 שנה"),
                    2
            ));
            questions.add(new Question(
                    "מה שאל יצחק את אביו בשעה שהלכו לעקידה?",
                    Arrays.asList("מתי נגיע", "למה זה כול כך רחוק", "מה נעשה כשנגיע", "איפה השה לעולה"),
                    3
            ));
        }
        if(intent.getStringExtra("button").equals("yellow")){
            questions.add(new Question(
                    "מה העניק אליעזר לרבקה לאחר שהשקתה אותו ואת גמליו?",
                    Arrays.asList("גמל", "כסף", "עגילים וטבעת", "נזם וצמידים"),
                    3
            ));

            questions.add(new Question(
                    "יצחק ורבקה התפללו שיוולדו להם ילדים למי נענה ה׳ ומדוע?",
                    Arrays.asList("לרבקה כי היא רצתה יותר ילד", "ליצחק כי הוא התפלל יותר", "ליצחק כי הוא צדיק ובן אברהם", "רבקה כי היא צדיקה"),
                    2
            ));
            questions.add(new Question(
                    "להיכן הלך יצחק כאשר היה רעב בארץ?",
                    Arrays.asList("מצרים", "ממלכת אבימלך", "אור כשדים", "מואב"),
                    1
            ));
            questions.add(new Question(
                    "מדוע קנאו הפלישתים ביצחק?",
                    Arrays.asList("כי הוא היה אהוב על אלוהים", "כי היה לו משפחה חזקה ומבוססת", "כי היה לו נחלה גדולה", "כי היה לו מקנה רב"),
                    3
            ));
            questions.add(new Question(
                    "כיצד הגיב לבן כששמע מרבקה אחותו על בואו של אליעזר?",
                    Arrays.asList("הוא הזמין אותו לביתו", "הוא התנגד להכניס זרים", "הוא דרש מאליעזר כסף על מנת להישאר", "הוא סירב לתת את רבקה לאישה"),
                    0
            ));
            questions.add(new Question(
                    "כיצד ענה לבן כאשר ביקש אליעזר לקחת את רבקה אל יצחק?",
                    Arrays.asList("נשלח אותה מיד", "תביא כסף והיא תנשא", "תעבוד עבורי במשך שבע שנים", "נקרא לה ונשאל אותה"),
                    3
            ));
            questions.add(new Question(
                    "מי הוקרב על המזבח במקומו של יצחק?",
                    Arrays.asList("כבשה", "אייל", "עז", "עוף"),
                    1
            ));
            questions.add(new Question(
                    "מה השביע אברהם את אליעזר בשעה ששלחו למצוא אישה ליצחק?",
                    Arrays.asList("שייקח אישה טובת מראה", "שייקח אישה ממשפחה מבוססת", "שייקח אישה צדיקה", "שייקח אישה מארץ מולדתו"),
                    3
            ));
            questions.add(new Question(
                    "מה ביקש עשו מיעקב כאשר שב מן השדה עייף?",
                    Arrays.asList("שיאכיל אותו מהנזיד", "שיצא לציד במקומו", "שיאמר ליצחק שהוא חזר", "שיכין לו בגדים להחלפה"),
                    0
            ));
            questions.add(new Question(
                    "מה ארע מיד לאחר שברך יצחק את יעקב ויעקב יצא?",
                    Arrays.asList("עשו נכנס", "רבקה נכנסה", "יצחק דיבר עם אלוהים", "רבקה הכינה עוד אוכל"),
                    0
            ));
            questions.add(new Question(
                    "מיהו האדם הראשון והוא בן שמונה ימים?",
                    Arrays.asList("יעקב", "עשו", "אברהם", "יצחק"),
                    3
            ));
            questions.add(new Question(
                    "מה פירוש השם יצחק?",
                    Arrays.asList("אברהם הצחיק אותה", "היא יכולה לצחוק על הגר", "אלוהים עשה לה צחוק", "היא שמחה שהוא נולד"),
                    2
            ));
            questions.add(new Question(
                    "מה עשתה רבקה כאשר ראתה לראשונה את יצחק אבינו?",
                    Arrays.asList("היא נפלה מהגמל", "היא השתחווה בפניו", "היא אכלה לחם", "היא העניקה לו מתנות"),
                    0
            ));
            questions.add(new Question(
                    "מדוע היו נשות עשו למורת רוח ליצחק ורבקה?",
                    Arrays.asList("כי הן היו רעות", "כי הן עבדו עבודה זרה", "כי הן היו חמדניות", "כי הן לא יכלו להביא ילדים לעולם"),
                    1
            ));
            questions.add(new Question(
                    "מדוע כהו עיניו של יצחק מראות?",
                    Arrays.asList("כי הוא חטא", "כי הוא חלה", "כי נעקרו לו העיניים", "כי בזמן שנעקד בכו המלאכים אל תוך עיניו"),
                    3
            ));
            questions.add(new Question(
                    "מדוע קרא יצחק את שם העיר באר שבע?",
                    Arrays.asList("על שם הבארות שבה", "כי שבע הוא מספר טופולוגי", "על שם שבע האנשים שגרו שם", "על שם השבועה שנשבע לאבימלך"),
                    3
            ));
            questions.add(new Question(
                    "מה ציוותה רבקה על יעקב כשנודע לה שעשו זומם להרגו?",
                    Arrays.asList("שייקח סכין ויהרוג את עשו", "שיברח לחרן אל קרובי משפחתה", "שיברח למצרים אל בני ישמאל", "שיתנצל בפני עשו"),
                    1
            ));
            questions.add(new Question(
                    "בן כמה היה יצחק במותו?",
                    Arrays.asList("180 שנה", "150 שנה", "130 שנה", "140 שנה"),
                    0
            ));
            questions.add(new Question(
                    "בן כמה היה יצחק אבינו בזמן העקידה?",
                    Arrays.asList("40 שנה", "36 שנה", "37 שנה", "34 שנה"),
                    2
            ));
            questions.add(new Question(
                    "מה שאל יצחק את אביו בשעה שהלכו לעקידה?",
                    Arrays.asList("מתי נגיע", "למה זה כול כך רחוק", "מה נעשה כשנגיע", "איפה השה לעולה"),
                    3
            ));

        }
        if(intent.getStringExtra("button").equals("blue")){
            questions.add(new Question(
                    "What is the capital of France?",
                    Arrays.asList("London", "Paris", "Berlin", "Madrid"),
                    1
            ));

            questions.add(new Question(
                    "What is the largest planet in our solar system?",
                    Arrays.asList("Mars", "Venus", "Jupiter", "Saturn"),
                    2
            ));
        }
        if(intent.getStringExtra("button").equals("red")){
            questions.add(new Question(
                    "What is the capital of France?",
                    Arrays.asList("London", "Paris", "Berlin", "Madrid"),
                    1
            ));

            questions.add(new Question(
                    "What is the largest planet in our solar system?",
                    Arrays.asList("Mars", "Venus", "Jupiter", "Saturn"),
                    2
            ));
        }
        showQuestion(currentQuestionIndex);


    }
    public void onNextQuestionClicked(View view) {
        // Check if the user has selected an answer for the current question
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        int selectedOptionIndex = radioGroup.getCheckedRadioButtonId();
        if (selectedOptionIndex == -1) {
            // The user hasn't selected an answer, show a toast message and return
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the selected answer is correct and update the score if needed
        Question question = questions.get(currentQuestionIndex);
        RadioButton rb = (RadioButton)findViewById(selectedOptionIndex);
        String sa = rb.getText().toString();
        String ca ="";
        if (question.getCorrectOptionIndex() == 0){
            ca = question.getOptionA();
        }
        if (question.getCorrectOptionIndex() == 1){
            ca = question.getOptionB();
        }
        if (question.getCorrectOptionIndex() == 2){
            ca = question.getOptionC();
        }
        if (question.getCorrectOptionIndex() == 3){
            ca = question.getOptionD();
        }
        System.out.println(question.getCorrectOptionIndex());
        if (sa.equals(ca)) {
            score1+=1;
        }

        // Move to the next question
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            // There are more questions, update the UI with the next question
            showQuestion(currentQuestionIndex);
        } else {
            // The quiz is over, show the final score
            showFinalScore(score1);
            sendData();
        }
    }
    private boolean sendData()
    {
        JSONObject jsonToSend = new JSONObject();
        jsonRecieved = new JSONObject();
        try
        {
            Intent intent = getIntent();
            jsonToSend.put("user_name", intent.getStringExtra("user_name").toString());
            jsonToSend.put("score", score1);
            jsonToSend.put("ls", "score");
            SocketTask send1 = new SocketTask(jsonToSend);
            fromServer = send1.execute().get();
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return false;
    }

}
