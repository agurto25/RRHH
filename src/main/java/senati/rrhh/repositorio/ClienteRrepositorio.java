package senati.rrhh.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import senati.rrhh.modelo.Clientes;

public interface ClienteRrepositorio extends JpaRepository<Clientes,Integer> {
}
