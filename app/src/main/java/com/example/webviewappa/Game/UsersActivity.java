package com.example.webviewappa.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.webviewappa.R;

public class UsersActivity extends AppCompatActivity {
    Button singlePlayerBtn, multiPlayerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        singlePlayerBtn = (Button) findViewById(R.id.SinglePlayerBtn);
        multiPlayerBtn = (Button) findViewById(R.id.MultiPlayerBtn);

        //Implementing OnclickListener and navigating to Single Player activity on button Click
        singlePlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
                startActivity(intent);
            }
        });

        //Implementing OnclickListener and navigating to Main Activity on button Click
        multiPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MultiPlayerActivity.class);
                startActivity(intent);
            }
        });
    }
}