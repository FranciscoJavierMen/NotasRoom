package com.example.mynotes.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mynotes.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        initComponents();
        setListeners();
    }

    private void initComponents(){
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void setListeners(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio = new Intent(LoginActivity.this, DashboardActivity.class);
                inicio.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                inicio.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(inicio);

                finish();
            }
        });
    }
}
