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
@Table(name = "SEGURIDAD_GATEWAY")
public class SeguridadGateway implements Serializable {

    @Id
    @Column(name = "COD_GATEWAY", length = 4, nullable = false)
    private Integer codGateway;
    @Column(name = "CLAVE", length = 128, nullable = false)
    private String clave;
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_ACTUALIZACION", nullable = false)
    private LocalDate fechaActualizacion;

    public SeguridadGateway() {
    }

    public SeguridadGateway(Integer codGateway) {
        this.codGateway = codGateway;
    }

    public Integer getCodGateway() {
        return codGateway;
    }

    public void setCodGateway(Integer codGateway) {
        this.codGateway = codGateway;
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
        result = prime * result + ((codGateway == null) ? 0 : codGateway.hashCode());
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
        SeguridadGateway other = (SeguridadGateway) obj;
        if (codGateway == null) {
            if (other.codGateway != null)
                return false;
        } else if (!codGateway.equals(other.codGateway))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SeguridadGateway [codGateway=" + codGateway + ", clave=" + clave + ", fechaActualizacion="
                + fechaActualizacion + "]";
    }

}
