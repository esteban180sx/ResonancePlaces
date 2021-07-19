package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.Categoria;
import co.edu.uniquindio.resonance.entidades.Ciudad;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.servicios.CategoriaServicio;
import co.edu.uniquindio.resonance.servicios.CiudadServicio;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import co.edu.uniquindio.resonance.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class LugarBean {
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Getter @Setter
    private Usuario usuario;

    @Autowired
    private LugarServicio lugarServicio;
    @Getter @Setter
    private Lugar lugar;

    @Autowired
    private CategoriaServicio categoriaServicio;
    @Getter @Setter
    private Categoria categoria;

    @Autowired
    private CiudadServicio ciudadServicio;
    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<Categoria> categorias;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @PostConstruct
    public void inicializar(){
        this.lugar = new Lugar();
        this.usuario = new Usuario();
        this.categoria = new Categoria();
        this.ciudad = new Ciudad();
        this.categorias = categoriaServicio.listarCategorias();
        this.ciudades = ciudadServicio.listarCiudades();

    }

    public void registrarLugar(){
        try {
            lugarServicio.registrarLugar(lugar);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                    "Registro exitoso");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

    }

}
