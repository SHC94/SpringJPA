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
//            회원 등록
//            Member member = new Member();
//            member.setId(101L);
//            member.setUsername("HelloJPA2");
//            em.persist(member);

//            회원 조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember ID = " + findMember.getId());
//            System.out.println("findMember NAME = " + findMember.getUsername());

//            회원 삭제
//            Member removeMember = new Member();
//            em.remove(removeMember);

//            회원 수정
//            Member findMember = em.find(Member.class, 1L);
//            em.detach(findMember);
//            findMember.setUsername("HelloJPA100");
            /*
            JPA를 통해 값을 가져오면 JPA가 데이터를 관리하기 시작.
            JPA가 변경 여부를 트랜잭션 커밋 시점에 체크
            */

            /*JPQL*/
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
            //대상이 객체. 객체 지향 쿼리
            //DB의 방언에 맞게 번역
            //push


//            for(Member loopMember : result) {
//                System.out.println(loopMember.getId() + " / " + loopMember.getUsername());
//            }//end for()

            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member2 member = new Member2();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member2 findMember = em.find(Member2.class, member.getId());
            List<Member2> members = findMember.getTeam().getMembers();

            for(Member2 m : members) {
                System.out.println(m.getId() + " / " + m.getUsername() + " / " + m.getTeam().getId());
            }//end for()

            System.out.println("====================================");


//            진짜 매핑이 있는 곳에 데이터 넣기
//            team.setName("신형철 팀");
//            em.persist(team);
//
//            Member2 memberTest = new Member2();
//            memberTest.setUsername("신형철 멤버");
//            memberTest.setTeam(team);
//            em.persist(memberTest);


            Team team123 = em.find(Team.class, 1L);
//            가짜 매핑이 있는 곳에 데이터 넣기
//            Member2 testMember = new Member2();
//            testMember.setUsername("연관관계의 주인을 찾아보자");
//            testMember.setTeam(team);
//            List<Member2> zzmemberList = new ArrayList<>();
//            zzmemberList.add(testMember);
//            team123.setMembers(zzmemberList);

            for (Member2 team123Member : team123.getMembers()) {
                System.out.println(team123Member.getId() + " / " + team123Member.getUsername());
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }//end main()
}//end class()
