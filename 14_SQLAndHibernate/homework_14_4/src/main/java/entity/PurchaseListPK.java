package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.hibernate.Hibernate;

@Data
@AllArgsConstructor
@ToString
@Embeddable
public class PurchaseListPK implements Serializable {

  @Column(name = "student_name")
  private String studentName;

  @Column(name = "course_name")
  private String courseName;

  @SuppressWarnings("unused")//подавляет unused предупреждения компилятора
  private PurchaseListPK() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    PurchaseListPK that = (PurchaseListPK) o;
    return Objects.equals(studentName, that.studentName)
        && Objects.equals(courseName, that.courseName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentName, courseName);
  }
}
