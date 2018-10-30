package academy.android.exercise3.recycler;

import android.view.View;

import com.bumptech.glide.Glide;

import academy.android.exercise3.DataHolder;
import academy.android.exercise3.model.NewsItem;

public class ViewHolderBinder {

    private final HolderClickListener listener;

    public ViewHolderBinder(HolderClickListener listener) {
        this.listener = listener;
    }

    void bindViewHolder(NewsAdapter.NewsHolder holder, final int position) {
        NewsItem item = DataHolder.getInstance().getDataList().get(position);
        Glide.with(holder.image.getContext())
                .load(item.getImageUrl())
                .into(holder.image);

        holder.content.setText(item.getPreviewText());
        holder.title.setText(item.getTitle());
        holder.category.setText(item.getCategory().getName());
        holder.time.setText(item.getPublishDate().toString());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHolderClicked(position);
            }
        });
    }

    public interface HolderClickListener {

        void onHolderClicked(int position);
    }
}
