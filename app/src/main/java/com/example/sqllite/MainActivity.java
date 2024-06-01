package com.example.sqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etid,etnombre,ettelefono;
    Button btningresar,btnbuscar,btneliminar,btnactualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etid = (EditText) findViewById(R.id.etid);
        etnombre = (EditText) findViewById(R.id.etnombre);
        ettelefono = (EditText) findViewById(R.id.ettelefono);

        btningresar = (Button) findViewById(R.id.btningresar);
        btnbuscar = (Button) findViewById(R.id.btnbuscar);
        btneliminar = (Button) findViewById(R.id.btneliminar);
        btnactualizar = (Button) findViewById(R.id.btnactualizar);

        BaseDeDatos ayudaDB = new BaseDeDatos(getApplicationContext());

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SQLiteDatabase db=ayudaDB.getWritableDatabase();

                    ContentValues valores = new ContentValues();
                    valores.put(BaseDeDatos.Datostabla.Columna_Id, etid.getText().toString());
                    valores.put(BaseDeDatos.Datostabla.Columna_Nombre, etnombre.getText().toString());
                    valores.put(BaseDeDatos.Datostabla.Columna_Telefono, ettelefono.getText().toString());

                    Long idGuardado = db.insert(BaseDeDatos.Datostabla.Nombre_Table, BaseDeDatos.Datostabla.Columna_Id, valores);

                    Toast.makeText(getApplicationContext(), "Se guardo el dato "+idGuardado, Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Hubo un error"+ex, Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SQLiteDatabase db = ayudaDB.getReadableDatabase();
                    String[] dato = {etid.getText().toString()};
                    String[] proyeccion = {BaseDeDatos.Datostabla.Columna_Nombre,BaseDeDatos.Datostabla.Columna_Telefono};
                    Cursor c = db.query(BaseDeDatos.Datostabla.Nombre_Table,proyeccion,
                            BaseDeDatos.Datostabla.Columna_Id+"=?",dato,null,null,null);
                    c.moveToFirst();
                    etnombre.setText(c.getString(0));
                    ettelefono.setText(c.getString(1));
                    Toast.makeText(getApplicationContext(), "Encontrado", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Hubo un error"+ex, Toast.LENGTH_SHORT).show();
                }

            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SQLiteDatabase db = ayudaDB.getWritableDatabase();
                    String[] dato = {etid.getText().toString()};
                    String seleccion = BaseDeDatos.Datostabla.Columna_Id+"=?";
                    db.delete(BaseDeDatos.Datostabla.Nombre_Table,seleccion,dato);
                    Toast.makeText(getApplicationContext(), "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Hubo un error"+ex, Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SQLiteDatabase db = ayudaDB.getWritableDatabase();
                    ContentValues valores = new ContentValues();
                    valores.put(BaseDeDatos.Datostabla.Columna_Nombre,etnombre.getText().toString());
                    valores.put(BaseDeDatos.Datostabla.Columna_Telefono,ettelefono.getText().toString());
                    String[] dato = {etid.getText().toString()};
                    String seleccion = BaseDeDatos.Datostabla.Columna_Id+"=?";
                    int count = db.update(BaseDeDatos.Datostabla.Nombre_Table,valores,seleccion,dato);
                    Toast.makeText(getApplicationContext(), "Se Actualizo el dato ", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Hubo un error"+ex, Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}