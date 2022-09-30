package ec1app.activity.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ec1app.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import ec1app.activity.model.CategoriaModel;


public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder>{

    ArrayList<CategoriaModel> categoriaModel;

    public CategoriaAdapter(ArrayList<CategoriaModel> categoriaModel) {
        this.categoriaModel = categoriaModel;
    }

    @NonNull
    @Override
    public CategoriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoria_item,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaAdapter.ViewHolder holder, int position) {

        holder.nombrecat.setText(categoriaModel.get(position).getNomcat());
        String picURL="";
        switch (position){
            case 0:{
                picURL="cat_1";
                holder.mainLayaout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_borde));

                break;
            }
            case 1:{
                picURL="cat_2";
                holder.mainLayaout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_borde1));
                break;
            }
            case 2:{
                picURL="cat_3";
                holder.mainLayaout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_borde2));
                break;
            }
            case 3:{
                picURL="cat_4";
                holder.mainLayaout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_borde1));
                break;
            }
            case 4:{
                picURL="cat_5";
                holder.mainLayaout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_borde2));
                break;
            }

        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picURL,"drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.photocat);
    }

    @Override
    public int getItemCount() {
        return categoriaModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombrecat;
        ImageView photocat;
        ConstraintLayout mainLayaout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombrecat = itemView.findViewById(R.id.textcat);
            photocat = itemView.findViewById(R.id.imagecat);
            mainLayaout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
