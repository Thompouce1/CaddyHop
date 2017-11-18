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
public class ItemViewWithCheckboxHolder extends RecyclerView.ViewHolder{
    private TextView itemName;
    private CheckBox checkBox;
    private TextView itemQuantity;

    public ItemViewWithCheckboxHolder(View itemView) {
        super(itemView);
        itemName = (TextView) itemView.findViewById(R.id.item_name);
        checkBox = (CheckBox) itemView.findViewById(R.id.item_checkbox);
        itemQuantity = (TextView) itemView.findViewById(R.id.item_quantity);
    }

    public void bind(Article article) {
        itemName.setText(article.getNom());
        itemQuantity.setText("x" + article.getQuantite());
    }

    public void updateCheckbox(boolean value) {
        checkBox.setChecked(value);
    }
}
