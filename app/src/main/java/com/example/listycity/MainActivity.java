package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdaptor;
    ArrayList<String> dataList;
    String selectedCity;
    EditText addCityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        Button addButton = findViewById(R.id.add_button);
        Button deleteButton = findViewById(R.id.delete_button);
        Button confirmButton = findViewById(R.id.confirm_button);
        addCityEditText = findViewById(R.id.add_city_edit_text);

        String []cities = {"Edmonton", "Vancouver"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdaptor = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdaptor);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
        });

        addButton.setOnClickListener(v -> {
            addCityEditText.setText("");
            addCityEditText.requestFocus();
        });

        confirmButton.setOnClickListener(v -> {
            String cityName = addCityEditText.getText().toString();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdaptor.notifyDataSetChanged();
                addCityEditText.setText("");
            }
        });

        deleteButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity);
                cityAdaptor.notifyDataSetChanged();
                selectedCity = null;
            }
        });
    }
}