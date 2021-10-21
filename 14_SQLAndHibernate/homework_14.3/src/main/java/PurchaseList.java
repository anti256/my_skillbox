import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="purchaselist")
public class PurchaseList {

  @EmbeddedId
  private PurchaseListId id;

  private Integer price;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  @ManyToOne(optional=false)
  @Transient
  private Student student;

  @ManyToOne(optional=false)
  @Transient
  private Course course;

//  @Column(name = "student_name", insertable = false, updatable = false)
//  private String studentName;
//
//  @Column(name = "course_name", insertable = false, updatable = false)
//  private String courseName;

//  public String getStudentName() {
//    return student.getName();
//  }

//  public String getStudentName() {
//    return studentName;
//  }
//
//  public void setStudentName(String studentName) {
//    this.studentName = studentName;
//  }

//  public String getCourseName() {
//    return course.getName();
//  }

//  public void setCourseName(String courseName) {
//    this.courseName = courseName;
//  }

  public PurchaseListId getId() {
    return id;
  }

  public void setId(PurchaseListId id) {
    this.id = id;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Date getSubscriptionDate() {
    return subscriptionDate;
  }

  public void setSubscriptionDate(Date subscriptionDate) {
    this.subscriptionDate = subscriptionDate;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }


//  public String getCourseName() {
//    return courseName;
//  }

  @Embeddable
  public class PurchaseListId implements Serializable {

    static final long serialVersionUID = 1L;

    //@ManyToOne(optional=false)
    @Column(name = "student_name")
    private String studentName;

    //@ManyToOne(optional=false)
    @Column(name = "course_name")
    private String courseName;

    public String getStudentName() {
      return studentName;
    }

    public void setStudentName(String studentName) {
      this.studentName = studentName;
    }

    public String getCourseName() {
      return courseName;
    }

    public void setCourseName(String courseName) {
      this.courseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      PurchaseListId that = (PurchaseListId) o;

      if (getStudentName() != null ? !getStudentName().equals(that.getStudentName())
          : that.getStudentName() != null) {
        return false;
      }
      return getCourseName() != null ? getCourseName().equals(that.getCourseName())
          : that.getCourseName() == null;
    }

    @Override
    public int hashCode() {
      int result = getStudentName() != null ? getStudentName().hashCode() : 0;
      result = 31 * result + (getCourseName() != null ? getCourseName().hashCode() : 0);
      return result;
    }
  }

}
