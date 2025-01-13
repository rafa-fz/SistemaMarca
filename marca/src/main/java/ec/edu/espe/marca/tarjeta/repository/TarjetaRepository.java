package ec.edu.espe.marca.tarjeta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.marca.tarjeta.model.Tarjeta;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {
    Optional<Tarjeta> findByNumeroTarjeta(String numeroTarjeta);
}
