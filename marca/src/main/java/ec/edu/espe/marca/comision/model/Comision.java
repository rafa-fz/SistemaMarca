package ec.edu.espe.marca.comision.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import ec.edu.espe.marca.liquidacion.model.Liquidacion;
import ec.edu.espe.marca.transaccion.model.Transaccion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMISION")
public class Comision implements Serializable {

    @Id
    @Column(name = "COD_COMISION", nullable = false)
    private Integer codComision;

    @Column(name = "MARCA", nullable = false, length = 20)
    private String marca;

    //CÓDIGO BIN DE LA TARJETA (6 DÍGITOS)
    @Column(name = "PREFIJO_MARCA", nullable = false, length = 6)
    private String prefijoMarca;

    @Column(name = "TIPO", nullable = false, length = 15)
    private String tipo;

    @Column(name = "PAIS", nullable = false, length = 3)
    private String pais;

    @Column(name = "COMISION_INTERNACIONAL", precision = 18, scale = 4)
    private BigDecimal comisionInternacional;

    @Column(name = "COMISION_LOCAL", precision = 18, scale = 4)
    private BigDecimal comisionLocal;

    @Column(name = "TIPO_COMISION", nullable = false, length = 15)
    private String tipoComision;

    @Column(name = "COMISION_FIJA", precision = 18, scale = 4)
    private BigDecimal comisionFija;

    @Column(name = "ESTADO", nullable = false, length = 10)
    private String estado;

    @Column(name = "FECHA_CREACION", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion;

    @OneToMany(mappedBy = "comision", cascade = CascadeType.ALL) // , fetch = FetchType.LAZY)
    private List<Transaccion> transacciones;

    @OneToMany(mappedBy = "comision", cascade = CascadeType.ALL) // , fetch = FetchType.LAZY)
    private List<Liquidacion> liquidaciones;

    // Getters y Setters
    public Integer getCodComision() {
        return codComision;
    }

    public void setCodComision(Integer codComision) {
        this.codComision = codComision;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPrefijoMarca() {
        return prefijoMarca;
    }

    public void setPrefijoMarca(String prefijoMarca) {
        this.prefijoMarca = prefijoMarca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public BigDecimal getComisionInternacional() {
        return comisionInternacional;
    }

    public void setComisionInternacional(BigDecimal comisionInternacional) {
        this.comisionInternacional = comisionInternacional;
    }

    public BigDecimal getComisionLocal() {
        return comisionLocal;
    }

    public void setComisionLocal(BigDecimal comisionLocal) {
        this.comisionLocal = comisionLocal;
    }

    public String getTipoComision() {
        return tipoComision;
    }

    public void setTipoComision(String tipoComision) {
        this.tipoComision = tipoComision;
    }

    public BigDecimal getComisionFija() {
        return comisionFija;
    }

    public void setComisionFija(BigDecimal comisionFija) {
        this.comisionFija = comisionFija;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public List<Liquidacion> getLiquidaciones() {
        return liquidaciones;
    }

    public void setLiquidaciones(List<Liquidacion> liquidaciones) {
        this.liquidaciones = liquidaciones;
    }
}
