package dci.isoft.test3.modelo;

import java.sql.Date;

import javax.print.DocFlavor.STRING;

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

    private  java.util.Date ultimaConexion;

    private String[] siguiendo;
    
    
}
