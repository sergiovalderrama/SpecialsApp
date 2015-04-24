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
    @NamedQuery(name = "Bprofile.findAll", query = "SELECT b FROM Bprofile b"),
    @NamedQuery(name = "Bprofile.findByBname", query = "SELECT b FROM Bprofile b WHERE b.bname = :bname"),
    @NamedQuery(name = "Bprofile.findByPhone", query = "SELECT b FROM Bprofile b WHERE b.phone = :phone"),
    @NamedQuery(name = "Bprofile.findByAddress", query = "SELECT b FROM Bprofile b WHERE b.address = :address"),
    @NamedQuery(name = "Bprofile.findByWebsite", query = "SELECT b FROM Bprofile b WHERE b.website = :website"),
    @NamedQuery(name = "Bprofile.findByPictype", query = "SELECT b FROM Bprofile b WHERE b.pictype = :pictype"),
    @NamedQuery(name = "Bprofile.findByBuserid", query = "SELECT b FROM Bprofile b WHERE b.buserid = :buserid"),
    @NamedQuery(name = "Bprofile.findById", query = "SELECT b FROM Bprofile b WHERE b.id = :id")})
public class Bprofile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String bname;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(nullable = false, length = 15)
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(nullable = false, length = 70)
    private String website;
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
    @JoinColumn(name = "BUSERID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Buser buserid;

    public Bprofile() {
    }

    public Bprofile(Integer id) {
        this.id = id;
    }

    public Bprofile(Integer id, String bname, String phone, String address, String website) {
        this.id = id;
        this.bname = bname;
        this.phone = phone;
        this.address = address;
        this.website = website;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public Buser getBuserid() {
        return buserid;
    }

    public void setBuserid(Buser buserid) {
        this.buserid = buserid;
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
        if (!(object instanceof Bprofile)) {
            return false;
        }
        Bprofile other = (Bprofile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sergio.project.specialsApp.Bprofile[ id=" + id + " ]";
    }
    
}
