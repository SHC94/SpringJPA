package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Jpa_PersistenceContext {

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
//            회원 등록

//            비영속
            Member member = new Member();
//            member.setId(100L);
            member.setUsername("HelloJPA");

            Member memberB = new Member();
//            member.setId(101L);
            member.setUsername("HelloJPA2");

//            영속
            em.persist(member);
            em.persist(memberB);
            //여기까지 Insert SQL을 데이터베이스에 보내지 않는다.

            Member findMember1 = em.find(Member.class, 100L);   //DB 조회 > 영속성 컨텍스트(1차 캐시)   : 쿼리 O
            Member findMember2 = em.find(Member.class, 100L);   //영속성 컨텍스트(1차 캐시) 조회        : 쿼리 X

//            동일성 보장
            System.out.println(findMember1 == findMember2);

            //커밋하는 순간 데이터베이스에 Insert SQL을 보낸다.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }//end main()
}//end class()
