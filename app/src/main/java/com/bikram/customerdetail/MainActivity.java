package com.bikram.customerdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.bikram.customerdetail.adapter.CustomerAdapter;
import com.bikram.customerdetail.model.Customers;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List list=new ArrayList();
    private EditText age, name;
    private RadioButton male, female, others;
    String sname, sgender;
    int sage;
    private Spinner spinner;
    private Button btnsave;
    private RecyclerView recyclerView;
    int imageid;
    final List<Customers> customers =new ArrayList<>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        age = findViewById(R.id.etAge);
        name = findViewById(R.id.nameStudent);
        male = findViewById(R.id.rbMale);
        female = findViewById(R.id.rbFemale);
        others = findViewById(R.id.rbOthers);
        spinner = findViewById(R.id.spinner);
        btnsave = findViewById(R.id.btnSave);

        recyclerView = findViewById(R.id.recycleview);
        CustomerAdapter customerAdapter =new CustomerAdapter(this,list);

        recyclerView.setAdapter(customerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        male.setOnClickListener(this);
        female.setOnClickListener(this);
        others.setOnClickListener(this);
        btnsave.setOnClickListener(this);

        String images[] = {"one", "two", "three", "four", "five"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                images
        );
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getSelectedItem().toString().equals("one")) {
                    imageid = R.drawable.one;
                } else if (spinner.getSelectedItem().toString().equals("two")) {
                    imageid = R.drawable.two;
                } else if (spinner.getSelectedItem().toString().equals("three")) {
                    imageid = R.drawable.three;
                } else if (spinner.getSelectedItem().toString().equals("four")) {
                    imageid = R.drawable.four;
                } else {
                    imageid = R.drawable.five;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rbMale:
                sgender = "M";
                break;

            case R.id.rbFemale:
                sgender = "F";
                break;
            case R.id.rbOthers:
                sgender = "O";
                break;
            case R.id.btnSave:
                if (TextUtils.isEmpty(name.getText().toString())){
                    name.setError("please enter name");
                }
                if (TextUtils.isEmpty(name.getText().toString())){
                    name.setError("please enter Age");
                }else{
                    customers.add(new Customers(imageid,name.getText().toString(),age.getText().toString(),sgender));
                    CustomerAdapter customerAdapter =new CustomerAdapter(this, customers);
                    recyclerView.setAdapter(customerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    name.setText("");
                    male.setSelected(false);
                    female.setSelected(false);
                    others.setSelected(false);
                    age.setText("");
                }
        }
    }

}
