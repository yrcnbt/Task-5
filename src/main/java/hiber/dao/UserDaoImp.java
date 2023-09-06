package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public List getUserByCarsModelAndSeries(String model, int serises) {

      String HQL="FROM User u LEFT JOIN FETCH u.myCar c WHERE c.model =: paramCarModel AND c.series =: paramCarSeries";
      Query query = sessionFactory.openSession().createQuery(HQL, User.class);
      query.setParameter("paramCarModel", model);
      query.setParameter("paramCarSeries", serises);
      return query.getResultList();
   }

   @Autowired
   public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

}
