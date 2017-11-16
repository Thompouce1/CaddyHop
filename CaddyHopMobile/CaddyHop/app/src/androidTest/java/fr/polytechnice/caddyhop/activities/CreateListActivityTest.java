package fr.polytechnice.caddyhop.activities;


import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.text.InputType;

import static android.support.test.espresso.Espresso.onView;

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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateListActivityTest {

    @Rule
    public ActivityTestRule<CreateListActivity> mActivityTestRule = new ActivityTestRule<>(CreateListActivity.class, true, false);

    @Before
    public void setUp() {
        Intent editListIntent = new Intent();
        List<Article> itemAvailable = new ArrayList<>();
        editListIntent.putExtra("allItemAvailable", (Serializable) itemAvailable);
        mActivityTestRule.launchActivity(editListIntent);
    }


    @Test
    public void createListActivityTest() {
        //onView(withInputType(InputType.TYPE_CLASS_TEXT)).perform(click()).perform(typeText("list"));
        //onView(withText("OK")).perform(click());
    }

}
