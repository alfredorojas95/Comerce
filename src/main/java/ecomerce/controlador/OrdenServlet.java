/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.controlador;

import ecomerce.modelo.TecOrden;
import ecomerce.modelo.TecUsuario;
import ecomerce.persistencia.dao.TecOrdenDao;
import ecomerce.persistencia.dao.TecUsuarioDao;
import ecomerce.persistencia.factory.DAOFactory;
import ecomerce.persistencia.factory.TipoBD;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wilfr
 */
public class OrdenServlet extends HttpServlet {

    public static DAOFactory fabrica;
    ArrayList<TecUsuario> listadoUsuario;

    @Override
    public void init() throws ServletException {
        ControladorEComerce.fabrica = DAOFactory.getFactory(TipoBD.MYSQL, this.getServletContext());
        TecUsuarioDao userDao = ControladorEComerce.fabrica.getUsuarioDao();
        this.listadoUsuario = userDao.listar();
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
        String userPath = request.getServletPath();
        try {
            switch (userPath) {
                case "/ordenes":
                    this.listarOrdenes(request, response);
                    break;

                case "/nuevaOrden":
                    this.mostrarFormOrden(request, response);
                    break;
                    
                case "/editarFormOrden":
                    this.borarOrden(request, response);
                break;
                case "/eliminarFormOrden":
                    this.borarOrden(request, response);
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
                    this.guardarOrden(request, response);
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

    private void listarOrdenes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        TecOrdenDao ordenDao = ControladorEComerce.fabrica.getOrdenDao();
        ArrayList<TecOrden> listadoOrden = ordenDao.listar();
        request.setAttribute("listadoOrden", listadoOrden);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/ordenes.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormOrden(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
        request.setAttribute("listadoUsuario", listadoUsuario);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/orden_form.jsp");
        dispatcher.forward(request, response);
    }

    private void guardarOrden(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
        TecOrden orden = new TecOrden();
        orden.setOrdFcreacion(request.getParameter("fecha_creacion_orden"));
        int cliente = Integer.parseInt(request.getParameter("cliente"));
        TecUsuario usu = this.listadoUsuario.get(cliente);
        orden.setCli(usu);
        orden.setOrdFumConfirmacion(Integer.parseInt(request.getParameter("num_confirmacion_orden")));
        orden.setOrdPrecioTotal(Integer.parseInt(request.getParameter("precio_orden")));


        TecOrdenDao ordenDao = ControladorEComerce.fabrica.getOrdenDao();
        ordenDao.guardar(orden);
        response.sendRedirect("index.jsp");
    }

    private void editarFormOrden(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void borarOrden(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        TecOrdenDao ordenDao = ControladorEComerce.fabrica.getOrdenDao();
        ordenDao.borrar(id);
        response.sendRedirect("/IComerce/ordenes");
       
    }

}
