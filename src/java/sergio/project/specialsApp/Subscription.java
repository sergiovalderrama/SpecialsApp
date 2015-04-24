package sergio.project.specialsApp;

import java.io.Serializable;
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

@Entity
@Table(catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "Subscription.findAll", query = "SELECT s FROM Subscription s"),
    @NamedQuery(name = "Subscription.findById", query = "SELECT s FROM Subscription s WHERE s.id = :id")})
public class Subscription implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @JoinColumn(name = "BUSERID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Buser buserid;
    @JoinColumn(name = "CUSERID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Cuser cuserid;

    public Subscription() {
    }

    public Subscription(Integer id) {
        this.id = id;
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
        if (!(object instanceof Subscription)) {
            return false;
        }
        Subscription other = (Subscription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sergio.project.specialsApp.Subscription[ id=" + id + " ]";
    }
    
}
