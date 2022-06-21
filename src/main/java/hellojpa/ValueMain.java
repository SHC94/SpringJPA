package hellojpa;

public class ValueMain {
    public static void main(String[] args) {

//        System.out.println("start");
//            //엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//
//            //엔티티 매니저는 쓰레드간에 공유X (사용하고 버려야 한다).
//            EntityManager em = emf.createEntityManager();
//
//            //JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
//            EntityTransaction tx = em.getTransaction();
//            tx.begin();
//
//            int a = 10;
//            int b = a;
//
//            a = 20;
//
//            //기본값
//            //자바 기본 타입(int, double)
//            //기본 타입은 공유가 안됨.
//            System.out.println("a = " + a);
//            System.out.println("b = " + b);
//
//            //래퍼 클래스, String은 공유는 가능하지만 변경X
//            Integer c = new Integer(10);
//            Integer d = c;
//
//            c = 20;
//            System.out.println("c = " + c);
//            System.out.println("d = " + d);
//
//            try {

            //임베디드 타입
//            Member member = new Member();
//            member.setUsername("신형철");
//            member.setHomeAddress(new Address("경기도", "고양시", "덕양구"));
//            member.setWorkPeriod(new Period());
//            em.persist(member);

            //값 타입 공유 참조
//            Address address = new Address("경기도", "고양시", "덕양구");
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setHomeAddress(address);
//            em.persist(member);
//
//            Address address2 = new Address("newCity", address.getStreet(), address.getZipcode());
//            member.setHomeAddress(address2);

//            Address address2 = new Address(address.getCity(), address.getStreet(), address.getZipcode());
//
//            Member member2 = new Member();
//            member2.setUsername("member2");
//            member2.setHomeAddress(address2);
//            em.persist(member2);

            //임베디드 타입 같은 값 타입을 여러 엔티티에서 공유하면 위험함
            //side effect
//            member.getHomeAddress().setCity("newCity");

            //객체 타입을 수정할 수 없게 만들어 부작용을 원천 차단
            //붋변 객체 : 생성 시점에 이후 절대 값을 변경할 수 없는 객체


            //값 타입 컬렉션
//            Member member = new Member();
//            member.setUsername("형철따리형철따");
//            member.setWorkAddress(new Address("고양시", "덕양구", "고양동"));
//
//            member.getFaveriteFoods().add("치킨");
//            member.getFaveriteFoods().add("족발");
//            member.getFaveriteFoods().add("피자");
//
//            member.getAdddressHistory().add(new Address("서울", "덕양구", "고양동"));
//            member.getAdddressHistory().add(new Address("경기도", "덕양구", "고양동"));
//
//            //값 타입 컬렉션도 같이 persist됨..
//            em.persist(member);
//
//            System.out.println("========================================================");
//            Member findMember = em.find(Member.class, member.getId());
//            //findMember.getHomeAddress().setCity("newCity"); //???? 이렇게 하면 안됨.
//
//            //값 타입... 새로 갈아끼워야 한다.
//            Address odlAddr = findMember.getHomeAddress();
//
//            //객체 값 타입
//            findMember.setHomeAddress(new Address("newCity", odlAddr.getStreet(), odlAddr.getZipcode()));
//
//            //컬렉션
//            findMember.getFaveriteFoods().remove("치킨");
//            findMember.getFaveriteFoods().add("한식");
//
//            findMember.getAdddressHistory().remove(new Address("서울", "덕양구", "고양동"));
//            findMember.getAdddressHistory().add(new Address("뉴욕", "덕양구", "고양동"));

//            select
//            member0_.MEMBER_ID as MEMBER_I1_6_0_,
//                    member0_.city as city2_6_0_,
//            member0_.street as street3_6_0_,
//                    member0_.zipcode as zipcode4_6_0_,
//            member0_.USERNAME as USERNAME5_6_0_,
//                    member0_.WORK_CITY as WORK_CIT6_6_0_,
//            member0_.WOKR_STREET as WOKR_STR7_6_0_,
//                    member0_.WORK_ZIPCODE as WORK_ZIP8_6_0_,
//            member0_.endDate as endDate9_6_0_,
//                    member0_.startDate as startDa10_6_0_
//            from
//            Member member0_
//            where
//            member0_.MEMBER_ID=?

            //컬렉션은 지연로딩..
//            List<Address> addressHistory = findMember.getAdddressHistory();
//
//            for(Address address : addressHistory){
//                System.out.println("address = " + address.getCity());
//            }
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//            emf.close();
//        }



    }//end main()
}//end class()
