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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table (name = "students")
@Data
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

//  @OneToMany(mappedBy="student", cascade= CascadeType.ALL)//fetch= FetchType.EAGER, cascade= CascadeType.ALL)
//  private List<PurchaseList> purchaseLists;

  @OneToOne(optional=false, mappedBy="student")
  private PurchaseList purchaseList;


}
