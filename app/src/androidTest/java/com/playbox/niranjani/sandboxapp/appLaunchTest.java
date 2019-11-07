package com.playbox.niranjani.sandboxapp;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.playbox.niranjani.sandboxapp.commonUtils.LoginRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.playbox.niranjani.sandboxapp.Ui.interactWithUi;

@RunWith(AndroidJUnit4.class)
public class appLaunchTest {

    @Rule
    public LoginRule rule = new LoginRule();

    HomeRobot homeRobot = new HomeRobot();

    @Test
    public void appLaunchesSuccessfully() {

       homeRobot.IdsAreDisplayed();

    }


}
