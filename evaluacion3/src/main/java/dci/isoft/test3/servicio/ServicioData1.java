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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.print.DocFlavor.STRING;

import org.apache.commons.collections.functors.TruePredicate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

@Service
public class ServicioData1 {
    
    public void generar(ArrayList<Usuario> usuarios){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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

               
                LocalDate fecha = LocalDate.parse(datos[2],formato);  

                Usuario usuario = new Usuario(Integer.parseInt(datos[0]), datos[1],fecha, datosPunto);
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

    public List<Usuario> filtroPorMayoSeguidos(ArrayList<Usuario> usuarios){
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



    public List<Usuario> todosInactivos(ArrayList<Usuario> usuarios){

        List<Usuario> usuariosInactivos=new ArrayList<>();
        
        for (Usuario usuario : usuarios) {
            LocalDate fechaReferencia = LocalDate.of(2023,5,22);
            
            if(usuario.getUltimaConexion().isBefore(fechaReferencia.minusYears(4))){
                usuariosInactivos.add(usuario);
            }
            
        }

        return usuariosInactivos;
    }


    public List<Usuario> inactivosSeguidosMitad(ArrayList<Usuario> usuarios, List<Usuario> usuariosInactivos){

        List<Usuario> sigoAInactivos=new ArrayList<>();
        
        List<Integer> usuariosInactivosId=new ArrayList<>();

        for (Usuario usuarioId : usuariosInactivos ) {         
           usuariosInactivosId.add(usuarioId.getId());     
         }
        for (Usuario usuario : usuarios) {    
            int inactivos=0;
            String[] sigoString= usuario.getSiguiendo();
            int [] sigoInt=new int[sigoString.length];
         
            for (int index = 0; index < sigoString.length; index++) {
                sigoInt[index] = Integer.parseInt(sigoString[index]);
            }


            for (Integer estaInactivo : sigoInt) {
                  if(usuariosInactivosId.contains(estaInactivo)){
                    inactivos++;
                  }
            }

            if(inactivos > (sigoInt.length/2) ){
                sigoAInactivos.add(usuario);

            }    
        }
  
        return sigoAInactivos;
    }







}
