<%@page import="modelo.CategoriasPOJO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registra un Artículo</title>
        <link rel="stylesheet" href="estilo.css">
    </head>
    <body>
        <%

            if (session != null) {

                String a = (String) session.getAttribute("nombre");
                if (a != null) {
        %>
        <%=a%>
        <%
            } else {
                response.sendRedirect("index.html");
            }
        %>
        <h1>Registra un nuevo Artículo!</h1>
        <br><br>

        <form action="Consulta" method="post" accept-charset="ISO-8859-15">
            Clave:&nbsp;&nbsp;&nbsp;<input type="text" name="clave"><br><br>
            Título: &nbsp;&nbsp;<input type="text" name="titulo"><br><br>   
            Descripción: <br><textarea cols="40" rows="7" name="descripcion"></textarea><br><br>
            Categoria: 
            <%
                List categorias = (List) session.getAttribute("listaCat");
                if (categorias != null) {
            %>

            <select name="cat5">

                <option selected value="01">Default

                    <%
                        for (Object o : categorias) {
                            CategoriasPOJO categoria = (CategoriasPOJO) o;
                    %>

                <option value=<%=categoria.getId_Categoria()%> > 
                    <%=categoria.getCategoria()%>
                    <%
                            }
                        }
                    %>

            </select>
            URL Imagen<input type="text" name="imagen"><br><br>
            <input type="hidden" value=<%=a%> name="usuario">
            <input type="hidden" name="accion" value="ingresar">
            <input type="reset" value="Borrar">
            <input type="submit" value="Enviar">
        </form>
        <%
                //--------------
                /**
                 *
                 * Para modificar el tiempo que dura un sesión se odifica en el
                 * Web.xml el atributo
                 * <session-config>
                 * <session-timeout>5</session-timeout>
                 *
                 * </session-config>
                 */
                //-------   
            } else {
                response.sendRedirect("index.html");
            }

        %>
        <a href="busqueda.jsp">Regresar</a>
    </body>
</html>
