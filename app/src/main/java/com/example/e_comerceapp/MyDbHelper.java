package com.example.e_comerceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Information_Store";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Login_Info";
    private static final String KEY_ID = "Id";
    private static final String KEY_NAME = "Name";
    private static final String KEY_EMAIL = "Email"; // Changed from "E-mail" to "Email"
    private static final String KEY_PASSWORD = "Password";
    private Context context;
    public MyDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " +
                KEY_EMAIL + " TEXT, " +
                KEY_PASSWORD + " TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // This method is called when the database needs to be upgraded.
        // You can implement your upgrade logic here.
        // For example, you can alter the table, add new columns, or migrate data.
        // For now, we're not doing any upgrades, so the method is empty.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void add_info_table_method(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if the password already exists in the table
        if (!isPasswordExists(db, password)) {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, name);
            values.put(KEY_EMAIL, email);
            values.put(KEY_PASSWORD, password);
            db.insert(TABLE_NAME, null, values);
        } else {
            // Password already exists, show an error message or handle the case accordingly
            // For now, I'm displaying a toast message
            // You can customize this part based on your requirement
            Toast.makeText(context, "Password already exists in the database!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isPasswordExists(SQLiteDatabase db, String password) {
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_PASSWORD + "=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public static boolean isValidCredentials(Context context, String email, String password) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define the columns you want to retrieve from the database
        String[] columns = {MyDbHelper.KEY_EMAIL, MyDbHelper.KEY_PASSWORD};

        // Define the selection criteria (WHERE clause)
        String selection = MyDbHelper.KEY_EMAIL + " = ? AND " + MyDbHelper.KEY_PASSWORD + " = ?";

        // Define the selection arguments (values to be replaced in the selection criteria)
        String[] selectionArgs = {email, password};

        // Query the database to retrieve records with matching email and password
        Cursor cursor = db.query(MyDbHelper.TABLE_NAME, columns, selection, selectionArgs,
                null, null, null);

        // Check if any record is returned from the query
        boolean isValid = cursor.getCount() > 0;

        // Close the cursor and database
        cursor.close();
        db.close();

        return isValid;
    }

}
