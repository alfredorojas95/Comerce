/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wilfr
 */
public class ProductoOrdenServlet extends HttpServlet {

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
        String userPath = request.getServletPath();
        try {
            switch (userPath) {
                case "/productoOrden":
                    this.listarProductoOrden(request, response);
                    break;
                case "/nuevoProductoOrden":
                    this.mostrarFormProductoOrden(request, response);
                    break;
                case "/editarProductoOrden":
                    this.editarFormProductoOrden(request, response);
                    break;
                case "/eliminarProductoOrden":
                    this.borarProductoOrden(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
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
        String userPath = request.getServletPath();
        try {
            switch (userPath) {
                case "/guardarProductoOrden":
                    this.guardarProductoOrden(request, response);
                    break;
                case "/actualizarProductoOrden":
                    this.actualizarProductoOrden(request, response);
                    break;

            }
        } catch (SQLException e) {
            throw new ServletException(e);
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

    private void guardarProductoOrden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

    }

    private void listarProductoOrden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

    }

    private void mostrarFormProductoOrden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

    }

    private void borarProductoOrden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

    }

    private void actualizarProductoOrden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

    }

    private void editarFormProductoOrden(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException  {
        
    }

}
