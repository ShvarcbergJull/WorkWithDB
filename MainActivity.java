package com.example.pc.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    SimpleCursorAdapter adapter;
    TextView textView_pr, textView_cost, textView_result;
    SQLiteDatabase productsDB;

    private int sumcost(Cursor cursor)
    {
        int result = 0;
        if (cursor.moveToFirst()) {
            do {
                result += cursor.getInt(2);
                Log.d("tritritri", String.valueOf(result));
            } while (cursor.moveToNext());
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.shop_list);
        textView_pr = findViewById(R.id.product);
        textView_cost = findViewById(R.id.cost);
        textView_result = findViewById(R.id.result);
        DBHelper helper = new DBHelper(this);
        productsDB = helper.getWritableDatabase();

        Cursor products = productsDB.rawQuery("SELECT * FROM shopping_list", null);
        textView_result.setText("All: " + String.valueOf(sumcost(products)));

        String[] product_fields = products.getColumnNames();

        int[] views = { R.id.id, R.id.product, R.id.cost};

        adapter = new SimpleCursorAdapter(this, R.layout.product, products, product_fields, views, 0);
        lv.setAdapter(adapter);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.add)
        {
            String insertprod = textView_pr.getText().toString();
            Integer price = Integer.parseInt(textView_cost.getText().toString());
            if (insertprod != null && price != null) {
                productsDB.execSQL("INSERT INTO shopping_list (`product`, `cost`) VALUES ('" + insertprod + "', " + price + ");");
//                adapter.notifyDataSetChanged();
                Cursor products = productsDB.rawQuery("SELECT * FROM shopping_list", null);
                textView_result.setText("All: " + String.valueOf(sumcost(products)));
                String[] product_fields = products.getColumnNames();

                int[] views = {R.id.id, R.id.product, R.id.cost};

                adapter = new SimpleCursorAdapter(this, R.layout.product, products, product_fields, views, 0);
                lv.setAdapter(adapter);
            }
        }
    }

}
