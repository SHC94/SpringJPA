package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

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
            Member member = new Member();
            member.setUsername("신형철");
            member.setCreatedBy("hcshin2");
            member.setCreateDate(LocalDateTime.now());
            member.setLastModifiedBy("hcshin2");
            member.setLastModifiedDate(LocalDateTime.now());

            em.persist(member);

            em.flush();
            em.clear();

            //하이버네이트가 만든 가짜 프록시 클래스
            //하이버네이트 내 라이브러리를 활용
//            Member findMember = em.getReference(Member.class, member.getId());
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getUsername());

            Member refMember = em.getReference(Member.class, member.getId());
            System.out.println("m1 = " + refMember.getClass());
            
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember = " + findMember.getClass());

            System.out.println("a == a : " + (refMember == findMember));    //항상 true

            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));   //프록시 인스턴스의 초기화 여부 확인
            Hibernate.initialize(refMember);    //강제 초기화
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }//end main()
}//end class()
