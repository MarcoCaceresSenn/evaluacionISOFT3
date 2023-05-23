package dci.isoft.test3.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private long id;
    private String correo;
    private String ultimaConexion;
    private ArrayList<Users> seguidos;
}
