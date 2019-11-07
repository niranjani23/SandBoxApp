package com.playbox.niranjani.sandboxapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private static final String TOTAL_COUNT = "total_count";
    private static String NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    /**
     * Show a toast
     *
     */
    public void toastMe(View view){
        EditText text = (EditText)findViewById(R.id.editText);
        String name = text.getText().toString();
        Toast myToast = Toast.makeText(this, "Hello "+name+"!",
                Toast.LENGTH_LONG);
        myToast.show();
    }

    /**
     *
     * Increments the textView
     */
    public void countMe (View view) {
        // Get the text view
        TextView showCountTextView =
                (TextView) findViewById(R.id.textView);
        String countString = showCountTextView.getText().toString();

        Integer count = Integer.parseInt(countString);
        count++;

        // Display the new value in the text view.
        showCountTextView.setText(count.toString());

    }

    public void randomMe(View view) {
        // Create an Intent to start the second activity
        Intent randomIntent = new Intent(this, RandomActivity.class);
        // Get the text view that shows the count.
        TextView showCountTextView = (TextView) findViewById(R.id.textView);

        // Get the value of the text view.
        String countString = showCountTextView.getText().toString();

        // Convert the count to an int
        int count = Integer.parseInt(countString);

        // Add the count to the extras for the Intent.
        randomIntent.putExtra(TOTAL_COUNT, count);

        EditText text = (EditText)findViewById(R.id.editText);
        String name = text.getText().toString();
        randomIntent.putExtra(NAME,name);
        // Start the new activity.
        startActivity(randomIntent);
    }

}
