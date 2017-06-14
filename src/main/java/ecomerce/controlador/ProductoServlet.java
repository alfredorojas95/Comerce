/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.controlador;

import ecomerce.modelo.TecCategoria;
import ecomerce.modelo.TecProducto;
import ecomerce.persistencia.dao.TecCategoriaDao;
import ecomerce.persistencia.dao.TecProductoDao;
import ecomerce.persistencia.factory.DAOFactory;
import ecomerce.persistencia.factory.TipoBD;
import java.io.IOException;
import java.io.PrintWriter;

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
public class ProductoServlet extends HttpServlet {

    public static DAOFactory fabrica;
    ArrayList<TecCategoria> listadoCategoria;

    @Override
    public void init() throws ServletException {

        ProductoServlet.fabrica = DAOFactory.getFactory(TipoBD.MYSQL, this.getServletContext());
        TecCategoriaDao categoriaDao = ProductoServlet.fabrica.getTecCategoriaDao();
        this.listadoCategoria = categoriaDao.listar();
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
                case "/productos":
                    this.listarProductos(request, response);
                    break;
                case "/nuevoProducto":
                    this.mostrarFormNuevoProducto(request, response);
                    break;
                case "/eliminarFormProducto":
                    this.eliminarProducto(request, response);
                    break;
                case "/editarFormProducto":
                    this.editarProducto(request, response);
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
                case "/guardarProducto":
                    this.guardarProducto(request, response);
                    break;
                case "/actualizarFormProducto":
                    this.actualizarProducto(request, response);
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

    private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        TecProductoDao productoaDao = ProductoServlet.fabrica.getProductoDao();
        ArrayList<TecProducto> listadoProducto = productoaDao.listar();
        request.setAttribute("listadoProducto", listadoProducto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/productos.jsp");
        dispatcher.forward(request, response);

    }

    private void mostrarFormNuevoProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        request.setAttribute("listadoCategoria", listadoCategoria);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/producto_form.jsp");
        dispatcher.forward(request, response);
    }

    private void guardarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        TecProducto prod = new TecProducto();
        prod.setProNombre(request.getParameter("nombre_producto"));
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        System.out.println("categoria " + categoria);
        TecCategoriaDao catDao = ProductoServlet.fabrica.getTecCategoriaDao();
        TecCategoria cat = catDao.buscar(categoria);
        prod.setCat(cat);
        prod.setProDescripcion(request.getParameter("descripcion_producto"));
        prod.setPrPrecio(Integer.parseInt(request.getParameter("precio_producto")));
        
        //obtener fecha
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        String f = formato.format(fecha);
        prod.setProUltimaUctualizacion(f);
        //prod.setProUltimaUctualizacion(request.getParameter("fecha_mod_producto"));

        TecProductoDao productoDao = ProductoServlet.fabrica.getProductoDao();
        productoDao.guardar(prod);
        response.sendRedirect("index.jsp");
        
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        TecProductoDao productoDao = ProductoServlet.fabrica.getProductoDao();
        productoDao.borrar(id);
        response.sendRedirect("/IComerce/productos");
    }

    private void editarProducto(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException , SQLException  {
        
        TecProductoDao productoDao = ProductoServlet.fabrica.getProductoDao();
        int id = Integer.parseInt(request.getParameter("id"));

        //se busca la categotia del id obtenido
        TecProducto producto = productoDao.buscar(id);

        request.setAttribute("tecProducto", producto);
        request.setAttribute("listadoCategoria", listadoCategoria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/producto_form_edit.jsp");
        dispatcher.forward(request, response);
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
        
        int idProducto = Integer.parseInt(request.getParameter("prod_id"));
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        String nombreProducto = request.getParameter("nombre_producto");
        String descripcionProducto = request.getParameter("descripcion_producto");
        int precioProducto = Integer.parseInt(request.getParameter("precio_producto"));
        //String fModProducto = request.getParameter("fecha_mod_producto");

        TecProductoDao productoDao = ProductoServlet.fabrica.getProductoDao();
        TecProducto prod = productoDao.buscar(idProducto);
        TecCategoriaDao catDao = ProductoServlet.fabrica.getTecCategoriaDao();
        TecCategoria cat = catDao.buscar(categoria);
        prod.setCat(cat);
        System.out.println("-------->new categoria "+cat.getCatId()+"  --   "+cat.getCatNombre());
        prod.setProNombre(nombreProducto);
        prod.setProDescripcion(descripcionProducto);
        prod.setPrPrecio(precioProducto);
        
        //obtener fecha
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        String f = formato.format(fecha);
        prod.setProUltimaUctualizacion(f);
             
        productoDao.editar(prod);
        response.sendRedirect("/IComerce/productos");
    }

    
}
