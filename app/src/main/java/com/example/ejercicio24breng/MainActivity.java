package com.example.ejercicio24breng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio24breng.transacciones.Transacciones;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText descripcion;
    Button btn_guardar, btn_galeria;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descripcion = (EditText) findViewById(R.id.txtdescripcion);
        view = (View) findViewById(R.id.viewfirma);

        btn_guardar = (Button)findViewById(R.id.btnguardar);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarfirma();
            }
        });

        btn_galeria = (Button)findViewById(R.id.btngaleria);
        btn_galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void guardarfirma() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try {
            String descripcionText = descripcion.getText().toString();

            // Validar que haya una descripción antes de guardar
            if (descripcionText.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Por favor, ingresa una descripción antes de guardar", Toast.LENGTH_LONG).show();
                return; // Salir del método si no hay descripción
            }

            ContentValues valores = new ContentValues();

            valores.put(Transacciones.image, Viewfirma(view));
            valores.put(Transacciones.descripcion, descripcionText);

            Long resultado = db.insert(Transacciones.tabla_firmas, Transacciones.id, valores);

            Toast.makeText(getApplicationContext(), "FIRMA REGISTRADA: " + resultado.toString(), Toast.LENGTH_LONG).show();
            descripcion.setText("");
            view.setDrawingCacheEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[]  Viewfirma(View view5) {
        view5.setDrawingCacheEnabled(true);
        Bitmap bitmap = view5.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }

    private void ClearScreen() {
        descripcion.setText("");
        view.setDrawingCacheEnabled(false);
    }
}