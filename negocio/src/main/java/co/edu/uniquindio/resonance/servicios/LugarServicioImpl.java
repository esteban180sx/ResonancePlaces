package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.*;
import co.edu.uniquindio.resonance.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LugarServicioImpl implements LugarServicio{

    @Autowired
    private LugarRepo lugarRepo;

    @Autowired
    private FotoRepo fotoRepo;

    @Autowired
    private CalificacionRepo calificacionRepo;

    @Autowired
    private FavoritoRepo favoritoRepo;

    @Autowired
    private HorarioRepo horarioRepo;


    @Override
    public Lugar registrarLugar(Lugar lugar) throws Exception {
        Optional<Lugar> buscado = lugarRepo.findById(lugar.getCodigo());

        if(buscado.isPresent()){
            throw new Exception("Ya existe un lugar con ese codigo!");
        }

        return lugarRepo.save(lugar);
    }

    @Override
    public void eliminarLugar(Lugar lugar) throws Exception {
        Optional<Lugar> buscado = lugarRepo.findById(lugar.getCodigo());



        if(!buscado.isPresent()){
            throw new Exception("No existe un lugar con ese codigo!");
        }

        for (Favorito f : lugarRepo.obtenerFavoritos(lugar.getCodigo())){
            favoritoRepo.delete(f);
        }

        for(Calificacion f : lugarRepo.obtenerCalificaciones(lugar.getCodigo())){
            calificacionRepo.delete(f);
        }

        for(Horario h : lugarRepo.obtenerHorarios(lugar.getCodigo())){
            horarioRepo.delete(h);
        }


        List<String> fotosClone = lugar.getFoto();
        lugar.getFoto().removeAll(fotosClone);
        lugarRepo.delete(lugar);
    }

    @Override
    public Lugar actualizarLugar(Lugar lugar) throws Exception {
        Optional<Lugar> buscado = lugarRepo.findById(lugar.getCodigo());

        if(!buscado.isPresent()){
            throw new Exception("No existe un lugar con ese codigo!");
        }

        return lugarRepo.save(lugar);
    }

    @Override
    public List<Lugar> listarLugares() {
         return lugarRepo.findAll();
    }

    @Override
    public List<Lugar> buscarLugares(String parametro) {

        return lugarRepo.buscarLugares(parametro);
    }

    @Override
    public Lugar obtenerLugar(Integer codigo) throws Exception{
        Lugar lugar = lugarRepo.findById(codigo).get();
        if(lugar==null)
            throw new Exception("No existe un lugar con ese codigo");


        return lugar;


    }

    @Override
    public List<Horario> listarHorarios(Integer codigo) {
        return lugarRepo.listarHorarios(codigo);
    }

    @Override
    public List<Calificacion> listarCalificaciones(Integer codigo) {
        return lugarRepo.listarCalificaciones(codigo);
    }

    @Override
    public Calificacion crearCalificacion(Calificacion c)
    {
        c.setFecha(new Date());
        return calificacionRepo.save(c);
    }

    @Override
    public Foto registrarFoto(Foto foto) {
        return fotoRepo.save(foto);
    }

    @Override
    public List<String> obtenerFotos(Integer id) {
        return null;
    }

    @Override
    public void marcarFavorito(Lugar lugar, Usuario usuario) {

        Favorito buscado = lugarRepo.obtenerFavorito(usuario.getNickname(), lugar.getCodigo());
        if(buscado==null){
            Favorito guardado = new Favorito(usuario, lugar);
            favoritoRepo.save(guardado);
        }else{
            favoritoRepo.delete(buscado);
        }


    }

    @Override
    public Favorito obtenerFavorito(Lugar lugar, Usuario usuario) {
        return lugarRepo.obtenerFavorito(usuario.getNickname(), lugar.getCodigo());
    }

    @Override
    public List<Lugar> listarLugaresAutorizados() {
        return lugarRepo.listarLugaresAutorizados();
    }
}
