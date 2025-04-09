package web.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    public void save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}