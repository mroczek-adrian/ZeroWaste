package com.example.projektmagisterski;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button bOK=findViewById(R.id.bOK),bAnuluj=findViewById(R.id.bAnuluj);
        EditText et1=findViewById(R.id.et1),et2=findViewById(R.id.et2);

        bAnuluj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();

            }
        });

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().length()>0 && et2.getText().length()>0){
                    Integer l1=Integer.parseInt(et1.getText().toString());
                    Integer l2=Integer.parseInt(et2.getText().toString());
                    Intent i=new Intent();
                    i.putExtra("l1",l1);
                    i.putExtra("l2",l2);
                    setResult(Activity.RESULT_OK,i);
                    finish();
                }else
                    Toast.makeText(getApplicationContext(),"Nie wprowadzono liczby",Toast.LENGTH_SHORT).show();

            }
        });
    }
}