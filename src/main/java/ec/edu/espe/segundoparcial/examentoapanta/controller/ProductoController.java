package ec.edu.espe.segundoparcial.examentoapanta.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.segundoparcial.examentoapanta.domain.Comentario;
import ec.edu.espe.segundoparcial.examentoapanta.domain.Producto;
import ec.edu.espe.segundoparcial.examentoapanta.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

  private final ProductoService service;

  public ProductoController(ProductoService service) {
    this.service = service;
  }

  @GetMapping("/empresa/{ruc}")
  public ResponseEntity<List<Producto>> obtenerProductosPorRucEmpresa(@PathVariable("ruc") String ruc) {
    log.info("Obteniendo productos de la empresa con el RUC {}", ruc);
    try {
      List<Producto> productos = this.service.obtenerPorRucEmpresa(ruc);
      return ResponseEntity.ok(productos);
    } catch (RuntimeException rte) {
      log.error("Error al obtener productos", rte);
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Producto> obtenerProducto(@PathVariable("codigo") String codigo) {
    log.info("Obteniendo producto con el codigo {}", codigo);
    try {
      return ResponseEntity.ok(this.service.obtenerPorCodigoUnico(codigo));
    } catch (RuntimeException rte) {
      log.error("Error al obtener producto", rte);
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/comentarios/{codigo}")
  public ResponseEntity<List<Comentario>> obtenerComentariosPorCodigoUnico(@PathVariable("codigo") String codigo) {
    log.info("Obteniendo comentarios del producto con el codigo {}", codigo);
    try {
      List<Comentario> comentarios = this.service.obtenerComentariosPorCodigoUnico(codigo);
      return ResponseEntity.ok(comentarios);
    } catch (RuntimeException rte) {
      log.error("Error al obtener comentarios", rte);
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/nuevo-producto")
  public ResponseEntity<Void> crear(@RequestBody Producto producto) {
    log.info("Se va a crear el producto: {}", producto);
    try {
      this.service.crear(producto);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException rte) {
      log.error("Error al crear el producto", rte);
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/nuevo-comentario/{codigoUnico}")
  public ResponseEntity<Void> agregarComentario(
      @RequestParam("codigoUnico") String codigoUnico,
      @RequestBody List<Comentario> comentarios) {
    log.info("Agregando comentarios al producto con código único: {}", codigoUnico);
    try {
      this.service.agregarComentario(codigoUnico, comentarios);
      return ResponseEntity.ok().build();
    } catch (RuntimeException rte) {
      log.error("Error al agregar comentarios", rte);
      return ResponseEntity.badRequest().build();
    }
  }
}
