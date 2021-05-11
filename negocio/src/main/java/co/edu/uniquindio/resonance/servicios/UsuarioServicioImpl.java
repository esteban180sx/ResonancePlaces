package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Clase de implementación de UsuarioServicio
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Service
public class UsuarioServicioImpl implements UsuarioServicio{
    private final UsuarioRepo usuarioRepo;
    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    /**
     * Método que registra usuario en la capa de servicios
     * @param u
     * @return
     * @throws Exception
     */
    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(u.getNickname());
        if(buscado.isPresent()){
            throw new Exception("El nickname ya se encuentra en uso");
        }
        if(!estaDisponible(u.getEmail())){
            throw new Exception("El email ya se encuentra en uso");
        }

        if (u.getNickname().length()>25){
            throw new Exception("El nickname no puede exceder los 25 caracteres");
        }

        if (u.getNombre().length()>70){
            throw new Exception("El nombre no puede exceder 25 caracteres");
        }


        return usuarioRepo.save(u);
    }

    /**
     * Método que actualiza usuario en la capa de servicios
     * @param u
     * @return
     * @throws Exception
     */
    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {

        Usuario buscado = usuarioRepo.findByNickname(u.getNickname());
        boolean sameEmail = false;
        boolean sameNick = false;
        if (u.getNickname().equals(buscado.getNickname())){
            sameNick = true;
        }

        if (u.getEmail().equals((buscado.getEmail()))){
            sameEmail = true;
        }


        if(buscado!=null && sameNick==false){
            throw new Exception("El nickname ya se encuentra en uso");
        }

        if(!estaDisponible(u.getEmail()) && sameEmail==false) {
            throw new Exception("El email ya se encuentra en uso");
        }

        if (u.getNickname().length()>25){
            throw new Exception("El nickname no puede exceder los 25 caracteres");
        }

        if (u.getNombre().length()>70){
            throw new Exception("El nombre no puede exceder 25 caracteres");
        }

        usuarioRepo.save(u);

        return u;
    }

    /**
     * Método que elimina usuario en la capa de servicios
     * @param nickname
     * @throws Exception
     */
    @Override
    public void eliminarUsuario(String nickname) throws Exception {

        Usuario user = usuarioRepo.findByNickname(nickname);

        if (user!=null){
            usuarioRepo.delete(user);
        }else{
            throw new Exception("El usuario que desea eliminar no existe");
        }

    }

    /**
     * Método que inicia sesion en la aplicacion
     * @param usuario
     * @param contrasena
     * @return
     * @throws Exception
     */
    @Override
    public Usuario iniciarSesion(String usuario, String contrasena) throws Exception {

        Usuario user = usuarioRepo.findByNicknameAndContrasena(usuario, contrasena);

        if (user!=null){
            return user;
        } else {

            user = usuarioRepo.findByEmailAndContrasena(usuario,contrasena);
            if (user!=null){
                return user;
            } else {
                throw new Exception("Usuario no encontrado");
            }

        }
    }

    /**
     * Método que lista usuarios de la aplicacion
     * @return
     */
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    /**
     * Método que verifica si un email esta disponible
     * @param email
     * @return
     */
    public boolean estaDisponible(String email){
        Optional<Usuario> usuario = usuarioRepo.findByEmail(email);
        return usuario.isEmpty();
    }


}