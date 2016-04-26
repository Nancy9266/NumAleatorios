package com.example.joseandres.numaleatorios;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
//Declaramos nuestros elemntos a usar
    private EditText edt;
    private TextView txv2, txv3;
    private Button btn;
    private int numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt = (EditText) findViewById(R.id.edt);
        txv2 = (TextView) findViewById(R.id.txv2);
        txv3 = (TextView) findViewById(R.id.txv3);
        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String v = String.valueOf(prefe.getInt("puntos", 0));
        txv2.setText(v);
        //Aqui es donde vamos a generar nuestro numero aleatorio.
        numero = 1 + (int) (Math.random() * 50);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void verificar(View v) {
        int valor = Integer.parseInt(edt.getText().toString());
        //en este if es donde verificaremos si coincidio el numero pensado al que
        //penso la maquina.
        if (numero == valor) {
            int puntosactual = Integer.parseInt(txv2.getText()
                    .toString());
            puntosactual++;
            txv2.setText(String.valueOf(puntosactual));
            txv3.setText("GANASTE!. Ahora pienso otro numero");
            edt.setText("");
            SharedPreferences preferencias =getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putInt("puntos", puntosactual);
            editor.commit();
            //Si no se notificara si el numero ingresado es mayor o menor al que
            //penso la maquina.
        } else {
            if (valor > numero) {
                txv3.setText("Pensaste un numero mayor al que penso la maquina.");
            } else {
                txv3.setText("Pensaste un numero menor al que penso la maquina.");
            }
        }
    }
}
