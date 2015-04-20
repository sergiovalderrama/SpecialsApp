/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author sergio
 */
@Entity
@Table(catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "Specials.findAll", query = "SELECT s FROM Specials s"),
    @NamedQuery(name = "Specials.findBySdate", query = "SELECT s FROM Specials s WHERE s.sdate = :sdate"),
    @NamedQuery(name = "Specials.findByStime", query = "SELECT s FROM Specials s WHERE s.stime = :stime"),
    @NamedQuery(name = "Specials.findByStime2", query = "SELECT s FROM Specials s WHERE s.stime2 = :stime2"),
    @NamedQuery(name = "Specials.findByStype", query = "SELECT s FROM Specials s WHERE s.stype = :stype"),
    @NamedQuery(name = "Specials.findBySpecial", query = "SELECT s FROM Specials s WHERE s.special = :special"),
    @NamedQuery(name = "Specials.findByPrice", query = "SELECT s FROM Specials s WHERE s.price = :price"),
    @NamedQuery(name = "Specials.findByBuserid", query = "SELECT s FROM Specials s WHERE s.buserid = :buserid ORDER BY s.sdate"),
    @NamedQuery(name = "Specials.findById", query = "SELECT s FROM Specials s WHERE s.id = :id")})

public class Specials implements Serializable {
    private static final long serialVersionUID = 1L;
    @Temporal(TemporalType.DATE)
    private Date sdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(nullable = false, length = 15)
    private String stime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(nullable = false, length = 15)
    private String stime2;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(nullable = false, length = 30)
    private String price;
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

    public Specials(Integer id, String stime, String stime2, String stype, String special, String price) {
        this.id = id;
        this.stime = stime;
        this.stime2 = stime2;
        this.stype = stype;
        this.special = special;
        this.price = price;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getStime2() {
        return stime2;
    }

    public void setStime2(String stime2) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
