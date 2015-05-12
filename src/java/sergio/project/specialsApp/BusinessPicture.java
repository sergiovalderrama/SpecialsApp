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

@WebServlet(name = "BusinessPicture", urlPatterns = {"/BusinessPicture"})
public class BusinessPicture extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getBusinessProfile(request, response);
    }
    private void getBusinessProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Buser buser = (Buser)request.getSession().getAttribute("buser");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        String query = "SELECT b FROM Bprofile b WHERE b.buserid =:buserid";
        Bprofile bprofile = (Bprofile)em.createQuery(query)
                .setParameter("buserid", buser)
                .getSingleResult();
        
        request.setAttribute("bprofile", bprofile);
        request.getRequestDispatcher("WEB-INF/bpicture.jsp").forward(request, response);
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
