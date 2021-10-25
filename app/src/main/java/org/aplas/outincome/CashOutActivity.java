package org.aplas.outincome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.aplas.outincome.database.DBHelper;
import org.aplas.outincome.model.Account;
import org.aplas.outincome.model.Note;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CashOutActivity extends AppCompatActivity {

    SimpleDateFormat dateFormatter;
    Button BtnSave, BtnBack;
    ImageView Calender;
    EditText CalenderText, AmountText, DescText;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_out);

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");

        dbHelper = new DBHelper(this);
        BtnBack = (Button) findViewById(R.id.btnCashOutBack);
        Calender = (ImageView) findViewById(R.id.imgCalenderOut);
        BtnSave = (Button) findViewById(R.id.btnCashOutSave);
        CalenderText = (EditText) findViewById(R.id.txDateCashOut);
        AmountText = (EditText) findViewById(R.id.txAmountCashOut);
        DescText = (EditText) findViewById(R.id.txDescCashOut);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT);

        Calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CashOutActivity.this, BerandaActivity.class);
                intent.putExtra("account", account);
                startActivity(intent);
            }
        });

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Note note = new Note();
                    if (CalenderText.getText().toString().isEmpty() || AmountText.getText().toString().isEmpty() || DescText.getText().toString().isEmpty()){
                        Toast.makeText(CashOutActivity.this, "Check Your Form Again", Toast.LENGTH_SHORT).show();
                    }else{
                        note.setDateCash(CalenderText.getText().toString());
                        note.setAmount(Integer.parseInt(AmountText.getText().toString()));
                        note.setDescription(DescText.getText().toString());
                        note.setStatus("out");
                        if(dbHelper.createNote(note)){
                            Toast.makeText(CashOutActivity.this, "New Note Added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CashOutActivity.this, BerandaActivity.class);
                            intent.putExtra("account", account);
                            startActivity(intent);
                        }else {
                            Toast.makeText(CashOutActivity.this, "Failed Make a New Note", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(CashOutActivity.this, "Failed Make a New Note!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                CalenderText.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}