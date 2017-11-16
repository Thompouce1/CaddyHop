package fr.polytechnice.caddyhop.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.List;

import fr.polytechnice.caddyhop.R;
import fr.polytechnice.caddyhop.models.Article;
import fr.polytechnice.caddyhop.models.ItemList;
import fr.polytechnice.caddyhop.views.adapters.ItemAdapter;
import fr.polytechnice.caddyhop.views.adapters.ItemListWithCheckboxAdapter;

/**
 * Created by shafiq on 07/06/16.
 *
 * The UX flow for editList is the same as createList
 *
 * TODO : editList and createList have some common attributes. Refactoring should be done
 *
 * TODO: editList not fully functional.
 */

public class EditListActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    // TODO : use ButterKnife
    private AutoCompleteTextView addItemAutoCompleteTextView;
    private ItemAdapter itemAdapter;
    private TextView itemListName;
    private Button saveExitButton;

    private RecyclerView recyclerView;
    private ItemListWithCheckboxAdapter itemListWithCheckboxAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ItemList itemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        itemList = (ItemList) getIntent().getSerializableExtra("itemList");

        itemListName = (TextView) findViewById(R.id.new_list_name);
        saveExitButton = (Button) findViewById(R.id.save_exit_button);

        saveExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateList(itemList);
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        itemListWithCheckboxAdapter = new ItemListWithCheckboxAdapter(itemList.getArticleList());
        recyclerView.setAdapter(itemListWithCheckboxAdapter);

        List<Article> allItemAvailable = (List<Article>) getIntent().getSerializableExtra("allItemAvailable");
        Log.d(TAG, "Item available : " + allItemAvailable);

        Log.d(TAG, "onCreate: ItemList in extra : " + itemList);


        addItemAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.add_item_input);
        itemAdapter = new ItemAdapter(this, allItemAvailable);
        addItemAutoCompleteTextView.setAdapter(itemAdapter);
        addItemAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addItemAutoCompleteTextView.setText("");
                addItemInList((Article) itemAdapter.getItem(position));
            }
        });
    }

    private void updateList(ItemList newItemList) {
        Log.d(TAG, "updateList: new List: " + newItemList);
        finish();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Selectionnez quitter si vous voulez quitter sans sauvegarder.")
                .setTitle("Confirmation");

        builder.setPositiveButton("SAUVEGARDER", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                updateList(itemList);
            }
        });
        builder.setNegativeButton("QUITTER", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                EditListActivity.this.finish();
            }
        });
        builder.setNeutralButton("ANNULER", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public void addItemInList(final Article article) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.scan_complete_dialog, null);
        final NumberPicker np = (NumberPicker) v.findViewById(R.id.quantity_picker);
        np.setMaxValue(20);
        np.setMinValue(1);
        TextView dialogText = (TextView) v.findViewById(R.id.scan_complete_dialog_text);
        dialogText.setText("Article found : " + article.getNom() + ". \nAjouter dans la liste ?");

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(v)
                // Add action buttons
                .setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("Qte selected : " + np.getValue());
                        int quantity = np.getValue();
                        article.setQuantite(quantity);
                        itemList.getArticleList().add(article);
                        Log.d(TAG, "Item added : " + article);
                        itemListWithCheckboxAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.setTitle("Confirmation ajoute d'article");
        builder.create();
        builder.show();
    }
}
