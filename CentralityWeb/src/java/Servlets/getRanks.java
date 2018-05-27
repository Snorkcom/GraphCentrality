package Servlets;

import Algorithms.Centrality;
import Other.CreateGraphFromPajek;
import edu.uci.ics.jung.graph.Graph;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Morris
 */
@WebServlet(name = "getRanks", urlPatterns = {"/getRanks"})
public class getRanks extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id")); // получает гет параметр id

        try (PrintWriter out = response.getWriter()) {
            // Загрузить граф из .net файла
            Graph g = new CreateGraphFromPajek().LoadPajek("network.net");
            Centrality centralitySeeker = new Centrality(g);
            switch (id) {
                case 1:
                    // BetweennessCentrality
                     centralitySeeker.CalculateBetweennessCentrality();
                    break;
                case 2:
                    // ClosenessCentrality
                     centralitySeeker.CalculateClosenessCentrality();
                    break;
                case 3:
                    // DegreeCentrality
                     centralitySeeker.CalculateDegreeScorer();
                    break;
                case 4:
                    // EigenvectorCentrality
                    centralitySeeker.CalculateEigenvectorCentrality();
                    break;
                case 5:
                    // PageRank
                    centralitySeeker.CalculatePageRank();
                    break;
                case 6:
                    // HITS
                    centralitySeeker.CalculateHITS();
                    break;

                default:
                    return;
            }
            
            String stringRanks = centralitySeeker.toString();
            out.write(stringRanks);
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
        processRequest(request, response);
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
