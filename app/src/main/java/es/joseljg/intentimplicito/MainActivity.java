package es.joseljg.intentimplicito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edt_direccion_web = null;
    private EditText edt_direccion_mapa = null;
    private EditText edt_direccion_telefono = null;
    private EditText edt_copiar_texto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_direccion_web = (EditText) findViewById(R.id.edt_direccion_web);
        edt_direccion_mapa = (EditText) findViewById(R.id.edt_direccion_mapa);
        edt_direccion_telefono = (EditText) findViewById(R.id.edt_llamar_telefono);
        edt_copiar_texto = (EditText) findViewById(R.id.edt_copiar_texto);
    }

    public void mostrar_pagina_web(View view) {
        String texto_direccion_web = String.valueOf(edt_direccion_web.getText());
        Uri pagina_web = null;
        if(!texto_direccion_web.contains("https://")) {
            pagina_web = Uri.parse("https://" + texto_direccion_web);
        }
        else{
            pagina_web = Uri.parse( texto_direccion_web);
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, pagina_web);
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"no tengo ninguna aplicacion para abrir paginas web",Toast.LENGTH_LONG).show();
        }
    }

    public void mostrar_direccion_en_mapa(View view) {
        String texto_direccion_mapa = String.valueOf(edt_direccion_mapa.getText());
        Uri direccion_mapa = Uri.parse("geo:0,0?q=" + texto_direccion_mapa);
        Intent intent = new Intent(Intent.ACTION_VIEW, direccion_mapa);
      //  if (intent.resolveActivity(getPackageManager()) != null)
     //  {
            startActivity(intent);
      //  }
      //  else{
      //      Toast.makeText(this,"no tengo ninguna aplicacion para abrir mapas",Toast.LENGTH_LONG).show();
      //  }

    }

    public void hacer_llamada(View view) {
        String texto_direccion_telefono = String.valueOf(edt_direccion_telefono.getText());
        Uri direccion_telefono = Uri.parse("tel:" + texto_direccion_telefono);
        Intent intent = new Intent(Intent.ACTION_DIAL, direccion_telefono);
        //  if (intent.resolveActivity(getPackageManager()) != null)
        //  {
        startActivity(intent);
        //  }
        //  else{
        //      Toast.makeText(this,"no tengo ninguna aplicacion para abrir mapas",Toast.LENGTH_LONG).show();
        //  }
    }

    public void copiar_texto(View view) {
        String texto = String.valueOf(edt_copiar_texto.getText());
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.titulo1)
                .setText(texto)
                .startChooser();
    }
}