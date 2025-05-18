package com.example.mydictionary;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    DataBaseHelper dbhelper;
    ArrayList<HashMap<String,String>> arrayList;
    HashMap<String,String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#ffffff"));}
        WindowCompat.getInsetsController(getWindow(),getWindow().getDecorView())
                .setAppearanceLightStatusBars(true);
        setContentView(R.layout.activity_main);
        listview=findViewById(R.id.listview);
        dbhelper = new DataBaseHelper(this);

        Cursor cursor = dbhelper.getAllData();

        if (cursor!=null && cursor.getCount()>0){

            arrayList = new ArrayList<>();

            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String word = cursor.getString(1);
                String meaning = cursor.getString(2);
                String partsOfSpeech = cursor.getString(3);
                String example = cursor.getString(4);

                hashMap = new HashMap<>();
                hashMap.put("id",""+id);
                hashMap.put("word",word);
                hashMap.put("meaning",meaning);
                hashMap.put("partsOfSpeech",partsOfSpeech);
                hashMap.put("example",example);
                arrayList.add(hashMap);
            }

        }

        listview.setAdapter(new myadapter());

    }

    public class myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View myview = layoutInflater.inflate(R.layout.item,parent,false);
            TextView tvword = myview.findViewById(R.id.tvword);
            TextView tvmeaning = myview.findViewById(R.id.tvmeaning);
            TextView tvexample = myview.findViewById(R.id.tvexample);

            hashMap = arrayList.get(position);
            String word = hashMap.get("word");
            String meaning = hashMap.get("meaning");
            String partsOfSpeech = hashMap.get("partsOfSpeech");
            String example = hashMap.get("example");

            tvword.setText(word+"("+partsOfSpeech+")");
            tvmeaning.setText(meaning);
            tvexample.setText(example);




            return myview;
        }
    }
}