package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "subscriptions")
public class Subscription {

  @EmbeddedId
  private SubscriptionId id;

  @ManyToOne(optional=false)
  @JoinColumn(name = "course_id", insertable = false, updatable = false)
  private Course course;

  @ManyToOne(optional=false)
  @JoinColumn(name = "student_id", insertable = false, updatable = false)
  private Student student;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public SubscriptionId getId() {
    return id;
  }

  public void setId(SubscriptionId id) {
    this.id = id;
  }

  public Date getSubscriptionDate() {
    return subscriptionDate;
  }

  public void setSubscriptionDate(Date subscriptionDate) {
    this.subscriptionDate = subscriptionDate;
  }

  //класс составного идентификатора
  @Embeddable
  public class SubscriptionId implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    public int getStudentId() {
      return studentId;
    }

    public void setStudentId(int studentId) {
      this.studentId = studentId;
    }

    public int getCourseId() {
      return courseId;
    }

    public void setCourseId(int courseId) {
      this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      SubscriptionId that = (SubscriptionId) o;

      if (getStudentId() != that.getStudentId()) {
        return false;
      }
      return getCourseId() == that.getCourseId();
    }

    @Override
    public int hashCode() {
      int result = getStudentId();
      result = 31 * result + getCourseId();
      return result;
    }
  }
}
