package ec.edu.espe.segundoparcial.examentoapanta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.segundoparcial.examentoapanta.dao.ProductoRepository;
import ec.edu.espe.segundoparcial.examentoapanta.domain.Comentario;
import ec.edu.espe.segundoparcial.examentoapanta.domain.Producto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoService {

  private final ProductoRepository productoRepository;

  public ProductoService(ProductoRepository productoRepository) {
    this.productoRepository = productoRepository;
  }

  public List<Producto> obtenerPorRucEmpresa(String rucEmpresa) {
    log.info("Se van a obtener los productos de la empresa con RUC {}", rucEmpresa);
    List<Producto> productos = this.productoRepository.findByRucEmpresaOrderByDescripcion(rucEmpresa);
    if (productos != null && productos.isEmpty()) {
      return productos;
    } else {
      throw new RuntimeException("No existen productos con el ruc de empresa {}" + rucEmpresa);
    }
  }

  public Producto obtenerPorCodigoUnico(String codigoUnico) {
    log.info("Obteniendo el producto con codigo unico: {}", codigoUnico);
    Optional<Producto> optionalProducto = this.productoRepository.findByCodigoUnico(codigoUnico);
    if (optionalProducto.isPresent()) {
      return optionalProducto.get();
    } else {
      throw new RuntimeException("No existe el producto con el codigo único: " + codigoUnico);
    }
  }

  public List<Comentario> obtenerComentariosPorCodigoUnico(String codigoUnico) {
    log.info("Obteniendo comentarios del producto con codigo unico: {}", codigoUnico);
    Optional<Producto> optionalProducto = this.productoRepository.findByCodigoUnico(codigoUnico);
    if (optionalProducto.isPresent()) {
      return optionalProducto.get().getComentarios();
    } else {
      throw new RuntimeException("No existe el producto con el codigo único: " + codigoUnico);
    }
  }

  @Transactional
  public void crear(Producto producto) {
    log.info("Se esta creando un producto...");
    try {
      this.productoRepository.save(producto);
      log.info("Se ha creado el producto {}", producto);
    } catch (Exception e) {
      throw new RuntimeException("Error al crear el producto", e);
    }
  }

  @Transactional
  public void agregarComentario(String codigoUnico, List<Comentario> comentario) {
    log.info("Se va a crear comentario en el producto con codigo unico: {}", codigoUnico);
    try {
      Producto producto = this.obtenerPorCodigoUnico(codigoUnico);
      producto.setComentarios(comentario);
      this.productoRepository.save(producto);
      log.info("Se creo el comentario", comentario);
    } catch (Exception e) {
      throw new RuntimeException("Error al crear comentario: ", e);
    }
  }

}
