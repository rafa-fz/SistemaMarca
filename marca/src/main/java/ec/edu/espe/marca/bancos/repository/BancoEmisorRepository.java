package ec.edu.espe.marca.bancos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.edu.espe.marca.bancos.model.BancoEmisor;

public interface BancoEmisorRepository extends JpaRepository<BancoEmisor, Integer> {

}
