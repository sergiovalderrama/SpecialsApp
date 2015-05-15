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

@WebServlet(name = "EditBusinessProfile", urlPatterns = {"/EditBusinessProfile"})
public class EditBusinessProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("disabled", "disabled");
        } else if (action.equals("edit")) {
            request.setAttribute("disabled", null);
        } else if (action.equals("update")){
            updateBusinessProfile(request);
            request.setAttribute("disabled", "disabled");
        }
        request.setAttribute("bprofile", getBusinessProfile(request));
        request.getRequestDispatcher("WEB-INF/editbprofile.jsp").forward(request, response);
    }
    private Bprofile getBusinessProfile(HttpServletRequest request) {
        Buser buser = (Buser) request.getSession().getAttribute("buser");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        String query = "SELECT b FROM Bprofile b WHERE b.buserid =:buserid";
        Bprofile bprofile = (Bprofile) em.createQuery(query)
                .setParameter("buserid", buser)
                .getSingleResult();
        return bprofile;
    }
    private void updateBusinessProfile(HttpServletRequest request){
        Bprofile bprofile = getBusinessProfile(request);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        String bname = request.getParameter("bname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String bstate = request.getParameter("bstate");
        String zipcode = request.getParameter("zipcode");
        String website = request.getParameter("website");
        bprofile.setBname(bname);
        bprofile.setPhone(phone);
        bprofile.setAddress(address);
        bprofile.setCity(city);
        bprofile.setBstate(bstate);
        bprofile.setZipcode(zipcode);
        bprofile.setWebsite(website);
        em.getTransaction().begin();
        em.merge(bprofile);
        em.getTransaction().commit();
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
