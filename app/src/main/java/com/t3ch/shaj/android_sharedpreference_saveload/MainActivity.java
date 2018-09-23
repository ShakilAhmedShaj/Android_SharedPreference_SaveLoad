package com.t3ch.shaj.android_sharedpreference_saveload;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button saveButton, loadButton;
    private TextView userTV, passTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usernameEdit = findViewById(R.id.username_id);
        passwordEdit = findViewById(R.id.password_id);

        saveButton = findViewById(R.id.saveBTN_ID);
        loadButton = findViewById(R.id.loadBTN_ID);

        userTV = findViewById(R.id.TVusername_id);
        passTV = findViewById(R.id.TVpassword_id);

        saveButton.setOnClickListener(this);
        loadButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.saveBTN_ID) {
            String username = usernameEdit.getText().toString();
            String password = passwordEdit.getText().toString();

            if (username.equals("") && password.equals("")) {
                Toast.makeText(getApplicationContext(), "Please Enter Data", Toast.LENGTH_SHORT).show();
            } else {
                //Writing Data
                SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usernameKey", username);
                editor.putString("passwordKey", password);
                editor.commit();

                usernameEdit.setText("");
                passwordEdit.setText("");

                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
            }


        } else if (v.getId() == R.id.loadBTN_ID) {

            //Reading Data

            SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);

            if (sharedPreferences.contains("usernameKey") && sharedPreferences.contains("passwordKey")) {

                String username = sharedPreferences.getString("usernameKey", "Data not Found");
                String password = sharedPreferences.getString("passwordKey", "Data not Found");

                userTV.setText("Username : " + username + "\n\n" + "Password : " + password);


                //passTV.setText(password);
            }


        }

    }
}
