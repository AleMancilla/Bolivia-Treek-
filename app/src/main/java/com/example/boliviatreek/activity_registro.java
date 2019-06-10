package com.example.boliviatreek;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class activity_registro extends AppCompatActivity {
    private EditText editText_email;
    private EditText editText_pass;
    private EditText editText_nombre;
    private EditText editText_Apellidos;

    private Spinner spinner_dias;
    private Spinner spinner_mes;
    private Spinner spinner_año;

    private RadioButton radioButton_hombre;
    private RadioButton radioButton_mujer;
    private RadioButton radioButton_otro;


    private EditText editText_nickname;
   // private Time fecha_registro;




    private ProgressDialog progresDialog;
    private FirebaseAuth firebaseAuth;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        progresDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        editText_email = findViewById(R.id.editText_EmailRegistro);
        editText_pass = findViewById(R.id.editText_contraseniaRegistro);
        editText_nombre = findViewById(R.id.editText_name_registro);
        editText_Apellidos = findViewById(R.id.editText_lastName_registro);

        editText_nickname= findViewById(R.id.editText_nickname);

        spinner_dias = findViewById(R.id.spinner_dias);
        spinner_mes = findViewById(R.id.spinner_mes);
        spinner_año = findViewById(R.id.spinner_año);

        radioButton_hombre= findViewById(R.id.radioButton_hombre);
        radioButton_mujer= findViewById(R.id.radioButton_mujer);
        radioButton_otro= findViewById(R.id.radioButton_otro);


        ArrayAdapter<CharSequence> adapter_dias = ArrayAdapter.createFromResource(this,R.array.Dias,R.layout.support_simple_spinner_dropdown_item);
        spinner_dias.setAdapter(adapter_dias);
        ArrayAdapter<CharSequence> adapter_mes = ArrayAdapter.createFromResource(this,R.array.Mes,R.layout.support_simple_spinner_dropdown_item);
        spinner_mes.setAdapter(adapter_mes);
        ArrayAdapter<CharSequence> adapter_anio = ArrayAdapter.createFromResource(this,R.array.Año,R.layout.support_simple_spinner_dropdown_item);
        spinner_año.setAdapter(adapter_anio);
    }

    public void registrarUsuario(View v)
    {


        // el trim es para eliminar espacios que tengamos al principio y al final
        final String email = editText_email.getText().toString().trim();
        final String pass = editText_pass.getText().toString().trim();
        final String nombre = editText_nombre.getText().toString();
        final String Apellidos = editText_Apellidos.getText().toString();
        final String nikcname = editText_nickname.getText().toString();

        final String fecha_cumpleaños;
        final android.text.format.Time fecha_registro;

        String dia,mes,anio;


        dia=spinner_dias.getSelectedItem().toString();
        mes=spinner_mes.getSelectedItem().toString();
        anio=spinner_año.getSelectedItem().toString();

        //fecha_registro



        fecha_cumpleaños = dia+"/"+mes+"/"+anio;

        fecha_registro = new Time(Time.getCurrentTimezone());
        fecha_registro.setToNow();
        int idia = fecha_registro.monthDay;
        int imes= fecha_registro.month+1;
        int iyear = fecha_registro.year;
        final String fecha_registro_sistema = idia + "/" + imes + "/" + iyear;
        final String genero ;
        String genero1 = null;

        if (radioButton_hombre.isChecked())
        {
            genero1 = "Hombre";
        }
        if (radioButton_mujer.isChecked())
        {
            genero1 = "Mujer";
        }
        if (radioButton_otro.isChecked())
        {
            genero1 = "Otro";
        }


        genero = genero1;
        //Toast.makeText(this, ""+fecha_registro_sistema, Toast.LENGTH_SHORT).show();


        // para la base de datos
        final Map<String, Object> userdb = new HashMap<>();


        //verificamos que las cajas de texto no esten vacias
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Debe colocar un Email en la caja de Email.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "Debe colocar una Contraseña en la caja de contraseña.", Toast.LENGTH_SHORT).show();
            return;
        }

        //en caso de que se lleno las cajas de usuario y contrasenia
        //se mostrara la barra de progreso
        progresDialog.setMessage("Realizando registro en linea...");
        progresDialog.show();

        //creamos a un nuevo usuario
        FirebaseUser user = firebaseAuth.getCurrentUser();



        final boolean[] nickname_existe = new boolean[1];

        ///
        DocumentReference docRef = db.collection("Usuarios").document(nikcname);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        nickname_existe[0] = true;

                        Toast.makeText(activity_registro.this, "El NickName que trata de registrar Ya esta siendo Usado", Toast.LENGTH_SHORT).show();
                    } else {
                        nickname_existe[0]=false;

                        ////////////////////////



                        agregaDatos( nikcname,email, pass,  userdb,  nombre,  Apellidos,  genero,  fecha_cumpleaños,  fecha_registro_sistema,  nikcname);

                        ////////////


                    }

                    progresDialog.dismiss();
                } else {
                    Log.d("______", "__________________NOOO ni idea ______________________", task.getException());
                }
            }
        });
        //Toast.makeText(activity_registro.this, "id = "+ idDocument, Toast.LENGTH_SHORT).show();
        /////




    }

    public void agregaDatos(final String nickname,final String email, final String pass, final Map<String, Object> userdb, final String nombre, final String Apellidos, final String genero, final String fecha_cumpleaños, final String fecha_registro_sistema, final String nikcname)
    {
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(activity_registro.this, "Se registro el usuario Correctamente...", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            // para la base de datos
                            userdb.put("Nombre",nombre);
                            userdb.put("Apellidos", Apellidos);
                            userdb.put("Email",email);
                            userdb.put("Contraseña",pass);
                            userdb.put("IDUser", user.getUid());
                            userdb.put("Genero", genero);
                            userdb.put("Cumpleaños", fecha_cumpleaños);
                            userdb.put("fecha registro", fecha_registro_sistema);
                            userdb.put("nickname", nickname);




                            db.collection("Usuarios").document(nikcname)
                                    .set(userdb)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            Toast.makeText(activity_registro.this, "Datos agregados correctamente a la base de datos", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(activity_registro.this, "Datos NO agregados correctamente a la base de datos", Toast.LENGTH_SHORT).show();
                                        }
                                    });
//                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                        @Override
//                                        public void onSuccess(DocumentReference documentReference) {
//                                            Toast.makeText(activity_registro.this, "Datos agregados correctamente a la base de datos", Toast.LENGTH_SHORT).show();
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            // en caso de que falle
//                                        }
//                                    });
                            //
                            user.sendEmailVerification();
                        }
                        else
                        {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException)//si se presenta una colicion
                            {
                                Toast.makeText(activity_registro.this, "El usuario(Correo) que intenta registrar ya se encuentra registrado", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(activity_registro.this, "No se pudo registrar el usuario, intentelo nuevamente", Toast.LENGTH_SHORT).show();
                            }

                        }
                        progresDialog.dismiss();
                    }
                });

        //firebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, RegistroDeUsuarios.class);
        startActivity(intent);
    }
}