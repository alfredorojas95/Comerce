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

}
