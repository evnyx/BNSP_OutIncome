package org.aplas.outincome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.aplas.outincome.database.DBHelper;
import org.aplas.outincome.model.Account;
import org.aplas.outincome.model.DataList;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    ListView simpleList;
    Button btnBack;
    DBHelper dbHelper;
    ArrayList<DataList> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");
        dbHelper = new DBHelper(this);

        simpleList = (ListView)findViewById(R.id.simpleListView);
        btnBack = (Button) findViewById(R.id.btnBackDetail);
        listItems = new ArrayList<>();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, BerandaActivity.class);
                intent.putExtra("account", account);
                startActivity(intent);
            }
        });

        Cursor cursor = dbHelper.GetData();
        listItems.clear();
        while (cursor.moveToNext()){
            listItems.add(new DataList(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4)));
        }
        itemAdapter adapter = new itemAdapter(getApplicationContext(), R.layout.activity_listview, listItems);
        simpleList.setAdapter(adapter);
    }

    private String formatRupiah(int number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}