package android.academy.exercise1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private static final String TEXT_KEY = "text-key";
    private static final String EMAIL = "justbucket416@gmail.com";
    private static final String SUBJECT = "Hello, Android Academy MSK!";
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button_send);

        String text = getIntent().getStringExtra(TEXT_KEY);
        if (text != null) textView.setText(text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = makeSendIntent(textView.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else Toast.makeText(Main2Activity.this, R.string.no_app_found, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static final Intent newIntent(Context context, String text) {
        Intent intent = new Intent(context, Main2Activity.class);
        intent.putExtra(TEXT_KEY, text);
        return intent;
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
