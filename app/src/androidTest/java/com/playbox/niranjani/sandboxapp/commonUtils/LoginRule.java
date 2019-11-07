package com.playbox.niranjani.sandboxapp.commonUtils;

import android.view.View;
import com.playbox.niranjani.sandboxapp.R;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

public class LoginRule implements TestRule {


    public LoginRule() {
        onView(withId(R.id.login_btn)).check(matches(isDisplayed())).perform(click());

    }

    public Statement apply(final Statement base, final Description description) {
        System.out.println("Logging in... ");
        return base;
    }
}