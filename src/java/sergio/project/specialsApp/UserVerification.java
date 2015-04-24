package sergio.project.specialsApp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class UserVerification {

    String verifyUser(String username, String username2, String password,
            String password2, String email, String email2) {
        if (username.length() < 4 || username.length() > 20) {
            return "Username must be between 4 and 20 characters.";
        } else if (password.length() < 4 || password.length() > 10) {
            return "Password must be between 4 and 10 characters.";
        } else if (!username.equals(username2)) {
            return "Username does not match.";
        } else if (!password.equals(password2)) {
            return "Password does not match.";
        } else if (!email.equals(email2)) {
            return "E-mail does not match";
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpecialsAppPU");
        EntityManager em = emf.createEntityManager();
        try {
            Buser buserName = (Buser) em.createNamedQuery("Buser.findByUsername")
                    .setParameter("username", username)
                    .getSingleResult();
            if (buserName != null) {
                return "Username: " + buserName.getUsername() + " is already in use.";
            }
        } catch (NoResultException nre1) {
            try {
                Cuser cuserName = (Cuser) em.createNamedQuery("Cuser.findByUsername")
                        .setParameter("username", username)
                        .getSingleResult();
                if (cuserName != null) {
                    return "Username: " + cuserName.getUsername() + " is already in use.";
                }
            } catch (NoResultException nre2) {
                try {
                    Administrator administratorName = (Administrator) em.createNamedQuery("Administrator.findByUsername")
                            .setParameter("username", username)
                            .getSingleResult();
                    if (administratorName != null) {
                        return "Username: " + administratorName.getUsername() + " is already in use";
                    }
                } catch (NoResultException nre3) {
                    return "valid";
                }
            }
        }
        return "valid";
    }
}
