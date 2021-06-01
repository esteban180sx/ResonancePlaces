package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Administrador;

import java.util.List;

public interface AdministradorServicio  {

    Administrador registrarAdministrador(Administrador admin) throws Exception;
    void eliminarAdministrador(Administrador admin) throws Exception;
    Administrador actualizarAdministrador(Administrador admin) throws Exception;
    List<Administrador> listarAdministradores();

}
