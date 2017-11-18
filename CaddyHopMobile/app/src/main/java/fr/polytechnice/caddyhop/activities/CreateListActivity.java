package fr.polytechnice.caddyhop.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import fr.polytechnice.caddyhop.R;
import fr.polytechnice.caddyhop.models.Article;
import fr.polytechnice.caddyhop.models.ItemList;
import fr.polytechnice.caddyhop.views.adapters.ItemAdapter;
import fr.polytechnice.caddyhop.views.adapters.ItemListWithCheckboxAdapter;

/**
 * Created by shafiq on 07/06/16.
 */

public class CreateListActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    // TODO : use ButterKnife to refactor
    private AutoCompleteTextView addItemAutoCompleteTextView;
    private ItemAdapter itemAdapter;
    private TextView itemListName;
    private Button saveExitButton;

    private RecyclerView recyclerView;
    private ItemListWithCheckboxAdapter itemListWithCheckboxAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ItemList itemList = new ItemList();
    private String listName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);


        itemListName = (TextView) findViewById(R.id.new_list_name);
        saveExitButton = (Button) findViewById(R.id.save_exit_button);

        saveExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "  + "saveButton clicked");
                returnNewList(itemList);
                finish();
            }
        });

        showEnterNameDialog();

        itemList.setArticleList(new ArrayList<Article>());
        recyclerView = (RecyclerView) findViewById(R.id.list_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        itemListWithCheckboxAdapter = new ItemListWithCheckboxAdapter(itemList.getArticleList());
        recyclerView.setAdapter(itemListWithCheckboxAdapter);

        List<Article> allItemAvailable = (List<Article>) getIntent().getSerializableExtra("allItemAvailable");
        Log.d(TAG, "Item available : " + allItemAvailable);


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

    private void returnNewList(ItemList itemList) {
        Log.d(TAG, "returnNewList: " + itemList);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("newList",itemList);
        setResult(Activity.RESULT_OK,returnIntent);

        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Selectionnez quitter si vous voulez quitter sans sauvegarder.")
                .setTitle("Confirmation");

        builder.setPositiveButton("SAUVEGARDER", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                returnNewList(itemList);
            }
        });
        builder.setNegativeButton("QUITTER", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                CreateListActivity.this.finish();
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

    public void showEnterNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Entrez le nom de cette liste");

        // Set up the input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setPadding(30,30,30,30);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listName = input.getText().toString();
                itemList.setNom(listName);
                itemListName.setText(listName);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });

        builder.show();
    }
}
