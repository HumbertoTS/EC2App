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

public class LoginActivity extends AppCompatActivity {
    TextView textRegister;
    MaterialButton buttonlogin;
    TextInputEditText InputEmail, InputPassword;
    TextInputLayout InputlayoutEmail, InputlayoutPassword;
    String TextEmail, TextPassword;

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

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

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
                    } else {
                        InputlayoutPassword.setError(null);
                    }
                } else {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Bievenido", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}