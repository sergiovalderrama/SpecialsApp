package sergio.project.specialsApp;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "", schema = "sergio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Specials.findAll", query = "SELECT s FROM Specials s"),
    @NamedQuery(name = "Specials.findBySdate", query = "SELECT s FROM Specials s WHERE s.sdate = :sdate ORDER BY s.sdate, s.stime"),
    @NamedQuery(name = "Specials.findByStime", query = "SELECT s FROM Specials s WHERE s.stime = :stime"),
    @NamedQuery(name = "Specials.findByStime2", query = "SELECT s FROM Specials s WHERE s.stime2 = :stime2"),
    @NamedQuery(name = "Specials.findByStype", query = "SELECT s FROM Specials s WHERE s.stype = :stype"),
    @NamedQuery(name = "Specials.findBySpecial", query = "SELECT s FROM Specials s WHERE s.special = :special"),
    @NamedQuery(name = "Specials.findByBuserid", query = "SELECT s FROM Specials s WHERE s.buserid = :buserid ORDER BY s.sdate"),
    @NamedQuery(name = "Specials.findById", query = "SELECT s FROM Specials s WHERE s.id = :id")})
public class Specials implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date sdate;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date stime;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date stime2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(nullable = false, length = 25)
    private String stype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(nullable = false, length = 500)
    private String special;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @JoinColumn(name = "BUSERID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Buser buserid;

    public Specials() {
    }

    public Specials(Integer id) {
        this.id = id;
    }

    public Specials(Integer id, Date sdate, Date stime, Date stime2, String stype, String special) {
        this.id = id;
        this.sdate = sdate;
        this.stime = stime;
        this.stime2 = stime2;
        this.stype = stype;
        this.special = special;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getStime2() {
        return stime2;
    }

    public void setStime2(Date stime2) {
        this.stime2 = stime2;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
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
        if (!(object instanceof Specials)) {
            return false;
        }
        Specials other = (Specials) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sergio.project.specialsApp.Specials[ id=" + id + " ]";
    }
    
}
