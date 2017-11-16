package fr.polytechnice.caddyhop.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.orhanobut.wasp.Callback;
import com.orhanobut.wasp.Response;
import com.orhanobut.wasp.Wasp;
import com.orhanobut.wasp.WaspError;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import fr.polytechnice.caddyhop.R;
import fr.polytechnice.caddyhop.data.DataManager;
import fr.polytechnice.caddyhop.fragments.AccountInfoFragment;
import fr.polytechnice.caddyhop.fragments.ActiveListFragment;
import fr.polytechnice.caddyhop.fragments.AllListFragment;
import fr.polytechnice.caddyhop.models.ItemList;
import fr.polytechnice.caddyhop.models.User;
import fr.polytechnice.caddyhop.network.CaddyHopWaspService;
import fr.polytechnice.caddyhop.network.CaddyHopRetrofitService;
import fr.polytechnice.caddyhop.views.adapters.AllListAdapter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import za.co.riggaroo.materialhelptutorial.TutorialItem;
import za.co.riggaroo.materialhelptutorial.tutorial.MaterialTutorialActivity;

/**
 * The main activity contain a viewPager, in which we have 3 fragments (active list, all list and user info).
 * This allow swiping between the fragments easily.
 * A bottombar is also added for the navigation between fragments.
 *
 * TODO: fix bug when changing fragment (swiping left or right), the data in current fragment is not saved (for example the name of active list)
 *
 */
public class MainActivity extends FragmentActivity {

    private static final int REQUEST_CODE = 9;
    private BottomBar bottomBar;

    private AllListAdapter allListAdapter;

    public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;
    public static final int FRAGMENT_THREE = 2;

    public static final int FRAGMENTS = 3;

    private ActiveListFragment activeListFragment;
    private AllListFragment allListFragment;
    private AccountInfoFragment accountInfoFragment;

    private FragmentPagerAdapter _fragmentPagerAdapter;
    private ViewPager _viewPager;
    private List<Fragment> _fragments = new ArrayList<Fragment>();

    private List<ItemList> allList;
    private ItemList activeList;
    private User currentUser;

    private static final int LOGIN_REQUEST = 2;

    @Inject
    DataManager dataManager;

    private CaddyHopWaspService service;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkIsLoggedIn();

        service = new Wasp.Builder(this)
                .setEndpoint(CaddyHopWaspService.ENDPOINT)
                .build()
                .create(CaddyHopWaspService.class);


        CaddyHopRetrofitService caddyHopRetrofitService = CaddyHopRetrofitService.Creator.newCHService();

        caddyHopRetrofitService.getUserInfo(1).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: Complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(User user) {
                Log.d(TAG, "onNext: " + user);
            }
        });

        Log.d(TAG, "User: " + currentUser);

        activeListFragment = new ActiveListFragment();
        allListFragment = new AllListFragment();
        accountInfoFragment = new AccountInfoFragment();

        _fragments.add(FRAGMENT_ONE, activeListFragment);
        _fragments.add(FRAGMENT_TWO, allListFragment);
        _fragments.add(FRAGMENT_THREE, accountInfoFragment);

        _fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()){

            @Override
            public int getCount() {
                return FRAGMENTS;
            }

            @Override
            public Fragment getItem(final int position) {
                return _fragments.get(position);
            }
        };

        _viewPager = (ViewPager) findViewById(R.id.view_pager);
        _viewPager.setAdapter(_fragmentPagerAdapter);

        _viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position,true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.useFixedMode();
        bottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch(menuItemId) {
                    case R.id.bb_current_list:
                        _viewPager.setCurrentItem(FRAGMENT_ONE);
                        break;
                    case R.id.bb_all_list:
                        _viewPager.setCurrentItem(FRAGMENT_TWO);
                        break;
                    case R.id.bb_account_info:
                        _viewPager.setCurrentItem(FRAGMENT_THREE);
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });

    }

    public void setAllList(List<ItemList> allList) {
        this.allList = allList;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public AllListAdapter getAllListAdapter() {
        return allListAdapter;
    }

    public ActiveListFragment getActiveListFragment() {
        return activeListFragment;
    }

    public ItemList getActiveList() {
        return activeList;
    }

    public void setActiveList(ItemList activeList) {
        this.activeList = activeList;
    }

    public void updateAllListAdapter() {
        Log.d(TAG, "User updated: " + currentUser.hashCode());
        allListAdapter.update(currentUser.getItemListList());
        allListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Destroying activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: Pausing");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //prefs.edit().putBoolean("isLoggedIn", true).apply();
        Gson gson = new Gson();
        String json = gson.toJson(currentUser);
        prefs.edit().putString("user", json).apply();
    }



    public void deleteList(final ItemList itemList) {

        service.deleteList(currentUser.getId(), itemList.getId(), new Callback<Object>()
        {
            int i = 0;

            @Override
            public void onSuccess(Response response, Object o) {
                Log.d(TAG, "onSuccess: List deleted : ");
                currentUser.getItemListList().remove(itemList);
                updateAllListAdapter();
            }

            @Override
            public void onError(WaspError error) {
                i++;
                Log.d(TAG, "onError: " + error);
                if(i<3)
                    deleteList(itemList);
            }
        });
    }

    /**
     * Check in SharedPreference if a user is already logged in.
     * The user profile will be loaded from the SharedPreferences.
     * Ideally, the backend service should be called in order to get an update of user.
     *
     * TODO: call the backend API to get latest data of user
     */
    private void checkIsLoggedIn() {
        // TODO: use the PreferencesHelper
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //prefs.edit().putBoolean("isLoggedIn", false).commit();
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

        if(isLoggedIn) {
            Log.d(TAG, "Already logged in");

            Gson gson = new Gson();
            String json = prefs.getString("user", "");
            User user = gson.fromJson(json, User.class);
            Log.d(TAG, "checkIsLoggedIn: user " + user);

            if(user == null) {
                user = new User();
                user.setItemListList(new ArrayList<ItemList>());
            }

            setCurrentUser(user);

            allList = currentUser.getItemListList();

            allListAdapter = new AllListAdapter(this, allList);

        } else {
            // Right now, a user that is not signed in will see the tutorial activity
            // TODO : make it so that only on first time of application launch that the tutorial is loaded

            loadTutorial();
            Log.d(TAG, "Not yet logged in");
        }
    }

    /**
     * Get the result of a startActivityForResult.
     * Currently, only the tutorial activity is using this function.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: " + requestCode + " " + resultCode);


        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Log.d(TAG, "onActivityResult: Tutorial finished");

                final Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Launch the Tutorial activity.
     */
    public void loadTutorial() {
        Intent mainAct = new Intent(this, MaterialTutorialActivity.class);
        mainAct.putParcelableArrayListExtra(MaterialTutorialActivity.MATERIAL_TUTORIAL_ARG_TUTORIAL_ITEMS, getTutorialItems(this));
        startActivityForResult(mainAct, REQUEST_CODE);
    }

    /**
     * Function used to return a list of defined tutorial pages
     *
     * @param context
     * @return A list of tutorial item
     */
    private ArrayList<TutorialItem> getTutorialItems(Context context) {
        TutorialItem welcomeTutorial = new TutorialItem(getString(R.string.tutorial_welcome_title), getString(R.string.tutorial_welcome_subtitle),
                R.color.colorPrimary, R.drawable.trolley_full);

        TutorialItem accountTutorial = new TutorialItem(getString(R.string.tutorial_account_title), getString(R.string.tutorial_account_subtitle),
                R.color.red500, R.drawable.login_screen);

        TutorialItem addListTutorial = new TutorialItem(getString(R.string.tutorial_createlist_title), getString(R.string.tutorial_createlist_subtitle),
                R.color.pink500, R.drawable.add_list_screen);

        TutorialItem activateListTutorial = new TutorialItem(getString(R.string.tutorial_activatelist_title), getString(R.string.tutorial_activatelist_subtitle),
                R.color.purple500, R.drawable.active_list_screen);

        TutorialItem scanBarcodeTutorial = new TutorialItem(getString(R.string.tutorial_scanbarcode_title), getString(R.string.tutorial_scanbarcode_subtitle),
                R.color.deepPurple500, R.drawable.scan_barcode);

        TutorialItem generateQRCodeTutorial = new TutorialItem(getString(R.string.tutorial_generateqrcode_title), getString(R.string.tutorial_generateqrcode_subtitle),
                R.color.indigo500, R.drawable.cashier);


        ArrayList<TutorialItem> tutorialItems = new ArrayList<>();
        tutorialItems.add(welcomeTutorial);
        tutorialItems.add(accountTutorial);
        tutorialItems.add(addListTutorial);
        tutorialItems.add(activateListTutorial);
        tutorialItems.add(scanBarcodeTutorial);
        tutorialItems.add(generateQRCodeTutorial);

        return tutorialItems;
    }

}
