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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "subscriptions")
@Getter
@Setter
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

  //класс составного идентификатора
  @Embeddable
  @Data
  public class SubscriptionId implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;


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
