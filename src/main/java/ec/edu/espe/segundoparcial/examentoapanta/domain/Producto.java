package ec.edu.espe.segundoparcial.examentoapanta.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "producto")
public class Producto {
  @Id
  private String id;

  @Indexed(unique = true)
  @Field("codigo_unico")
  private String codigoUnico;

  @Field("rucEmpresa")
  private String rucEmpresa;

  @Field("descripcion")
  private String descripcion;

  @Field("precio")
  private double precio;

  private List<Comentario> comentarios;

  @Version
  private Long version;

  public Producto(String id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Producto other = (Producto) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Producto [id=" + id + ", codigoUnico=" + codigoUnico + ", rucEmpresa=" + rucEmpresa + ", descripcion="
        + descripcion + ", precio=" + precio + ", comentarios=" + comentarios + ", version=" + version + "]";
  }

}
