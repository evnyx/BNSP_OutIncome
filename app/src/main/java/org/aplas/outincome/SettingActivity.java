package org.aplas.outincome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.aplas.outincome.database.DBHelper;
import org.aplas.outincome.model.Account;

import java.util.Set;

public class SettingActivity extends AppCompatActivity {

    EditText OldPassword, NewPassword;
    Button Save, Back;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        dbHelper = new DBHelper(this);
        OldPassword = (EditText) findViewById(R.id.oldPassword);
        NewPassword = (EditText) findViewById(R.id.newPassword);
        Save = (Button) findViewById(R.id.btnSave);
        Back = (Button) findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account currentUser = dbHelper.findUser(account.getId());
                if (OldPassword.getText().toString().equals(account.getPassword())){
                    if (NewPassword.getText().toString().equals(OldPassword.getText().toString())){
                        Toast.makeText(SettingActivity.this, "The Password is Currently Used", Toast.LENGTH_SHORT).show();
                    }else if(NewPassword.getText().toString().isEmpty()){
                        Toast.makeText(SettingActivity.this, "Fill The Password", Toast.LENGTH_SHORT).show();
                    }else{
                        currentUser.setPassword(NewPassword.getText().toString());
                        if(dbHelper.updateData(currentUser)){
                            Toast.makeText(SettingActivity.this, "Password Has Been Changed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(SettingActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, BerandaActivity.class);
                intent.putExtra("account", account);
                startActivity(intent);
            }
        });
    }
}