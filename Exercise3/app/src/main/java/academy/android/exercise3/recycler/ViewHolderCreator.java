package academy.android.exercise3.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import academy.android.exercise3.R;

public class ViewHolderCreator {

    NewsAdapter.NewsHolder createViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new NewsAdapter.NewsHolder(inflater.inflate(R.layout.recycler_item, parent, false));
    }
}
