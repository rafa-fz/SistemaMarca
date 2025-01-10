package ec.edu.espe.marca.comision.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import ec.edu.espe.marca.transaccion.model.Transaccion;

@Entity
@Table(name = "COMISION")
public class Comision implements Serializable {
    @Id
    @Column(name = "COD_COMISION", length = 10, nullable = false)
    private Integer codComision;

    @Column(name = "COD_TRANSACCION", length = 10, nullable = false)
    private Integer codTransaccion;

    @Column(name = "MONTO_COMISION", precision = 18, scale = 2, nullable = false)
    private BigDecimal montoComision;

    @Column(name = "TIPO_COMISION", length = 20, nullable = false)
    private String tipoComision;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_COMISION", nullable = false)
    private LocalDate fechaComision;

    @Column(name = "ESTADO", length = 3, nullable = false)
    private String estado;

    @Column(name = "DESCRIPCION", length = 255, nullable = true)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "COD_TRANSACCION", referencedColumnName = "COD_TRANSACCION", insertable = false, updatable = false)
    private Transaccion transaccion;

    public Comision() {
    }

    public Comision(Integer codComision) {
        this.codComision = codComision;
    }

    public Integer getCodComision() {
        return codComision;
    }

    public void setCodComision(Integer codComision) {
        this.codComision = codComision;
    }

    public Integer getCodTransaccion() {
        return codTransaccion;
    }

    public void setCodTransaccion(Integer codTransaccion) {
        this.codTransaccion = codTransaccion;
    }

    public BigDecimal getMontoComision() {
        return montoComision;
    }

    public void setMontoComision(BigDecimal montoComision) {
        this.montoComision = montoComision;
    }

    public String getTipoComision() {
        return tipoComision;
    }

    public void setTipoComision(String tipoComision) {
        this.tipoComision = tipoComision;
    }

    public LocalDate getFechaComision() {
        return fechaComision;
    }

    public void setFechaComision(LocalDate fechaComision) {
        this.fechaComision = fechaComision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codComision == null) ? 0 : codComision.hashCode());
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
        Comision other = (Comision) obj;
        if (codComision == null) {
            if (other.codComision != null)
                return false;
        } else if (!codComision.equals(other.codComision))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Comision [codComision=" + codComision + ", codTransaccion=" + codTransaccion + ", montoComision="
                + montoComision + ", tipoComision=" + tipoComision + ", fechaComision=" + fechaComision + ", estado="
                + estado + ", descripcion=" + descripcion + ", transaccion=" + transaccion + "]";
    }
}
