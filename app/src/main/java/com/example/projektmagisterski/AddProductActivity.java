package com.example.projektmagisterski;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class AddProductActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    //references to buttons and other controls on the layout
    Button btn_add,btn_viewAll,bExpiryDate;
    EditText et_name,et_age;
    Switch sw_activeProduct;
    ListView lv_productList;
    ArrayAdapter productArrayAdapter;
    DataBaseHelper dataBaseHelper;
    TextView dateText;
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
        bExpiryDate = findViewById(R.id.bExpiryDate1);
        dateText = findViewById(R.id.date_text1);

        //data
        findViewById(R.id.bExpiryDate1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });




        //baza danych
        dataBaseHelper  = new DataBaseHelper(AddProductActivity.this);

        ShowProductOnListView(dataBaseHelper);

        //button listeners for the add and view All buttons
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductModel productModel;
                try{
                    //ogar daty
                    String txtExpiryDate = dateText.getText().toString();
                    //inne
                    productModel = new ProductModel(-1,et_name.getText().toString(),Integer.parseInt(et_age.getText().toString()),sw_activeProduct.isChecked());
                    Toast.makeText(AddProductActivity.this,productModel.toString(), Toast.LENGTH_SHORT).show();

                }catch(Exception e){
                    Toast.makeText(AddProductActivity.this,"Error creating product", Toast.LENGTH_SHORT).show();
                    productModel = new ProductModel(-1,"error",0,false);

                }


                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddProductActivity.this);
                boolean success = dataBaseHelper.addOne(productModel);
                Toast.makeText(AddProductActivity.this,"Success="+ success, Toast.LENGTH_SHORT).show();

                ShowProductOnListView(dataBaseHelper);


            }
        });
        btn_viewAll.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper  = new DataBaseHelper(AddProductActivity.this);
                List<ProductModel> everyone = dataBaseHelper.getEveryone();


//                NIE DZIALA.. - OGANRAC JAK GOSCIU FORMATOWAL LIST VIEW BO COS WPISYWAL I MU FAJNIE POKAZUJE A MI WYWALA
                //po to aby wyslietlac rekordy w liscie
                ShowProductOnListView(dataBaseHelper);


//                Toast.makeText(AddProductActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

//                Toast.makeText(AddProductActivity.this, "View all button", Toast.LENGTH_SHORT).show();
            }


        });

        lv_productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductModel clickedProduct = (ProductModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedProduct);
                ShowProductOnListView(dataBaseHelper);
                Toast.makeText(AddProductActivity.this,"Deleted="+ clickedProduct.toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }
    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }
    private void ShowProductOnListView(DataBaseHelper dataBaseHelper2) {
        productArrayAdapter = new ArrayAdapter<ProductModel>(AddProductActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryone());
        lv_productList.setAdapter(productArrayAdapter);
    }

    //do daty potrzebne
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "month/day/year: " + month + "/" + dayOfMonth + "/" + year;
        dateText.setText(date);
    }
}