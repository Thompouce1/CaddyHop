package fr.polytechnice.caddyhop.network;

import android.os.AsyncTask;
import android.util.Log;

import com.orhanobut.wasp.Wasp;

import java.util.List;

import fr.polytechnice.caddyhop.fragments.ActiveListFragment;
import fr.polytechnice.caddyhop.models.Article;

/**
 * Created by shafiq on 31/05/16.
 */
public class CHSGetArticles extends AsyncTask<String,String,List<Article>>{

    CaddyHopWaspService service;
    ActiveListFragment activity;

    private String TAG = this.getClass().getSimpleName();

    public CHSGetArticles() {

    }

    public CHSGetArticles(ActiveListFragment activity) {
        service = new Wasp.Builder(activity.getContext())
                .setEndpoint(CaddyHopWaspService.ENDPOINT)
                .build()
                .create(CaddyHopWaspService.class);

        this.activity = activity;
    }

    @Override
    protected List<Article> doInBackground(String... params) {

        List<Article> itemList = null;

        try {
            itemList = service.getArticle(0);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;

    }

    @Override
    protected void onPostExecute(List<Article> articleList) {
        super.onPostExecute(articleList);
        if(articleList != null)
            activity.setListItemAvailable(articleList);

        Log.d(TAG, "onPostExecute: listItemAvailable: " + articleList);
    }
}
