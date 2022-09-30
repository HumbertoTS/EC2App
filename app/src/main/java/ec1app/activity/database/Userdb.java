package ec1app.activity.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class Userdb extends FoodBD{

    Context context;

    public Userdb(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertUser(String dni, String nombres, String apellidos, String telefono, String direccion, String fechanac, String email, String contrasena) {
        long id = 0;
        try {
            FoodBD dbHelper = new FoodBD(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("dni", dni);
            values.put("nombres", nombres);
            values.put("apellidos", apellidos);
            values.put("telefono", telefono);
            values.put("direccion", direccion);
            values.put("fechanac", fechanac);
            values.put("email", email);
            values.put("contrasena", contrasena);
            id = db.insert(TABLE_USER, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public boolean isUserExists(String dni, String nombres, String apellidos, String telefono, String direccion, String fechanac, String email, String contrasena) {
        boolean exists = false;
        try {
            FoodBD dbHelper = new FoodBD(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_USER + " WHERE dni = '" + dni + "', nombres = '" + nombres + "', apellidos = '" + apellidos + "', telefono = '" + telefono + "', direccion = '" + direccion + "', fechanac = '" + fechanac + "', email + AND contrasena = '" + contrasena + "'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public Cursor getAllUsers() {
        Cursor cursor = null;
        try {
            FoodBD dbHelper = new FoodBD(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_USER;
            cursor = db.rawQuery(query, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }
}
