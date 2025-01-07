package ec.edu.espe.marca.tarjeta.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
import ec.edu.espe.marca.bancos.model.BancoEmisor;
import ec.edu.espe.marca.cliente.model.Cliente;

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
    @Column(name = "CVV", length = 4, nullable = true)
    private String cvv;
    //@Temporal(TemporalType.DATE)
    @Column(name = "FECHA_EXPIRACION", nullable = false)
    private LocalDate fechaExpiracion;
    @Column(name = "TIPO_TARJETA", length = 3, nullable = false)
    private String tipoTarjeta;

    @ManyToOne
    @JoinColumn(name = "COD_BANCO_EMISOR", referencedColumnName = "COD_BANCO_EMISOR", insertable = false, updatable = false)
    private BancoEmisor bancoEmisor;
    @ManyToOne
    @JoinColumn(name = "COD_CLIENTE", referencedColumnName = "COD_CLIENTE", insertable = false, updatable = false)
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

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
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
                + ", numeroTarjeta=" + numeroTarjeta + ", cvv=" + cvv + ", fechaExpiracion=" + fechaExpiracion
                + ", tipoTarjeta=" + tipoTarjeta + ", bancoEmisor=" + bancoEmisor + ", cliente=" + cliente + "]";
    }

}
