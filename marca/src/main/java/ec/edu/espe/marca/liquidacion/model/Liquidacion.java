package ec.edu.espe.marca.liquidacion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import ec.edu.espe.marca.bancos.model.BancoAdquiriente;
import ec.edu.espe.marca.bancos.model.BancoEmisor;
import ec.edu.espe.marca.comision.model.Comision;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "LIQUIDACION")
public class Liquidacion implements Serializable {
    @Id
    @Column(name = "COD_LIQUIDACION", length = 10, nullable = false)
    private Integer codLiquidacion;

    @Column(name = "COD_BANCO_ADQUIRENTE")
    private Integer codBancoAdquirente;

    @Column(name = "COD_BANCO_EMISOR")
    private Integer codBancoEmisor;

    @Column(name = "COD_COMISION")
    private Integer codComision;

    //@Temporal(TemporalType.DATE)
    @Column(name = "FECHA_LIQUIDACION", nullable = false)
    private LocalDateTime fechaLiquidacion;

    @Column(name = "TOTAL_LIQUIDADO", precision = 18, scale = 2, nullable = false)
    private BigDecimal totalLiquidado;

    @Column(name = "TARIFAS", precision = 18, scale = 2, nullable = false)
    private BigDecimal tarifas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_BANCO_ADQUIRENTE", referencedColumnName = "COD_BANCO_ADQUIRENTE", insertable = false, updatable = false)
    private BancoAdquiriente bancoAdquiriente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_BANCO_EMISOR", referencedColumnName = "COD_BANCO_EMISOR", insertable = false, updatable = false)
    private BancoEmisor bancoEmisor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COD_COMISION", referencedColumnName = "COD_COMISION", insertable = false, updatable = false)
    private Comision comision;

    public Liquidacion() {
    }

    public Liquidacion(Integer codLiquidacion) {
        this.codLiquidacion = codLiquidacion;
    }

    public Integer getCodLiquidacion() {
        return codLiquidacion;
    }

    public void setCodLiquidacion(Integer codLiquidacion) {
        this.codLiquidacion = codLiquidacion;
    }

    public Integer getCodBancoAdquirente() {
        return codBancoAdquirente;
    }

    public void setCodBancoAdquirente(Integer codBancoAdquirente) {
        this.codBancoAdquirente = codBancoAdquirente;
    }

    public Integer getCodBancoEmisor() {
        return codBancoEmisor;
    }

    public void setCodBancoEmisor(Integer codBancoEmisor) {
        this.codBancoEmisor = codBancoEmisor;
    }

    public LocalDateTime getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(LocalDateTime fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public BigDecimal getTotalLiquidado() {
        return totalLiquidado;
    }

    public void setTotalLiquidado(BigDecimal totalLiquidado) {
        this.totalLiquidado = totalLiquidado;
    }

    public BigDecimal getTarifas() {
        return tarifas;
    }

    public void setTarifas(BigDecimal tarifas) {
        this.tarifas = tarifas;
    }

    public BancoAdquiriente getBancoAdquiriente() {
        return bancoAdquiriente;
    }

    public void setBancoAdquiriente(BancoAdquiriente bancoAdquiriente) {
        this.bancoAdquiriente = bancoAdquiriente;
    }

    public BancoEmisor getBancoEmisor() {
        return bancoEmisor;
    }

    public void setBancoEmisor(BancoEmisor bancoEmisor) {
        this.bancoEmisor = bancoEmisor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codLiquidacion == null) ? 0 : codLiquidacion.hashCode());
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
        Liquidacion other = (Liquidacion) obj;
        if (codLiquidacion == null) {
            if (other.codLiquidacion != null)
                return false;
        } else if (!codLiquidacion.equals(other.codLiquidacion))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Liquidacion [codLiquidacion=" + codLiquidacion + ", codBancoAdquirente=" + codBancoAdquirente
                + ", codBancoEmisor=" + codBancoEmisor + ", fechaLiquidacion=" + fechaLiquidacion + ", totalLiquidado="
                + totalLiquidado + ", tarifas=" + tarifas + ", bancoAdquiriente=" + bancoAdquiriente + ", bancoEmisor="
                + bancoEmisor + "]";
    }

    public Comision getComision() {
        return comision;
    }

    public void setComision(Comision comision) {
        this.comision = comision;
    }

    public Integer getCodComision() {
        return codComision;
    }

    public void setCodComision(Integer codComision) {
        this.codComision = codComision;
    }

}
