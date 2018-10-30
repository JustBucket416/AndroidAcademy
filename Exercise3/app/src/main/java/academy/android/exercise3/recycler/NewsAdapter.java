package academy.android.exercise3.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import academy.android.exercise3.R;
import academy.android.exercise3.model.NewsItem;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private final List<NewsItem> items;
    private final ViewHolderBinder binder;
    private final ViewHolderCreator creator;

    public NewsAdapter(List<NewsItem> items, ViewHolderBinder binder, ViewHolderCreator creator) {
        this.items = items;
        this.binder = binder;
        this.creator = creator;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return creator.createViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        binder.bindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class NewsHolder extends RecyclerView.ViewHolder {

        final TextView category;
        final TextView title;
        final TextView content;
        final TextView time;
        final ImageView image;
        final CardView card;

        NewsHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardView);
            category = itemView.findViewById(R.id.textCat);
            title = itemView.findViewById(R.id.textTitle);
            content = itemView.findViewById(R.id.textCont);
            time = itemView.findViewById(R.id.textTime);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
