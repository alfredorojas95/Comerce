/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.controlador;

import ecomerce.modelo.TecUsuario;
import ecomerce.persistencia.dao.TecUsuarioDao;
import ecomerce.persistencia.factory.DAOFactory;
import ecomerce.persistencia.factory.TipoBD;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UsuarioServlet extends HttpServlet {

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
        
        String userPath = request.getServletPath();
        try {
            switch (userPath) {
                case "/usuarios":
                    System.out.println("usuarios");
                    this.listarUsuarios(request, response);
                    break;
                case "/nuevoUsuario":
                    this.mostrarFormUsuario(request, response);
                    break;
                    
                case "/editarUsuario":
                    this.mostrarFormEditUsuario(request, response);
                    break;
                    
                case "/eliminarUsuario":
                    this.eliminarUsuario(request, response);
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
                case "/guardarUsuario":
                    this.guardarUsuario(request, response);
                    break;
                case "/actualizarUsuario":
                    this.actualizarUsuario(request, response);
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

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        System.out.println("listar usuarios");
        TecUsuarioDao usuarioaDao = ControladorEComerce.fabrica.getUsuarioDao();
        ArrayList<TecUsuario> listadoUsuario = usuarioaDao.listar();
        request.setAttribute("listadoUsuario", listadoUsuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuarios.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormUsuario(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_form.jsp");
        dispatcher.forward(request, response);
    }

    private void guardarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        TecUsuario usuario = new TecUsuario();
        usuario.setCliNombre(request.getParameter("nombre_usuario"));
        usuario.setCliApellido(request.getParameter("apellido_usuario"));
        usuario.setCliTelefono(request.getParameter("telefono_usuario"));
        usuario.setCliDireccion(request.getParameter("direccion_usuario"));
        usuario.setCliComuna(request.getParameter("comuna_usuario"));
        
        TecUsuarioDao usuDao = ControladorEComerce.fabrica.getUsuarioDao();
        usuDao.guardar(usuario);
        response.sendRedirect("index.jsp");
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        TecUsuarioDao usuarioDao = ControladorEComerce.fabrica.getUsuarioDao();
        usuarioDao.borrar(id);
        response.sendRedirect("/IComerce/usuarios");
    }

    private void mostrarFormEditUsuario(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
        TecUsuarioDao userDao = ControladorEComerce.fabrica.getUsuarioDao();
        int id = Integer.parseInt(request.getParameter("id"));
        TecUsuario user = userDao.buscar(id);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/usuario_form_edit.jsp");
        dispatcher.forward(request, response);
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id_cat"));
        
        TecUsuarioDao userDao = ControladorEComerce.fabrica.getUsuarioDao();
        TecUsuario user = userDao.buscar(id);
        
        user.setCliNombre(request.getParameter("nombre_usuario"));
        user.setCliApellido(request.getParameter("apellido_usuario"));
        user.setCliTelefono(request.getParameter("telefono_usuario"));
        user.setCliDireccion(request.getParameter("direccion_usuario"));
        user.setCliComuna(request.getParameter("comuna_usuario"));
        userDao.editar(user);
        response.sendRedirect("/IComerce/usuarios");
    }

}
