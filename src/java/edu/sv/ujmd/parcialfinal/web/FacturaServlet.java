/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.sv.ujmd.parcialfinal.web;

import edu.sv.ujmd.parcialfinal.dao.FacturaDAO;
import edu.sv.ujmd.parcialfinal.model.Factura;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "FacturaServlet", urlPatterns = {"/FacturaServlet"})
public class FacturaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private FacturaDAO facturaDAO;

    public void init() {
        facturaDAO = new FacturaDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertFactura(request, response);
                    break;
                case "/delete":
                    deleteFacturas(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateFacturas(request, response);
                    break;
                default:
                    listFacturas(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listFacturas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Factura> listFactura = facturaDAO.selectAllFacturas();
        request.setAttribute("listFactura", listFactura);
        RequestDispatcher dispatcher = request.getRequestDispatcher("factura-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("facturas-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Factura existingFactura = facturaDAO.selectFactura(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("factura-form.jsp");
        request.setAttribute("Factura", existingFactura);
        dispatcher.forward(request, response);

    }

    private void insertFactura(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        String id_alimento = request.getParameter("Alimento");
        String id_bebida = request.getParameter("Bebida");
        String subtotal = request.getParameter("Subtotal");
        Factura newFactura = new Factura(Integer.parseInt (id_alimento),Integer.parseInt(id_bebida),Double.parseDouble(subtotal));
        facturaDAO.insertFactura(newFactura);
        response.sendRedirect("list");
    }
//Double.parseDouble(id_alimento),Double.parseDouble(id_bebida),
    private void updateFacturas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int id_alimento = Integer.parseInt(request.getParameter("alimento"));
        int id_bebida = Integer.parseInt(request.getParameter("Bebida"));
        Double subtotal = Double.parseDouble (request.getParameter("subtotal"));
        
        Factura newfactura = new Factura(id,id_alimento,id_bebida,subtotal);
        facturaDAO.updateFacturas(newfactura);
        response.sendRedirect("list");
    }

    private void deleteFacturas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        facturaDAO.deleteFacturas(id);
        response.sendRedirect("list");

    }

}



