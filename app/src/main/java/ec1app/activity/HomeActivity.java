package ec1app.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ec1app.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {

    private Button boton;
    MaterialButton butonsnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        boton = findViewById(R.id.botonsalir);
        butonsnack = findViewById(R.id.buttoncompras);

        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            AlertDialog.Builder alerta=new AlertDialog.Builder(HomeActivity.this);
            alerta.setMessage("¿Deseas cerrar sesión?")
                    .setCancelable(false)
                    .setPositiveButton("Si",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog,int which){
                    finish();
                }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog,int which){
                    dialog.cancel();
                }
                });

                AlertDialog titulo = alerta.create();
                titulo.setTitle("SALIR");
                titulo.show();
            }
        });

        butonsnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, "Aún estamos construyendo esa opción", Snackbar.LENGTH_LONG);
                snackbar.setDuration(1000);
                snackbar.setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar snackbar1 = Snackbar.make(v, "Gracias por tu comprensión", Snackbar.LENGTH_LONG);
                        snackbar1.show();
                    }
                });
                snackbar.show();
            }
        });

    }
}