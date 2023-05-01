package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
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
   @Override
   public User getUserCar(Car car) {
//      String hql =
//              "SELECT u.name, u.last_name, u.email, c.model, c.series" +
//              "FROM users u" +
//              "    inner join relationUserCar uc" +
//              "    on u.id=uc.user_id" +
//              "    inner join cars c" +
//              "    on uc.car_id=c.id" +
//              "    where c.model = :model and c.series = :series";
      return sessionFactory.getCurrentSession().createQuery(
//              "FROM User u inner join Car c where c.model = :model and c.series = :series",User.class)
                      "from User u where u.car.model = :model and u.car.series = :series", User.class)
              .setParameter("model", car.getModel())
              .setParameter("series", car.getSeries())
              .uniqueResult();
   }

}
