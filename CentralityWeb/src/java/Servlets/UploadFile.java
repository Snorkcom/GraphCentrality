package Servlets;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

        try {

            file = new File("network.net");
            System.out.println("Путь сохраненного файла на сервере: "+ file.getAbsolutePath().toString());

            in = new InputStreamReader(part.getInputStream());           
            
           
            OutputStreamWriter out = new OutputStreamWriter(
                new FileOutputStream(file),
                "UTF-8"
            );

            int c;
            while ((c = in.read()) != -1) {               
                out.write((char) c);
                System.out.println((char) c);
            }
            
            out.flush();
            out.close();
            // путь до файла: System.out.println(myFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();            
        }

       
        
        
        response.setContentType("text/html;charset=UTF-8"); // кодировка символов
        try (PrintWriter responseOut = response.getWriter()) {
             responseOut.write("Граф успешно загружен, перенаправляю на страницу анализа...");
             responseOut.write("<script>"
                     + "function func(){document.location.href = '/CentralityWeb/pages/Graph.html';}\n"
                     + "setTimeout(func, 2000);"
                     + "</script>");
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
