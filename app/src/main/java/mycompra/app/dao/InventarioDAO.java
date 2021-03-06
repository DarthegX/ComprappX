package mycompra.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mycompra.app.dbhelper.DBHelper;
import mycompra.app.iterador.Agregado;
import mycompra.app.iterador.AgregadoConcreto;
import mycompra.app.iterador.Iterador;
import mycompra.app.modelo.Inventario;

public class InventarioDAO {
    private static DBHelper dbHelper;

    public InventarioDAO(Context context) {
        dbHelper = DBHelper.getDbH(context);
    }

    public int insert(Inventario inventario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Inventario.KEY_Nombre, inventario.getNombre());

        long idInventario = db.insert(Inventario.TABLE, null, values);
        db.close();
        return (int) idInventario;
    }

    public void delete(int idInventario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Inventario.TABLE, Inventario.KEY_ID + "= ?", new String[]{String.valueOf(idInventario)});
        db.close();
    }

    public void update(Inventario inventario) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Inventario.KEY_Nombre, inventario.getNombre());

        db.update(Inventario.TABLE, values, Inventario.KEY_ID + "= ?", new String[]{String.valueOf(inventario.getId())});
        db.close();
    }

    public Inventario getInventarioById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Inventario.KEY_ID + "," +
                Inventario.KEY_Nombre +
                " FROM " + Inventario.TABLE
                + " WHERE " +
                Inventario.KEY_ID + "=?";

        Inventario inventario = new Inventario();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                inventario.setId(cursor.getInt(cursor.getColumnIndex(Inventario.KEY_ID)));
                inventario.setNombre(cursor.getString(cursor.getColumnIndex(Inventario.KEY_Nombre)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return inventario;
    }

    public Iterador<Inventario> getInventarioList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Inventario.KEY_ID + "," +
                Inventario.KEY_Nombre +
                " FROM " + Inventario.TABLE;
        Agregado<Inventario> agregaInv = new AgregadoConcreto<Inventario>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Inventario inventario = new Inventario();
                inventario.setId(cursor.getInt(cursor.getColumnIndex(Inventario.KEY_ID)));
                inventario.setNombre(cursor.getString(cursor.getColumnIndex(Inventario.KEY_Nombre)));
                agregaInv.add(inventario);
            } while (cursor.moveToNext());
        }
        Iterador<Inventario> iteraInv = agregaInv.iterador();
        cursor.close();
        db.close();
        return iteraInv;
    }
}
