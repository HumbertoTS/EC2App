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

import com.example.ec1app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    TextView textRegister;
    MaterialButton buttonlogin;
    TextInputEditText InputEmail, InputPassword;
    TextInputLayout InputlayoutEmail, InputlayoutPassword;
    String TextEmail, TextPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textRegister = findViewById(R.id.textRegister);
        buttonlogin = findViewById(R.id.login_button);
        InputlayoutEmail = findViewById(R.id.login_inputlayout_email);
        InputlayoutPassword = findViewById(R.id.login_inputlayout_password);
        InputEmail = findViewById(R.id.login_input_email);
        InputPassword = findViewById(R.id.login_input_password);

        mAuth = FirebaseAuth.getInstance();

        //redirige a la vista register
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //acción de login
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextEmail = InputEmail.getText().toString();
                TextPassword = InputPassword.getText().toString();
                Log.e("Correo", TextEmail);
                Log.e("Contraseña", TextPassword);

                if (TextUtils.isEmpty(TextEmail) || TextUtils.isEmpty(TextPassword)) {
                   if (TextUtils.isEmpty(TextEmail)) {
                       InputlayoutEmail.setError("Ingrese su correo");
                    } else {
                       InputlayoutEmail.setError(null);
                    }
                    if (TextUtils.isEmpty(TextPassword)) {
                        InputlayoutPassword.setError("Ingrese una contraseña");

                    } else if (TextPassword.length() >= 6){
                        InputlayoutPassword.setError("Debe ingresar una contrasela mínima de 6 caracteres");
                    }else {
                        InputlayoutPassword.setError(null);
                    }
                } else {
                    //Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    //startActivity(intent);

                    ingresar();

                }
            }
        });

        //cuando el usuario empiece a escribir en el campo usuario, ya no figure el error
        InputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                InputlayoutEmail.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //cuando el usuario empiece a escribir en el campo contraseña, ya no figure el error
        InputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                InputlayoutPassword.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void ingresar(){
        TextEmail = InputEmail.getText().toString();
        TextPassword = InputPassword.getText().toString();

            mAuth.signInWithEmailAndPassword(TextEmail, TextPassword)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                if(user != null){
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(),"Bienvenido", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Usuario/Contraseña inválido", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }


}