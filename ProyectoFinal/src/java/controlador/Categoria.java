package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.*;

public class Categoria extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        HttpSession a = request.getSession();
        List datos;
        int clave = 0;
        String categoria = "";

        switch (accion) {
            case "buscar": {
                try {
                    CategoriaDAO dao = new CategoriaDAO();
                    CategoriasPOJO pj = new CategoriasPOJO();

                    datos = dao.buscar(pj);
                    a.setAttribute("listaCat", datos);

                    response.sendRedirect("registroArticulo.jsp");
                } catch (Exception e) {
                    response.sendRedirect("error.jsp");
                }
                break;
            }
            case "insertar":
                try {

                    CategoriaDAO dao = new CategoriaDAO();
                    CategoriasPOJO pj = new CategoriasPOJO();
                    categoria = antiXSS(request.getParameter("categoria"));
                    clave = Integer.parseInt(antiXSS(request.getParameter("clave")));
                    if ((categoria != "")) {

                        pj.setCategoria(categoria);
                        pj.setId_Categoria(clave);
                        dao.insertar(pj);
                        response.sendRedirect("busqueda.jsp");
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                } catch (Exception e) {
                    response.sendRedirect("error.jsp");
                }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
