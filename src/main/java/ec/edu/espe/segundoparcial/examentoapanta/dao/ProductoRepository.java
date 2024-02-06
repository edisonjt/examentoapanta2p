package ec.edu.espe.segundoparcial.examentoapanta.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ec.edu.espe.segundoparcial.examentoapanta.domain.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String> {

  List<Producto> findByRucEmpresaOrderByDescripcion(String rucEmpresa);

  Optional<Producto> findByCodigoUnico(String codigoUnico);
}
