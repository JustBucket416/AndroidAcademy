package academy.android.exercise3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import academy.android.exercise3.model.NewsItem;

public class DetailActivity extends AppCompatActivity {

    private static final String POSITION_KEY = "title-key";
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView textContent;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout =
                findViewById(R.id.collapsing_toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        int position = getIntent().getIntExtra(POSITION_KEY, -1);
        if (position == -1) return;

        textContent = findViewById(R.id.textContent);
        imageView = findViewById(R.id.toolbarImage);

        NewsItem item = DataHolder.getInstance().getDataList().get(position);

        Glide.with(this)
                .load(item.getImageUrl())
                .into(imageView);

        collapsingToolbarLayout.setTitle(item.getTitle());
        textContent.setText(item.getFullText());

    }

    public static final Intent newIntent(Context context, int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(POSITION_KEY, position);
        return intent;
    }

}
