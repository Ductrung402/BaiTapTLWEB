package murach.data;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUtil {
    
    // Tạo Factory dựa trên tên cấu hình trong file persistence.xml
   // private static final EntityManagerFactory emf =
           // Persistence.createEntityManagerFactory("emailListPU");

    public static EntityManagerFactory getEmFactory() {
        return null;
    }
}