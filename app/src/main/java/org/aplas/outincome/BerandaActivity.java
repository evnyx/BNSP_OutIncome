package org.aplas.outincome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.aplas.outincome.database.DBHelper;
import org.aplas.outincome.model.Account;
import org.aplas.outincome.model.Note;
import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Locale;

public class BerandaActivity extends AppCompatActivity {

    TextView viewSetting, viewCashIn, viewCashOut, viewDetails, titleIn, titleOut;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        dbHelper = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");

        Note note_in = dbHelper.getCash("in");
        titleIn = (TextView) findViewById(R.id.txTitleIn);
        titleIn.setText("Pemasukkan " + formatRupiah(note_in.getGetInCash()));

        Note note_out = dbHelper.getCash("out");
        titleOut = (TextView) findViewById(R.id.txTitleOut);
        titleOut.setText("Pengeluaran " + formatRupiah(note_out.getGetOutCash()));

        viewCashIn = (TextView) findViewById(R.id.textView1);
        viewCashIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BerandaActivity.this, CashInActivity.class);
                intent.putExtra("account", account);
                startActivity(intent);
            }
        });

        viewSetting = (TextView) findViewById(R.id.textView4);
        viewSetting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, SettingActivity.class);
                intent.putExtra("account", account);
                startActivity(intent);
            }
        });

        viewCashOut = (TextView) findViewById(R.id.textView2);
        viewCashOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, CashOutActivity.class);
                intent.putExtra("account", account);
                startActivity(intent);
            }
        });

        viewDetails = (TextView) findViewById(R.id.textView3);
        viewDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, DetailActivity.class);
                intent.putExtra("account", account);
                startActivity(intent);
            }
        });
    }
    private String formatRupiah(int number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}