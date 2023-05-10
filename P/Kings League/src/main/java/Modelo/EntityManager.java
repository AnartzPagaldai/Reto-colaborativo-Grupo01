package Modelo;

import Modelo.Jornadas.JornadasEntity;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import javax.swing.*;

public class EntityManager {
    private EntityManagerFactory emf;
    private jakarta.persistence.EntityManager em;
    private EntityTransaction transaction;

    public EntityManager() {
        emf= Persistence.createEntityManagerFactory ("default");
        em=emf.createEntityManager();
        transaction=em.getTransaction();
    }
    public void insertar(Object objeto) {
        try {
            transaction.begin();
            em.persist(objeto);
            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getClass() + e.getMessage());
        }
    }

    public void actualizar(Object objeto) {
        try {
            transaction.begin();
            objeto = em.find(objeto.getClass(),objeto);
            if (objeto != null) {
                em.persist(objeto);
            }
            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getClass() + e.getMessage());
        }
    }

    public void eliminar(Object objeto) {
        try {
            transaction.begin();
            objeto = em.find(objeto.getClass(),objeto);
            if (objeto != null) {
                em.remove(objeto);
            }
            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getClass() + e.getMessage());
        }
    }

    public void consultar(Object objeto) {
        try {
            transaction.begin();
            objeto = em.find(objeto.getClass(),objeto);
            transaction.commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getClass() + e.getMessage());
        }
    }
}
