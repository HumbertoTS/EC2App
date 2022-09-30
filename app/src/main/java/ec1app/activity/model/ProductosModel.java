package ec1app.activity.model;

import java.io.Serializable;

public class ProductosModel implements Serializable {

    private int idprod;
    private String nomprod;
    private String photoprod;
    private String descripprod;
    private Double preciprod;
    private int numbertInCart;

    public ProductosModel(int idprod, String nomprod, String photoprod, String descripprod, Double preciprod) {
        this.idprod = idprod;
        this.nomprod = nomprod;
        this.photoprod = photoprod;
        this.descripprod = descripprod;
        this.preciprod = preciprod;
    }

    public ProductosModel(int numbertInCart) {
        this.numbertInCart = numbertInCart;
    }

    public int getIdprod() {
        return idprod;
    }

    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }

    public String getNomprod() {
        return nomprod;
    }

    public void setNomprod(String nomprod) {
        this.nomprod = nomprod;
    }

    public String getPhotoprod() {
        return photoprod;
    }

    public void setPhotoprod(String photoprod) {
        this.photoprod = photoprod;
    }

    public String getDescripprod() {
        return descripprod;
    }

    public void setDescripprod(String descripprod) {
        this.descripprod = descripprod;
    }

    public Double getPreciprod() {
        return preciprod;
    }

    public void setPreciprod(Double preciprod) {
        this.preciprod = preciprod;
    }

    public int getNumbertInCart() {
        return numbertInCart;
    }

    public void setNumbertInCart(int numbertInCart) {
        this.numbertInCart = numbertInCart;
    }
}
