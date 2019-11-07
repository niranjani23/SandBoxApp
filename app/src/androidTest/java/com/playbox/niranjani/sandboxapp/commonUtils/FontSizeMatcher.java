package com.playbox.niranjani.sandboxapp.commonUtils;

import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

    public class FontSizeMatcher extends TypeSafeMatcher<View> {
        // field to store values
        private final float expectedSize;

        public FontSizeMatcher(float expectedSize) {
            super(View.class);
            this.expectedSize = expectedSize;
        }

        @Override
        protected boolean matchesSafely(View target) {
            // stop executing if target is not textview
            if (!(target instanceof TextView)){
                return false;
            }
            // target is a text view so apply casting then retrieve and test the desired value
            TextView targetEditText = (TextView) target;
            return targetEditText.getTextSize() == expectedSize;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("with fontSize: ");
            description.appendValue(expectedSize);
        }
}

