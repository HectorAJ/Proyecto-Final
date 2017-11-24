package modelo;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class CategoriaDAO {

    private Connection conexion;

    private void abrirConexion() throws SQLException {

        String dbURI = "jdbc:derby://localhost:1527/BIBLIOTECA3";
        Properties prop = new Properties();

        try {
            prop.load(getClass().getResourceAsStream("server.properties"));
        } catch (IOException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String username = prop.getProperty("usuario");
        String password = prop.getProperty("contrasena");

        conexion = DriverManager.getConnection(dbURI, username, password);
    }

    private void cerrarConexion() throws SQLException {

        conexion.close();

    }

    public void insertar(CategoriasPOJO articulo) {

        try {

            abrirConexion();
            PreparedStatement stmt;
            String sql = "INSERT INTO CATEGORIAS VALUES (?,?)";
            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, articulo.getId_Categoria());
            stmt.setString(2, articulo.getCategoria());

            stmt.executeUpdate();

            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List buscar(CategoriasPOJO POJO) throws SQLException {

        ResultSet resultados;
        List beans = new ArrayList();
        PreparedStatement ps = null;
        try {
            abrirConexion();

            String sql = "select * from CATEGORIAS  where CATEGORIA like ? ";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, "%" + "" + "%");

            resultados = ps.executeQuery();

            while (resultados.next()) {
                String n = resultados.getString("CATEGORIA");
                String c = resultados.getString("ID_CATEGORIA");

                CategoriasPOJO bean = new CategoriasPOJO();
                bean.setCategoria(n);
                bean.setId_Categoria(Integer.parseInt(c));

                beans.add(bean);
            }
            //Cerrar PreparedSatement
            ps.close();
            cerrarConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return beans;
    }

}
