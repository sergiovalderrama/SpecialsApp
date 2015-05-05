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

@WebServlet(name = "InsertBusinessProfile", urlPatterns = {"/InsertBusinessProfile"})
public class InsertBusinessProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("WEB-INF/brofile.jsp").forward(request, response);
        } else if (action.equals("bprofile")) {
            insertBusinessProfile(request, response);
        }
    }
    private void insertBusinessProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bname = request.getParameter("bname");
        String bphone = request.getParameter("bphone");
        String baddress = request.getParameter("baddress");
        String bwebsite = request.getParameter("bwebsite");
        Buser buser = (Buser) request.getSession().getAttribute("buser");
        if (buser == null) {
            response.sendRedirect("BusinessLogin");
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        Bprofile bprofile = new Bprofile();
        bprofile.setBname(bname);
        bprofile.setPhone(bphone);
        bprofile.setAddress(baddress);
        bprofile.setWebsite(bwebsite);
        bprofile.setBuserid(buser);
        em.getTransaction().begin();
        em.merge(bprofile);
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("BusinessLogin");
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
