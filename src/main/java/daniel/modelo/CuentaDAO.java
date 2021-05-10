/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daniel.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NitroPc
 */
public class CuentaDAO implements ICuenta {

    private Connection con = null;

    public CuentaDAO() {
        con = Conexion.getInstance();
    }

    //Este metodo nos va a permitir recorrer todas las filas de nuestra tabla,
    //donde guardaremos cada fila en una lista de objetos creada para la tabla a leer en especifico
    @Override
    public List<CuentaVO> getAll() throws SQLException {
        List<CuentaVO> listaCuenta = new ArrayList<>();

        //Usaremos el objeto Statement para consultar los datos
        //ya que no necesitamos parametros en la sentencia SQL
        try ( Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from cuenta");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                CuentaVO cuenta = new CuentaVO();
                // Recogemos los datos de la Cuenta, guardamos en un objeto
                //El argumeto que se le pasa al res es el nombre de los campos de la 
                //base de datos
                cuenta.setPk(res.getInt("codcuenta"));
                cuenta.setCorreoElectronico(res.getString("correoelectronico"));
                cuenta.setContrasena(res.getString("contrasena"));

                //Añadimos el objeto a la lista
                listaCuenta.add(cuenta);
            }
        }

        return listaCuenta;

    }

    //Este metodo nos va a permitir recorrer la fila de nuestra tabla que contenga las clave primaria pasada por parametro,
    //donde guardaremos la fila en un objeto creada para la tabla a leer en especifico
    @Override
    public CuentaVO findByPk(int pk) throws SQLException {

        ResultSet res = null;
        CuentaVO cuenta = new CuentaVO();

        String sql = "select * from cuenta where codcuenta=?";

        try ( PreparedStatement prest = con.prepareStatement(sql)) {
            // Preparamos la sentencia parametrizada
            prest.setInt(1, pk);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.next()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                cuenta.setPk(res.getInt("codcuenta"));
                cuenta.setCorreoElectronico(res.getString("correoelectronico"));
                cuenta.setContrasena(res.getString("contrasena"));
                return cuenta;
            }

            return null;
        }

    }

    //Este metodo nos va a permitir inserta en nuestra tabla de la base de datos 
    //una cuenta que le pasaremos por parametros
    @Override
    public int insertCuenta(CuentaVO cuenta) throws SQLException {
        int numFilas = 0;
        String sql = "insert into cuenta values (?,?,?)";

        if (findByPk(cuenta.getPk()) != null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try ( PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setInt(1, cuenta.getPk());
                prest.setString(2, cuenta.getCorreoElectronico());
                prest.setString(3, cuenta.getContrasena());

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    //Este metodo nos permitira insertar en la tabla de nuestra base de datos
    //una lista de cuentas que nosotros le pasemos por parametro
    @Override
    public int insertCuenta(List<CuentaVO> listaCuenta) throws SQLException {
        int numFilas = 0;

        for (CuentaVO lista : listaCuenta) {
            numFilas += insertCuenta(lista);
        }

        return numFilas;
    }

    //Este metodo nos permite borrar una tabla en especifico pasandole una cuenta
    @Override
    public int deleteCuenta(CuentaVO cuenta) throws SQLException {

        int numFilas = 0;

        String sql = "delete from cuenta where codcuenta = ?";

        // Sentencia parametrizada
        try ( PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setInt(1, cuenta.getPk());
            // Ejecutamos la sentencia
            numFilas = prest.executeUpdate();
        }
        return numFilas;

    }

    //Este metodo nos permite borrar todas las filas de nuestra tabla
    @Override
    public int deleteCuenta() throws SQLException {
        String sql = "delete from cuenta";

        int nfilas = 0;

        // Preparamos el borrado de datos  mediante un Statement
        // No hay parámetros en la sentencia SQL
        try ( Statement st = con.createStatement()) {
            // Ejecución de la sentencia
            nfilas = st.executeUpdate(sql);
        }

        // El borrado se realizó con éxito, devolvemos filas afectadas
        return nfilas;
    }

    //Este metodo nos permite modificar una cuenta mediante su clave primaria
    //pasandole los valores a modificar mediante un objeto cuenta
    @Override
    public int updateCuenta(int pk, CuentaVO nuevosDatos) throws SQLException {
        int numFilas = 0;
        String sql = "update cuenta set correoelectronico = ?, contrasena = ? where codcuenta=?";

        if (findByPk(pk) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try ( PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, nuevosDatos.getCorreoElectronico());
                prest.setString(2, nuevosDatos.getContrasena());
                prest.setInt(3, pk);

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }

    }

    //Este metodo nos permite borrar una tabla pasandole como parametro la pk    
    @Override
    public int deleteCuenta(int pk) throws SQLException {

        int numFilas = 0;

        String sql = "delete from cuenta where codcuenta = ?";

        if (findByPk(pk) == null) {
            // Existe un registro con esa pk
            // No se hace la inserción
            return numFilas;
        } else {
            // Sentencia parametrizada
            try ( PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setInt(1, pk);
                // Ejecutamos la sentencia
                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }

    @Override
    public int updateCuenta(int pk, String nuevosContra) throws SQLException {
        int numFilas = 0;
        String sql = "update cuenta set contrasena = ? where codcuenta=?";

        if (findByPk(pk) == null) {
            // La persona a actualizar no existe
            return numFilas;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try ( PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, nuevosContra);
                prest.setInt(2, pk);

                numFilas = prest.executeUpdate();
            }
            return numFilas;
        }
    }


}
