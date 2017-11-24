package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.*;

public class Consulta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        HttpSession a = request.getSession();

        List datos = null;
        List datoB = null;
        String titulo = null;
        int categoria = 0;

        switch (accion) {
            case "buscar": {
                ArticulosPOJO pojo = new ArticulosPOJO();
                ArticuloDAO model = new ArticuloDAO();
                if (request.getParameter("titulo") != "") {
                    titulo = antiXSS(request.getParameter("titulo"));
                    pojo.setTitulo(titulo);
                    datos = model.buscar(pojo);
                    datoB = model.abc();
                    a.setAttribute("listaArt", datos);
                    a.setAttribute("listaNameCat", datoB);
                    response.sendRedirect("busqueda.jsp");
                } else {
                    response.sendRedirect("busqueda.jsp");
                }
                break;
            }

            case "ingresar": {

                ArticulosPOJO pojo = new ArticulosPOJO();
                ArticuloDAO model = new ArticuloDAO();
                String clave = antiXSS(request.getParameter("clave"));
                titulo = antiXSS(request.getParameter("titulo"));
                String descripcion = antiXSS(request.getParameter("descripcion"));
                String urlImagen = antiXSS(request.getParameter("imagen"));
                try {
                    categoria = Integer.parseInt(request.getParameter("cat5"));
                } catch (Exception e) {
                    response.sendRedirect("error.jsp");
                }
                String dueño = request.getParameter("usuario");
                System.out.println(clave + titulo + descripcion + urlImagen + dueño);
                if ((clave != "") && (titulo != "") && (descripcion != "") && (urlImagen != "") && (dueño != "")) {
                    pojo.setId_articulo(clave);
                    pojo.setTitulo(titulo);
                    pojo.setDescripcion(descripcion);
                    pojo.setUrl_Imagen(urlImagen);
                    pojo.setId_categoria(categoria);
                    pojo.setUsuario(dueño);
                    model.insertar(pojo);
                    response.sendRedirect("busqueda.jsp");

                } else {
                    response.sendRedirect("error.jsp");
                }
                break;
            }

            case "sessionOff":
                a.invalidate();
                response.sendRedirect("index.html");
                break;
            default:
                response.sendRedirect("error.jsp");
                break;
        }
    }

    private String antiXSS(String dato) {
        String cosa = null;
        cosa = dato;
        String alerta = "ok";
        char[] c = cosa.toCharArray();

        int i;
        if ((cosa == null) || (cosa.equals(" "))) {
            alerta = "No de finido";
        }
        if (cosa.isEmpty()) {
            alerta = "No de finido";
        }

        for (i = 0; i < c.length; i++) {
            if (c[i] == '<') {
                c[i] = '?';
            } else if (c[i] == '>') {
                c[i] = '?';
            }
        }
        if (alerta.equals("No de finido")) {
            System.out.println(alerta);
            cosa = "";
        } else {
            cosa = String.valueOf(c);
            System.out.println(cosa);
        }
        return cosa;
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(Consulta.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
