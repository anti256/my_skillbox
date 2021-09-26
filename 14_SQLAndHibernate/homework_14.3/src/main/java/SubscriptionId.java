import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class SubscriptionId implements Serializable {

  @ManyToOne(optional=false, cascade=CascadeType.ALL)
  @JoinColumn(name = "student_id")
  private Student student;

  @ManyToOne(optional=false, cascade=CascadeType.ALL)
  @JoinColumn(name = "course_id")
  private Course course;

//  @ManyToMany
//  @JoinTable(name="courses",
//      joinColumns=@JoinColumn (name="id"),
//      inverseJoinColumns=@JoinColumn(name="name"))
//  private List<PurchaseListId> purchaseListIds;
//
//  @ManyToMany
//  @JoinTable(name="students",
//      joinColumns=@JoinColumn (name="id"),
//      inverseJoinColumns=@JoinColumn(name="name"))
//  private List<PurchaseListId> purchaseListIds2;

  public Student getStudent() {return student;}

  public void setStudent(Student student) {this.student = student;}

  public Course getCourse() {return course;}

  public void setCourse(Course course) {this.course = course;}

  @Override
  public boolean equals(Object o) {
    if (this == o) {return true;}
    if (o == null || getClass() != o.getClass()) {return false;}

    SubscriptionId that = (SubscriptionId) o;

    if (getStudent() != that.getStudent()) {return false;}
    return getCourse() == that.getCourse();
  }

  @Override
  public int hashCode() {
    int result = getStudent().hashCode();
    result = 31 * result + getCourse().hashCode();
    return result;
  }
}
