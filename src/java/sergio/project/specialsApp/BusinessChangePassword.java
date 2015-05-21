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

@WebServlet(name = "BusinessChangePassword", urlPatterns = {"/BusinessChangePassword"})
public class BusinessChangePassword extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("WEB-INF/bchangepassword.jsp").forward(request, response);
        } else if (action.equals("changep")) {
            changeBusinessPassword(request, response);
        }
        request.getRequestDispatcher("WEB-INF/bchangepassword.jsp").forward(request, response);
    }
    private void changeBusinessPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Buser buser = (Buser) request.getSession().getAttribute("buser");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword1 = request.getParameter("newpassword1");
        String newpassword2 = request.getParameter("newpassword2");
        if (!oldpassword.equals(buser.getPassword())) {
            request.setAttribute("flash", "Your current password did not match");
            request.getRequestDispatcher("WEB-INF/bchangepassword.jsp").forward(request, response);
        } else if (!newpassword1.equals(newpassword2)) {
            request.setAttribute("flash", "The new password does not match.");
            request.getRequestDispatcher("WEB-INF/bchangepassword.jsp").forward(request, response);
        } else if(newpassword1.length() < 4 || newpassword1.length() > 12 ){
            request.setAttribute("flash", "Password length must be greater than 4 and less than 12 characters.");
        }else{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
            EntityManager em = emf.createEntityManager();
            buser.setPassword(newpassword1);
            em.getTransaction().begin();
            em.merge(buser);
            em.getTransaction().commit();
            request.setAttribute("flash", "Password has been changed");
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
