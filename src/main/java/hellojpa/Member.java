package hellojpa;

import javax.persistence.*;
import java.util.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> faveriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> adddressHistory = new ArrayList<>();

    //Period
    @Embedded
    private Period workPeriod;
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;

    //address
    @Embedded
    private Address homeAddress;
//    private String city;
//    private String street;
//    private String zipcode;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "WOKR_STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address workAddress;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFaveriteFoods() {
        return faveriteFoods;
    }

    public void setFaveriteFoods(Set<String> faveriteFoods) {
        this.faveriteFoods = faveriteFoods;
    }

    public List<Address> getAdddressHistory() {
        return adddressHistory;
    }

    public void setAdddressHistory(List<Address> adddressHistory) {
        this.adddressHistory = adddressHistory;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) && Objects.equals(username, member.username) && Objects.equals(faveriteFoods, member.faveriteFoods) && Objects.equals(adddressHistory, member.adddressHistory) && Objects.equals(workPeriod, member.workPeriod) && Objects.equals(homeAddress, member.homeAddress) && Objects.equals(workAddress, member.workAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, faveriteFoods, adddressHistory, workPeriod, homeAddress, workAddress);
    }
}
