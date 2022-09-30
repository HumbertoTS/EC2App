package ec1app.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ec1app.R;
import com.google.android.material.button.MaterialButton;

import ec1app.activity.ToolsCarShop.ManagementCart;
import ec1app.activity.Interface.ChangeNumberListener;
import ec1app.activity.adapter.CarritoAdapter;

public class CarritoActivity extends AppCompatActivity {

    //declaracion de nombres de variables
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView itemtotaltxt, deliverytotaltxt, dscttxt, totalprice, emptytxt;
    private double dsct;
    private ScrollView scrollView;
    LinearLayout homebtn, perfilbtn, helpbtn, exitbtn, carritoly;
    MaterialButton btnpedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        managementCart = new ManagementCart(this);
        initView(); //inicia las variables por id de los componentes
        initList(); //inicializador de lista de productos
        calculoprecio(); //calculos de los precios de los productos
        buttonnavigation(); //acciones en el boton de navegación
        deliverygo(); //ir a la pestaña de confirmación de pedido
    }

    //declarando las variable por su id
    private void initView() {

        totalprice = findViewById(R.id.pricetotal);
        dscttxt = findViewById(R.id.pricedsct);
        itemtotaltxt = findViewById(R.id.priceitem);
        deliverytotaltxt = findViewById(R.id.pricedelivery);
        emptytxt = findViewById(R.id.emptytxt);
        scrollView = findViewById(R.id.scrollViewcart);
        btnpedido = findViewById(R.id.pedidodeli);
        recyclerViewList = findViewById(R.id.recyclercart);

        //variables de la barra nav
        homebtn=findViewById(R.id.casabtn);
        perfilbtn = findViewById(R.id.perfilbtn);
        helpbtn = findViewById(R.id.helpbtn);
        exitbtn = findViewById(R.id.exittbtn);
    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CarritoAdapter(managementCart.getListCart(), this, new ChangeNumberListener(){
            @Override
            public void changed() {
                calculoprecio();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptytxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else if(managementCart.getListCart().remove(recyclerViewList)){
            emptytxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else{
            emptytxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculoprecio(){
        double desctporcentaje = 0.02;
        double delivery = 10;

        dsct = Math.round((managementCart.getTotalprice()*desctporcentaje)*100)/100;
        double total = Math.round((managementCart.getTotalprice()+delivery-dsct)*100)/100;
        double itemtotal = Math.round(managementCart.getTotalprice());

        itemtotaltxt.setText("S/."+itemtotal);
        dscttxt.setText("S/."+dsct);
        deliverytotaltxt.setText("S/."+delivery);
        totalprice.setText("S/."+total);
    }

    private void buttonnavigation(){

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarritoActivity.this, HomeActivity.class));
            }
        });

        perfilbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarritoActivity.this, PerfilActivity.class));
            }
        });

        helpbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(CarritoActivity.this, LocalActivity.class));
                    }
                });

        exitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //accion de cerrar sesión
                AlertDialog.Builder alerta=new AlertDialog.Builder(CarritoActivity.this);
                alerta.setMessage("¿Deseas cerrar sesión?")
                        .setCancelable(false)
                        .setPositiveButton("Si",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog,int which){
                                startActivity(new Intent(CarritoActivity.this, LoginActivity.class));
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

    private void deliverygo(){

        btnpedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarritoActivity.this, DeliveryActivity.class);
                startActivity(intent);
            }
        });

    }
}