package com.example.masini.repository;

import com.example.masini.models.Masina;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.lang.reflect.Type;
import java.time.Year;
import java.util.List;

@Repository
@Transactional
public class MasinaJpaRepository {
    @PersistenceContext
    EntityManager entityManager;
    public void adaugaMasina(Masina masina)
    {
        entityManager.persist(masina);
    }
    public void stergeMasina(String nr_inmatriculare)
    {
        Masina masina = entityManager.find(Masina.class, nr_inmatriculare);
        if(masina != null) {
            entityManager.remove(masina);
        }
    }
    public Masina cautaDupaNrInmatriculare(String nr_inmatriculare)
    {
        return entityManager.find(Masina.class, nr_inmatriculare);

    }
    public List<Masina> getToateMasinile()
    {
        TypedQuery<Masina> query = entityManager.createQuery("SELECT m FROM Masina m", Masina.class);
        return query.getResultList();
    }
    public long getMarca(String marca)
    {
        TypedQuery<Long> query=entityManager.createQuery("select count(m) FROM Masina m where m.marca=:marca",Long.class);
        query.setParameter("marca",marca);
        return query.getSingleResult();
    }
    public long getNrMasini() {
        TypedQuery<Long> query = entityManager.createQuery("select count(m) FROM Masina m where m.km<100000", Long.class);
        return query.getSingleResult();
    }
    public List<Masina> getMasiniNoi()
    {
        int anLimita= Year.now().getValue()-5;
        TypedQuery<Masina> query = entityManager.createQuery("SELECT m FROM Masina m where m.anul>:an",Masina.class);
        query.setParameter("an",anLimita);
        return query.getResultList();
    }

}
