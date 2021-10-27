package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchaselist")
@IdClass(PurchaseList.PurchaseListId.class)
public class PurchaseList{

  @Id
  //@Column(name = "student_name")
  private String studentName;

  @Id
  //@Column(name = "course_name")
  private String courseName;

  private Integer price;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  @ManyToOne(optional=false)
  private Student student;

  @ManyToOne(optional=false)
  private Course course;

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

  //класс составного идентификатора
  public class PurchaseListId implements Serializable {

    static final long serialVersionUID = 1L;

    @Column(name = "student_name")
    private String studentName;

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

    @Override
    public String toString() {
      return "PurchaseListId{" +
          "studentName='" + studentName + '\'' +
          ", courseName='" + courseName + '\'' +
          '}';
    }
  }

}
