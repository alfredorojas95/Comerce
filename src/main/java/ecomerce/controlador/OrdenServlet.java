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
import ecomerce.persistencia.impl.TecUsuarioImp;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        OrdenServlet.fabrica = DAOFactory.getFactory(TipoBD.MYSQL, this.getServletContext());
        TecUsuarioDao userDao = OrdenServlet.fabrica.getUsuarioDao();
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
                    this.mostrarFormEditar(request, response);
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
                case "/guardarOrden":
                    this.guardarOrden(request, response);
                    break;

                case "/actualizarOrden":
                    this.actualizarOrden(request, response);
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
        TecOrdenDao ordenDao = OrdenServlet.fabrica.getOrdenDao();
        ArrayList<TecOrden> listadoOrden = ordenDao.listar();
        request.setAttribute("listadoOrden", listadoOrden);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/ordenes.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormOrden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setAttribute("listadoUsuario", listadoUsuario);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/orden_form.jsp");
        dispatcher.forward(request, response);
    }

    private void guardarOrden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        TecOrden orden = new TecOrden();
        
        //obtener fecha
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        String f = formato.format(fecha);
        orden.setOrdFcreacion(f);
        int idCcliente = Integer.parseInt(request.getParameter("cliente"));

        //error desborde de arreglo
        TecUsuarioDao userDao = OrdenServlet.fabrica.getUsuarioDao();
        TecUsuario usu = userDao.buscar(idCcliente);
        System.out.println(usu.getCliNombre());
        orden.setCli(usu);
        orden.setOrdFumConfirmacion(Integer.parseInt(request.getParameter("num_confirmacion_orden")));
        orden.setOrdPrecioTotal(Integer.parseInt(request.getParameter("precio_orden")));

        TecOrdenDao ordenDao = OrdenServlet.fabrica.getOrdenDao();
        ordenDao.guardar(orden);
        response.sendRedirect("index.jsp");
    }

    private void borarOrden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        TecOrdenDao ordenDao = OrdenServlet.fabrica.getOrdenDao();
        ordenDao.borrar(id);
        response.sendRedirect("/IComerce/ordenes");

    }

    private void mostrarFormEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
        TecOrdenDao ordenDao = OrdenServlet.fabrica.getOrdenDao();
        int id = Integer.parseInt(request.getParameter("id"));
        TecOrden orden = ordenDao.buscar(id);
        System.out.println(orden.getOrdFcreacion());
        
        //usuarios
        TecUsuarioDao usuarioDao = OrdenServlet.fabrica.getUsuarioDao();
        ArrayList<TecUsuario> listadoUsuarios = usuarioDao.listar();
        
        request.setAttribute("orden", orden);
        request.setAttribute("listadoUsuarios", listadoUsuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/orden_form_edit.jsp");
        dispatcher.forward(request, response);
    }

    private void actualizarOrden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        
        int idOrden = Integer.parseInt(request.getParameter("ord_id"));
        
        //obtener fecha
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        String f = formato.format(fecha);
        
        //String fechaCreacion = request.getParameter("fecha_creacion_orden");
        int idCliente = Integer.parseInt(request.getParameter("cliente"));
        int numConfirmacion = Integer.parseInt(request.getParameter("num_confirmacion_orden"));
        int precioTotal = Integer.parseInt(request.getParameter("precio_orden"));

        //buscar orden
        TecOrdenDao ordenDao = OrdenServlet.fabrica.getOrdenDao();
        TecOrden orden = ordenDao.buscar(idOrden);
        
        //buscar cliente
        TecUsuarioDao userDao = OrdenServlet.fabrica.getUsuarioDao();
        TecUsuario user = userDao.buscar(idCliente);
        
        //set atributos
        orden.setOrdFcreacion(f);
        orden.setCli(user);
        orden.setOrdFumConfirmacion(numConfirmacion);
        orden.setOrdPrecioTotal(precioTotal);
             
        ordenDao.editar(orden);
        response.sendRedirect("/IComerce/ordenes");
    }

}
