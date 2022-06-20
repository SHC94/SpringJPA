package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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

            //하이버네이트가 만든 가짜 프록시 클래스
            //하이버네이트 내 라이브러리를 활용
//            Member findMember = em.getReference(Member.class, member.getId());
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getUsername());

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("신형철");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("신형철");
            member2.setTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

//            Member m = em.find(Member.class, member1.getId());
//            System.out.println(m.getTeam().getClass());

//            LAZY 지연로딩
//            EAGER 즉시로딩
//            System.out.println("================");
//            System.out.println(m.getTeam().getName());
//            System.out.println("================");

            //즉시 로딩 N+1 문제 (최초 쿼리 1, 추가 쿼리 N)
            List<Member> members = em.createQuery("select m from Member m", Member.class)
                            .getResultList();

            for(Member m : members) {
                System.out.println(m.getId() + " / " + m.getUsername());
            }
            //SQL 로 번역 : SELECT * FROM MEMBER;
            //SQL : SELECT * FROM TEAM WHERE TEAM_ID = MEMBER.TEAM_ID



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }//end main()
}//end class()
