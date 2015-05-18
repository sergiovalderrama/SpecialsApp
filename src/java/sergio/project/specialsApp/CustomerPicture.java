package sergio.project.specialsApp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CustomerPicture", urlPatterns = {"/CustomerPicture"})
public class CustomerPicture extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getCustomerProfile(request, response);
    }
    private void getCustomerProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Cuser cuser = (Cuser)request.getSession().getAttribute("cuser");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        String query = "SELECT c FROM Cprofile c WHERE c.cuserid =:cuserid";
        Cprofile cprofile = (Cprofile)em.createQuery(query)
                .setParameter("cuserid", cuser)
                .getSingleResult();
        
        request.setAttribute("cprofile", cprofile);
        request.getRequestDispatcher("WEB-INF/cpicture.jsp").forward(request, response);
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
