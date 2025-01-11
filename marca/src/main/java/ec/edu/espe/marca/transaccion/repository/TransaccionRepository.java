package ec.edu.espe.marca.transaccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ec.edu.espe.marca.transaccion.model.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {

    @Query(value = "SELECT COALESCE(MAX(cod_transaccion), 0) + 1 FROM transaccion", nativeQuery = true)
    int obtenerSiguienteId();
}
