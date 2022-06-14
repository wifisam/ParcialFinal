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

import edu.sv.ujmd.parcialfinal.dao.AlimentosDAO;
import edu.sv.ujmd.parcialfinal.model.Alimentos;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the user.
 *
 * @email Ramesh Fadatare
 */
@WebServlet("/")
public class AlimentoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AlimentosDAO alimentosDAO;

    public void init() {
        alimentosDAO = new AlimentosDAO();
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
                    insertAlimento(request, response);
                    break;
                case "/delete":
                    deleteAlimento(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateAlimento(request, response);
                    break;
                default:
                    listAlimento(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAlimento(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Alimentos> listAlimento = alimentosDAO.selectAllAlimentos();
        request.setAttribute("listAlimento", listAlimento);
        RequestDispatcher dispatcher = request.getRequestDispatcher("alimentos-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("alimentos-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Alimentos existingAlimento = alimentosDAO.selectAlimento(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("alimentos-form.jsp");
        request.setAttribute("alimento", existingAlimento);
        dispatcher.forward(request, response);

    }

    private void insertAlimento(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        String alimento = request.getParameter("alimento");
        String precio = request.getParameter("precio");
        Alimentos newAlimento = new Alimentos(alimento, Double.parseDouble(precio));
        alimentosDAO.insertAlimento(newAlimento);
        response.sendRedirect("list");
    }

    private void updateAlimento(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String alimentos = request.getParameter("alimento");
        String precio = request.getParameter("precio");
        

        Alimentos alimento = new Alimentos(id, alimentos, Double.parseDouble(precio));
        alimentosDAO.updateAlimento(alimento);
        response.sendRedirect("list");
    }

    private void deleteAlimento(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        alimentosDAO.deleteAlimentos(id);
        response.sendRedirect("list");

    }

}
