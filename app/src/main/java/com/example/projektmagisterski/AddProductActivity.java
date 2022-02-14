package com.example.projektmagisterski;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    //references to buttons and other controls on the layout
    Button btn_add,btn_viewAll;
    EditText et_name,et_age;
    Switch sw_activeProduct;
    ListView lv_productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        btn_add=findViewById(R.id.btn_add);
        btn_viewAll=findViewById(R.id.btn_viewAll);
        et_age=findViewById(R.id.et_age);
        et_name=findViewById(R.id.et_name);
        sw_activeProduct = findViewById(R.id.sw_activeProduct);
        lv_productList = findViewById(R.id.lv_productList);

        //button listeners for the add and view All buttons
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductModel productModel;
                try{
                    productModel = new ProductModel(-1,et_name.getText().toString(),Integer.parseInt(et_age.getText().toString()),sw_activeProduct.isChecked());
                    Toast.makeText(AddProductActivity.this,productModel.toString(), Toast.LENGTH_SHORT).show();

                }catch(Exception e){
                    Toast.makeText(AddProductActivity.this,"Error creating product", Toast.LENGTH_SHORT).show();
                    productModel = new ProductModel(-1,"error",0,false);

                }


                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddProductActivity.this);
                boolean success = dataBaseHelper.addOne(productModel);
                Toast.makeText(AddProductActivity.this,"Success="+ success, Toast.LENGTH_SHORT).show();






            }
        });
        btn_viewAll.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper  = new DataBaseHelper(AddProductActivity.this);
                List<ProductModel> everyone = dataBaseHelper.getEveryone();
                Toast.makeText(AddProductActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

//                Toast.makeText(AddProductActivity.this, "View all button", Toast.LENGTH_SHORT).show();
            }


        });


    }
}