package ec1app.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ec1app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LocalActivity extends AppCompatActivity {


    FloatingActionButton carritobtn;
    LinearLayout homebtn, perfilbtn, exitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        initView();
        buttonnavigation();
    }

    private void initView() {
        exitbtn = findViewById(R.id.exittbtn);
        perfilbtn = findViewById(R.id.perfilbtn);
        carritobtn = findViewById(R.id.deliverycartbtn);
        homebtn=findViewById(R.id.casabtn);
    }

    private void buttonnavigation() {

        //regresar a la vista home
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocalActivity.this, HomeActivity.class));
            }
        });

        //ver la lista de pedidos
        carritobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocalActivity.this, CarritoActivity.class));
            }
        });

        //ver perfil del usuario
        perfilbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocalActivity.this, PerfilActivity.class));
            }
        });


        //accion de cerrar sesión
        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(LocalActivity.this);
                alerta.setMessage("¿Deseas cerrar sesión?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //finish();
                                startActivity(new Intent(LocalActivity.this, LoginActivity.class));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog titulo = alerta.create();
                titulo.setTitle("SALIR");
                titulo.show();
            }
        });

    }
}