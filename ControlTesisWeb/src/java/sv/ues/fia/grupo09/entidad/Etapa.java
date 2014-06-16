/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.fia.grupo09.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author oscar
 */
@Entity
@Table(name = "etapa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etapa.findAll", query = "SELECT e FROM Etapa e")})
public class Etapa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "IDETAPA")
    private String idetapa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHADEINICIOETAPA")
    @Temporal(TemporalType.DATE)
    private Date fechadeinicioetapa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHADEFINALIZACIONETAPA")
    @Temporal(TemporalType.DATE)
    private Date fechadefinalizacionetapa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etapa", fetch = FetchType.LAZY)
    private List<Nota> notaList;

    public Etapa() {
    }

    public Etapa(String idetapa) {
        this.idetapa = idetapa;
    }

    public Etapa(String idetapa, Date fechadeinicioetapa, Date fechadefinalizacionetapa) {
        this.idetapa = idetapa;
        this.fechadeinicioetapa = fechadeinicioetapa;
        this.fechadefinalizacionetapa = fechadefinalizacionetapa;
    }

    public String getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(String idetapa) {
        this.idetapa = idetapa;
    }

    public Date getFechadeinicioetapa() {
        return fechadeinicioetapa;
    }

    public void setFechadeinicioetapa(Date fechadeinicioetapa) {
        this.fechadeinicioetapa = fechadeinicioetapa;
    }

    public Date getFechadefinalizacionetapa() {
        return fechadefinalizacionetapa;
    }

    public void setFechadefinalizacionetapa(Date fechadefinalizacionetapa) {
        this.fechadefinalizacionetapa = fechadefinalizacionetapa;
    }

    @XmlTransient
    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idetapa != null ? idetapa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etapa)) {
            return false;
        }
        Etapa other = (Etapa) object;
        if ((this.idetapa == null && other.idetapa != null) || (this.idetapa != null && !this.idetapa.equals(other.idetapa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.ues.fia.grupo09.entidad.Etapa[ idetapa=" + idetapa + " ]";
    }
    
}
