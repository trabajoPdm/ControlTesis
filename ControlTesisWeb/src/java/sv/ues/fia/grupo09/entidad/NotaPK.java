/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.fia.grupo09.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author oscar
 */
@Embeddable
public class NotaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "IDETAPA")
    private String idetapa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "CARNET")
    private String carnet;

    public NotaPK() {
    }

    public NotaPK(String idetapa, String carnet) {
        this.idetapa = idetapa;
        this.carnet = carnet;
    }

    public String getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(String idetapa) {
        this.idetapa = idetapa;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idetapa != null ? idetapa.hashCode() : 0);
        hash += (carnet != null ? carnet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotaPK)) {
            return false;
        }
        NotaPK other = (NotaPK) object;
        if ((this.idetapa == null && other.idetapa != null) || (this.idetapa != null && !this.idetapa.equals(other.idetapa))) {
            return false;
        }
        if ((this.carnet == null && other.carnet != null) || (this.carnet != null && !this.carnet.equals(other.carnet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.ues.fia.grupo09.entidad.NotaPK[ idetapa=" + idetapa + ", carnet=" + carnet + " ]";
    }
    
}
