import java.util.Date;
import javax.persistence.Column;
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
  public Student student;

  @ManyToOne(optional=false)
  @Transient
  public Course course;

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
}
