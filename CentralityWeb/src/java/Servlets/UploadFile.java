package Servlets;

import Algorithms.CalculateBetweennessCentrality;
import Algorithms.CalculateClosenessCentrality;
import Algorithms.Сentrality;
import Other.CreateGraphFromPajek;
import edu.uci.ics.jung.graph.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Morris
 */
@WebServlet(name = "UploadFile", urlPatterns = {"/UploadFile"})
public class UploadFile extends HttpServlet {

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

        Part part = request.getPart("file");

        File file = null;
        InputStreamReader in = null;
        FileOutputStream out = null;

        try {

            file = new File("C:\\Users\\herob\\Desktop\\network.net");

            in = new InputStreamReader(part.getInputStream());
            out = new FileOutputStream(file);

            int c;
            while ((c = in.read()) != -1) {
                out.write((char) c);
            }

            // путь до файла: System.out.println(myFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.flush();
            out.close();
        }

       

        Graph g = new CreateGraphFromPajek().LoadPajek("C:\\Users\\herob\\Desktop\\network.net");

         try (PrintWriter responseOut = response.getWriter()) {
            
            String stringRanks = ""; // строка для вывода рангов
            Сentrality centralitySeeker = new Сentrality(g); // объект класса, где лежат алгоритмы
            
            centralitySeeker.CalculateBetweennessCentrality(); // поиск BetweennessCentrality                       
            stringRanks = centralitySeeker.toString();  // получает строку отсортированного Hashmap'a           
            responseOut.write(stringRanks); // Вывод на html страницу
            
            responseOut.write("________________________________________________________________ \n");
            
            centralitySeeker.CalculateDegreeScorer(); // поиск DegreeCentrality
            stringRanks = centralitySeeker.toString();
            responseOut.write(stringRanks); // Вывод на html страницу
            
        } catch (Exception ex) { 
             System.out.println("ОШИБКА");
           ex.printStackTrace();            
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
        return "LoadFile";
    }// </editor-fold>

}