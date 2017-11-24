<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registra una Categoria</title>
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
        <h1>Registra una nueva Categoria!</h1>
        <br><br>
        
        <form action="Categoria" method="post"  accept-charset="ISO-8859-15">
            Clave:&nbsp;&nbsp;&nbsp;<input type="text" name="clave" ><br><br>
            Categoria &nbsp;&nbsp;<input type="text" name="categoria" ><br><br>

            <input type="submit" value="Agregar Categoria">
            <input type="hidden" name="accion" value="insertar">
        </form>
        <a href="busqueda.jsp">Regresar</a>
                <%            } else {
                response.sendRedirect("index.html");
            }
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
%>
    </body>
</html>
