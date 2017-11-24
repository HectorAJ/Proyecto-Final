package modelo;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class ArticuloDAO {

    private Connection conexion;
    int idCategoria;
    List beanss;

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

    public void insertar(ArticulosPOJO articulo) throws SQLException {

        try {

            abrirConexion();
            PreparedStatement stmt;
            String sql = "INSERT INTO ARTICULOS VALUES (?,?,?,?,?,?)";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, articulo.getId_articulo());
            stmt.setString(2, articulo.getTitulo());
            stmt.setString(3, articulo.getDescripcion());
            stmt.setString(4, articulo.getUrl_Imagen());
            stmt.setInt(5, articulo.getId_categoria());
            stmt.setString(6, articulo.getUsuario());

            stmt.executeUpdate();

            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List buscar(ArticulosPOJO POJO) throws SQLException {

        ResultSet resultados;
        List beans = new ArrayList();
        beanss = new ArrayList();
        PreparedStatement ps = null;
        try {
            abrirConexion();

            String sql = "select * from ARTICULOS INNER JOIN CATEGORIAS ON ARTICULOS.ID_CATEGORIA=CATEGORIAS.ID_CATEGORIA where TITULO like ? ";

            ps = conexion.prepareStatement(sql);
            ps.setString(1, "%" + POJO.getTitulo() + "%");

            resultados = ps.executeQuery();

            while (resultados.next()) {
                String idArticulo = resultados.getString("ID_ARTICULO");
                String titulo = resultados.getString("TITULO");
                String descricion = resultados.getString("DESCRIPCION");
                String urlImagen = resultados.getString("URL_IMAGEN");
                idCategoria = Integer.parseInt(resultados.getString("ID_CATEGORIA"));
                String categoria = resultados.getString("CATEGORIA");
                String usuario = resultados.getString("USUARIO");

                ArticulosPOJO bean = new ArticulosPOJO();
                CategoriasPOJO beanC = new CategoriasPOJO();
                bean.setId_articulo(idArticulo);
                bean.setTitulo(titulo);
                bean.setDescripcion(descricion);
                bean.setUrl_Imagen(urlImagen);
                bean.setId_categoria(idCategoria);
                bean.setUsuario(usuario);
                beanC.setId_Categoria(idCategoria);
                beanC.setCategoria(categoria);

                beans.add(bean);
                beanss.add(beanC);
            }

            ps.close();
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return beans;
    }

    public List inicial(ArticulosPOJO POJO) throws SQLException {

        ResultSet resultados;
        List beans = new ArrayList();
        beanss = new ArrayList();
        PreparedStatement ps = null;
        try {
            abrirConexion();

            String sql = "select * from ARTICULOS INNER JOIN CATEGORIAS ON ARTICULOS.ID_CATEGORIA=CATEGORIAS.ID_CATEGORIA";

            ps = conexion.prepareStatement(sql);

            resultados = ps.executeQuery();

            while (resultados.next()) {
                String idArticulo = resultados.getString("ID_ARTICULO");
                String titulo = resultados.getString("TITULO");
                String descricion = resultados.getString("DESCRIPCION");
                String urlImagen = resultados.getString("URL_IMAGEN");
                idCategoria = Integer.parseInt(resultados.getString("ID_CATEGORIA"));
                String categoria = resultados.getString("CATEGORIA");
                String usuario = resultados.getString("USUARIO");

                ArticulosPOJO bean = new ArticulosPOJO();
                CategoriasPOJO beanC = new CategoriasPOJO();
                bean.setId_articulo(idArticulo);
                bean.setTitulo(titulo);
                bean.setDescripcion(descricion);
                bean.setUrl_Imagen(urlImagen);
                bean.setId_categoria(idCategoria);
                bean.setUsuario(usuario);
                beanC.setId_Categoria(idCategoria);
                beanC.setCategoria(categoria);

                beans.add(bean);
                beanss.add(beanC);
            }

            ps.close();
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return beans;
    }

    public List abc() {
        return beanss;
    }

}
