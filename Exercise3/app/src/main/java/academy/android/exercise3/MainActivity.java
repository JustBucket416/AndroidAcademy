package academy.android.exercise3;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import academy.android.exercise3.model.NewsItem;
import academy.android.exercise3.recycler.NewsAdapter;
import academy.android.exercise3.recycler.ViewHolderBinder;
import academy.android.exercise3.recycler.ViewHolderCreator;

public class MainActivity extends AppCompatActivity implements ViewHolderBinder.HolderClickListener {

    private static final int MARGIN = 4;
    private final Thread dataLoadThread = new Thread(new DataLoader());
    private RecyclerView recyclerView;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RecyclerView.LayoutManager layoutManager;
        recyclerView = findViewById(R.id.recyclerView);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            layoutManager = new GridLayoutManager(this, 2);
        else layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect,
                                       @NonNull View view,
                                       @NonNull RecyclerView parent,
                                       @NonNull RecyclerView.State state) {
                outRect.left = MARGIN;
                outRect.bottom = MARGIN;
            }
        });

        dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setView(createProgressBar())
                .setTitle(R.string.please_wait)
                .show();

        dataLoadThread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dialog.dismiss();
        dataLoadThread.interrupt();
    }

    private View createProgressBar() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);
        layout.addView(progressBar);

        TextView textView = new TextView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(lp);
        textView.setText(R.string.loading);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        layout.addView(textView);

        return layout;
    }

    @Override
    public void onHolderClicked(int position) {
        startActivity(DetailActivity.newIntent(this, position));
    }

    private class DataLoader implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                final List<NewsItem> list = DataHolder.getInstance().getDataList();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(new NewsAdapter(list,
                                new ViewHolderBinder(MainActivity.this),
                                new ViewHolderCreator()));
                        dialog.dismiss();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
