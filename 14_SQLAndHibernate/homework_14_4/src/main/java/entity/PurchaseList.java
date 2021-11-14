package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Formatter;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="purchaselist")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PurchaseList implements Serializable{


  @EmbeddedId
  private PurchaseListPK id;

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
