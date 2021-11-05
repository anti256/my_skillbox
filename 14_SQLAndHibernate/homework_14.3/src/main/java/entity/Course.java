package entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Courses")
@Data
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private Integer duration;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "enum")
  private CourseType type;

  private String description;

  @ManyToOne//(cascade = CascadeType.ALL)
  @JoinColumn(name = "teacher_id")
  private Teacher teacher;

  @Column(name = "students_count")
  private Integer studentsCount;

  private Integer price;

  @Column(name = "price_per_hour")
  private Float pricePerHour;

  @ManyToMany(cascade = CascadeType.REFRESH)
  @JoinTable(name = "subscriptions",
              joinColumns = @JoinColumn(name = "course_id"),
              inverseJoinColumns = @JoinColumn(name = "student_id")
  )
  private List<Student> students;

  @OneToMany(mappedBy="course", fetch= FetchType.EAGER, cascade= CascadeType.ALL)//fetch= FetchType.EAGER, cascade= CascadeType.ALL)
  private List<Subscription> subscriptionList;

//  @OneToMany(mappedBy="course", cascade= CascadeType.ALL)//fetch= FetchType.EAGER, cascade= CascadeType.ALL)
//  private List<PurchaseList> purchaseLists;

//  @OneToOne(optional=false, mappedBy="course")
//  private PurchaseList purchaseList;
  
}
