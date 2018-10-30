package academy.android.exercise3;

import java.util.List;

import academy.android.exercise3.model.DataUtils;
import academy.android.exercise3.model.NewsItem;

public class DataHolder {
    private static final DataHolder ourInstance = new DataHolder();

    private final List<NewsItem> dataList = DataUtils.generateNews();

    public static DataHolder getInstance() {
        return ourInstance;
    }

    private DataHolder() {
    }

    public List<NewsItem> getDataList() {
        return dataList;
    }
}
