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
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
    @NamedQuery(name = "Review.findByPost", query = "SELECT r FROM Review r WHERE r.post = :post"),
    @NamedQuery(name = "Review.findByRdatetime", query = "SELECT r FROM Review r WHERE r.rdatetime = :rdatetime"),
    @NamedQuery(name = "Review.findById", query = "SELECT r FROM Review r WHERE r.id = :id")})
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(nullable = false, length = 1024)
    private String post;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date rdatetime;
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

    public Review() {
    }

    public Review(Integer id) {
        this.id = id;
    }

    public Review(Integer id, String post, Date rdatetime) {
        this.id = id;
        this.post = post;
        this.rdatetime = rdatetime;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getRdatetime() {
        return rdatetime;
    }

    public void setRdatetime(Date rdatetime) {
        this.rdatetime = rdatetime;
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
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sergio.project.specialsApp.Review[ id=" + id + " ]";
    }
    
}
