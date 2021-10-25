package org.aplas.outincome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.aplas.outincome.database.DBHelper;
import org.aplas.outincome.model.Account;

public class RegisterActivity extends AppCompatActivity {

    EditText TxUsername, TxPassword;
    Button BtnBack, BtnRegister;
    DBHelper dbHelper;
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(getApplicationContext());

        TxUsername = (EditText)findViewById(R.id.txUsername);
        TxPassword = (EditText)findViewById(R.id.txPassword);
        BtnBack = (Button)findViewById(R.id.btnBack);
        BtnRegister = (Button)findViewById(R.id.btnRegister);

        account = new Account();

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account.setUsername(TxUsername.getText().toString());
                account.setPassword(TxPassword.getText().toString());

                dbHelper.createUser(account);

                Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();

            }
        });

        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}