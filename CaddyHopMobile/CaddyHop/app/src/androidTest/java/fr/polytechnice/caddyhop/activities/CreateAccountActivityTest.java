package fr.polytechnice.caddyhop.activities;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.polytechnice.caddyhop.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateAccountActivityTest {

    @Rule
    public ActivityTestRule<CreateAccountActivity> mActivityTestRule = new ActivityTestRule<>(CreateAccountActivity.class);

    @Test
    public void loginActivityTest() {
        ViewInteraction login_input = onView(withId(R.id.register_login_input)).check(matches(isDisplayed()));
        ViewInteraction password_input = onView(withId(R.id.register_password_input)).check(matches(isDisplayed()));
        ViewInteraction nom_input = onView(withId(R.id.register_nom_input)).check(matches(isDisplayed()));
        ViewInteraction prenom_input = onView(withId(R.id.register_prenom_input)).check(matches(isDisplayed()));
        ViewInteraction email_input = onView(withId(R.id.register_email_input)).check(matches(isDisplayed()));

        onView(withId(R.id.register_button)).perform(click());

        login_input.perform(typeText("login"));
        pressImeActionButton();
        password_input.perform(typeText("mdp"));
        pressImeActionButton();
        email_input.perform(typeText("email"));
        pressImeActionButton();

        nom_input.perform(typeText("nom"), pressImeActionButton());

        prenom_input.perform(typeText("prenom"), pressImeActionButton());

        onView(withId(R.id.register_button)).perform(click());

    }

    @After
    public void tearDown() {
        mActivityTestRule.getActivity().finish();
    }

}
