package ec.edu.espe.segundoparcial.examentoapanta.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.segundoparcial.examentoapanta.dao.EmpresaRepository;
import ec.edu.espe.segundoparcial.examentoapanta.domain.Empresa;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpresaService {
  private final EmpresaRepository empresaRepository;

  public EmpresaService(EmpresaRepository empresaRepository) {
    this.empresaRepository = empresaRepository;
  }

  public Empresa obtenerPorRuc(String ruc) {
    log.info("Obteniendo la empresa con ruc: {}", ruc);
    Optional<Empresa> optionalEmpresa = this.empresaRepository.findByRuc(ruc);
    if (optionalEmpresa.isPresent()) {
      return optionalEmpresa.get();
    } else {
      throw new RuntimeException("No existe la empresa con el RUC: " + ruc);
    }
  }

  @Transactional
  public void crear(Empresa empresa) {
    log.info("Se esta creando la empresa...");
    try {
      empresa.setFechaCreacion(new Date());
      this.empresaRepository.save(empresa);
      log.info("Se ha creado la empresa {}", empresa);
    } catch (Exception e) {
      throw new RuntimeException("Error al crear la empresa", e);
    }
  }

}
