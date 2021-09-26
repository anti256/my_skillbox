import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Courses")
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

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "teacher_id")
  private Teacher teacher;

  @Column(name = "students_count")
  private Integer studentsCount;

  private Integer price;

  @Column(name = "price_per_hour")
  private Float pricePerHour;

  @ManyToMany (cascade = CascadeType.ALL)
  @JoinTable (name = "subscriptions",
              joinColumns = @JoinColumn(name = "course_id"),
              inverseJoinColumns = @JoinColumn(name = "student_id")
  )
  private List<Student> students;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public CourseType getType() {
    return type;
  }

  public void setType(CourseType type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacherId(Teacher teacher) {
    this.teacher = teacher;
  }

  public Integer getStudentsCount() {
    if (studentsCount==null){return 0;}
    return studentsCount;}

  public void setStudentsCount(Integer studentsCount) {
    if (studentsCount==null){studentsCount = 0;}
    this.studentsCount = studentsCount;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Float getPricePerHour() {
    return pricePerHour;
  }

  public void setPricePerHour(Float pricePerHour) {
    this.pricePerHour = pricePerHour;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }
}
