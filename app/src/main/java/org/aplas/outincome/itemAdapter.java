package org.aplas.outincome;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.aplas.outincome.model.DataList;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class itemAdapter extends ArrayAdapter<DataList> {
    public static final String Tag = "DataList";
    private Context context;
    int resource;

    public itemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DataList> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).getId();
        int amount = getItem(position).getAmount();
        String desc = getItem(position).getDescription();
        String date = getItem(position).getDate();
        String status = getItem(position).getStatus();

        DataList dataList = new DataList(id, date, amount, desc, status);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource, parent, false);

        TextView txAmount = (TextView) convertView.findViewById(R.id.txDetailCash);
        TextView txDate = (TextView) convertView.findViewById(R.id.txDetailDate);
        TextView txDesc = (TextView) convertView.findViewById(R.id.txDetailDesc);
        ImageView arrowDesc = (ImageView) convertView.findViewById(R.id.Arrow);

        if (status.equals("in")){
            txAmount.setText("[ + ] " + formatRupiah(amount));
            arrowDesc.setImageResource(R.drawable.greenarrow);
        }else if(status.equals("out")){
            txAmount.setText("[ - ] " + formatRupiah(amount));
            arrowDesc.setImageResource(R.drawable.redarrow);
        }
        txDate.setText(date);
        txDesc.setText(desc);

        return convertView;
    }

    private String formatRupiah(int number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}
