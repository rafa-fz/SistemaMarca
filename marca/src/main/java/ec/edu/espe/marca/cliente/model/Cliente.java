package ec.edu.espe.marca.cliente.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import ec.edu.espe.marca.tarjeta.model.Tarjeta;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

    @Id
    @Column(name = "COD_CLIENTE", length = 13, nullable = false)
    private Integer codCliente;
    @Column(name = "NOMBRE", length = 50, nullable = false)
    private String nombre;
    @Column(name = "APELLIDO", length = 50, nullable = false)
    private String apellido;
    @Column(name = "DIRECCION_LINEA1", length = 100, nullable = false)
    private String direccionLinea1;
    @Column(name = "DIRECCION_LINEA2", length = 100, nullable = true)
    private String direccionLinea2;
    @Column(name = "TELEFONO", length = 15, nullable = true)
    private String telefono;
    @Column(name = "CORREO", length = 100, nullable = true)
    private String correo;

    @OneToMany(mappedBy = "cliente")
    private List<Tarjeta> tarjetas;

    public Cliente() {
    }

    public Cliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccionLinea1() {
        return direccionLinea1;
    }

    public void setDireccionLinea1(String direccionLinea1) {
        this.direccionLinea1 = direccionLinea1;
    }

    public String getDireccionLinea2() {
        return direccionLinea2;
    }

    public void setDireccionLinea2(String direccionLinea2) {
        this.direccionLinea2 = direccionLinea2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codCliente == null) ? 0 : codCliente.hashCode());
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
        Cliente other = (Cliente) obj;
        if (codCliente == null) {
            if (other.codCliente != null)
                return false;
        } else if (!codCliente.equals(other.codCliente))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cliente [codCliente=" + codCliente + ", nombre=" + nombre + ", apellido=" + apellido
                + ", direccionLinea1=" + direccionLinea1 + ", direccionLinea2=" + direccionLinea2 + ", telefono="
                + telefono + ", correo=" + correo + ", tarjetas=" + tarjetas + "]";
    }

}
