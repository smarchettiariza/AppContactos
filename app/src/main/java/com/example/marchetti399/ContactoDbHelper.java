package com.example.marchetti399;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ContactoDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contactos.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "contactos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_TELEFONO = "telefono";
    public static final String COLUMN_CORREO = "correo";
    public static final String COLUMN_DOMICILIO = "domicilio";
    public static final String COLUMN_GENERO = "genero";

    public ContactoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + " TEXT, " +
                COLUMN_TELEFONO + " TEXT, " +
                COLUMN_CORREO + " TEXT, " +
                COLUMN_DOMICILIO + " TEXT, " +
                COLUMN_GENERO + " TEXT)";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void insertarContacto(Contacto contacto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(COLUMN_NOMBRE, contacto.getNombre());
        valores.put(COLUMN_TELEFONO, contacto.getTelefono());
        valores.put(COLUMN_CORREO, contacto.getCorreo());
        valores.put(COLUMN_DOMICILIO, contacto.getDomicilio());
        valores.put(COLUMN_GENERO, contacto.getGenero());
        db.insert(TABLE_NAME, null, valores);
        db.close();
    }


    public ArrayList<Contacto> obtenerContactos() {
        ArrayList<Contacto> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, COLUMN_NOMBRE + " ASC");

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE));
                String telefono = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONO));
                String correo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CORREO));
                String domicilio = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOMICILIO));
                String genero = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENERO));
                lista.add(new Contacto(nombre, telefono, correo, domicilio, genero));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }


    public void eliminarContacto(String nombre) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_NOMBRE + "=?", new String[]{nombre});
        db.close();
    }


    public void actualizarContacto(String nombreAntiguo, Contacto nuevoContacto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(COLUMN_NOMBRE, nuevoContacto.getNombre());
        valores.put(COLUMN_TELEFONO, nuevoContacto.getTelefono());
        valores.put(COLUMN_CORREO, nuevoContacto.getCorreo());
        valores.put(COLUMN_DOMICILIO, nuevoContacto.getDomicilio());
        valores.put(COLUMN_GENERO, nuevoContacto.getGenero());

        db.update(TABLE_NAME, valores, COLUMN_NOMBRE + "=?", new String[]{nombreAntiguo});
        db.close();
    }


    public void eliminarTodos() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
