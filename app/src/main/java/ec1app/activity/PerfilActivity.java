package ec1app.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ec1app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class PerfilActivity extends AppCompatActivity {

    LinearLayout homebtn, helpbtn, exitbtn;
    FloatingActionButton carritobtn;
    LinearLayout perfileedit, passedit;
    private static final int PERMISSION_CODE = 1500;
    private static final int CAPTURE_CODE = 1200;
    private Button changeimagebtn;
    private ImageView perfileimage;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        initView();
        buttonnavigation();

        //Cargar foto de perfil
        perfileimage = findViewById(R.id.perfile_image);
        changeimagebtn = findViewById(R.id.changeimagen);
        changeimagebtn.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_DENIED ||
                        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                PackageManager.PERMISSION_DENIED) {
                    String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permission, PERMISSION_CODE);
                } else {
                    openCamera();
                }
            } else {
                openCamera();
            }
        });

        //Método para editar perfil
        perfileedit = findViewById(R.id.editperfile);
        perfileedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, "Aún estamos construyendo esa opción", Snackbar.LENGTH_LONG);
                snackbar.setDuration(1000);
                snackbar.show();
            }
        });

        //Método cambio de contraseña
        passedit = findViewById(R.id.passwordchange);
        passedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, "Aún estamos construyendo esa opción", Snackbar.LENGTH_LONG);
                snackbar.setDuration(1000);
                snackbar.show();
            }
        });
    }

    //Método de abrir cámara
    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, CAPTURE_CODE);
    }

    private void initView() {
        carritobtn = findViewById(R.id.deliverycartbtn);
        homebtn=findViewById(R.id.casabtn);
        helpbtn = findViewById(R.id.helpbtn);
        exitbtn = findViewById(R.id.exittbtn);
    }

    private void buttonnavigation(){

        carritobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PerfilActivity.this, CarritoActivity.class));
            }
        });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PerfilActivity.this, HomeActivity.class));
            }
        });


        helpbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(PerfilActivity.this, LocalActivity.class));
                    }
                });

        exitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //accion de cerrar sesión
                AlertDialog.Builder alerta=new AlertDialog.Builder(PerfilActivity.this);
                alerta.setMessage("¿Deseas cerrar sesión?")
                        .setCancelable(false)
                        .setPositiveButton("Si",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog,int which){
                                startActivity(new Intent(PerfilActivity.this, LoginActivity.class));
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
    }
}