/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wilfr
 */
public class ControladorEComerce extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorEComerce</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorEComerce at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
       if(userPath.equals("/categoria")){
           System.out.println(" seccion categoria");
           userPath = "categorias";
           
       } else if (userPath.equals("/agregarAlCarro")){
           System.out.println("agregarAlCarro");
           userPath = "agregar_al_carro";
           
       } else if (userPath.equals("/verCarro")){
           System.out.println("verCarro");
           userPath = "carro";
           
       }else if (userPath.equals("/actualizarCarro")){
           System.out.println("actualizarCarro");
           userPath = "actualizar_carro";
           
       }else if (userPath.equals("/pagar")){
           System.out.println("pagar");
           userPath = "pagar";
           
       }else{
           System.out.println("sin seccion ");
           userPath = "not_found";
       }
       
       String url = "/WEB-INF/view/"+userPath+".jsp";
       request.getRequestDispatcher(url).forward(request, response);
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
