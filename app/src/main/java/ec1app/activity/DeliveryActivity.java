package ec1app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ec1app.R;
import com.google.android.material.button.MaterialButton;

public class DeliveryActivity extends AppCompatActivity {

    MaterialButton returnhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        initView(); //inicializador de los componenters
        returnhome(); //método para retornar a la vista home
    }

    private void initView(){
        returnhome = findViewById(R.id.returnhome);
    }

    private void returnhome(){

        returnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveryActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}