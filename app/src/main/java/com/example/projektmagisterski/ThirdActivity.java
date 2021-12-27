package com.example.projektmagisterski;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity {
    private ArrayList<NoteData> notes;
    private RecyclerView rvNotes;
    private NotesAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private Button bAdd;
    private EditText etNote;



    // przycisk wstecz
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        notes=new ArrayList<>();


        rvNotes=findViewById(R.id.rvNotes);
        adapter=new NotesAdapter(notes);
        rvNotes.setAdapter(adapter);
        linearLayoutManager=new LinearLayoutManager(this);
        rvNotes.setLayoutManager(linearLayoutManager);
        bAdd=findViewById(R.id.bAdd);
        etNote=findViewById(R.id.etNote);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String txt=etNote.getText().toString();
                if(txt.length()>0){
                    NoteData data=new NoteData(txt, LocalDateTime.now());
                    int currentPosition=notes.size();
                    notes.add(data);
                    adapter.notifyItemInserted(currentPosition);
                    etNote.setText("");
                }
            }
        });
        Button bWstecz=findViewById(R.id.bWstecz);

        bWstecz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();

            }
        });
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        outState.putParcelableArrayList("DANE",notes);
        super.onSaveInstanceState(outState);
    }
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        ArrayList<NoteData> notes =  savedInstanceState.getParcelableArrayList("DANE");
        adapter.setNotes(notes);
        super.onRestoreInstanceState(savedInstanceState);
    }



    class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
        private ArrayList<NoteData> notes;

        public NotesAdapter(ArrayList<NoteData> notes){
            this.notes=notes;
        }
        @NonNull
        @Override
        public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            View view=inflater.inflate(R.layout.note_item,parent,false);
            return new NotesViewHolder(view);
        }
        public void setNotes(ArrayList<NoteData> notes){
            this.notes = notes;
        }

        @Override
        public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
            NoteData data=notes.get(position);
            holder.tvNote.setText(data.getData());
            holder.tvTimestamp.setText(data.getTimestamp().toString());
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }

        class NotesViewHolder extends RecyclerView.ViewHolder{
            public TextView tvNote,tvTimestamp;
            public NotesViewHolder(@NonNull View itemView) {
                super(itemView);
                tvNote=itemView.findViewById(R.id.tvProductName);
                tvTimestamp=itemView.findViewById(R.id.tvAmount);

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
                                        notes.remove(i);
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


class NoteData implements Parcelable {
    private String data;
    private LocalDateTime timestamp;

    public NoteData(String data, LocalDateTime timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }


    public String getData() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    //-------------------------------------

    public static final Creator<NoteData> CREATOR = new Creator<NoteData>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public NoteData createFromParcel(Parcel in) {
            return new NoteData(in);
        }

        @Override
        public NoteData[] newArray(int size) {
            return new NoteData[size];
        }
    };



    @RequiresApi(api = Build.VERSION_CODES.O)
    protected NoteData(Parcel in) {
        data = in.readString();
        timestamp = LocalDateTime.parse(in.readString());
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(data);
        dest.writeString(timestamp.toString());

    }




}

class ProductData implements Parcelable {
    private String nameProduct;
    private Integer ilosc;
    private Date expiryDate;

    public ProductData(String nameProduct, Integer ilosc,Date expiryDate) {
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

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
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
        dest.writeString(nameProduct);
        if (ilosc == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(ilosc);
        }
    }
    protected ProductData(Parcel in) {
        nameProduct = in.readString();
        if (in.readByte() == 0) {
            ilosc = null;
        } else {
            ilosc = in.readInt();
        }
    }

    public static final Creator<ProductData> CREATOR = new Creator<ProductData>() {
        @Override
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        @Override
        public ProductData[] newArray(int size) {
            return new ProductData[size];
        }
    };

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






}
