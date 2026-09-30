<<<<<<< HEAD
package murach.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import murach.business.User;

public class UserDB {

    public static void insert(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(user); // Lệnh persist tương đương với INSERT
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static User selectUser(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        // Dùng JPQL (Java Persistence Query Language), truy vấn trên Object, không phải Table
        String qString = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("email", email);
        try {
            User user = q.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static boolean emailExists(String email) {
        User u = selectUser(email);
        return u != null;
    }
=======
package murach.data;

import murach.business.User;

public class UserDB {

    public static long insert(User user) {
        // Giả lập lưu user vào cơ sở dữ liệu
        // Tạm thời trả về 1 để phục vụ logic luồng đi
        return 1;
    }
>>>>>>> b1afbd999bd42958f471e97f7700f611520e5d6a
}