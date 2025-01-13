package ec.edu.espe.marca.bancos.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ec.edu.espe.marca.liquidacion.model.Liquidacion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "BANCO_ADQUIRENTE")
public class BancoAdquiriente implements Serializable {

    @Id
    @Column(name = "COD_BANCO_ADQUIRENTE", length = 10, nullable = false)
    private Integer codBancoAdquiriente;
    @Column(name = "CODIGO_BIC", length = 11, nullable = false)
    private String codigoBic;
    @Column(name = "NOMBRE", length = 50, nullable = false)
    private String nombre;
    @Column(name = "PAIS", length = 3, nullable = false)
    private String pais;

    @OneToMany(mappedBy = "bancoAdquiriente", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Liquidacion> liquidaciones;

    public BancoAdquiriente() {
    }

    public BancoAdquiriente(Integer codBancoAdquiriente) {
        this.codBancoAdquiriente = codBancoAdquiriente;
    }

    public Integer getCodBancoAdquiriente() {
        return codBancoAdquiriente;
    }

    public void setCodBancoAdquiriente(Integer codBancoAdquiriente) {
        this.codBancoAdquiriente = codBancoAdquiriente;
    }

    public String getCodigoBic() {
        return codigoBic;
    }

    public void setCodigoBic(String codigoBic) {
        this.codigoBic = codigoBic;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Liquidacion> getLiquidaciones() {
        return liquidaciones;
    }

    public void setLiquidaciones(List<Liquidacion> liquidaciones) {
        this.liquidaciones = liquidaciones;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codBancoAdquiriente == null) ? 0 : codBancoAdquiriente.hashCode());
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
        BancoAdquiriente other = (BancoAdquiriente) obj;
        if (codBancoAdquiriente == null) {
            if (other.codBancoAdquiriente != null)
                return false;
        } else if (!codBancoAdquiriente.equals(other.codBancoAdquiriente))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BancoAdquiriente [codBancoAdquiriente=" + codBancoAdquiriente + ", codigoBic=" + codigoBic + ", nombre=" + nombre + ", pais=" + pais
                + ", liquidaciones=" + liquidaciones + "]";
    }

}
