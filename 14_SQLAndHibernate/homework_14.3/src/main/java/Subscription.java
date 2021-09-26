import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "subscriptions")
public class Subscription {

  @EmbeddedId
  private SubscriptionId id;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

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
