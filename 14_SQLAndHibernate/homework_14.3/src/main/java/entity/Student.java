package entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "students")
public class Student {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private Integer age;

  @Column (name = "registration_date")
  private Date registrationDate;

  @ManyToMany
  @JoinTable (name = "subscriptions",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id"))
  private List<Course> courses;

  @OneToMany(mappedBy="student", cascade= CascadeType.ALL)//fetch= FetchType.EAGER, cascade= CascadeType.ALL)
  private List<Subscription> subscriptionList;

  @OneToMany(mappedBy="student", cascade= CascadeType.ALL)//fetch= FetchType.EAGER, cascade= CascadeType.ALL)
  private List<PurchaseList> purchaseLists;

  public List<PurchaseList> getPurchaseLists() {
    return purchaseLists;
  }

  public void setPurchaseLists(List<PurchaseList> purchaseLists) {
    this.purchaseLists = purchaseLists;
  }

  public List<Subscription> getSubscriptionList() {
    return subscriptionList;
  }

  public void setSubscriptionList(List<Subscription> subscriptionList) {
    this.subscriptionList = subscriptionList;
  }

  public Integer getId() {return id;}

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  public void setId(Integer id) {this.id = id;}

  public String getName() {return name;}

  public void setName(String name) {this.name = name;}

  public Integer getAge() {return age;}

  public void setAge(Integer age) {this.age = age;}

  public Date getRegistrationDate() {return registrationDate;}

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

}
