/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daniel.aplicacion;

import daniel.modelo.CuentaDAO;
import daniel.modelo.CuentaVO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NitroPc
 */
public class Programa {

    public static void main(String[] args) {

        CuentaDAO daoCuenta = new CuentaDAO();
        List<CuentaVO> listaCuenta = new ArrayList<>();
        listaCuenta.add(new CuentaVO(1, "agustin@gmail.com", "agusto"));
        listaCuenta.add(new CuentaVO(2, "tedo@gmail.com", "tejero"));
        listaCuenta.add(new CuentaVO(3, "Manuela@gmail.com", "Cardeña"));
        listaCuenta.add(new CuentaVO(4, "Dober@gmail.com", "man"));
        try {
            
            System.out.println("Nº cuentas insertadas " + daoCuenta.insertCuenta(listaCuenta));
            System.out.println("-----------------------------------------");
            System.out.println("Comprobamos en una nueva lista que se recogen los datos desde la tabla.");
            List<CuentaVO> nuevaLista = daoCuenta.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("-----------------------------------------");
            System.out.println("Cuenta con primary key 1: ");
            System.out.println(daoCuenta.findByPk(1));
            System.out.println("-----------------------------------------");
            System.out.println("Se va a borrar la cuenta con pk 1");
            System.out.println("Nº cuenta borradas "
                    + daoCuenta.deleteCuenta(new CuentaVO(1, "agustin@gmail.com", "agusto")));
            System.out.println("-----------------------------------------");
            nuevaLista = daoCuenta.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D despues de borrar la cuenta 1 -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("-----------------------------------------");
            System.out.println("Modificación de la cuenta con pk 3");
            System.out.println("Nº Personas modificadas " + 
                    daoCuenta.updateCuenta(3, new CuentaVO(3, "Manuela@gmail.com", "Juana")));
            System.out.println("-----------------------------------------");
            System.out.println("Nº de cuentas borradas" + daoCuenta.deleteCuenta(1));
            nuevaLista = daoCuenta.getAll();
            System.out.println("-------- Lista con datos recogidos desde la B.D despues de modificar una cuenta y borrar la cuenta 1 -------------");
            nuevaLista.forEach(System.out::println);
            System.out.println("Cambio de la contraseña de la cuenta 2");
            System.out.println("Contaseña modificada: " + daoCuenta.updateCuenta(101, "tupepito"));
            nuevaLista = daoCuenta.getAll();
            System.out.println("\nListar datos de la base de datos:");
            nuevaLista.forEach(System.out::println);
            
            
        } catch (SQLException sqle) {

            System.out.println("No se ha podido realizar la operación:");
            System.out.println(sqle.getMessage());
        }
    }

}
