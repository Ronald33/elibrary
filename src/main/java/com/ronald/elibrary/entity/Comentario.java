package com.ronald.elibrary.entity;
// Generated 30/08/2015 11:06:10 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Comentario generated by hbm2java
 */
@Entity
@Table(name="comentario"
    ,catalog="elibrary"
)
public class Comentario  implements java.io.Serializable {


     private Integer id;
     private Libro libro;
     private Usuario usuario;
     private String texto;

    public Comentario() {
    }

    public Comentario(Libro libro, Usuario usuario, String texto) {
       this.libro = libro;
       this.usuario = usuario;
       this.texto = texto;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="libro_id", nullable=false)
    public Libro getLibro() {
        return this.libro;
    }
    
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="texto", nullable=false, length=65535)
    public String getTexto() {
        return this.texto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }




}


