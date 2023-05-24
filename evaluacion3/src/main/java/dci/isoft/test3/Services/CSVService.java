package dci.isoft.test3.Services;

import dci.isoft.test3.Model.Users;
import dci.isoft.test3.modelo.Usuario;
import dci.isoft.test3.servicio.ServicioData1;
import org.springframework.stereotype.Service;
import dci.isoft.test3.Model.Users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CSVService {
    ArrayList<Users> users = new ArrayList<>();
    ServicioData1 servicioData1 = new ServicioData1();

    public ArrayList<Users> listar(ArrayList<Users> usuarios){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        String linea = null;

        try {
            archivo = new File("src/main/java/dci/isoft/test3/databases/dataset2.csv");

            fr = new FileReader(archivo);

            br = new BufferedReader(fr);
            boolean primeraLinea=true;


            String[] datos = null;
            String[] datosPunto= null;


//-----------------------------------------------------------------------------------

            while ((linea = br.readLine()) != null) {
                if(primeraLinea==true){
                    primeraLinea=false;
                    continue;
                }
                datos = linea.split(";");
                datosPunto = datos[3].split(",");
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fecha = LocalDate.parse(datos[2],formato);

                Users usuario = new Users(Integer.parseInt(datos[0]), datos[1],fecha, datosPunto);
                usuarios.add(usuario);
            }
            this.users=usuarios;
            return usuarios;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return usuarios;
    }
    public List<Users> ultimosDiezUsuariosConectadosRecientemente(ArrayList<Users> usuarios){
        ArrayList<Users> user = listar(usuarios);
        List<Users> ultimosDiez = new ArrayList<>();

        Collections.sort(user, (u1, u2) -> u2.getUltimaConexion().compareTo(u1.getUltimaConexion()));

        int count = 0;
        for (Users userAux : users) {
            if (userAux.getUltimaConexion().isBefore(LocalDate.now())) {
                ultimosDiez.add(userAux);
                count++;
            }
            if (count == 10) {
                break;
            }
        }

        return ultimosDiez;
    }

    /*usuario con más seguidores o usuarios*/
    public List<Users> usuarioConMasSeguidores(ArrayList<Users> usuarios){
        ArrayList<Users> user = listar(usuarios);
        Users userAux = null;
        List<Users> usuarioMasSeguido = new ArrayList<>();
        int max = 0;
        for (Users user1 : user) {
            if (user1.getSeguidos().length > max) {
                max = user1.getSeguidos().length;
                usuarioMasSeguido.add(userAux);
            }
        }
        return usuarioMasSeguido;
    }

    /*public List<Usuario> usuarioInactivoConMasSeguidores(ArrayList<Usuario> users){
        List<Usuario> inactivos = servicioData1.todosInactivos(users); //inactivos
        ArrayList<Usuario> inactivoList = new ArrayList<>(inactivos);
        List<Usuario> mayorSeguidoInactivo = servicioData1.filtroPorMayoSeguidores(inactivoList);
        return mayorSeguidoInactivo;
    }

     */

    /*copia del otro servicio parseado a diferente model*/
    public List<Users> filtroPorMayoSeguidores(ArrayList<Users> usuarios){
        String[] seguidoresMaximos={};
        List<Users> usuariosPopulares = new ArrayList<>();

        for (Users usuario : usuarios) {
            if(seguidoresMaximos.length < usuario.getSeguidos().length){
                seguidoresMaximos = usuario.getSeguidos();
            }
        }

        for (Users usuarioPopular : usuarios) {
            if(usuarioPopular.getSeguidos().length == seguidoresMaximos.length){
                usuariosPopulares.add(usuarioPopular);
            }
        }
        System.out.println(usuariosPopulares.size());
        return usuariosPopulares;

    }


}
