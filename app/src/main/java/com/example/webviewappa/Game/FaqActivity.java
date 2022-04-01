package com.example.webviewappa.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.webviewappa.GameActivity;
import com.example.webviewappa.R;

public class FaqActivity extends AppCompatActivity {
    Button backButton;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(FaqActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}