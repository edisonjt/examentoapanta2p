package ec.edu.espe.segundoparcial.examentoapanta.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Comentario {
  private int calificacion;
  private String comentario;
  private String usuario;
  private Date fechaComentario;
}
