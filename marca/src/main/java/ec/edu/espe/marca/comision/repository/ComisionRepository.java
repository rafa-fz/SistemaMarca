package ec.edu.espe.marca.comision.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ec.edu.espe.marca.comision.model.Comision;
import jakarta.persistence.Tuple;

public interface ComisionRepository extends JpaRepository<Comision, Integer> {

   @Query(value = "SELECT COALESCE(MAX(cod_comision), 0) + 1 FROM comision", nativeQuery = true)
   int obtenerSiguienteId();
   
   @Query(value = "select distinct c.prefijo_Marca, c.marca from comision c", nativeQuery = true)
   List<Object[]>  obtenerMarcasYPrefijos();

   @Query("SELECT c FROM Comision c " +
         "WHERE c.marca = :marca " +
         //"AND c.prefijoMarca = :prefijoMarca " +
         "AND c.tipo = :tipoTarjeta " +
         "AND (c.pais = :pais OR c.pais IS NULL) " +
         "AND c.estado = 'ACTIVO' " +
         "ORDER BY c.fechaCreacion DESC")
   Comision obtenerComision(@Param("marca") String marca,
         //@Param("prefijoMarca") String prefijoMarca,
         @Param("tipoTarjeta") String tipoTarjeta,
         @Param("pais") String pais);

   @Query(value = "SELECT c.marca, c.prefijo_Marca FROM Comision c WHERE :numeroTarjeta LIKE CONCAT(c.prefijo_Marca, '%') limit 1", nativeQuery = true)
   Tuple obtenerMarcaYPrefijo(String numeroTarjeta);

}
