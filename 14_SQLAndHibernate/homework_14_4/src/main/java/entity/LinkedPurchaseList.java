package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//класс составного идентификатора
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@ToString
@Embeddable
class LinkedPurchaseListId implements Serializable {

  @Getter
  @Setter
  @Column(name = "course_id")
  private Integer courseId;

  @Getter
  @Setter
  @Column(name = "student_id")
  private Integer studentId;

}

//класс сущности
@NoArgsConstructor
@Entity
@Table(name = "linkedpurchaselist")
public class LinkedPurchaseList {

  public LinkedPurchaseList(int courseId, int studentId){
    this.courseId = courseId;
    this.studentId = studentId;
    this.setId(this.setId(new LinkedPurchaseListId(courseId, studentId)));
  }

  @Getter
  @EmbeddedId
  private LinkedPurchaseListId id;

  public LinkedPurchaseListId setId(LinkedPurchaseListId id) {
    this.id = id;
    return id;
  }//такой сеттер нужен для конструктора класса LinkedPurchaseList со всеми параметрами

  @Getter
  @Setter
  @Column(name = "course_id", insertable = false, updatable = false)
  private Integer courseId;

  @Getter
  @Setter
  @Column(name = "student_id", insertable = false, updatable = false)
  private Integer studentId;
}
