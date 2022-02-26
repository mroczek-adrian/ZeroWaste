package com.example.projektmagisterski;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    //przepisy
    public static final String PRZEPIS_TABLE = "PRZEPIS_TABLE";
    public static final String COLUMN_PRZEPIS_NAME = "PRZEPIS_NAME";
    public static final String COLUMN_SKLADNIK_NAME1 = "SKLADNIK_NAME1";
    public static final String COLUMN_SKLADNIK_NAME2 = "SKLADNIK_NAME2";
    public static final String COLUMN_SKLADNIK_NAME3 = "SKLADNIK_NAME3";
    public static final String PRZEPIS_ID = "ID";



    //product
    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_DATA_TIME = "PRODUCT_DATE_TIME";
    public static final String COLUMN_PRODUCT_AMOUNT = "PRODUCT_AMOUNT";
    public static final String COLUMN_ACTIVE_PRODUCT = "ACTIVE_PRODUCT";
    public static final String PRODUCT_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "produkty.db", null, 1);

    }

    // t his is called the first time a database is accessed. . There should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE "+PRODUCT_TABLE+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCT_NAME TEXT, PRODUCT_DATE_TIME TEXT ,PRODUCT_AMOUNT INT , ACTIVE_PRODUCT BOOL)";
        String createTableStatementPrzepis = "CREATE TABLE "+PRZEPIS_TABLE+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRZEPIS_NAME TEXT, SKLADNIK_NAME1 TEXT ,SKLADNIK_NAME2 TEXT,SKLADNIK_NAME3 TEXT)";

        db.execSQL(createTableStatement);
        db.execSQL(createTableStatementPrzepis);


    }

    // I haave 5 milion users ;D . Zabezpieczenie kiedy zmieniam strukture bazy danych
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //produkt dodanie
    public boolean addOne(ProductModel productModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PRODUCT_NAME,productModel.getName());
        cv.put(COLUMN_PRODUCT_DATA_TIME,productModel.getDateTime());
        cv.put(COLUMN_PRODUCT_AMOUNT,productModel.getAmount());
        cv.put(COLUMN_ACTIVE_PRODUCT,productModel.isActive());

        long insert = db.insert(PRODUCT_TABLE, null, cv);


        if(insert == -1){
            return false;

        }else{
            return true;
        }
    }

    //dodanie przepisu
    public boolean addOnePrzepis(PrzepisModel przepisModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();

//        public static final String PRZEPIS_TABLE = "PRZEPIS_TABLE";
//        public static final String PRZEPIS_ID = "ID";
//        public static final String COLUMN_PRZEPIS_NAME = "PRZEPIS_NAME";
//        public static final String COLUMN_SKLADNIK_NAME1 = "SKLADNIK_NAME1";
//        public static final String COLUMN_SKLADNIK_NAME2 = "SKLADNIK_NAME2";
//        public static final String COLUMN_SKLADNIK_NAME3 = "SKLADNIK_NAME3";

        cv1.put(COLUMN_PRZEPIS_NAME,przepisModel.getPrzepis_name());
        cv1.put(COLUMN_SKLADNIK_NAME1,przepisModel.getSkladnik_name1());
        cv1.put(COLUMN_SKLADNIK_NAME2,przepisModel.getSkladnik_name2());
        cv1.put(COLUMN_SKLADNIK_NAME3,przepisModel.getSkladnik_name3());

        long insert = db.insert(PRZEPIS_TABLE, null, cv1);
        if(insert == -1){
            return false;

        }else{
            return true;
        }
    }

    public boolean deleteOne(ProductModel productModel){
        // find productModel in the database. if it found, delete it and return true.
        // if it is not found, return false

         SQLiteDatabase db = this.getWritableDatabase();
         String queryString = "DELETE FROM " + PRODUCT_TABLE +" WHERE "+PRODUCT_ID + " = " + productModel.getId();
         Cursor cursor = db.rawQuery(queryString,null);
         if(cursor.moveToFirst()){
             return true;

         }else{
             return false;
         }


    }
    public boolean deleteOnePrzepis(PrzepisModel przepisModel){
        // find productModel in the database. if it found, delete it and return true.
        // if it is not found, return false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + PRZEPIS_TABLE +" WHERE "+PRZEPIS_ID + " = " + przepisModel.getPrzepis_id();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            return true;

        }else{
            return false;
        }


    }

    public List<ProductModel>  getEveryone(){
        List<ProductModel> returnList = new ArrayList<>();
        //get data from the database

        String queryString = "SELECT * FROM " + PRODUCT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            // loop through the cursor (result set) and create new customer  objects.Put them into the result list
            do{
                int productID = cursor.getInt(0);
                String productName = cursor.getString(1);
                String productDateTime = cursor.getString(2);
                int productAmount = cursor.getInt(3);
                boolean productActive = cursor.getInt(4) == 1 ?true:    false;

                ProductModel newProduct = new ProductModel(productID,productName,productDateTime,productAmount,productActive);
                returnList.add(newProduct);


            }while(cursor.moveToNext());

        }else{
            //failure . do not add anything to the list

        }

        //close both the cursor and the db when done.

        cursor.close();
        db.close();

        return returnList;
    }

    public List<PrzepisModel>  getEveryonePrzepis(){
        List<PrzepisModel> returnList = new ArrayList<>();
        //get data from the database

        String queryString = "SELECT * FROM " + PRZEPIS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor1 = db.rawQuery(queryString,null);

        if(cursor1.moveToFirst()){
            // loop through the cursor (result set) and create new customer  objects.Put them into the result list
            do{
                int przepisID = cursor1.getInt(0);
                String przepisName = cursor1.getString(1);
                String skladniknr1 = cursor1.getString(2);
                String skladniknr2 = cursor1.getString(3);
                String skladniknr3 = cursor1.getString(4);


                PrzepisModel newPrzepis = new PrzepisModel(przepisID,przepisName,skladniknr1,skladniknr2,skladniknr3);
                returnList.add(newPrzepis);


            }while(cursor1.moveToNext());

        }else{
            //failure . do not add anything to the list

        }

        //close both the cursor and the db when done.

        cursor1.close();
        db.close();

        return returnList;
    }

    //podpowiedzi tych przepisow aby bylo zero waste oraz w 2 kolejnosci aby uwzglednic to ze jakiego produktu jest za duzo
    public List<PrzepisModel>  getHintPrzepis() throws ParseException {
        //1 na podtawie produktow ktorym konczy sie data waznosci chce dac odp przepis ktory ten produkt zawiera (nie musze wybierac przepisu gdzie najweicej jest produktu kotry traci waznosc )
        //1.1 sprawdzic date waznosci produktu ktora wymaga spozycia, na podstawei daty zczytanej z kompa
        //1.2.?
        //2 kolejnosci aby uwzglednic to ze jakiego produktu jest za duzo


        List<PrzepisModel> returnList = new ArrayList<>();
        //get data from the database

        String queryStringPrzepis = "SELECT * FROM " + PRZEPIS_TABLE;
        String queryStringProduct = "SELECT * FROM " + PRODUCT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorPrzepis = db.rawQuery(queryStringPrzepis,null);
        Cursor cursorProduct = db.rawQuery(queryStringProduct,null);



        //current date https://stackoverflow.com/questions/5369682/how-to-get-current-time-and-date-in-android
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int currentdayOfTheMonth = today.monthDay;             // Day of the month (1-31)
        int currentmonth = today.month;                // Month (0-11)
        int currentyear = today.year;                   // Year



        //pobieram produkty i szukam 1 produktu ktorego data jest najmniejsza
        String najmniejszaData = "null";
        if(cursorProduct.moveToFirst()){
            // loop through the cursor (result set) and create new customer  objects.Put them into the result list
            do{
                String productName = cursorProduct.getString(1);
                String productDateTime = cursorProduct.getString(2);
                //boolean productActive = cursor.getInt(4) == 1 ?true:    false;


                //String sDate1="31/12/1998"; https://www.codegrepper.com/code-examples/java/convert+string+value+to+date+in+java+android
                String sDate1=productDateTime;
                Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                DateFormat format2=new SimpleDateFormat("EEEE");
                String finalDay=format2.format(date1);
                int finalDayint= Integer.parseInt(finalDay);

                System.out.println(sDate1+"\t"+date1);
                System.out.println("\n"+"finalDay+"+finalDay);
                Log.d(DataBaseHelper.PRODUCT_ID,"finalDay");

                //szukam i zapamietuje nazwe Produktu z najmniejsza data
                if(currentdayOfTheMonth>=finalDayint){
                    najmniejszaData=productName;
                }


            }while(cursorProduct.moveToNext());

        }else{
            //failure . do not add anything to the list
        }
        System.out.println("\n najmnieszjaData= "+najmniejszaData);
        Log.d(DataBaseHelper.PRODUCT_ID,"Application started");
















        //close both the cursor and the db when done.
        cursorProduct.close();
        db.close();
        return returnList;
    }


}
