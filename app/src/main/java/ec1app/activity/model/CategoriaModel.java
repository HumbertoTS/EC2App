package ec1app.activity.model;

public class CategoriaModel {

    private int idcat;
    private String catphoto;
    private String nomcat;

    public CategoriaModel(int idcat, String catphoto, String nomcat) {
        this.idcat = idcat;
        this.catphoto = catphoto;
        this.nomcat = nomcat;
    }

    public int getIdcat(){ return idcat; }

    public void setIdcat(int idcat){this.idcat = idcat;}

    public String getCatphoto() {
        return catphoto;
    }

    public void setCatphoto(String catphoto) {
        this.catphoto = catphoto;
    }

    public String getNomcat() {
        return nomcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }
}
