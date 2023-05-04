package com.example.renzhubserver.repository;

import com.example.renzhubserver.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(User user){
        System.out.println("test");
        em.persist(user);
    }

    public User findOne(Long id){
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        return em.createQuery("select m from User m", User.class)
                .getResultList();
    }

    public List<User> findByUserId(String userId){
        return em.createQuery("select m from User m where m.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
