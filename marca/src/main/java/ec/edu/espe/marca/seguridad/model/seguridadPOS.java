package ec.edu.espe.marca.seguridad.model;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;

@Entity
@Table(name = "SEGURIDAD_POS")
public class seguridadPOS implements Serializable {

    @Id
    @Column(name = "COD_POS", length = 4, nullable = false)
    private Integer codPos;
    @Column(name = "CLAVE", length = 128, nullable = false)
    private String clave;
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_ACTUALIZACION", nullable = false)
    private LocalDate fechaActualizacion;

    public seguridadPOS() {
    }

    public seguridadPOS(Integer codPos) {
        this.codPos = codPos;
    }

    public Integer getCodPos() {
        return codPos;
    }

    public void setCodPos(Integer codPos) {
        this.codPos = codPos;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codPos == null) ? 0 : codPos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        seguridadPOS other = (seguridadPOS) obj;
        if (codPos == null) {
            if (other.codPos != null)
                return false;
        } else if (!codPos.equals(other.codPos))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SeguridadPOS [codPos=" + codPos + ", clave=" + clave + ", fechaActualizacion=" + fechaActualizacion
                + "]";
    }

}
