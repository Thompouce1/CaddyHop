package fr.polytechnice.caddyhop.fragments;


import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.google.android.gms.vision.barcode.Barcode;

import net.glxn.qrgen.android.QRCode;

import java.util.ArrayList;
import java.util.List;

import fr.polytechnice.caddyhop.R;
import fr.polytechnice.caddyhop.activities.MainActivity;
import fr.polytechnice.caddyhop.activities.ShowQRCodeActivity;
import fr.polytechnice.caddyhop.models.Article;
import fr.polytechnice.caddyhop.models.ItemList;
import fr.polytechnice.caddyhop.models.User;
import fr.polytechnice.caddyhop.network.CHSGetArticles;
import fr.polytechnice.caddyhop.views.adapters.ItemListAdapter;
import fr.polytechnice.caddyhop.views.adapters.ItemListWithCheckboxAdapter;

/**
 * Created by shafiq on 06/06/16.
 */
public class ActiveListFragment extends Fragment {


    // TODO: use ButterKnife
    private FloatingActionButton scanBarcodeFab;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView activeListTitle;
    private Button generateQRCodeButton;

    private RecyclerView recyclerView;
    private RecyclerView basketRv;

    private ItemListWithCheckboxAdapter itemListWithCheckboxAdapter;
    private ItemListAdapter itemInBasketListAdapter;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager basketLayoutManager;

    private MainActivity mainActivity;


    private List<Article> listItem = new ArrayList<>();
    private List<Article> listArticleInBasket = new ArrayList<>();

    private ItemList activeList;

    private List<Article> listItemAvailable = new ArrayList<>();

    private String result;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Resuming ...");
        mainActivity.updateAllListAdapter();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_list, null);
        mainActivity = (MainActivity) this.getActivity();

        User user = ((MainActivity)this.getActivity()).getCurrentUser();
        Log.d(TAG, "User found: " + user);

        generateQRCodeButton = (Button) view.findViewById(R.id.generate_qrcode_button);
        activeListTitle = (TextView) view.findViewById(R.id.active_list_title);

        generateQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Pay me now!!";

                Intent intent = new Intent(v.getContext(), ShowQRCodeActivity.class);
                intent.putExtra("qrcodedata", data);
                v.getContext().startActivity(intent);
            }
        });

        // Setting up the Floating Action Button for code scanner

        scanBarcodeFab = (FloatingActionButton) view.findViewById(R.id.scan_barcode_fab);
        scanBarcodeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScan();
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.currentListSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: " + "Refreshing : " + listItem);
                itemListWithCheckboxAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.list_recycler_view);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        try {
            activeList = user.getActiveItemList();
            activeListTitle.setText("ACTIVE LIST: " + user.getActiveItemList().getNom());
            listItem = activeList.getArticleList();
        } catch (Exception e) {
            activeListTitle.setText("ACTIVE LIST: no list selected");
        }


        itemListWithCheckboxAdapter = new ItemListWithCheckboxAdapter(listItem);
        recyclerView.setAdapter(itemListWithCheckboxAdapter);

        new CHSGetArticles(this).execute();

        /*
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
                System.out.println(listItem);;
                System.out.println("Swiped " + viewHolder.getAdapterPosition());
                listItem.remove(viewHolder.getAdapterPosition());
                itemListWithCheckboxAdapter.notifyDataSetChanged();
                //recyclerView.removeViewAt(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);*/

        // Set up the view for basket
        basketRv = (RecyclerView) view.findViewById(R.id.item_in_basket_list_recycler_view);
        basketLayoutManager = new LinearLayoutManager(this.getContext());
        basketRv.setLayoutManager(basketLayoutManager);

        itemInBasketListAdapter = new ItemListAdapter(listArticleInBasket);
        basketRv.setAdapter(itemInBasketListAdapter);

        return view;
    }


    private void startScan() {
        /**
         * Build a new MaterialBarcodeScanner
         */
        final MaterialBarcodeScanner materialBarcodeScanner = new MaterialBarcodeScannerBuilder()
                .withActivity(this.getActivity())
                .withEnableAutoFocus(true)
                .withBleepEnabled(true)
                .withBackfacingCamera()
                .withCenterTracker()
                .withText("Scanning...")
                .withResultListener(new MaterialBarcodeScanner.OnResultListener() {
                    @Override
                    public void onResult(Barcode barcode) {
                        result = barcode.rawValue;
                        System.out.println("Barcode result : " + result);
                        scanComplete();
                    }
                })
                .build();
        materialBarcodeScanner.startScan();
    }

    private void scanComplete() {
        final Article article = findArticleByBarcode(result);
        Toast.makeText(this.getContext(),"Scanned barcode " + result + "\n Found item : " + article, Toast.LENGTH_SHORT).show();
        System.out.println(listItemAvailable);

        if(article != null) {
            for(Article a:listItem) {
                if(a.getCodeBarre().equals(article.getCodeBarre())) {
                    System.out.println("Item is in list");
                }
            }
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.scan_complete_dialog, null);
        final NumberPicker np = (NumberPicker) v.findViewById(R.id.quantity_picker);
        np.setMaxValue(20);
        np.setMinValue(1);
        TextView dialogText = (TextView) v.findViewById(R.id.scan_complete_dialog_text);
        dialogText.setText("Article found : " + article.getNom() + ". \nAjouter dans le panier ?");

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(v)
                // Add action buttons
                .setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        itemListWithCheckboxAdapter.checkAnArticle(article);
                        System.out.println("Qte selected : " + np.getValue());
                        int quantity = np.getValue();

                        addItemInBasket(article, quantity);
                        itemInBasketListAdapter.notifyDataSetChanged();
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

    private void addItemInBasket(Article article, int quantity) {
        if(listArticleInBasket.contains(article)) {
            int i = listArticleInBasket.indexOf(article);
            Article a = listArticleInBasket.get(i);
            a.setQuantite(a.getQuantite() + quantity);
        } else {
            article.setQuantite(quantity);
            listArticleInBasket.add(article);
        }
    }


    public void updateActiveList(ItemList itemList) {

        listItem.clear();
        listItem.addAll(itemList.getArticleList());
        activeListTitle.setText("ACTIVE LIST: " + itemList.getNom());
        itemListWithCheckboxAdapter.notifyDataSetChanged();
    }


    public Article findArticleByBarcode(String barcode) {

        for(Article a:listItemAvailable) {
            if(a.getCodeBarre().equals(barcode)) {
                return a;
            }
        }

        return null;
    }

    public void setListItemAvailable(List<Article> listItemAvailable) {
        this.listItemAvailable = listItemAvailable;
    }

    public void notifyActiveListAdapter() {
        itemListWithCheckboxAdapter.notifyDataSetChanged();
    }

    public List<Article> getListItemAvailable() {
        return listItemAvailable;
    }
}
