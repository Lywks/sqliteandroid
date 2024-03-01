package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // instanciar los ids del archivo xml
    // no necesariamente tiene que ser el nombre que hay en el xml al crear las bariables

    EditText etuser, etfullname, etemail, etpass, etpalabraR;
    TextView tvmessage;
    Button btnsave, btnsearch, btnedit, btndelet,btnlist;
    
    // apenas cargue el aplicativo , colocarlo de manera general
    //instanciar la clase dbUser (el objeto quedara global para mainactivity)
    //this hace referencia a esta clase o mainactivity , osea en este contexto
    //el nombre de la bd debe de ser para todos los metodos
    dbUsers oUsers = new dbUsers(this,"dbUser", null,1);
    
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
        
        //EVENTOS DE LOS BOTONES
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //valide si todos los datos estan llenos
                //empty esta preguntando si esta vacio los datos
                //es un objeto booleano pero con el ! estamos diciendo 
                // si no esta vacio 
                String muser = etuser.getText().toString();
                String mfullname = etfullname.getText().toString();
                String mpassword = etpass.getText().toString();
                String memail = etemail.getText().toString();
                String mreserverdWord = etpalabraR.getText().toString();
                
                
                if(!muser.isEmpty() && !mfullname.isEmpty() && !memail.isEmpty()
                && !mpassword.isEmpty() && !mreserverdWord.isEmpty())
                {
                    saveUser(muser,mfullname,memail,mpassword,mreserverdWord);
                }else
                {
                    tvmessage.setText("Debe ingresar todos los datos ");
                }
                
            }
        });


    }

    private void saveUser(String muser, String mfullname, String memail, String mpassword, String mreserverdWord) {
    }
}