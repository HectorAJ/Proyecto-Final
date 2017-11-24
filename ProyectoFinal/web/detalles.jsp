<%@page import="modelo.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilo.css">
        <title>Detalles</title>
    </head>
    <body>
        <%
            if (session != null) {
                String a = (String) session.getAttribute("nombre");
                if (a != null) {
        %>
        <%
                out.println(a);
            } else {
                response.sendRedirect("index.html");
            }
            List inicio = (List) session.getAttribute("inicio");
            List inicioC = (List) session.getAttribute("inicioC");

            String A1 = null;
            String A2 = null;

            List art = (List) session.getAttribute("listaArt");
            List catN = (List) session.getAttribute("listaNameCat");
            if ((art != null && catN != null)) {
                String det = request.getParameter("nombree");
                for (Object o : art) {
                    ArticulosPOJO articulos = (ArticulosPOJO) o;
                    A1 = articulos.getTitulo();
                    if (articulos.getTitulo().equals(det)) {
                        for (Object z : catN) {
                            CategoriasPOJO cat = (CategoriasPOJO) z;
                            if (articulos.getId_categoria() == cat.getId_Categoria() && ((A1.equals(A2)) == false)) {
                                A2 = articulos.getTitulo();
        %>
        <br><font color="white">Clave: <%=articulos.getId_articulo()%></font><br>
        <font color="white">Titulo: <%=articulos.getTitulo()%></font><br>
        <font color="white">Descripcion: <%=articulos.getDescripcion()%></font><br>
        <img src="<%=articulos.getUrl_Imagen()%>" width='250' height='250'><br>
        <font color="white">Categoria: <%=cat.getCategoria()%></font><br>
        <font color="white">Dueño: <%=articulos.getUsuario()%></font><br>
        <font color="white"><a href = "busqueda.jsp">Regresar</a></font>
        <%
                                }
                            }
                        }
                    }
                }else if ((inicio != null && inicioC != null)) {
                String det = request.getParameter("nombree");
                for (Object o : inicio) {
                    ArticulosPOJO articulos = (ArticulosPOJO) o;
                    A1 = articulos.getTitulo();
                    if (articulos.getTitulo().equals(det)) {
                        for (Object z : inicioC) {
                            CategoriasPOJO cat = (CategoriasPOJO) z;
                            if (articulos.getId_categoria() == cat.getId_Categoria() && ((A1.equals(A2)) == false)) {
                                A2 = articulos.getTitulo();
        %>
        <br>Clave: <%=articulos.getId_articulo()%><br>
        Titulo: <%=articulos.getTitulo()%><br>
        Descripcion: <%=articulos.getDescripcion()%><br>
        <img src="<%=articulos.getUrl_Imagen()%>" width='250' height='250'><br>
        Categoria: <%=cat.getCategoria()%><br>
        Dueño: <%=articulos.getUsuario()%><br>
        <a href = "busqueda.jsp">Regresar</a>
        <%
                                }
                            }
                        }
                    }
                }
            } else {
                response.sendRedirect("index.html");

            }

            //--------------
            /**
             *
             * Para modificar el tiempo que dura un sesión se odifica en el
             * Web.xml el atributo
             * <session-config>
             * <session-timeout>10</session-timeout>
             *
             * </session-config>
             */
             //-------    
%>
    </body>
</html>

