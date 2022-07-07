package ec1app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ec1app.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    //declarando variables
    TextView textLogin;
    MaterialButton buttonRegister;
    TextInputLayout InputLayoutFullName, InputLayoutNickname, InputLayoutPhoneNumber, InputLayoutEmail, InputLayoutPassword;
    TextInputEditText InputFullName, Inputnickname, InputphoneNumber, InputEmail, InputPassword;
    String TextFullName, TextNickname, TextPhoneNumber, TextEmail, TextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //declaranción de las variables con los id de la vista login, editText, botón y inputlayout
        textLogin = findViewById(R.id.textLogIn);
        buttonRegister = findViewById(R.id.register_button);
        InputLayoutFullName = findViewById(R.id.register_inputLayout_fullName);
        InputLayoutNickname = findViewById(R.id.register_inputLayout_nickname);
        InputLayoutPhoneNumber = findViewById(R.id.register_inputLayout_phoneNumber);
        InputLayoutEmail = findViewById(R.id.register_inputLayout_correo);
        InputLayoutPassword = findViewById(R.id.register_inputLayout_password);
        InputFullName = findViewById(R.id.register_input_fullName);
        Inputnickname = findViewById(R.id.register_input_nickname);
        InputphoneNumber = findViewById(R.id.register_input_phoneNumber);
        InputEmail = findViewById(R.id.register_input_correo);
        InputPassword = findViewById(R.id.register_input_password);

        //Enviar al usuario a la ventana de login
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Captura de valores de los textfield
                TextFullName = InputFullName.getText().toString();
                TextNickname = Inputnickname.getText().toString();
                TextPhoneNumber = InputphoneNumber.getText().toString();
                TextEmail = InputEmail.getText().toString();
                TextPassword = InputPassword.getText().toString();
                Log.e("Nombres", TextFullName);
                Log.e("Apellidos", TextNickname);
                Log.e("Telefono", TextPhoneNumber);
                Log.e("Correo", TextEmail);
                Log.e("Contraseña", TextPassword);

                if (TextUtils.isEmpty(TextFullName) || TextUtils.isEmpty(TextNickname) || TextUtils.isEmpty(TextPhoneNumber) || TextUtils.isEmpty(TextEmail) || TextUtils.isEmpty(TextPassword)) {
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
                    if (TextUtils.isEmpty(TextEmail)) {
                        InputLayoutEmail.setError("Ingrese su correo");
                    } else {
                        InputLayoutEmail.setError(null);
                    }
                    if (TextUtils.isEmpty(TextPassword)) {
                        InputLayoutPassword.setError("Ingrese una contraseña");
                    } else {
                        InputLayoutPassword.setError(null);
                    }
                } else {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Registro correcto", Toast.LENGTH_LONG).show();
                }
            }

        });

    }
}