package ec1app.activity.adapter;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ec1app.R;

import java.util.ArrayList;

import ec1app.activity.DetallePedidoActivity;
import ec1app.activity.model.ProductosModel;


public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder>{

    ArrayList<ProductosModel> productosModels;

    public ProductosAdapter(ArrayList<ProductosModel> productoModel) {
        this.productosModels = productoModel;
    }

    @NonNull
    @Override
    public ProductosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.producto_item,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(productosModels.get(position).getNomprod());
        holder.price.setText(String.valueOf(productosModels.get(position).getPreciprod()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(productosModels.get(position).getPhotoprod(),"drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.photoprod);


        holder.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetallePedidoActivity.class);
                intent.putExtra("object",productosModels.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productosModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, price;
        ImageView photoprod;
        TextView addbtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.pedidotitle);
            photoprod = itemView.findViewById(R.id.photoprod);
            price = itemView.findViewById(R.id.pricetitle);
            addbtn = itemView.findViewById(R.id.addbtn);
        }
    }
}
