package modelo;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class UsuarioDAO {

    private Connection conexion;

    private void abrirConexion() throws SQLException {

        String dbURI = "jdbc:derby://localhost:1527/BIBLIOTECA3";
        Properties prop = new Properties();

        try {
            prop.load(getClass().getResourceAsStream("server.properties"));
        } catch (IOException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String username = prop.getProperty("usuario");
        String password = prop.getProperty("contrasena");

        conexion = DriverManager.getConnection(dbURI, username, password);
    }

    private void cerrarConexion() throws SQLException {

        conexion.close();

    }

    public void registrar(UsuariosPOJO pojo) throws SQLException {

        try {
            abrirConexion();

            String cadena = "INSERT into USUARIOS values ('" + pojo.getUsuario() + "','" + pojo.getPassword() + "','" + pojo.getNombre() + "')";
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(cadena);
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean login(UsuariosPOJO pojo) throws SQLException {
        abrirConexion();
        String sql = "select * from USUARIOS where USUARIO= ?  and PASSWORD= ?";
        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setString(1, pojo.getUsuario());
        ps.setString(2, pojo.getPassword());
        ResultSet rs = ps.executeQuery();
        boolean bo = rs.next();
        cerrarConexion();
        return bo;
    }

    public String user(UsuariosPOJO POJO) throws SQLException {
        abrirConexion();
        String consulta = "SELECT * from USUARIOS where USUARIO= ?  and PASSWORD= ?";
        String nombre= " ";
        PreparedStatement ps = conexion.prepareStatement(consulta);
        ps.setString(1, POJO.getUsuario());
        ps.setString(2, POJO.getPassword());

        ResultSet resultado = ps.executeQuery();
        while (resultado.next()) {
            if(resultado.getString("USUARIO").equals(POJO.getUsuario())){
                nombre=resultado.getString("NOMBRE");
                break;
            }
        }
        cerrarConexion();
        return nombre;
    }

}
