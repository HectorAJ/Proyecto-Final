<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilo.css">
        <title>ERROR</title>
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
        <h1>Lo sentimos, ha ocurrido un error</h1>
        <h1>Revise sus datos e intentelo de nuevo</h1>
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
