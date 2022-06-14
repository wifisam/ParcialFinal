package edu.sv.ujmd.parcialfinal.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.sv.ujmd.parcialfinal.dao.BebidasDAO;
import edu.sv.ujmd.parcialfinal.model.Bebida;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the user.
 *
 * @email Ramesh Fadatare
 */
@WebServlet("/")
public class BebidaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BebidasDAO bebidasDAO;

    public void init() {
        bebidasDAO = new BebidasDAO();
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
                    insertBebida(request, response);
                    break;
                case "/delete":
                    deleteBebida(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateBebida(request, response);
                    break;
                default:
                    listBebida(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBebida(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Bebida> listBebida = bebidasDAO.selectAllBebidas();
        request.setAttribute("listBebida", listBebida);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bebidas-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("bebidas-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Bebida existingBebida = bebidasDAO.selectBebida(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bebidas-form.jsp");
        request.setAttribute("bebida", existingBebida);
        dispatcher.forward(request, response);

    }

    private void insertBebida(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        String bebida = request.getParameter("bebida");
        String precio = request.getParameter("precio");
        Bebida newBebida = new Bebida(bebida, Double.parseDouble(precio));
        bebidasDAO.insertBebida(newBebida);
        response.sendRedirect("list");
    }

    private void updateBebida(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String bebida = request.getParameter("bebida");
        String precio = request.getParameter("precio");
        

        Bebida bebidas = new Bebida(id, bebida, Double.parseDouble(precio));
        bebidasDAO.updateBebida(bebidas);
        response.sendRedirect("list");
    }

    private void deleteBebida(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bebidasDAO.deleteBebidas(id);
        response.sendRedirect("list");

    }

}
