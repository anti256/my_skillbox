import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PurchaseListId_end implements Serializable {

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

    PurchaseListId_end that = (PurchaseListId_end) o;

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
