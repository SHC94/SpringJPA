package hellojpa;

import org.hibernate.annotations.common.reflection.XMember;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

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
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

//            회원 조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember ID = " + findMember.getId());
//            System.out.println("findMember NAME = " + findMember.getName());

//            회원 삭제
//            Member removeMember = new Member();
//            em.remove(removeMember);

//            회원 수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");
            /*
            JPA를 통해 값을 가져오면 JPA가 데이터를 관리하기 시작.
            JPA가 변경 여부를 트랜잭션 커밋 시점에 체크
            */

            /*JPQL*/
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
//            대상이 객체. 객체 지향 쿼리
//            DB의 방언에 맞게 번역
            //push


            for(Member member : result) {
                System.out.println(member.getId() + " / " + member.getName());
            }//end for()

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }//end main()
}//end class()
