package fr.polytechnice.caddyhop.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import fr.polytechnice.caddyhop.R;
import fr.polytechnice.caddyhop.models.ItemList;
import fr.polytechnice.caddyhop.models.User;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mActivityTestRule.getActivity());
        prefs.edit().putBoolean("isLoggedIn", true).commit();
    }

    @Test
    public void mainActivityTest() {
        ViewInteraction fabButton = onView(withId(R.id.scan_barcode_fab)).check(matches(isDisplayed()));
        onView(withId(R.id.currentListFragmentLayout)).perform(swipeLeft());
    }

    @After
    public void tearDown() {
        mActivityTestRule.getActivity().finish();
    }


}
