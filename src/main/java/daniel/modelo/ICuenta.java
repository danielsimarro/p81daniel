/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daniel.modelo;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author NitroPc
 */
public interface ICuenta {
    
    // Método para obtener todos los registros de la tabla
    List<CuentaVO> getAll() throws SQLException;
    
    // Méodo para obtener un registro a partir de la PK
    CuentaVO findByPk(int pk) throws SQLException;
    
    // Método para insertar un registro
    int insertCuenta (CuentaVO persona) throws SQLException;
    
    // Método para insertar varios registros
    int insertCuenta (List<CuentaVO> lista) throws SQLException;
    
    // Método para borrar una cuenta
    int deleteCuenta (CuentaVO p) throws SQLException;
    
    // Método para borrar una cuenta mediante una pk
    int deleteCuenta (int pk) throws SQLException;
    
    // Método para borrar toda la tabla
    int deleteCuenta() throws SQLException;
    
    // Método para modificar una cuenta. Se modifica a la persona que tenga esa 'pk'
    // con los nuevos datos que traiga la persona 'nuevosDatos'
    int updateCuenta (int pk, CuentaVO nuevosDatos) throws SQLException;
    
    //Metodo para modificar la contraseña de una cuenta mediante el 
    //la pk
    int updateCuenta (int pk, String nuevosContra) throws SQLException;
    
    
    
}
