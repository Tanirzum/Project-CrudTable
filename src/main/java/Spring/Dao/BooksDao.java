package Spring.Dao;

import Spring.Model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BooksDao {

    private SessionFactory sessionFactory;


    @Autowired
    public BooksDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> list() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Book p", Book.class).getResultList();
    }


    @Transactional
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }


    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Book.class, id));
    }
}
