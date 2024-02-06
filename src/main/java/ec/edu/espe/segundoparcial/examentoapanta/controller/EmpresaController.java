package ec.edu.espe.segundoparcial.examentoapanta.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.segundoparcial.examentoapanta.domain.Empresa;
import ec.edu.espe.segundoparcial.examentoapanta.service.EmpresaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

  private final EmpresaService service;

  public EmpresaController(EmpresaService service) {
    this.service = service;
  }

  @GetMapping("/{ruc}")
  public ResponseEntity<Empresa> obtenerEmpresaPorRuc(@PathVariable("ruc") String ruc) {
    log.info("Obteniendo empresa con el RUC {}", ruc);
    try {
      return ResponseEntity.ok(this.service.obtenerPorRuc(ruc));
    } catch (RuntimeException rte) {
      log.error("Error al obtener empresa", rte);
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/crear")
  public ResponseEntity<Void> crear(@RequestBody Empresa empresa) {
    log.info("Se va a crear la empresa: {}", empresa);
    try {
      this.service.crear(empresa);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException rte) {
      log.error("Error al crear el cliente", rte);
      return ResponseEntity.badRequest().build();
    }
  }

}
