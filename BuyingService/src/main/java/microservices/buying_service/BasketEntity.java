package microservices.buying_service;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "baskets")
public class BasketEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
    @Column(name = "user")
    private String user;

    @Column(name = "abyssiniancat")
    private Integer AbyssinianCat = 0;
    @Column(name = "bengalcat")
    private Integer BengalCat = 0;
    @Column(name = "feeditem")
    private Integer Feeditem = 0;
    @Column(name = "toyitem")
    private Integer ToyItem = 0;

    @Column(columnDefinition = "double default 142.0")
    private Double AbyssinianCatPrice = 142.0;
    @Column(columnDefinition = "double default 85.0")
    private Double BengalCatPrice = 85.0;
    @Column(columnDefinition = "double default 15.0")
    private Double FeeditemPrice = 15.0;
    @Column(columnDefinition = "double default 10.0")
    private Double ToyItemPrice = 10.0;

    @Column(name = "totalprice")
    private Double totalPrice = 0.0;

    public BasketEntity() {
    }

    public void updateTotalPrice(){
        totalPrice =
                AbyssinianCat * AbyssinianCatPrice +
                        BengalCat * BengalCatPrice +
                        Feeditem * FeeditemPrice +
                        ToyItem * ToyItemPrice;
    }

    public BasketEntity(String user) {
        this.user = user;
        this.AbyssinianCat = 0;
        this.BengalCat = 0;
        this.Feeditem = 0;
        this.ToyItem = 0;
        updateTotalPrice();
    }

//    public Integer getId() {
//        return id;
//    }
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public Integer getAbyssinianCat() {
        return AbyssinianCat;
    }
    public void setAbyssinianCat(Integer AbyssinianCat) {
        this.AbyssinianCat = AbyssinianCat;
        updateTotalPrice();
    }

    public Integer getBengalCat() {
        return BengalCat;
    }
    public void setBengalCat(Integer BengalCat) {
        this.AbyssinianCat = BengalCat;
        updateTotalPrice();
    }

    public Integer getFeedItem() {
        return Feeditem;
    }
    public void setFeedItem(Integer Feeditem) {
        this.Feeditem = Feeditem;
        updateTotalPrice();
    }

    public Integer getToyItem() {
        return ToyItem;
    }
    public void setToyItem(Integer ToyItem) {
        this.Feeditem = ToyItem;
        updateTotalPrice();
    }

    public Double getAbyssinianCatPrice() {return AbyssinianCatPrice;}
    public Double getBengalCatPrice() {return BengalCatPrice;}
    public Double getFeedItemPrice() {return FeeditemPrice;}
    public Double getToyItemPrice() {return ToyItemPrice;}
    public Double getTotalPrice() {return totalPrice;}
}
