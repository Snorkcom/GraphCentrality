package Servlets;


import Algorithms.Centrality;
import Other.CreateGraphFromPajek;
import Other.GraphToJson;
import edu.uci.ics.jung.graph.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

       
        
        //Graph g = new CreateGraphFromPajek().LoadPajek("C:\\Users\\herob\\Desktop\\network.net");
        
        GraphToJson a = new GraphToJson();
        
            /*PrintWriter responseOut = response.getWriter();               
            String stringRanks = ""; // строка для вывода рангов
            Centrality centralitySeeker = new Centrality(g); // объект класса, где лежат алгоритмы            
            centralitySeeker.CalculatePageRank();// поиск CalculateDegreeScorer                      
            stringRanks = centralitySeeker.toString();  // получает строку отсортированного Hashmap'a           
            responseOut.write(stringRanks); // Вывод на html страницу*/
            

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
