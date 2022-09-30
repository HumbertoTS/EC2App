package ec1app.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ec1app.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import ec1app.activity.adapter.CategoriaAdapter;
import ec1app.activity.adapter.ProductosAdapter;
import ec1app.activity.model.CategoriaModel;
import ec1app.activity.model.ProductosModel;

public class HomeActivity extends AppCompatActivity {

    ImageView boton, perfil;
    private RecyclerView.Adapter adapter, adapterprod;
    private RecyclerView recyclerViewCatList, recylcleViewProdList;
    FloatingActionButton carritobtn;
    LinearLayout helpbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewCategoria();
        recyclerViewProductos();
        initView();
        buttonnavigation();

    }

    private void initView() {
        boton = findViewById(R.id.exittext);
        perfil = findViewById(R.id.perfil);
        carritobtn = findViewById(R.id.deliverycartbtn);
        helpbtn = findViewById(R.id.helpbtn);
    }

    private void buttonnavigation() {
        //ver la lista de pedidos
        carritobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CarritoActivity.class));
            }
        });

        //ver perfil del usuario
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PerfilActivity.class));
            }
        });

        //ver vista local
        helpbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(HomeActivity.this, LocalActivity.class));
                    }
                });

        //accion de cerrar sesión
        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta=new AlertDialog.Builder(HomeActivity.this);
                alerta.setMessage("¿Deseas cerrar sesión?")
                        .setCancelable(false)
                        .setPositiveButton("Si",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog,int which){
                                //finish();
                                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
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

    //agregar datos a la categoria
    private void recyclerViewCategoria() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCatList = findViewById(R.id.recyclercat);
        recyclerViewCatList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoriaModel> categoria = new ArrayList<>();
        categoria.add(new CategoriaModel(1,"cat_1", "Hamburguesas"));
        categoria.add(new CategoriaModel(2,"cat_2", "Broaster"));
        categoria.add(new CategoriaModel(3,"cat_3", "Salchipapas"));
        categoria.add(new CategoriaModel(4,"cat_4", "Mostros"));
        categoria.add(new CategoriaModel(5,"cat_5", "Criollo"));


        adapter = new CategoriaAdapter(categoria);
        recyclerViewCatList.setAdapter(adapter);
    }

    //agregar datos de productos
    private void recyclerViewProductos(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recylcleViewProdList = findViewById(R.id.recyclerprod);
        recylcleViewProdList.setLayoutManager(linearLayoutManager);


        ArrayList<ProductosModel> foodlist = new ArrayList<>();
        foodlist.add(new ProductosModel(1,"Hamburguesa Clásica","hamburclasica","Lechuga, tomate, hamburguesa casera de 120gr. y crocantes papas fritas",8.00));
        foodlist.add(new ProductosModel(2,"Hamburguesa Royal","royaaal","Lechuga, tomate, hamburguesa casera de 120gr., queso, huevo y crocantes papas fritas",11.00));
        foodlist.add(new ProductosModel(3,"Pechuga Broaster","pechugabroster","100gr de deliciosa pechiga broaster acompañada con ensalada y papas fritas",8.00));
        foodlist.add(new ProductosModel(4,"Alita Broaster","alitabroster","Crocante y jugosa alita broaster acompañada con ensalada y papas fritas",7.00));
        foodlist.add(new ProductosModel(5,"Salchipapa Clásica","salchipapa","Hot dog y crocantes papas fritas",8.00));
        foodlist.add(new ProductosModel(6,"Salchi Royal","salchiroyal","Hot dog, salchicha ahumada, queso, huevo, crocantes papas fritas y ensalada",13.00));
        foodlist.add(new ProductosModel(7,"Ala Mostro","alamostro","Alista broaster, delicioso arroz chaufa y crocantes papas fritas",10.00));
        foodlist.add(new ProductosModel(8,"Pechuga Mostro","pechugamostro","Pechuga broaster, delicioso arroz chaufa y crocantes papas fritas",11.00));
        foodlist.add(new ProductosModel(8,"Lomo Saltado","lomosaltado","Trozos de carne con un delicioso sabor ahumado, salteado con frescas hortalizas, acompañado con arroz graneado y crocantes papas fritas",15.00));
        foodlist.add(new ProductosModel(8,"Pollo Saltado","pollosaltado","Trozos de pollo salteado con frescas hortalizas, acompañado con arroz graneado y crocantes papas fritas",14.00));
        foodlist.add(new ProductosModel(8,"Arroz Chaufa","chifa","Arroz graneado salteado con pollo y frescas verduras en menjunje oriental",15.00));
        foodlist.add(new ProductosModel(8,"Aeropuesto","chifaaeropuerto","Arroz graneado salteado con verduras orientales, trozos de pollo en el menjunje tradicional del chef",16.00));

        adapterprod = new ProductosAdapter(foodlist);
        recylcleViewProdList.setAdapter(adapterprod);
    }
}