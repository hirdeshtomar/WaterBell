package com.waterbell.hito.watersource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hito on 30-01-2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "waterSuppliersManager";
        private static final String TABLE_WATER_SUPPLIERS = "Water_Suppliers";

        private static final String KEY_ID = "id";
        private static final String KEY_NAME = "name";
        private static final String KEY_LOCATION = "location";
        private static final String KEY_CITY = "city";
        private static final String KEY_STATE = "state";
        private static final String KEY_EMAIL_ID = "emailId";
        private static final String KEY_CONTACT_NUMBER = "contactNumber";
        private static final String KEY_ADDRESS = "address";


    public DatabaseHandler(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override

    public void onCreate(SQLiteDatabase db){
        String CREATE_WATER_SUPPLIERS_TABLE = "CREATE TABLE " + TABLE_WATER_SUPPLIERS + "( " + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," +
                                                KEY_LOCATION + " TEXT," + KEY_CITY + " TEXT," + KEY_STATE + " TEXT," + KEY_EMAIL_ID + " TEXT," +
                                                KEY_CONTACT_NUMBER + " TEXT," + KEY_ADDRESS + " TEXT" + " )";

        db.execSQL(CREATE_WATER_SUPPLIERS_TABLE);
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WATER_SUPPLIERS);

        onCreate(db);
    }

    //Add new Water Supplier

    public void addWaterSupplier(WaterSupplier waterSupplier){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, waterSupplier.get_name());
        values.put(KEY_LOCATION, waterSupplier.get_location());
        values.put(KEY_CITY, waterSupplier.get_city());
        values.put(KEY_STATE, waterSupplier.get_state());
        values.put(KEY_EMAIL_ID, waterSupplier.get_emailId());
        values.put(KEY_CONTACT_NUMBER, waterSupplier.get_contactNumber());
        values.put(KEY_ADDRESS, waterSupplier.get_address());

        db.insert(TABLE_WATER_SUPPLIERS, null, values);
        db.close();


    }

    //Getting Single Supplier
    public WaterSupplier getWaterSupplier(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WATER_SUPPLIERS, new String[]{KEY_ID, KEY_NAME, KEY_LOCATION, KEY_CITY, KEY_STATE, KEY_EMAIL_ID, KEY_CONTACT_NUMBER, KEY_ADDRESS}
                                    , KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

            WaterSupplier waterSupplier = new WaterSupplier(Integer.parseInt(cursor.getString(0)), cursor.getString(1)
                                                                    , cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)
                                                                    , cursor.getString(6), cursor.getString(7));

        return waterSupplier;

    }

    //Getting All Suppliers
    public List<WaterSupplier> getAllWaterSuppliers() {


            List<WaterSupplier> waterSupplierList = new ArrayList<WaterSupplier>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_WATER_SUPPLIERS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    WaterSupplier waterSupplier = new WaterSupplier();
                    waterSupplier.set_id(Integer.parseInt(cursor.getString(0)));
                    waterSupplier.set_name(cursor.getString(1));
                    waterSupplier.set_location(cursor.getString(2));
                    waterSupplier.set_city(cursor.getString(3));
                    waterSupplier.set_state(cursor.getString(4));
                    waterSupplier.set_emailId(cursor.getString(5));
                    waterSupplier.set_contactNumber(cursor.getString(6));
                    waterSupplier.set_address(cursor.getString(7));


                    // Adding supplier to list
                    waterSupplierList.add(waterSupplier);

                } while (cursor.moveToNext());

            }

            // return contact list
        return waterSupplierList;
    }

    //Getting Suppliers count
    public int getWaterSuppliersCount(){


            String countQuery = "SELECT  * FROM " + TABLE_WATER_SUPPLIERS;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();

            // return count
            return cursor.getCount();


    }

    //Updating single Supplier
    public int updateWaterSupplier(WaterSupplier waterSupplier){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, waterSupplier.get_name());
        values.put(KEY_LOCATION, waterSupplier.get_location());
        values.put(KEY_CITY, waterSupplier.get_city());
        values.put(KEY_STATE, waterSupplier.get_state());
        values.put(KEY_EMAIL_ID, waterSupplier.get_emailId());
        values.put(KEY_CONTACT_NUMBER, waterSupplier.get_contactNumber());
        values.put(KEY_ADDRESS, waterSupplier.get_address());

        return db.update(TABLE_WATER_SUPPLIERS, values, KEY_ID + "=?", new String[] {String.valueOf(waterSupplier.get_id())});

    }

    //Deleting single Supplier
    public void deleteWaterSupplier(WaterSupplier waterSupplier){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WATER_SUPPLIERS, KEY_ID + " ?" , new String[]{String.valueOf(waterSupplier.get_id())});
        db.close();
    }


}
