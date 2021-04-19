package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Calificacion;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.repositorios.CalificacionRepo;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import co.edu.uniquindio.resonance.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
/**
 * Clase Test para la entidad calificacion
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CalificacionTest {

    /**
     * Repositorio de la entidad calificacion
     */
    @Autowired
    private CalificacionRepo calificacionRepo;
    /**
     * Repositorio de la entidad lugar
     */
    @Autowired
    private LugarRepo lugarRepo;
    /**
     * Repositorio de la entidad usuario
     */
    @Autowired
    private UsuarioRepo usuarioRepo;

    /**
     * Método que permite registrar una calificacion en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void registrarCalificacion(){


        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        Lugar lugarGuardado = lugarRepo.save(lugar);
        Assertions.assertNotNull(lugarGuardado);

        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);


        Calificacion calificacion = new Calificacion(3.0,"Excelente lugar","Buena atencion pero la comida muy regular",usuarioGuardado,lugarGuardado);
        Calificacion calificacionGuardada = calificacionRepo.save(calificacion);

        Assertions.assertNotNull(calificacionGuardada);


    }
    /**
     * Método que permite eliminar una calificacion en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void eliminarCalificacion(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        Lugar lugarGuardado = lugarRepo.save(lugar);


        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);



        Calificacion calificacion = new Calificacion(3.0,"Excelente lugar","Buena atencion pero la comida muy regular",usuarioGuardado,lugarGuardado);
        Calificacion calificacionGuardada = calificacionRepo.save(calificacion);
        calificacionRepo.delete(calificacionGuardada);

        Calificacion buscado = calificacionRepo.findById(calificacionGuardada.getCodigo()).orElse(null);
        Assertions.assertNull(buscado);


    }
    /**
     * Método que permite actualizar una calificacion en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void actualizarCalificacion(){

        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        Lugar lugarGuardado = lugarRepo.save(lugar);


        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);



        Calificacion calificacion = new Calificacion(3.0,"Excelente lugar","Buena atencion pero la comida muy regular",usuarioGuardado,lugarGuardado);
        Calificacion calificacionGuardada = calificacionRepo.save(calificacion);

        calificacionGuardada.setTitulo("Paisaje espectacular");

        calificacionRepo.save(calificacionGuardada);
        Calificacion buscado = calificacionRepo.findById(calificacionGuardada.getCodigo()).orElse(null);

        Assertions.assertEquals("Paisaje espectacular",calificacionGuardada.getTitulo());


    }
    /**
     * Método que permite listar calificacion en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void listarCalificaciones(){
        List<Calificacion> lista = calificacionRepo.findAll();
        System.out.println(lista);

    }

    /**
     * Método que listar  calificacion en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql
    @Sql({"classpath:calificaciones.sql","classpath:usuarios.sql","classpath:lugares.sql"})
    public void listarCalificacionesSQL(){
        List<Calificacion> lista = calificacionRepo.findAll();
        System.out.println(lista);

    }

}
