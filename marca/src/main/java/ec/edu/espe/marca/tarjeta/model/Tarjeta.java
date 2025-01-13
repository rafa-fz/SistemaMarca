package ec.edu.espe.marca.tarjeta.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ec.edu.espe.marca.bancos.model.BancoEmisor;
import ec.edu.espe.marca.cliente.model.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TARJETA")
public class Tarjeta implements Serializable {
    @Id
    @Column(name = "COD_TARJETA", nullable = false)
    private Integer codTarjeta;

    @Column(name = "COD_BANCO_EMISOR", nullable = false)
    private Integer codBancoEmisor;

    @Column(name = "COD_CLIENTE", nullable = false)
    private Integer codCliente;

    @Column(name = "NUMERO_TARJETA", length = 16, nullable = false)
    private String numeroTarjeta;

    @Column(name = "CVV_", length = 4, nullable = true)
    private String cvv;

    // Campo mapeado a la base de datos
    //@Temporal(TemporalType.DATE)
    @Column(name = "FECHA_EXPIRACION", nullable = true)
    //@Access(AccessType.PROPERTY)
    @JsonProperty("fechaDeExpiracion") // Nombre para entrada/salida JSON
    private LocalDate fechaExpiracion;

    // Campo temporal no mapeado
    //@Transient
    @JsonProperty("fechaExpiracion") // Nombre para entrada/salida JSON
    //@Access(AccessType.FIELD)
    //@JsonIgnore // Este campo no se mostrará en la salida JSON
    private String fechaDeExpiracion;
    
    @Column(name = "TIPO_TARJETA", length = 3, nullable = false)
    private String tipoTarjeta;
    
    @Column(name = "ESTADO_TARJETA", length = 20, nullable = false)
    private String estadoTarjeta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_BANCO_EMISOR", referencedColumnName = "COD_BANCO_EMISOR", insertable = false, updatable = false)
    private BancoEmisor bancoEmisor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_CLIENTE", referencedColumnName = "COD_CLIENTE", insertable = false, updatable = false)
    @JsonIgnore
    private Cliente cliente;

    public Tarjeta() {
    }

    public Tarjeta(Integer codTarjeta) {
        this.codTarjeta = codTarjeta;
    }

    public Integer getCodTarjeta() {
        return codTarjeta;
    }

    public void setCodTarjeta(Integer codTarjeta) {
        this.codTarjeta = codTarjeta;
    }

    public Integer getCodBancoEmisor() {
        return codBancoEmisor;
    }

    public void setCodBancoEmisor(Integer codBancoEmisor) {
        this.codBancoEmisor = codBancoEmisor;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracionFormateada) {
        this.fechaExpiracion = fechaExpiracionFormateada;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public BancoEmisor getBancoEmisor() {
        return bancoEmisor;
    }

    public void setBancoEmisor(BancoEmisor bancoEmisor) {
        this.bancoEmisor = bancoEmisor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codTarjeta == null) ? 0 : codTarjeta.hashCode());
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
        Tarjeta other = (Tarjeta) obj;
        if (codTarjeta == null) {
            if (other.codTarjeta != null)
                return false;
        } else if (!codTarjeta.equals(other.codTarjeta))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Tarjeta [codTarjeta=" + codTarjeta + ", codBancoEmisor=" + codBancoEmisor + ", codCliente=" + codCliente
                + ", numeroTarjeta=" + numeroTarjeta + ", cvv=" + cvv + ", fechaExpiracion=" + fechaDeExpiracion
                + ", tipoTarjeta=" + tipoTarjeta + ", bancoEmisor=" + bancoEmisor + ", cliente=" + cliente + "]";
    }

    public String getFechaDeExpiracion() {
        return fechaDeExpiracion;
    }

    public void setFechaDeExpiracion(String fechaDeExpiracion) {
        this.fechaDeExpiracion = fechaDeExpiracion;
    }

    public String getEstadoTarjeta() {
        return estadoTarjeta;
    }

    public void setEstadoTarjeta(String estadoTarjeta) {
        this.estadoTarjeta = estadoTarjeta;
    }

}
