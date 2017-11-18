package fr.polytechnice.caddyhop.views.viewholders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import fr.polytechnice.caddyhop.R;
import fr.polytechnice.caddyhop.models.ItemList;
import fr.polytechnice.caddyhop.views.adapters.ItemListWithCheckboxAdapter;

/**
 * Created by shafiq on 08/06/16.
 */

public class ListViewHolder extends RecyclerView.ViewHolder {
    private TextView listNameTv;
    private RecyclerView listRecyclerView;
    private Button setActiveButton;
    private ImageButton deleteListButton;
    private ImageButton editListButton;

    RecyclerView.LayoutManager layoutManager;
    View view;

    private final String TAG = this.getClass().getSimpleName();

    public ListViewHolder(View itemView) {
        super(itemView);
        listNameTv = (TextView) itemView.findViewById(R.id.list_name);
        listRecyclerView = (RecyclerView) itemView.findViewById(R.id.list_recycler_view);
        setActiveButton = (Button) itemView.findViewById(R.id.set_active_button);
        deleteListButton = (ImageButton) itemView.findViewById(R.id.delete_list_button);
        editListButton = (ImageButton) itemView.findViewById(R.id.edit_list_button);

        layoutManager = new LinearLayoutManager(itemView.getContext());
        view = itemView;
    }

    public void bind(String nom, ItemList itemList) {
        listNameTv.setText(nom);
        listRecyclerView.setHasFixedSize(true);
        listRecyclerView.setLayoutManager(layoutManager);

        ItemListWithCheckboxAdapter itemListWithCheckboxAdapter = new ItemListWithCheckboxAdapter(itemList.getArticleList());
        listRecyclerView.setAdapter(itemListWithCheckboxAdapter);
        Log.d(TAG, "bind: " + "Putting tag : " + itemList);
        setActiveButton.setTag(itemList);
        Log.d(TAG, "bind: view " + view);
        view.setTag(itemList);
        deleteListButton.setTag(itemList);
        editListButton.setTag(itemList);
    }

}
