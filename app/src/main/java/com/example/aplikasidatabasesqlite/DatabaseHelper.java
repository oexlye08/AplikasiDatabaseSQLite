package com.example.aplikasidatabasesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME= "nama_database";
    private static final int DATABASE_VERSION=1;
    private static final String KEY_ID="id";
    private static final String TABLE_NAME="nama";
    private static final  String KEY_NAME="name";
    private static final  String KEY_ALAMAT="alamat";

    private static final  String CREATE_TABLE_NAMA= "CREATE TABLE " + TABLE_NAME + "("+
            KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME+ " TEXT," +KEY_ALAMAT+" TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(CREATE_TABLE_NAMA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS'" +TABLE_NAME+"'");
    onCreate(sqLiteDatabase);

    }



    public  long addNameDetail(String nama, String alamat){
    SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(KEY_NAME, nama);
        values.put(KEY_ALAMAT, alamat);
        long insert= db.insert(TABLE_NAME, null, values);
        return insert;
    }

    public ArrayList<Map<String, Object>> getAllDataList(){
    ArrayList<Map<String, Object>> dataArrayList = new ArrayList<>();
    String name="";
    String alamat="";
    int id=0;
    String selectQuery="SELECT * FROM "+TABLE_NAME;
    SQLiteDatabase db=this.getWritableDatabase();
    Cursor c=db.rawQuery(selectQuery, null);
        if (c.moveToFirst()){
            do {
                id=c.getInt(c.getColumnIndex(KEY_ID));
                name=c.getString(c.getColumnIndex(KEY_NAME));
                alamat=c.getString(c.getColumnIndex(KEY_ALAMAT));
                Map<String,Object> listItemMap = new HashMap<>();
                listItemMap.put("id",id);
                listItemMap.put("nama", name);
                listItemMap.put("alamat", alamat);
                dataArrayList.add(listItemMap);
            }while (c.moveToNext());
        }
        return dataArrayList;
    }


    public void update(int id, String nama, String alamat){
        SQLiteDatabase db=this.getWritableDatabase();
        String updateQuery="UPDATE "+TABLE_NAME+" SET "
                +KEY_NAME+ "='"+nama+"',"
                +KEY_ALAMAT+ "='"+alamat+"' WHERE "
                +KEY_ID+ "='"+id+"'";
        db.execSQL(updateQuery);
        db.close();
    }


    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String updateQuery="DELETE FROM "+TABLE_NAME
                +" WHERE " +KEY_ID+ "='"+id+"'";
        db.execSQL(updateQuery);
        db.close();
    }
}
