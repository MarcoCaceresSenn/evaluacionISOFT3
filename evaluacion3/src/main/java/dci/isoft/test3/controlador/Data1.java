package dci.isoft.test3.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dci.isoft.test3.modelo.Usuario;

import dci.isoft.test3.servicio.ServicioData1;

@RestController
@RequestMapping("/base1")
public class Data1 {

    ArrayList<Usuario> usuarios=new ArrayList<>();

    @Autowired
    ServicioData1 servicio;

    @GetMapping("/listar")
    public void listar(){
      servicio.listar(usuarios);
    }

    @GetMapping("/seguidoMaximo")
    public List<Usuario> maximoSeguidor(){
       return  servicio.filtroPorMayoSeguidores(usuarios);
    }



}
