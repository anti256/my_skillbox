import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="purchaselist")
public class PurchaseList {

  @EmbeddedId
  private PurchaseListId id;

  private Integer price;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  public Date getSubscriptionDate() {
    return subscriptionDate;
  }

  public void setSubscriptionDate(Date subscriptionDate) {
    this.subscriptionDate = subscriptionDate;
  }

  public PurchaseListId getId() {return id;}

  public void setId(PurchaseListId id) {this.id = id;}

  public Integer getPrice() {return price;}

  public void setPrice(Integer price) {this.price = price;}

  public Date getSubsriptionDate() {return subscriptionDate;}

  public void setSubsriptionDate(Date subsriptionDate) {
    this.subscriptionDate = subsriptionDate;
  }
}
