/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sergio.project.specialsApp;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author sergio
 */
@Entity
@Table(catalog = "", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"USERNAME"})})
@NamedQueries({
    @NamedQuery(name = "Buser.findAll", query = "SELECT b FROM Buser b"),
    @NamedQuery(name = "Buser.findByUsername", query = "SELECT b FROM Buser b WHERE b.username = :username"),
    @NamedQuery(name = "Buser.findByPassword", query = "SELECT b FROM Buser b WHERE b.password = :password"),
    @NamedQuery(name = "Buser.findByEmail", query = "SELECT b FROM Buser b WHERE b.email = :email"),
    @NamedQuery(name = "Buser.findByStatus", query = "SELECT b FROM Buser b WHERE b.status = :status"),
    @NamedQuery(name = "Buser.findById", query = "SELECT b FROM Buser b WHERE b.id = :id")})
public class Buser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 20)
    @Column(nullable = false, length = 20)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 10)
    @Column(nullable = false, length = 10)
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String email;
    @Size(max = 15)
    @Column(length = 15)
    private String status;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buserid")
    private List<Bprofile> bprofileList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buserid")
    private List<Subscription> subscriptionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buserid")
    private List<Review> reviewList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buserid")
    private List<Brating> bratingList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buserid")
    private List<Specials> specialsList;

    public Buser() {
    }

    public Buser(Integer id) {
        this.id = id;
    }

    public Buser(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Bprofile> getBprofileList() {
        return bprofileList;
    }

    public void setBprofileList(List<Bprofile> bprofileList) {
        this.bprofileList = bprofileList;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<Brating> getBratingList() {
        return bratingList;
    }

    public void setBratingList(List<Brating> bratingList) {
        this.bratingList = bratingList;
    }

    public List<Specials> getSpecialsList() {
        return specialsList;
    }

    public void setSpecialsList(List<Specials> specialsList) {
        this.specialsList = specialsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buser)) {
            return false;
        }
        Buser other = (Buser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sergio.project.specialsApp.Buser[ id=" + id + " ]";
    }
    
}
