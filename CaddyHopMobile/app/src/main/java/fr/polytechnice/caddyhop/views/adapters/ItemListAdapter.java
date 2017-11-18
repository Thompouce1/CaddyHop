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
import fr.polytechnice.caddyhop.views.viewholders.ItemViewHolder;
import fr.polytechnice.caddyhop.views.viewholders.ItemViewWithCheckboxHolder;

/**
 * Created by shafiq on 30/05/16.
 */
public class ItemListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Article> listItem;
    ViewGroup parent;

    public ItemListAdapter(List<Article> listItem) {
        this.listItem = listItem;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ItemViewHolder ivh = new ItemViewHolder(v);
        return ivh;

    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Article article = listItem.get(position);
        String nom = listItem.get(position).getNom();

        holder.bind(article);
        holder.itemView.setTag(listItem.get(position));
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }
}
