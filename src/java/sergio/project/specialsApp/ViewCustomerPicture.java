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

@WebServlet(name = "ViewCustomerPicture", urlPatterns = {"/ViewCustomerPicture"})
public class ViewCustomerPicture extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getCustomerImage(request, response);
    }
private void getCustomerImage(HttpServletRequest request, HttpServletResponse response) {
        String profileFor = request.getParameter("for");
        int cid = Integer.parseInt(profileFor);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        try {
            Cuser cuser = (Cuser)em.createNamedQuery("Cuser.findById")
                    .setParameter("id", cid)
                    .getSingleResult();
            String query = "SELECT c FROM Cprofile c WHERE c.cuserid =:cuserid";
            Cprofile cprofile = (Cprofile)em.createQuery(query)
                    .setParameter("cuserid", cuser)
                    .getSingleResult();
            String pictype = cprofile.getPictype();
            byte[] picdata = (byte[])cprofile.getPicture();
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
