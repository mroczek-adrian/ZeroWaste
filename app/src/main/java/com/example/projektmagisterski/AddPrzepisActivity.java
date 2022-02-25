package com.example.projektmagisterski;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class AddPrzepisActivity extends AppCompatActivity {
    //references to buttons and other controls on the layout
    Button btn_przepisAdd;
    EditText et_namePrzepis,et01,et02,et03;
    ListView lv_Przepis;
    ArrayAdapter przepisArrayAdapter;
    DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_przepis);

        btn_przepisAdd=findViewById(R.id.btn_przepisAdd);
        et_namePrzepis=findViewById(R.id.et_namePrzepis);
        et01=findViewById(R.id.et01);
        et02=findViewById(R.id.et02);
        et03=findViewById(R.id.et03);
        lv_Przepis = findViewById(R.id.lv_Przepis);


        //baza danych
        dataBaseHelper  = new DataBaseHelper(AddPrzepisActivity.this);

        ShowProductOnListView(dataBaseHelper);

        //button listeners for the add and view All buttons
        btn_przepisAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrzepisModel przepisModel;
                try{
//                    et_namePrzepis.setText("a");
                    //data musi byc podana
                    if(et_namePrzepis.getText().toString().isEmpty() || et01.getText().toString().isEmpty() ){
//                        Toast.makeText(AddProductActivity.this,"0??=", Toast.LENGTH_SHORT).show();
                        throw new Exception("Something failed.", new Throwable());
                    }
//                    String txtExpiryDate = dateText.getText().toString();
                    //inne
                    przepisModel = new PrzepisModel(-1,et_namePrzepis.getText().toString(),et01.getText().toString(),et02.getText().toString(),et03.getText().toString());
                    Toast.makeText(AddPrzepisActivity.this,przepisModel.toString(), Toast.LENGTH_SHORT).show();
                    boolean success = dataBaseHelper.addOnePrzepis(przepisModel);
                    Toast.makeText(AddPrzepisActivity.this,"Success="+ success, Toast.LENGTH_SHORT).show();

                }catch(Exception e){
                    Toast.makeText(AddPrzepisActivity.this,"Błąd dodania przepisu, nazwa przepisu jest obowiazkowa oraz przynajmniej 1 skladnik w miejscu skladnika nr1!", Toast.LENGTH_SHORT).show();
                    //productModel = new ProductModel(-1,"error ","!",0,false);

                }


                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddPrzepisActivity.this);


                ShowProductOnListView(dataBaseHelper);


            }
        });
        lv_Przepis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PrzepisModel clickedPrzepis = (PrzepisModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOnePrzepis(clickedPrzepis);
                ShowProductOnListView(dataBaseHelper);
                Toast.makeText(AddPrzepisActivity.this,"Usunięto="+ clickedPrzepis.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void ShowProductOnListView(DataBaseHelper dataBaseHelper2) {
        przepisArrayAdapter = new ArrayAdapter<PrzepisModel>(AddPrzepisActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryonePrzepis());
        lv_Przepis.setAdapter(przepisArrayAdapter);
    }

}