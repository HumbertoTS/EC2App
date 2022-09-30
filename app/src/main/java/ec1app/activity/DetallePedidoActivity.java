package ec1app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ec1app.R;

import ec1app.activity.ToolsCarShop.ManagementCart;
import ec1app.activity.model.ProductosModel;

public class DetallePedidoActivity extends AppCompatActivity {

    private TextView addtocartbtn;
    private TextView titletxt, pricetxt, descriptiontxt, numberordertxt;
    private ImageView plusbtn, minusbtn, picprod;
    private ProductosModel object;
    int numberOrder=1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido);

        initView();
        getBundle();
        managementCart = new ManagementCart(this);
    }

    private void getBundle() {

        object = (ProductosModel) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPhotoprod(),"drawable",this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(picprod);

        titletxt.setText(object.getNomprod());
        pricetxt.setText("S/."+object.getPreciprod());
        descriptiontxt.setText(object.getDescripprod());
        numberordertxt.setText(String.valueOf(numberOrder));
        //Aumentar la cantidad del producto
        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder=numberOrder+1;
                numberordertxt.setText(String.valueOf(numberOrder));
            }
        });
        //Disminuir la cantidad del producto
        minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder>1){
                    numberOrder = numberOrder-1;
                }
                numberordertxt.setText(String.valueOf(numberOrder));
            }
        });

        //agregar al carrito el pedido
        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumbertInCart(numberOrder);
                managementCart.insertarPedido(object);

                startActivity(new Intent(DetallePedidoActivity.this, HomeActivity.class));
            }
        });
    }

    private void initView() {
        addtocartbtn = findViewById(R.id.adddep);
        titletxt = findViewById(R.id.titledetped);
        pricetxt = findViewById(R.id.pricesymboldep);
        descriptiontxt = findViewById(R.id.descriptionprod);
        numberordertxt = findViewById(R.id.textcount);
        plusbtn = findViewById(R.id.imageplus);
        minusbtn = findViewById(R.id.imageminus);
        picprod = findViewById(R.id.picprod);

    }
}