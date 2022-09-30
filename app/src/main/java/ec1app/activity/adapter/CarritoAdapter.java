package ec1app.activity.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

import ec1app.activity.ToolsCarShop.ManagementCart;
import ec1app.activity.Interface.ChangeNumberListener;
import ec1app.activity.model.ProductosModel;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {

    private ArrayList<ProductosModel> productosModels;
    private ManagementCart managementCart;
    private ChangeNumberListener changeNumberListener;

    public CarritoAdapter(ArrayList<ProductosModel> productosModels, Context context, ChangeNumberListener changeNumberListener) {
        this.productosModels = productosModels;
        this.managementCart = new ManagementCart(context);
        this.changeNumberListener = changeNumberListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.carrito_item, parent,false);
        return new ViewHolder(inflate).link(this);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(productosModels.get(position).getNomprod());
        //holder.productositem.setText(String.valueOf(productosModels.get(position).getPreciprod()));
        //holder.deleteitem.removeOnLayoutChangeListener((View.OnLayoutChangeListener) productosModels.get(position));
        holder.totalitem.setText(String.valueOf(Math.round((productosModels.get(position).getNumbertInCart()*
                                                           productosModels.get(position).getPreciprod())*100.0)/100.0));
        holder.num.setText(String.valueOf(productosModels.get(position).getNumbertInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(productosModels.get(position).getPhotoprod(),
                                    "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.prodphoto);

        //Aumentar la cantidad del producto
       /* holder.plusitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusnumber(productosModels, position, new ChangeNumberListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberListener.changed();
                    }
                });
            }
        });
        //Disminuir la cantidad del producto
        holder.minusitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusnumber(productosModels, position, new ChangeNumberListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberListener.changed();
                    }
                });
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return productosModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, deleteitem;
        ImageView prodphoto, plusitem, minusitem;
        TextView totalitem, num;

        private CarritoAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titletxt);
            itemView.findViewById(R.id.delete).setOnClickListener(v -> {
                adapter.productosModels.remove(getAdapterPosition());
                adapter.notifyItemRemoved(getAdapterPosition());
            });
            prodphoto = itemView.findViewById(R.id.picprod);
            totalitem = itemView.findViewById(R.id.totalcosto);
            num = itemView.findViewById(R.id.numbprod);
            //plusitem = itemView.findViewById(R.id.plusbtn);
            //minusitem = itemView.findViewById(R.id.minusbtn);
        }

        public ViewHolder link(CarritoAdapter adapter){
                this.adapter = adapter;
                return this;
        }

    }
}
