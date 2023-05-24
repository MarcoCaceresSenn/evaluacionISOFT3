package dci.isoft.test3.modelo;


import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {

    private int id;

    private String correo;

    private  LocalDate ultimaConexion;

    private String[] siguiendo;
    
    
}
