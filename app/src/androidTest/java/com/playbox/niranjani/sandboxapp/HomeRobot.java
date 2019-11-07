package com.playbox.niranjani.sandboxapp;

import android.support.test.espresso.matcher.ViewMatchers;

import com.playbox.niranjani.sandboxapp.commonUtils.CustomMatchers;
import com.playbox.niranjani.sandboxapp.commonUtils.CustomMatchers.FontSizeMatcher;
import com.playbox.niranjani.sandboxapp.commonUtils.FontSizeMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.playbox.niranjani.sandboxapp.Ui.interactWithUi;

public class HomeRobot {
    public HomeRobot IdsAreDisplayed() {
        onView(ViewMatchers.withId(R.id.toast_button)).check(matches(isDisplayed()));
        onView(withId(R.id.toast_button)).check(matches(new FontSizeMatcher(12)));
        onView(withId(R.id.random_button)).check(matches(isDisplayed()));
        onView(withId(R.id.count_button)).check(matches(isDisplayed()));
        return this;
    }
}
