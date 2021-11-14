package entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "students")
public class Student {

  @Getter
  @Setter
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Integer id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private Integer age;

  @Getter
  @Setter
  @Column (name = "registration_date")
  private Date registrationDate;

  @Getter
  @Setter
  @ManyToMany
  @JoinTable (name = "subscriptions",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id"))
  private List<Course> courses;

  @Getter
  @Setter
  @OneToMany(mappedBy="student", fetch= FetchType.EAGER, cascade= CascadeType.ALL)
  private List<Subscription> subscriptionList;

//  @OneToMany(mappedBy="student", cascade= CascadeType.ALL)//fetch= FetchType.EAGER, cascade= CascadeType.ALL)
//  private List<PurchaseList> purchaseLists;

//  @OneToOne(optional=false, mappedBy="student")
//  private PurchaseList purchaseList;


}
