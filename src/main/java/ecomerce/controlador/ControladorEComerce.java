/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.controlador;

import ecomerce.persistencia.dao.TecCategoriaDao;
import ecomerce.persistencia.factory.DAOFactory;
import ecomerce.persistencia.factory.TipoBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ecomerce.modelo.TecCategoria;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author wilfr
 */
public class ControladorEComerce extends HttpServlet {

    public static DAOFactory fabrica;

    @Override
    public void init() throws ServletException {
        ControladorEComerce.fabrica = DAOFactory.getFactory(TipoBD.MYSQL, this.getServletContext());
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
        // processRequest(request, response);
        String userPath = request.getServletPath();
        try {
            switch (userPath) {
                case "/categorias":
                    this.listarCategorias(request, response);
                    break;
                case "/nuevaCategoria":
                    mostrarFormNuevaCategoria(request, response);
                    break;
                case "/editarFormCategoria":
                    editarCategoria(request, response);
                    break;
                case "/eliminarCategoria":
                    borrarCategoria(request, response);
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
                case "/guardarCategoria":
                    this.guardarCategoria(request, response);
                    break;
                case "/actualizarCategoria":
                    this.actualizarCategoria(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        System.out.println("------------> listar categoria!");
        TecCategoriaDao categoriaDao = ControladorEComerce.fabrica.getTecCategoriaDao();
        ArrayList<TecCategoria> listadoCategoria = categoriaDao.listar();
        request.setAttribute("listadoCategoria", listadoCategoria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/categorias.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormNuevaCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/categoria_form.jsp");
        dispatcher.forward(request, response);
    }

    private void guardarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        TecCategoria cat = new TecCategoria();
        cat.setCatNombre(request.getParameter("nombre_categoria"));

        TecCategoriaDao categoriaDao = ControladorEComerce.fabrica.getTecCategoriaDao();
        categoriaDao.guardar(cat);
        response.sendRedirect("index.jsp");

    }

    private void borrarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        TecCategoriaDao categoriaDao = ControladorEComerce.fabrica.getTecCategoriaDao();
        categoriaDao.borrar(id);
        response.sendRedirect("/IComerce/categorias");

    }

    //redireccionar datos a form editar
    private void editarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        TecCategoriaDao categoriaDao = ControladorEComerce.fabrica.getTecCategoriaDao();
        int id = Integer.parseInt(request.getParameter("id"));
        
        //se busca la categotia del id obtenido
        TecCategoria categoria = categoriaDao.buscar(id);
        
        request.setAttribute("tecCategoria", categoria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/categoria_editar.jsp");
        dispatcher.forward(request, response);
    }

    //obtener nuevos datos y setearlos en categoria
    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
      
        String numCateria = request.getParameter("id_cat");
        String nomCateria = request.getParameter("nombre_categoria");
        int id = Integer.parseInt(numCateria);
        System.out.println("llegó ------------------> : "+numCateria+" "+nomCateria);
        
        TecCategoriaDao categoriaDao = ControladorEComerce.fabrica.getTecCategoriaDao();
        TecCategoria categoria = categoriaDao.buscar(id);
        categoria.setCatNombre(nomCateria);
        categoriaDao.editar(categoria);

        categoria.setCatNombre(request.getParameter("nombre_categoria"));
        response.sendRedirect("/IComerce/categorias");
        
        
    }

}
