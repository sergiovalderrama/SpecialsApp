package sergio.project.specialsApp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "UploadCustomerPicture", urlPatterns = {"/UploadCustomerPicture"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 20)
public class UploadCustomerPicture extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        uploadImage(request, response);
    }
    private void uploadImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher("WEB-INF/cpicture.jsp").forward(request, response);
        }
        final Part filePart = request.getPart("pic");
        String filetype = filePart.getContentType();
        if (!filetype.contains("image")) {
            request.setAttribute("flash", "The uploaded file is not an image!");
            request.getRequestDispatcher("WEB-INF/cpicture.jsp").forward(request, response);
        }
        InputStream imgdata = filePart.getInputStream();
        byte[] pixels = readImage(imgdata);
        Cuser cuser = (Cuser) request.getSession().getAttribute("cuser");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        String query = "SELECT p FROM Cprofile p WHERE p.cuserid =:cuserid";
        Cprofile p = (Cprofile) em.createQuery(query)
                .setParameter("cuserid", cuser)
                .getSingleResult();
        p.setPicture(pixels);
        p.setPictype(filetype);
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            request.setAttribute("flash", "File Did Not Load");
            request.getRequestDispatcher("WEB-INF/cpicture.jsp").forward(request, response);
        }
        request.setAttribute("cprofile", p);
        request.setAttribute("flash", "File Uploaded");
        request.getRequestDispatcher("WEB-INF/cpicture.jsp").forward(request, response);
    }

    private byte[] readImage(InputStream imgdata) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[0xFFFF];
        for (int len; (len = imgdata.read(buffer)) != -1;) {
            os.write(buffer, 0, len);
        }
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
