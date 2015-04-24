package sergio.project.specialsApp;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "Cprofile.findAll", query = "SELECT c FROM Cprofile c"),
    @NamedQuery(name = "Cprofile.findByFname", query = "SELECT c FROM Cprofile c WHERE c.fname = :fname"),
    @NamedQuery(name = "Cprofile.findByLname", query = "SELECT c FROM Cprofile c WHERE c.lname = :lname"),
    @NamedQuery(name = "Cprofile.findByBiography", query = "SELECT c FROM Cprofile c WHERE c.biography = :biography"),
    @NamedQuery(name = "Cprofile.findByPictype", query = "SELECT c FROM Cprofile c WHERE c.pictype = :pictype"),
    @NamedQuery(name = "Cprofile.findById", query = "SELECT c FROM Cprofile c WHERE c.id = :id")})
public class Cprofile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String fname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(nullable = false, length = 25)
    private String lname;
    @Size(max = 1024)
    @Column(length = 1024)
    private String biography;
    @Lob
    private Serializable picture;
    @Size(max = 30)
    @Column(length = 30)
    private String pictype;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @JoinColumn(name = "CUSERID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Cuser cuserid;

    public Cprofile() {
    }

    public Cprofile(Integer id) {
        this.id = id;
    }

    public Cprofile(Integer id, String fname, String lname) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Serializable getPicture() {
        return picture;
    }

    public void setPicture(Serializable picture) {
        this.picture = picture;
    }

    public String getPictype() {
        return pictype;
    }

    public void setPictype(String pictype) {
        this.pictype = pictype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cuser getCuserid() {
        return cuserid;
    }

    public void setCuserid(Cuser cuserid) {
        this.cuserid = cuserid;
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
        if (!(object instanceof Cprofile)) {
            return false;
        }
        Cprofile other = (Cprofile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sergio.project.specialsApp.Cprofile[ id=" + id + " ]";
    }
    
}
