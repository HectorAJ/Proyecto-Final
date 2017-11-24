package modelo;

public class ArticulosPOJO {

    private String id_articulo="";
    private String titulo="";
    private String descripcion="";
    private String url_Imagen="";
    private int id_categoria=0;
    private String usuario="";

    /**
     * @return the id_articulo
     */
    public String getId_articulo() {
        return id_articulo;
    }

    /**
     * @param id_articulo the id_articulo to set
     */
    public void setId_articulo(String id_articulo) {
        this.id_articulo = id_articulo;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the url_Imagen
     */
    public String getUrl_Imagen() {
        return url_Imagen;
    }

    /**
     * @param url_Imagen the url_Imagen to set
     */
    public void setUrl_Imagen(String url_Imagen) {
        this.url_Imagen = url_Imagen;
    }

    /**
     * @return the id_categoria
     */
    public int getId_categoria() {
        return id_categoria;
    }

    /**
     * @param id_categoria the id_categoria to set
     */
    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
