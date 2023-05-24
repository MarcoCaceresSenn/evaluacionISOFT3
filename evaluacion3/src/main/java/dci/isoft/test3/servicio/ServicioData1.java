package dci.isoft.test3.servicio;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import dci.isoft.test3.modelo.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.functors.TruePredicate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

@Service
public class ServicioData1 {
    
    public void listar(ArrayList<Usuario> usuarios){
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

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                 


                Usuario usuario = new Usuario(Integer.parseInt(datos[0]), datos[1],formato.parse(datos[2]), datosPunto);
                usuarios.add(usuario);              
            
            }

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

    }

    public List<Usuario> filtroPorMayoSeguidores(ArrayList<Usuario> usuarios){
        String[] seguidoresMaximos={};
        List<Usuario> usuariosPopulares = new ArrayList<>();

      for (Usuario usuario : usuarios) {
        if(seguidoresMaximos.length < usuario.getSiguiendo().length){
            seguidoresMaximos = usuario.getSiguiendo();
        }
      }

      for (Usuario usuarioPopular : usuarios) {
        if(usuarioPopular.getSiguiendo().length == seguidoresMaximos.length){
            usuariosPopulares.add(usuarioPopular);
        }
      }
    System.out.println(usuariosPopulares.size());
    return usuariosPopulares;

    }



    public List<Usuario> inactivos(ArrayList<Usuario> usuarios){
        
        for (Usuario usuario : usuarios) {

         
        System.out.println(usuario.getUltimaConexion().getYear());
            
            
        }





        return usuarios;
    }






}
