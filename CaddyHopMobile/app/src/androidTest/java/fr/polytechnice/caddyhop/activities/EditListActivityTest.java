package fr.polytechnice.caddyhop.activities;


import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.polytechnice.caddyhop.models.Article;
import fr.polytechnice.caddyhop.models.ItemList;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditListActivityTest {

    @Rule
    public ActivityTestRule<EditListActivity> mActivityTestRule = new ActivityTestRule<>(EditListActivity.class, true, false);

    @Before
    public void setUp() {
        Intent editListIntent = new Intent();
        List<Article> itemAvailable = new ArrayList<>();
        ItemList itemList = new ItemList();
        itemList.setArticleList(itemAvailable);
        editListIntent.putExtra("allItemAvailable", (Serializable) itemAvailable);
        editListIntent.putExtra("itemList", (Serializable) itemList);
        mActivityTestRule.launchActivity(editListIntent);
    }

    @Test
    public void editListActivityTest() {

    }

    @After
    public void tearDown() {
        mActivityTestRule.getActivity().finish();
    }


}
