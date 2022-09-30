package ec1app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ec1app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import ec1app.activity.database.FoodBD;
import ec1app.activity.database.Userdb;

public class RegisterActivity extends AppCompatActivity {

    //declarando variables
    TextView textLogin;
    MaterialButton buttonRegister, btndni;
    TextInputLayout InputLayoutFullName, InputLayoutNickname, InputLayoutPhoneNumber, InputLayoutEmail, InputLayoutPassword, InputLayoutAdress, InputLayoutDate, InputLayoutdni;
    TextInputEditText InputFullName, Inputnickname, InputphoneNumber, InputEmail, InputPassword, Inputadress, Inputdate, Inputdni;
    String TextFullName, TextNickname, TextPhoneNumber, TextEmail, TextPassword, Textdate, Textadress, TextDNI;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);


        //declaranción de las variables con los id de la vista login, editText, botón y inputlayout
        textLogin = findViewById(R.id.textLogIn);
        buttonRegister = findViewById(R.id.register_button);
        btndni = findViewById(R.id.button_dni);

        //Input del Material

        InputLayoutFullName = findViewById(R.id.register_inputLayout_fullName);
        InputLayoutNickname = findViewById(R.id.register_inputLayout_nickname);
        InputLayoutPhoneNumber = findViewById(R.id.register_inputLayout_phoneNumber);
        InputLayoutAdress = findViewById(R.id.register_inputLayout_adrees);
        InputLayoutDate = findViewById(R.id.register_inputLayout_date);
        InputLayoutEmail = findViewById(R.id.register_inputLayout_correo);
        InputLayoutPassword = findViewById(R.id.register_inputLayout_password);
        InputLayoutdni = findViewById(R.id.register_inputLayout_dni);

        //EditText del Material
        InputFullName = findViewById(R.id.register_input_fullName);
        Inputnickname = findViewById(R.id.register_input_nickname);
        InputphoneNumber = findViewById(R.id.register_input_phoneNumber);
        Inputadress = findViewById(R.id.register_input_adress);
        Inputdate = findViewById(R.id.register_input_date);
        InputEmail = findViewById(R.id.register_input_correo);
        InputPassword = findViewById(R.id.register_input_password);
        Inputdni = findViewById(R.id.register_input_dni);


        //Enviar al usuario a la ventana de login
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //registro de nuevo usuario
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Captura de valores de los textfield
                TextDNI = Inputdni.getText().toString();
                TextFullName = InputFullName.getText().toString();
                TextNickname = Inputnickname.getText().toString();
                TextPhoneNumber = InputphoneNumber.getText().toString();
                Textadress = Inputadress.getText().toString();
                Textdate = Inputdate.getText().toString();
                TextEmail = InputEmail.getText().toString();
                TextPassword = InputPassword.getText().toString();

                Log.e("DNI", TextDNI);
                Log.e("Nombres", TextFullName);
                Log.e("Apellidos", TextNickname);
                Log.e("Telefono", TextPhoneNumber);
                Log.e("Direccion", Textadress);
                Log.e("Fech Nac", Textdate);
                Log.e("Correo", TextEmail);
                Log.e("Contraseña", TextPassword);

                if (TextUtils.isEmpty(TextDNI) || TextUtils.isEmpty(TextFullName) || TextUtils.isEmpty(TextNickname) || TextUtils.isEmpty(TextPhoneNumber)
                        || TextUtils.isEmpty(Textadress) || TextUtils.isEmpty(Textdate)  ||  TextUtils.isEmpty(TextEmail) || TextUtils.isEmpty(TextPassword)) {
                    if (TextUtils.isEmpty(TextDNI)) {
                        InputLayoutdni.setError("Ingrese su DNI");
                    } else {
                        InputLayoutdni.setError(null);
                    }
                    if (TextUtils.isEmpty(TextFullName)) {
                        InputLayoutFullName.setError("Ingrese sus Nombres completos");
                    } else {
                        InputLayoutFullName.setError(null);
                    }
                    if (TextUtils.isEmpty(TextNickname)) {
                        InputLayoutNickname.setError("Ingrese sus Apellidos");
                    } else {
                        InputLayoutNickname.setError(null);
                    }
                    if (TextUtils.isEmpty(TextPhoneNumber)) {
                        InputLayoutPhoneNumber.setError("Ingrese su teléfono");
                    } else {
                        InputLayoutPhoneNumber.setError(null);
                    }
                    if (TextUtils.isEmpty(Textadress)) {
                        InputLayoutAdress.setError("Ingrese su dirección");
                    } else {
                        InputLayoutAdress.setError(null);
                    }
                    if (TextUtils.isEmpty(Textdate)) {
                        InputLayoutDate.setError("Ingrese su Fecha de nacimiento");
                    } else {
                        InputLayoutDate.setError(null);
                    }
                    if (TextUtils.isEmpty(TextEmail)) {
                        InputLayoutEmail.setError("Ingrese su correo");
                    } else {
                        InputLayoutEmail.setError(null);
                    }
                    if (TextUtils.isEmpty(TextPassword)) {
                        InputLayoutPassword.setError("Ingrese una contraseña");
                    } else if (TextPassword.length() >= 6){
                        InputLayoutPassword.setError("Debe ingresar una contrasela mínima de 6 caracteres");
                    } else {
                        InputLayoutPassword.setError(null);
                    }
                } else {
                    registrarusuario ();
                    //Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    //startActivity(intent);
                    //Toast.makeText(getApplicationContext(),"Registro correcto", Toast.LENGTH_LONG).show();
                }
            }

        }); // fin de registro de usuario

        //OnClick de Buscar DNI
        btndni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextDNI = Inputdni.getText().toString();
                if (TextUtils.isEmpty(TextDNI)) {
                    Toast.makeText(RegisterActivity.this, "Ingrese su DNI", Toast.LENGTH_LONG).show();
                }
                else {
                    consultardni();
                }
            }
        }); //Fin OnClick de Buscar DNI

    } //Fin onCreate

    //Método de Api Consulta DNI
    public void consultardni(){

        TextDNI = Inputdni.getText().toString();
        String api = "https://api.apis.net.pe/v1/dni?numero="+TextDNI;

        StringRequest postRequest = new StringRequest(Request.Method.GET, api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        InputFullName.setText(jsonObject.getString("nombres"));
                        Inputnickname.setText(jsonObject.getString("apellidoPaterno") + " " + jsonObject.getString("apellidoMaterno"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(postRequest);
    }//Final Consulta DNI

    public void registrarusuario (){
         TextDNI = Inputdni.getText().toString();
         TextFullName = InputFullName.getText().toString();
         TextNickname = Inputnickname.getText().toString();
         TextPhoneNumber = InputphoneNumber.getText().toString();
          Textadress = Inputadress.getText().toString();
          Textdate = Inputdate.getText().toString();
          TextEmail = InputEmail.getText().toString();
         TextPassword = InputPassword.getText().toString();

        Userdb dbUsers = new Userdb(RegisterActivity.this);
        boolean exists = dbUsers.isUserExists(TextDNI, TextFullName, TextNickname, TextPhoneNumber, Textadress, Textdate, TextEmail,TextPassword);
        if (exists) {
            Toast.makeText(RegisterActivity.this, "Usuario ya existe", Toast.LENGTH_SHORT).show();
        }

        else {
            long id = dbUsers.insertUser(TextDNI, TextFullName, TextNickname, TextPhoneNumber, Textadress, Textdate, TextEmail, TextPassword);

            if (id > 0) {
                Log.d("ReisterActivity", "Usuario insertado de manera exitosa");
                mAuth.createUserWithEmailAndPassword(TextEmail, TextPassword)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "No se registró usuario", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                cleanFields();
            }
        }

    }

    private void cleanFields() {
        InputFullName.setText("");
        Inputnickname.setText("");
        InputphoneNumber.setText("");
        Inputadress.setText("");
        Inputdate.setText("");
        InputEmail.setText("");
        InputPassword.setText("");
        InputPassword.clearFocus();
    }


}
