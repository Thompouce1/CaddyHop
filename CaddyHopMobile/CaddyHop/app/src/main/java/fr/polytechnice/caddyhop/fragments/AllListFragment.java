package fr.polytechnice.caddyhop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.wasp.Callback;
import com.orhanobut.wasp.Response;
import com.orhanobut.wasp.Wasp;
import com.orhanobut.wasp.WaspError;

import java.io.Serializable;

import fr.polytechnice.caddyhop.R;
import fr.polytechnice.caddyhop.activities.CreateListActivity;
import fr.polytechnice.caddyhop.activities.MainActivity;
import fr.polytechnice.caddyhop.models.ItemList;
import fr.polytechnice.caddyhop.models.User;
import fr.polytechnice.caddyhop.network.CaddyHopWaspService;

import static android.app.Activity.RESULT_OK;

/**
 * Created by shafiq on 06/06/16.
 */
public class AllListFragment extends Fragment{

    // TODO: use ButterKnife
    private FloatingActionButton createListBtn;
    private RecyclerView allListRV;
    private RecyclerView.Adapter allListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    private CaddyHopWaspService service;

    private MainActivity mainActivity;

    private static final int ADD_LIST_REQUEST = 1;

    private User user;

    private final String TAG = this.getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_all_list, null);
        user = ((MainActivity)getActivity()).getCurrentUser();
        Log.d(TAG, "onCreateView: User from main: " + user.hashCode());

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.all_list_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: refreshing");
                // get update from server
                service.getUserInfo(user.getId(), new Callback<User>() {
                    @Override
                    public void onSuccess(Response response, User user) {
                        Log.d(TAG, "onSuccess: User obtained: " + user);
                        updateUser(user);
                    }

                    @Override
                    public void onError(WaspError error) {

                    }
                });

                swipeRefreshLayout.setRefreshing(false);
            }
        });

        service = new Wasp.Builder(this.getContext())
                .setEndpoint(CaddyHopWaspService.ENDPOINT)
                .build()
                .create(CaddyHopWaspService.class);

        mainActivity = (MainActivity) this.getActivity();
        allListRV = (RecyclerView) view.findViewById(R.id.all_list_recycler_view);

        // Create UI components here.
        createListBtn = (FloatingActionButton) view.findViewById(R.id.create_list_button);

        createListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addListIntent = new Intent(v.getContext(), CreateListActivity.class);
                addListIntent.putExtra("allItemAvailable", (Serializable) mainActivity.getActiveListFragment().getListItemAvailable());
                startActivityForResult(addListIntent, ADD_LIST_REQUEST);
            }
        });

        return view;
    }

    private void updateUser(User user) {
        mainActivity.setCurrentUser(user);
        this.user = mainActivity.getCurrentUser();
        mainActivity.updateAllListAdapter();
        Log.d(TAG, "updateUser: User : " + this.user);
        Log.d(TAG, "updateUser: hashcode : " + this.user.hashCode());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        layoutManager = new LinearLayoutManager(this.getContext());
        allListRV.setLayoutManager(layoutManager);

        Log.d(TAG, mainActivity.getCurrentUser().toString());

        allListAdapter = mainActivity.getAllListAdapter();
        allListRV.setAdapter(allListAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d(TAG, "onActivityResult: " + requestCode + " " + resultCode);

        if(requestCode == ADD_LIST_REQUEST) {
            if(resultCode == RESULT_OK) {
                Log.d(TAG, "onActivityResult: OK");
                ItemList newList = (ItemList) data.getSerializableExtra("newList");
                Log.d(TAG, "onActivityResult: newList: " + newList);
                addNewListToCurrentUser(newList);
            }
        }
    }

    private void addNewListToCurrentUser(final ItemList itemList) {
        service.createList(itemList, user.getId(), itemList.getNom(), new Callback<Long>() {
            @Override
            public void onSuccess(Response response, Long aLong) {
                if(aLong != -1) {
                    itemList.setId(aLong);
                    Log.d(TAG, "List creation successful, id given: " + aLong);
                    user.getItemListList().add(itemList);
                    service.updateList(itemList, user.getId(), itemList.getId(), new Callback<Object>() {

                        @Override
                        public void onSuccess(Response response, Object o) {
                            Log.d(TAG, "User update successful, list is added");
                        }

                        @Override
                        public void onError(WaspError error) {
                            Log.d(TAG, "User update failed, error " + error);
                        }
                    });
                    mainActivity.updateAllListAdapter();
                }
            }

            @Override
            public void onError(WaspError error) {
                Log.d(TAG, "List creation failed, " + error);
            }
        });

    }
}
