/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.fia.grupo09.entidad;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author oscar
 */
@Entity
@Table(name = "alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a")})
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "CARNET")
    private String carnet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "APELLIDOALUMNO")
    private String apellidoalumno;
    @Size(max = 30)
    @Column(name = "SEXOALUMNO")
    private String sexoalumno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DIRECCIONALUMNO")
    private String direccionalumno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "EMAILALUMNO")
    private String emailalumno;
    @Size(max = 8)
    @Column(name = "TELEFONOALUMNO")
    private String telefonoalumno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<Nota> notaList;

    public Alumno() {
    }

    public Alumno(String carnet) {
        this.carnet = carnet;
    }

    public Alumno(String carnet, String nombre, String apellidoalumno, String direccionalumno, String emailalumno) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellidoalumno = apellidoalumno;
        this.direccionalumno = direccionalumno;
        this.emailalumno = emailalumno;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoalumno() {
        return apellidoalumno;
    }

    public void setApellidoalumno(String apellidoalumno) {
        this.apellidoalumno = apellidoalumno;
    }

    public String getSexoalumno() {
        return sexoalumno;
    }

    public void setSexoalumno(String sexoalumno) {
        this.sexoalumno = sexoalumno;
    }

    public String getDireccionalumno() {
        return direccionalumno;
    }

    public void setDireccionalumno(String direccionalumno) {
        this.direccionalumno = direccionalumno;
    }

    public String getEmailalumno() {
        return emailalumno;
    }

    public void setEmailalumno(String emailalumno) {
        this.emailalumno = emailalumno;
    }

    public String getTelefonoalumno() {
        return telefonoalumno;
    }

    public void setTelefonoalumno(String telefonoalumno) {
        this.telefonoalumno = telefonoalumno;
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
        hash += (carnet != null ? carnet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.carnet == null && other.carnet != null) || (this.carnet != null && !this.carnet.equals(other.carnet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.ues.fia.grupo09.entidad.Alumno[ carnet=" + carnet + " ]";
    }
    
}
