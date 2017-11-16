package fr.polytechnice.caddyhop.views.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.polytechnice.caddyhop.R;
import fr.polytechnice.caddyhop.activities.CreateListActivity;
import fr.polytechnice.caddyhop.activities.EditListActivity;
import fr.polytechnice.caddyhop.activities.MainActivity;
import fr.polytechnice.caddyhop.models.ItemList;
import fr.polytechnice.caddyhop.views.viewholders.ListViewHolder;

/**
 * Created by shafiq on 08/06/16.
 */
public class AllListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    List<ItemList> allList;
    Button setAsActiveButton;
    ImageButton deleteListButton;
    ImageButton editListButton;

    MainActivity mainActivity;

    private final String TAG = this.getClass().getSimpleName();

    public AllListAdapter(List<ItemList> itemListList) {
        allList = itemListList;
    }

    public AllListAdapter() {
        allList = new ArrayList<>();
    }

    public AllListAdapter(MainActivity mainActivity, List<ItemList> allList) {
        this.mainActivity = mainActivity;
        this.allList = allList;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_cardview, parent, false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + v.getTag());
            }
        });

        setAsActiveButton = (Button) v.findViewById(R.id.set_active_button);
        deleteListButton = (ImageButton) v.findViewById(R.id.delete_list_button);
        editListButton = (ImageButton) v.findViewById(R.id.edit_list_button);


        setAsActiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("Clicked active 0 : " + v.getTag());
                ItemList itemList = (ItemList) v.getTag();
                System.out.println("Clicked active : " + itemList);
                //mainActivity.getCurrentUser().setActiveItemList(itemList);
                mainActivity.getActiveListFragment().updateActiveList(itemList);
            }
        });

        deleteListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ItemList itemList = (ItemList) v.getTag();
                Log.d(TAG, "onClick: delete button " + itemList);

                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                builder.setTitle("Supprimer liste");
                builder.setMessage("Voulez vous vraiment supprimer la liste " + itemList.getNom() + " ?");

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainActivity.deleteList(itemList);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        editListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Edit edit edit");
                Intent editListIntent = new Intent(v.getContext(), EditListActivity.class);
                editListIntent.putExtra("allItemAvailable", (Serializable) mainActivity.getActiveListFragment().getListItemAvailable());
                editListIntent.putExtra("itemList", (Serializable) v.getTag());
                v.getContext().startActivity(editListIntent);
            }
        });

        ListViewHolder lvh = new ListViewHolder(v);

        return lvh;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.bind(allList.get(position).getNom(), allList.get(position));
    }

    @Override
    public int getItemCount() {
        return allList.size();
    }

    public void update(List<ItemList> list) {
        allList = list;
        this.notifyDataSetChanged();
    }

}
