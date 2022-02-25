package com.example.projektmagisterski;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultLauncher<Intent> launcher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent i=result.getData();
                            Integer l1=i.getIntExtra("l1",0);
                            Integer l2=i.getIntExtra("l2",0);
                            Integer w=l1+l2;
                            ((TextView)findViewById(R.id.textView)).setText(w.toString());



                            //pod spisem produktow textview
//                            Intent ii=result.getData();
//                            ArrayList listaProduktow=ii.getStringArrayListExtra("listaProduktow");
//
//                            ((TextView)findViewById(R.id.tvProductResult)).setText(listaProduktow.toString());
                        }
                    }
                });
        findViewById(R.id.bDodaj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SecondActivity.class);
                launcher.launch(i);
            }
        });


        //Proba polaczenia aktywity z przyciskiem spis produktow
        findViewById(R.id.bSpisProduktow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(),ThirdActivity.class);
                launcher.launch(i1);
            }
        });
//
//        Intent intent = getIntent();
//        ArrayList listaProduktow=intent.getStringArrayListExtra("listaProduktow");
//        ((TextView)findViewById(R.id.tvProductResult)).setText(listaProduktow.toString());


        findViewById(R.id.bAddProduct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(),AddProductActivity.class);
                launcher.launch(i1);
            }
        });

        findViewById(R.id.btn_przepisy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(),AddPrzepisActivity.class);
                launcher.launch(i2);
            }
        });
    }
}