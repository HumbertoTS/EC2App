package ec1app.activity.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class FoodBD extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "vochitos.db";
    public static final String TABLE_USER = "t_user";


    public FoodBD(android.content.Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dni TEXT," +
                "nombres TEXT, "+
                "apellidos TEXT,"+
                "telefono TEXT, "+
                "direccion TEXT,"+
                "fechanac TEXT,"+
                "email TEXT," +
                "contrasena TEXT" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(sqLiteDatabase);
    }
}
