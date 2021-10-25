package org.aplas.outincome.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.aplas.outincome.model.Account;
import org.aplas.outincome.model.Note;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "db_login";
    public static final String table_name = "table_login";
    public static final String note_table = "table_note";

//    Login Table
    public static final String row_id = "_id";
    public static final String row_username = "Username";
    public static final String row_password = "Password";

    public static final String id = "1";
    public static final String username = "user";
    public static final String password = "user";

//    Note Table
    public static final String note_id = "_id";
    public static final String note_date = "dateCash";
    public static final String note_amount = "amount";
    public static final String note_desc = "description";
    public static final String note_status = "status";

    private static final String query = "CREATE TABLE " + table_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + row_username + " TEXT," + row_password + " TEXT)";

    public static final String queryNote = "CREATE TABLE " + note_table + "(" + note_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + note_date + " TEXT," + note_amount + " NUMERIC," + note_desc + " TEXT," + note_status + " TEXT)";

    private SQLiteDatabase db;

    public DBHelper(@Nullable Context context) {
        super(context, database_name, null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
        db.execSQL(queryNote);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("DROP TABLE IF EXISTS " + note_table);
        onCreate(db);
    }

//    Login Logic
    public boolean createUser(Account account){
        boolean result = true;
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(row_username, account.getUsername());
            contentValues.put(row_password, account.getPassword());
            result = sqLiteDatabase.insert(table_name, null, contentValues) > 0;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    public boolean updateData(Account account){
        boolean result = true;
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(row_username, account.getUsername());
            contentValues.put(row_password, account.getPassword());
            result = db.update(table_name, contentValues, row_id + " =?", new String[] {String.valueOf(account.getId())}) > 0;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    public Account login(String username, String password){
        Account account = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + table_name + " where username = ? and password = ?", new String[] {username, password});
            if (cursor.moveToFirst()){
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
            }
        }catch (Exception e){
            account = null;
        }
        return account;
    }

    public Account findUser(int id){
        Account account = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + table_name + " where _id = ? ", new String[] {String.valueOf(id)});
            if (cursor.moveToFirst()){
                account = new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
            }
        }catch (Exception e){
            account = null;
        }
        return account;
    }

//    Note Logic
    public boolean createNote(Note note){
        boolean result = true;
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(note_date, note.getDateCash());
            contentValues.put(note_amount, note.getAmount());
            contentValues.put(note_desc, note.getDescription());
            contentValues.put(note_status, note.getStatus());
            result = sqLiteDatabase.insert(note_table, null, contentValues) > 0;
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    public Note getCash(String status){
        Note note = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(amount) FROM " + note_table + " WHERE status = ?", new String[]{status});
            if (cursor.moveToFirst()){
                note = new Note();
                if (status.equals("in")){
                    note.setGetInCash(cursor.getInt(0));
                }else if(status.equals("out")){
                    note.setGetOutCash(cursor.getInt(0));
                }
            }
        }catch (Exception e){
            note = null;
        }
        return note;
    }

    public Cursor GetData(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + note_table, null);
        return cursor;
    }
}
