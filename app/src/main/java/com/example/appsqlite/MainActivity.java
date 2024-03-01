package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // instanciar los ids del archivo xml
    // no necesariamente tiene que ser el nombre que hay en el xml al crear las bariables

    EditText etuser, etfullname, etemail, etpass, etpalabraR;
    TextView tvmessage;
    Button btnsave, btnsearch, btnedit, btndelet,btnlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referenciar los objetos instanciados

        //hacer referencia a los id de el xml
        etuser =findViewById(R.id.etuser);
        etfullname = findViewById(R.id.etfullname);
        etemail = findViewById(R.id.etemail);
        etpass = findViewById(R.id.etpass);
        etpalabraR = findViewById(R.id.etpalabraR);
        tvmessage = findViewById(R.id.tvmessage);
        btnsave = findViewById(R.id.btnsave);
        btnsearch = findViewById(R.id.btnsearch);
        btnedit = findViewById(R.id.btnedit);
        btndelet = findViewById(R.id.btndelet);
        btnlist = findViewById(R.id.btnlist);


    }
}