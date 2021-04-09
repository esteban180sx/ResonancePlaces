package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;

@Entity
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="codigo",nullable = false)
    private int codigo;
    @Column(name="urlFoto")
    private String urlFoto;
    @JoinColumn(name="codigo_lugar")
    private int codigoLugar;


    public Foto(){

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getCodigoLugar() {
        return codigoLugar;
    }

    public void setCodigoLugar(int codigoLugar) {
        this.codigoLugar = codigoLugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foto foto = (Foto) o;

        return codigo == foto.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }
}
