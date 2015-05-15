package sergio.project.specialsApp;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BusinessIndividualComments", urlPatterns = {"/BusinessIndividualComments"})
public class BusinessIndividualComments extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          Buser buser = (Buser)request.getSession().getAttribute("buser");
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
          EntityManager em = emf.createEntityManager();
          String query = "SELECT Review.post, Cuser.username, Review.rdatetime FROM Review, Cuser "
                  + "WHERE Review.buserid =? and Cuser.id = Review.cuserid";
          List<Object> breview = em.createNativeQuery(query)
                  .setParameter(1, buser.getId())
                  .getResultList();
          request.setAttribute("breview", breview);
          request.getRequestDispatcher("WEB-INF/bindividualcomments.jsp").forward(request, response);
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
