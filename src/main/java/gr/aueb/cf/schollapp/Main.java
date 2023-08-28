package gr.aueb.cf.schollapp;

import gr.aueb.cf.schollapp.model.Course;
import gr.aueb.cf.schollapp.model.Student;
import gr.aueb.cf.schollapp.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school8PU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Teacher giorgos = new Teacher();
        giorgos.setFirstname("giorgos");
        giorgos.setLastname("kamps");

        Teacher kapetis = new Teacher();
        kapetis.setFirstname("makis");
        kapetis.setLastname("kapetis");

        Teacher thanos = new Teacher();
        thanos.setFirstname("ath");
        thanos.setLastname("andr");

        Course java = new Course();
        java.setTitle("JAVA");
        java.setDescription("Java Course");

        giorgos.addCourse(java);

        Student alice = new Student();
        alice.setFirstname("alice");
        alice.setLastname("w.");

        alice.addCourse(java);

        em.persist(giorgos);
        em.persist(kapetis);
        em.persist(thanos);
        em.persist(java);
        em.persist(alice);

        TypedQuery<Object[]> query = em.createQuery("SELECT t, c FROM Teacher t JOIN t.courses c", Object[].class);
        List<Object[]> results = query.getResultList();

        for (Object[] result : results) {
            System.out.println(result[0]);
            System.out.println(result[1]);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
