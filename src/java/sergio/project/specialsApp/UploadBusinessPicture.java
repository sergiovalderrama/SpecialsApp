package sergio.project.specialsApp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "UploadBusinessPicture", urlPatterns = {"/UploadBusinessPicture"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 20)
public class UploadBusinessPicture extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        uploadImage(request, response);
    }
    private void uploadImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equals("GET")){
            request.getRequestDispatcher("WEB-INF/bpicture.jsp").forward(request, response);
        }
        final Part filePart = request.getPart("pic");
        String filetype = filePart.getContentType();
        if (!filetype.contains("image")) {
                request.setAttribute("flash", "The uploaded file is not an image!");
                request.getRequestDispatcher("WEB-INF/bpicture.jsp").forward(request, response);
        }
        InputStream imgdata = filePart.getInputStream();
        byte[] pixels = readImage(imgdata);            
        Buser buser = (Buser)request.getSession().getAttribute("buser");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        String query ="SELECT p FROM Bprofile p WHERE p.buserid =:buserid";
        Bprofile p = (Bprofile)em.createQuery(query)
                .setParameter("buserid", buser)
                .getSingleResult();
        p.setPicture(pixels);
        p.setPictype(filetype);
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            request.setAttribute("flash","File Did Not Load");
            request.getRequestDispatcher("WEB-INF/bpicture.jsp").forward(request, response);
        }
        request.setAttribute("bprofile", p);
        request.setAttribute("flash", "File Uploaded");
        request.getRequestDispatcher("WEB-INF/bpicture.jsp").forward(request, response);
    }
    private byte[] readImage(InputStream imgdata) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[0xFFFF];
        for (int len; (len = imgdata.read(buffer)) != -1;)
            os.write(buffer, 0, len);
        os.flush();
        return os.toByteArray();        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
