package dci.isoft.test3.controlador;

import dci.isoft.test3.Model.Users;
import dci.isoft.test3.Services.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/csv")
public class ControllerCSV {
    @Autowired
    CSVService csvService;
    ArrayList<Users> usuarios = new ArrayList<>();
    @GetMapping("/listar")
    public List<Users> mostrarUsuarios() throws IOException {
        return csvService.listar(usuarios);
    }
    @GetMapping("/ultimosDiez")
    public List<Users> ultimosDiez() throws IOException {
        return csvService.ultimosDiezUsuariosConectadosRecientemente(usuarios);
    }
}
