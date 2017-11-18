package fr.polytechnice.caddyhop.views.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import fr.polytechnice.caddyhop.R;
import fr.polytechnice.caddyhop.models.Article;

/**
 * Created by shafiq on 30/05/16.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder{
    private TextView itemName;
    private TextView itemQuantity;

    public ItemViewHolder(View itemView) {
        super(itemView);
        itemName = (TextView) itemView.findViewById(R.id.item_name);
        itemQuantity = (TextView) itemView.findViewById(R.id.item_quantity);
    }

    public void bind(Article article) {
        itemName.setText(article.getNom());
        itemQuantity.setText("x" + article.getQuantite());
    }
}
