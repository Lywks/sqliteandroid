package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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


                if (!muser.isEmpty() && !mfullname.isEmpty() && !memail.isEmpty()
                        && !mpassword.isEmpty() && !mreserverdWord.isEmpty()) {

                    //Buscar el usuario en la tabla user a traves del metodo searchUser
                    //si no lo encuentra antes de guardar
                    if (!searchUser(muser)) {
                        // instrucciones para guardar el registro ( por que no lo encuentra)
                        // tambien poner si quiero uno nuevo o actualizar, en este caso es por insert
                        //true es para uno nuevo // false seria por que ya existe y va a actualizar
                        saveUser(muser,mfullname,memail,mpassword,mreserverdWord,true);
                    } else {
                        //instrucciones para generar el error de existencia del user
                        tvmessage.setTextColor(Color.RED);
                        tvmessage.setText("Usuario existente , intente con otro ");
                    }
                } else {
                    tvmessage.setText("Debe ingresar todos los datos ");
                }
            }

        });


    }

    private boolean searchUser(String muser) {
        //Instanciar la clase de SQLiteDatabase en modo lectura, hacer busqueda
        SQLiteDatabase odbUsers = oUsers.getReadableDatabase();
        //ejecutar la instruccion
        String query = "Select user from user where user ='"+muser+"'";
        //Crear un cursosr para almacenar los registror que retorna el query
        // en el null iria la instruccion de select , pero select ya la tenemos en la misma variable query
        Cursor cUsers = odbUsers.rawQuery(query,null);

        //movetofirst devuelve boolean
        if(cUsers.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    private void saveUser(String muser, String mfullname, String memail, String mpassword, String mreserverdWord,
                          boolean mtipoinst) {
        // cuando queremos hacer busqueda , lectura
        // cuando hacemos insert , delet etc, es en modo escritura

        //vamos a instanciar objeto SQLitedatabase en modo escritura
        SQLiteDatabase oUserWrite = oUsers.getWritableDatabase();
        if(mtipoinst){// preguntando por el verdadero y agregamos un user

            //manejo de excepciones o posibles errores de la app
            try{
                //crear tabla tipo contentvalues para pasar estos datos
                //a tabla fisica
                //contenedor de datos del contacto
                ContentValues cvUser = new ContentValues();
                //.put debe de llamarse como en la tabla
                //lo que hace es enlazar la tabla en ram por la fisica y juntar los datos
                cvUser.put("user",muser);
                cvUser.put("fullname",mfullname);
                cvUser.put("email", memail);
                cvUser.put("password", mpassword);
                cvUser.put("reservedword", mreserverdWord);
                oUserWrite.insert("user",null, cvUser);
                //apenas entre los datos tenemos que cerrar
                oUserWrite.close();
                tvmessage.setTextColor(Color.GREEN);
                tvmessage.setText("Usuario agregado correctamente...");



            }catch(Exception e){
                tvmessage.setTextColor(Color.RED);
                tvmessage.setText("Error al guardar");
            }
        }else{
            //ya existe al ser falso, se actualizaria
        }
    }
}

