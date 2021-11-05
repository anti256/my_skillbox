package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Formatter;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
class PurchaseListPK implements Serializable {
  @Column(name = "student_name")
  private String studentName;

  @Column(name = "course_name")
  private String courseName;

  @SuppressWarnings("unused")//подавляет unused предупреждения компилятора
  private PurchaseListPK(){}
}

@Entity
@Table(name="purchaselist")
@Data
public class PurchaseList implements Serializable{

  @EmbeddedId
  private PurchaseListPK id;

  //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//  @JoinColumn(name = "student_name", referencedColumnName = "name", insertable = false, updatable = false)
//  private Student student;

  //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//  @JoinColumn(name = "course_name", referencedColumnName = "name", insertable = false, updatable = false)
//  private Course course;

  @Column(name = "price")
  private int price;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  @Override
  public String toString() {
    return new Formatter().format(
        "PurchaseList (studentName: %s, courseName: %s, subscriptionDate: %3$td.%3$tm.%3$tY)",
        id.getStudentName(), id.getCourseName(), subscriptionDate).toString();
  }

}
