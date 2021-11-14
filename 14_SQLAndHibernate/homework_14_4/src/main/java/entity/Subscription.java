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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//класс составного идентификатора
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Embeddable
class SubscriptionId implements Serializable {

  @Getter
  @Setter
  @Column(name = "student_id")
  private int studentId;

  @Getter
  @Setter
  @Column(name = "course_id")
  private int courseId;

}

@Entity
@Table (name = "subscriptions")
public class Subscription {

  @EmbeddedId
  private SubscriptionId id;

  @Getter
  @Setter
  @ManyToOne(optional=false)
  @JoinColumn(name = "course_id", insertable = false, updatable = false)
  private Course course;

  @Getter
  @Setter
  @ManyToOne(optional=false)
  @JoinColumn(name = "student_id", insertable = false, updatable = false)
  private Student student;

  @Getter
  @Setter
  @Column(name = "subscription_date")
  private Date subscriptionDate;
}

