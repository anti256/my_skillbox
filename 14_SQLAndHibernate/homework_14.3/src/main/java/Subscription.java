import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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

  @ManyToOne(optional=false, cascade= CascadeType.ALL)
  @JoinColumn(name = "course_id", insertable = false, updatable = false)
  private Course course;

  @ManyToOne(optional=false, cascade= CascadeType.ALL)
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
}
