package ec1app.activity.model;

import java.util.Date;

public class UsuarioModel {

    int idusuario;

    int telefono;

    String nombre, apellidos, direccion, email, contrasena;

    Date fecnac;

    public UsuarioModel() {
    }

    public UsuarioModel(int telefono, String nombre, String apellidos, String direccion, String email, String password, Date fecnac) {
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.email = email;
        this.contrasena = password;
        this.fecnac = fecnac;
    }

    public boolean isNull(){
        if (nombre.equals("")&&apellidos.equals("")&&direccion.equals("")&&email.equals("")&&contrasena.equals("")&&fecnac.equals("")){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "idusuario=" + idusuario +
                ", telefono=" + telefono +
                ", Nombre='" + nombre + '\'' +
                ", Apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", password='" + contrasena + '\'' +
                ", fecnac=" + fecnac +
                '}';
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFecnac() {
        return fecnac;
    }

    public void setFecnac(Date fecnac) {
        this.fecnac = fecnac;
    }
}
