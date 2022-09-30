package ec1app.activity.ToolsCarShop;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import ec1app.activity.Interface.ChangeNumberListener;
import ec1app.activity.model.ProductosModel;

public class ManagementCart {

    private Context context;
    private MethodManagementExt methodManagementExt;

    public ManagementCart(Context context) {
        this.context = context;
        this.methodManagementExt = new MethodManagementExt(context);
    }

    //para el detalle pedido
    public void insertarPedido(ProductosModel item){
        ArrayList<ProductosModel> listprod = getListCart();
        boolean existAlready = false;
        int n=0;
        for (int i=0; i<listprod.size(); i++){
            if (listprod.get(i).getNomprod().equals(item.getNomprod())){
                existAlready = true;
                n = i;
                break;
            }
        }
        if (existAlready){
            listprod.get(n).setNumbertInCart(item.getNumbertInCart());
        }else {
            listprod.add(item);
        }
        methodManagementExt.putListObject("CartList", listprod);
        Toast.makeText(context,"Pedido agregado", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<ProductosModel> getListCart(){
        return methodManagementExt.getListObject("CartList");
    }

    public void plusnumber(ArrayList<ProductosModel> listprod, int position, ChangeNumberListener changeNumberListener){
        listprod.get(position).setNumbertInCart(listprod.get(position).getNumbertInCart() +1 );
        methodManagementExt.putListObject("Cartlist", listprod);
        changeNumberListener.changed();
    }

    public void minusnumber(ArrayList<ProductosModel> listprod, int position, ChangeNumberListener changeNumberListener){
        if (listprod.get(position).getNumbertInCart()==1){
            listprod.remove(position);
        }else {
            listprod.get(position).setNumbertInCart(listprod.get(position).getNumbertInCart() -1 );
        }
        methodManagementExt.putListObject("Cartlist", listprod);
        changeNumberListener.changed();
    }

    public Double getTotalprice(){
        ArrayList<ProductosModel> listprod = getListCart();
        double price = 0;
        for (int i=0; i<listprod.size(); i++){
            price = price+(listprod.get(i).getPreciprod()*listprod.get(i).getNumbertInCart());
        }
        return price;
    }
}
