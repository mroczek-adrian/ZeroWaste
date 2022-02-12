package com.example.projektmagisterski;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private ArrayList<ProductData> products;
    private RecyclerView rvNotes;
    private ProductsAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private Button bAdd,bWstecz,bExpiryDate;
    private EditText etNote,enterAmount;
    private TextView dateText;




    // przycisk wstecz
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        products = new ArrayList<>();

        //ustaweinie id dla konkretnych obiektow
        rvNotes = findViewById(R.id.rvNotes);
        adapter = new ProductsAdapter(products);
        rvNotes.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this);
        rvNotes.setLayoutManager(linearLayoutManager);
        bAdd = findViewById(R.id.bAdd);
        bExpiryDate = findViewById(R.id.bExpiryDate);
        dateText = findViewById(R.id.date_text);
        etNote = findViewById(R.id.etNote);
        bWstecz = findViewById(R.id.bWstecz);
        enterAmount = findViewById(R.id.enterAmount);

        findViewById(R.id.bExpiryDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        bAdd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String txtNameProduct = etNote.getText().toString();
                String txtIlosc = enterAmount.getText().toString();
                String txtExpiryDate = dateText.getText().toString();

                if (txtNameProduct.length() > 0) {
                    ProductData data = new ProductData(txtNameProduct, txtIlosc, txtExpiryDate);
//                     public ProductData(String nameProduct, Integer ilosc,Date expiryDate) {
                    int currentPosition = products.size();
                    products.add(data);
                    adapter.notifyItemInserted(currentPosition);
                    etNote.setText("");

                    //intencja
//                    String l1=dateText.toString();
//                    String l2=enterAmount.toString();
//                    String l3=etNote.toString();


                    Toast.makeText(getApplicationContext(),"Stworzono intencje",Toast.LENGTH_SHORT).show();



                }
            }
        });

        bWstecz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);

                Intent i=new Intent();
                i.putExtra("listaProduktow",products);

                finish();


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
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        outState.putParcelableArrayList("DANE",products);
        super.onSaveInstanceState(outState);
    }
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        ArrayList<ProductData> products =  savedInstanceState.getParcelableArrayList("DANE");
//        adapter.setProducts(products);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "month/day/year: " + month + "/" + dayOfMonth + "/" + year;
        dateText.setText(date);
    }


    class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>{
        private ArrayList<ProductData> products;

        public ProductsAdapter(ArrayList<ProductData> products){
            this.products=products;
        }
        @NonNull
        @Override
        public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            View view=inflater.inflate(R.layout.product_item,parent,false);
            return new ProductsViewHolder(view);
        }
        public void setNotes(ArrayList<ProductData> products){
            this.products = products;
        }

        @Override
        public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
            ProductData data=products.get(position);
            holder.tvProductName.setText(data.getNameProduct());
            holder.tvAmount.setText(data.getIlosc());
            holder.tvExpiryDate.setText(data.getExpiryDate().toString());

//            holder.tvTimestamp.setText(data.getTimestamp().toString());
        }

        @Override
        public int getItemCount() {
            return products.size();
        }

        class ProductsViewHolder extends RecyclerView.ViewHolder{
            public TextView tvProductName,tvAmount,tvExpiryDate;
            public ProductsViewHolder(@NonNull View itemView) {
                super(itemView);
                tvProductName=itemView.findViewById(R.id.tvProductName);
                tvAmount=itemView.findViewById(R.id.tvAmount);
                tvExpiryDate=itemView.findViewById(R.id.tvExpiryDate);

                itemView.setClickable(true);
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int i=getAdapterPosition();
                        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                        builder .setTitle("Usuwanie")
                                .setMessage("Czy na pewno usunąć ten element?")
                                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        products.remove(i);
                                        notifyItemRemoved(i);
                                    }
                                })
                                .setNegativeButton("Nie", null)
                                .create().show();
                        return true;
                    }
                });
            }
        }
    }
}

//
//class NoteData implements Parcelable {
//    private String data;
//    private LocalDateTime timestamp;
//
//    public NoteData(String data, LocalDateTime timestamp) {
//        this.data = data;
//        this.timestamp = timestamp;
//    }
//
//
//    public String getData() {
//        return data;
//    }
//
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    //-------------------------------------
//
//    public static final Creator<NoteData> CREATOR = new Creator<NoteData>() {
//        @RequiresApi(api = Build.VERSION_CODES.O)
//        @Override
//        public NoteData createFromParcel(Parcel in) {
//            return new NoteData(in);
//        }
//
//        @Override
//        public NoteData[] newArray(int size) {
//            return new NoteData[size];
//        }
//    };
//
//
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    protected NoteData(Parcel in) {
//        data = in.readString();
//        timestamp = LocalDateTime.parse(in.readString());
//    }
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(data);
//        dest.writeString(timestamp.toString());
//
//    }
//
//
//
//
//}

class ProductData implements Parcelable {
    private String nameProduct;
    private String ilosc;
    private String expiryDate;

    public ProductData(String nameProduct, String ilosc,String expiryDate) {
        this.expiryDate = expiryDate;
        this.ilosc = ilosc;
        this.nameProduct = nameProduct;


    }



    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }


    //ponizej chyba po to aby sie obracalo a poki co nie chce tego
    //-------------------------------------
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ilosc);
        dest.writeString(String.valueOf(expiryDate));
        dest.writeString(nameProduct);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected ProductData(Parcel in) {
        nameProduct = in.readString();
        expiryDate =  in.readString();
        ilosc = in.readString();
//        data = in.readString();
//        timestamp = LocalDateTime.parse(in.readString());
    }

    public static final Creator<ProductData> CREATOR = new Creator<ProductData>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        @Override
        public ProductData[] newArray(int size) {
            return new ProductData[size];
        }
    };



//
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    protected NoteData(Parcel in) {
//        data = in.readString();
//        timestamp = LocalDateTime.parse(in.readString());
//    }
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(data);
//        dest.writeString(timestamp.toString());
//
//    }






}
