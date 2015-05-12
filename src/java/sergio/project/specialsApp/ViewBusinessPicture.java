package sergio.project.specialsApp;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ViewBusinessPicture", urlPatterns = {"/ViewBusinessPicture"})
public class ViewBusinessPicture extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getImage(request, response);
    }
    private void getImage(HttpServletRequest request, HttpServletResponse response) {
        String profileFor = request.getParameter("for");
        int bid = Integer.parseInt(profileFor);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        try {
            Buser buser = (Buser)em.createNamedQuery("Buser.findById")
                    .setParameter("id", bid)
                    .getSingleResult();
            String query = "SELECT b FROM Bprofile b WHERE b.buserid =:buserid";
            Bprofile bprofile = (Bprofile)em.createQuery(query)
                    .setParameter("buserid", buser)
                    .getSingleResult();
            String pictype = bprofile.getPictype();
            byte[] picdata = (byte[])bprofile.getPicture();
            response.setContentType(pictype);
            response.getOutputStream().write(picdata);
        } catch (Exception e) {
            request.setAttribute("flash", e.getMessage());
        }
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
