package fr.polytechnice.caddyhop.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.io.UnsupportedEncodingException;
import java.util.List;

import fr.polytechnice.caddyhop.R;
import fr.polytechnice.caddyhop.models.Article;
import fr.polytechnice.caddyhop.views.viewholders.ItemViewWithCheckboxHolder;

/**
 * Created by shafiq on 30/05/16.
 */
public class ItemListWithCheckboxAdapter extends RecyclerView.Adapter<ItemViewWithCheckboxHolder> {

    private List<Article> listItem;
    ViewGroup parent;

    public ItemListWithCheckboxAdapter(List<Article> listItem) {
        this.listItem = listItem;
    }

    @Override
    public ItemViewWithCheckboxHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_with_checkbox, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ItemViewWithCheckboxHolder ivh = new ItemViewWithCheckboxHolder(v);
        return ivh;

    }

    @Override
    public void onBindViewHolder(ItemViewWithCheckboxHolder holder, int position) {
        Article article = listItem.get(position);

        holder.bind(article);
        holder.itemView.setTag(listItem.get(position));
    }

    public void checkAnArticle(Article article) {
        int i = 0;
        for(Article a:listItem) {
            if(a.getCodeBarre().equals(article.getCodeBarre())) {
                System.out.println("Found at position " + i);
                View v = parent.findViewWithTag(a);
                CheckBox cb = (CheckBox) v.findViewById(R.id.item_checkbox);
                cb.setChecked(true);
            }
            i++;
        }
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }
}
