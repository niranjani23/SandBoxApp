package com.playbox.niranjani.sandboxapp;

import android.support.test.espresso.matcher.ViewMatchers;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class OldAppLaunchTest {


    @Before
    public void setup() {
        onView(withId(R.id.login_btn)).check(matches(isDisplayed())).perform(click());
    }

    @Test
    public void appLaunchesSuccessfully() {

        onView(withId(R.id.toast_button)).check(matches(isDisplayed()));
        onView(withId(R.id.random_button)).check(matches(isDisplayed()));
        onView(withId(R.id.count_button)).check(matches(isDisplayed()));
    }

}