<%@page import="java.net.URLEncoder"%>
<%@page import="modelo.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar</title>
    </head>
    <body>
        <%
            if (session != null) {
                String a = (String) session.getAttribute("nombre");
                if (a != null) {
        %>
        <h3> <%=a%></h3>
        <%
            } else {
                response.sendRedirect("index.html");
            }

        %>
        <h1>Busqueda</h1>
        <form action="Consulta" methd="post" accept-charset="ISO-8859-15">
            <input type="text" name="titulo">
            <input type="submit" value="Buscar">
            <input type="hidden" name="accion" value="buscar">
        </form>

        <%            List inicio = (List) session.getAttribute("inicio");
            List inicioC = (List) session.getAttribute("inicioC");

            List art = (List) session.getAttribute("listaArt");
            List catN = (List) session.getAttribute("listaNameCat");
            if ((art != null && catN != null)) {
        %>
        <table  class ="a" border="1">
            <tr>
                <th>Clave</th>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Categoria</th>
                <th>Dueño</th>
                <th>Acciones</th>
                </tr>
            <%
                String A1 = null;
                String A2 = null;
                for (Object o : art) {
                    ArticulosPOJO articulos = (ArticulosPOJO) o;
                    A1 = articulos.getTitulo();
                    for (Object r : catN) {
                        CategoriasPOJO cat = (CategoriasPOJO) r;
                        if (cat.getId_Categoria() == articulos.getId_categoria() && ((A1.equals(A2)) == false)) {
                            A2 = articulos.getTitulo();
            %>
            <tr>
                <th><%=articulos.getId_articulo()%></th>
                <th><%=articulos.getTitulo()%></th>
                <th><%=articulos.getDescripcion()%></th>
                <th><%=cat.getCategoria()%></th>
                <th><%=articulos.getUsuario()%></th>

                <th>
            <form action="detalles.jsp" method="post" accept-charset="ISO-8859-15">
                <input type="hidden" name="nombree" value="<%=articulos.getTitulo()%>">
                <input type="submit" value="Detalles">
            </form>
        </th>
        </tr>
    <%
                }
            }
        }
    %>
</table>
<%
} else if ((inicio != null && inicioC != null)) {
%>
<table  class ="a" border="1">
    <tr>
        <th>Clave</th>
        <th>Titulo</th>
        <th>Descripcion</th>
        <th>Categoria</th>
        <th>Dueño</th>
        <th>Acciones</th>
    </tr>
    <%
        String A1 = null;
        String A2 = null;
        for (Object o : inicio) {
            ArticulosPOJO articulos = (ArticulosPOJO) o;
            A1 = articulos.getTitulo();
            for (Object r : inicioC) {
                CategoriasPOJO cat = (CategoriasPOJO) r;
                if (cat.getId_Categoria() == articulos.getId_categoria() && ((A1.equals(A2)) == false)) {
                    A2 = articulos.getTitulo();
    %>
    <tr>
        <th><%=articulos.getId_articulo()%></th>
        <th><%=articulos.getTitulo()%></th>
        <th><%=articulos.getDescripcion()%></th>
        <th><%=cat.getCategoria()%></th>
        <th><%=articulos.getUsuario()%></th>

        <th>
    <form action="detalles.jsp" method="post" accept-charset="ISO-8859-15">
        <input type="hidden" name="nombree" value="<%=articulos.getTitulo()%>">
        <input type="submit" value="Detalles">
    </form>
</th>
</tr>
<%
            }
        }
    }
%>
</table>
<%
    }
%>
<form action="Categoria" method="post">
    <input type="submit" value="Agregar Articulo">
    <input type="hidden" name="accion" value="buscar">
</form>
<a href="registroCategoria.jsp">Registrar Categoria</a>
<form action="Consulta" method="post">
    <input type="submit" value="Cerrar Sesion">
    <input type="hidden" name="accion" value="sessionOff">
</form>
<%
    } else {
        response.sendRedirect("index.html");
    }
    //--------------
    /**
     *
     * Para modificar el tiempo que dura un sesión se odifica en el Web.xml el
     * atributo
     * <session-config>
     * <session-timeout>5</session-timeout>
     *
     * </session-config>
     */
    //-------    
%>
</body>
</html>
