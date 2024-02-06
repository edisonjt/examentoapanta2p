package ec.edu.espe.segundoparcial.examentoapanta.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ec.edu.espe.segundoparcial.examentoapanta.domain.Empresa;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {

  Optional<Empresa> findByRuc(String ruc);

}
