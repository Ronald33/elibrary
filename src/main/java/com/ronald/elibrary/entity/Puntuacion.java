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
 * Puntuacion generated by hbm2java
 */
@Entity
@Table(name="puntuacion"
    ,catalog="elibrary"
)
public class Puntuacion  implements java.io.Serializable {


     private Integer id;
     private Libro libro;
     private Usuario usuario;
     private int puntos;

    public Puntuacion() {
    }

    public Puntuacion(Libro libro, Usuario usuario, int puntos) {
       this.libro = libro;
       this.usuario = usuario;
       this.puntos = puntos;
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

    
    @Column(name="puntos", nullable=false)
    public int getPuntos() {
        return this.puntos;
    }
    
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }




}


