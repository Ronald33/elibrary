package com.ronald.elibrary.aspectos;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggerAspect
{

    //variable que indica numero de veces que se ha llamado
//    private static int veces = 1;

    //Aquí hay varias partes, la anotación Before indica que se tiene que ejecutar antes de el pointcut que indiquemos dentro
    //El pointcut es un punto en el programa en el que poder ejecutar un aspecto, en este caso hemos indicado nuestro pointcut como cualquier
    //método que se llamado desde cualquier parte que se llame list(), con al menos un parámetro.
//    @Before("execution(* list(..))")
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Before("execution(* com.ronald.elibrary.api.categoria.ApiCategoria.getAll(..))")
    public void imprimirlog()
    {
        System.out.println("=========================================================");
        System.out.println("Se ha llamado a método list");
        System.out.println("=========================================================");
//        veces++;
    }

}
