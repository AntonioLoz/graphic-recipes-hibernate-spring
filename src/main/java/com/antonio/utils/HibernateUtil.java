/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonio.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.context.internal.ThreadLocalSessionContext;


/**
 *
 * @author Anton
 */
public class HibernateUtil {
    private static StandardServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;
    
    /**
     * Hacemos synchronized para que las peticiones al metodo
     * se ejecuten en orden.
     * La razon por la que hacemos este metodo synchronized es porque podría ocurrir
     * que dos hilos lo llamasen a la vez y, mientras uno crea  un sessionFactory
     * el otro podria entrar en if(sessionFactory == null) antes de que el primero
     * sea creado creando así un conflicto.
     */
    
    public static synchronized void buildSessionFactory() {
        if(sessionFactory == null) {
            
            // Registramos el servicio con la configuracion. ServiceRegistry se puede ver como una interface
            // que administra la InversionOfControl en la cual los Beans inyectados son Services.
            serviceRegistry = (StandardServiceRegistry) new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            
            // Creamos un MetadataSources y, a partir de el, creamos un Metadata
            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            Metadata metadata = metadataSources.getMetadataBuilder().build();
            
            // Creamos el SessionFactory.
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
    }
    
    /*
    * Abre una session nueva y la enlaza con el hilo llamador.
    * A efectos practicos, podria decirse que un hilo y una peticion, en una aplicacion web, son lo mismo
    * ya que por cada peticion estaremos creando un nuevo hilo. Con ThreadLocalSessionContext lo que 
    * conseguimos es almacenar el objeto Session en una zona de la memoria privada del hilo invocador
    * del metodo, para que el objeto session creado sea exclusivo de ese hilo.
    * Esto es asi ya que la clase Session no es thread-safe y Hibernate prohibe explicitamente
    * compartir objetos Session entre hilos.
    */
    public static void openSessionAndBindToThread() {
        Session session = sessionFactory.openSession();
        ThreadLocalSessionContext.bind(session);
    }
    
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            buildSessionFactory();
        }
        return sessionFactory;
    }
    
    // Desenlaza la sesion del hilo y la cierra
    public static void closeSessionAndUnbind() {
        Session session = ThreadLocalSessionContext.unbind(sessionFactory);
        if(session != null) {
            session.close();
        }
    }
    
    public static void closeSessionFactory() {
        if(sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }
    
}
