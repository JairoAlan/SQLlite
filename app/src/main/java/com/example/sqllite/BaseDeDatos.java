package com.example.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class BaseDeDatos extends SQLiteOpenHelper
{
    public static abstract class Datostabla implements BaseColumns{
        public static final String Nombre_Table="Datos";

        public static final String Columna_Id="Id";
        public static final String Columna_Nombre="Nombre";
        public static final String Columna_Telefono="Telefono";

        private  static  final String Crear_Table= "create table " +Datostabla.Nombre_Table+ "(" +Datostabla.Columna_Id+ " integer not null, " +Datostabla.Columna_Nombre+ " text not null, " +Datostabla.Columna_Telefono+ " text not null "+");";

        private static final String sql_Delete="drop table if exists "+Datostabla.Nombre_Table;
    }

    public static final int DataBase_Version=1;
    public static final String DataBase_Name="directorio.db";


    public BaseDeDatos(@Nullable Context context) {
        super(context, DataBase_Name, null, DataBase_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Datostabla.Crear_Table);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(Datostabla.sql_Delete);
        onCreate(db);
    }
}