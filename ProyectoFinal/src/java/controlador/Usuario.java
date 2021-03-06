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

public class Usuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        HttpSession a = request.getSession();

        String usuario = "";
        String contra = "";
        String nombre = "";

        switch (accion) {
            case "login": {
                UsuariosPOJO pojo = new UsuariosPOJO();
                UsuarioDAO model = new UsuarioDAO();
                ArticuloDAO dao = new ArticuloDAO();
                ArticulosPOJO pj = new ArticulosPOJO();
                usuario = antiXSS(request.getParameter("usuario"));
                contra = Integer.toString(antiXSS(request.getParameter("contra")).hashCode());
                if ((usuario != "") && (contra != "")) {
                    pojo.setPassword(contra);
                    pojo.setUsuario(usuario);
                    if (model.login(pojo)) {

                       List inicio = dao.inicial(pj);
                       List inicioC = dao.abc();

                        String r = model.user(pojo);
                        a.setAttribute("nombre", r);
                        a.setAttribute("inicio", inicio);
                        a.setAttribute("inicioC", inicioC);
                        response.sendRedirect("busqueda.jsp");

                    } else {
                        response.sendRedirect("index.html");
                    }
                }
                break;
            }

            case "registrar": {
                UsuariosPOJO pojo = new UsuariosPOJO();
                UsuarioDAO modelo = new UsuarioDAO();
                usuario = antiXSS(request.getParameter("usuario"));
                contra = Integer.toString(antiXSS(request.getParameter("contra")).hashCode());
                nombre = antiXSS(request.getParameter("nombre"));
                if ((usuario != "") && (contra != "") && (nombre != "")) {
                    pojo.setUsuario(usuario);
                    pojo.setPassword(contra);
                    pojo.setNombre(nombre);
                    modelo.registrar(pojo);
                    response.sendRedirect("busqueda.jsp");
                    break;
                }
            }
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
            cosa = "";
        } else {
            cosa = String.valueOf(c);
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
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
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
