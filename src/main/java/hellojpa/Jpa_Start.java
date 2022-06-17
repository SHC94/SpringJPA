package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Jpa_Start {

    public static void main(String[] args) {
        System.out.println("start");
        //엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //엔티티 매니저는 쓰레드간에 공유X (사용하고 버려야 한다).
        EntityManager em = emf.createEntityManager();

        //JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Movie movie = new Movie();
            movie.setDirector("신형철");
            movie.setActor("형철");
            movie.setName("형철따리형철따");
            movie.setPrice(20000);

            em.persist(movie);

            em.flush();
            em.clear();

            Movie findmove = em.find(Movie.class, movie.getId());
            System.out.println("findmove = " + findmove);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }//end main()
}//end class()
