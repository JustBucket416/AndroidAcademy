package academy.android.exercise2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String EMAIL = "justbucket416@gmail.com";
    private static final String SUBJECT = "Exercise 2";
    private static final String DISCLAIMER = "© 2018 Roman";
    private Button button;
    private EditText editText;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.text_message);
        button = findViewById(R.id.button_email);
        relativeLayout = findViewById(R.id.include_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = makeSendIntent(editText.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null) startActivity(intent);
                else Toast.makeText(MainActivity.this,
                        R.string.no_app_found, Toast.LENGTH_SHORT).show();
            }
        });

        showCopyRight();
    }

    private void showCopyRight() {
        TextView textView = new TextView(this);
        textView.setText(DISCLAIMER);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(params);
        relativeLayout.addView(textView);

    }

    private Intent makeSendIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, EMAIL);
        intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        return intent;
    }
}
