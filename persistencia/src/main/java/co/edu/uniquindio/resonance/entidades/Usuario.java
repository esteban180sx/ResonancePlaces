package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @Column(name="nickname",nullable = false, length = 25)
    private String nickname;

    @Column(name="nombre",nullable = false,length = 70)
    private String nombre;

    @Column(name="email", nullable = false)
    private  String email;

    @Column(name="contrasena",nullable = false)
    private String contrasena;

    @OneToMany(mappedBy = "usuario")
    private List<Usuario> favoritos;

    @OneToMany(mappedBy = "usuario")
    private List<Calificacion> calificaciones;

    @ManyToOne
    @JoinColumn(name = "codigo_ciudad", nullable = false)
    private Ciudad ciudad;

    public Usuario() {

    }

    public List<Usuario> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Usuario> favoritos) {
        this.favoritos = favoritos;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return nickname != null ? nickname.equals(usuario.nickname) : usuario.nickname == null;
    }

    @Override
    public int hashCode() {
        return nickname != null ? nickname.hashCode() : 0;
    }
}