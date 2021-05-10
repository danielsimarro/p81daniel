/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daniel.modelo;

/**
 *
 * @author NitroPc
 */
public class CuentaVO {
    
    //Atributos para almacenar cada columna de la tabla a leer
    private int pk;
    private String correoElectronico;
    private String contrasena;
    
    //Constructor parametrizado
    public CuentaVO(int pk, String correoElectronico, String contrasena) {
        this.pk = pk;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
    }

    //Constructor por defecto
    public CuentaVO() {
    }

    
    //Getter y Setters
    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    //ToString
    @Override
    public String toString() {
        return "CuentaVO{" + "pk=" + pk + ", correoElectronico=" + correoElectronico + ", contrasena=" + contrasena + '}';
    }
   
   
}
