import java.util.List;


public class Order {
    private List<String> itemBarcodesUsed;
    private Integer price;

    /**
     * Constructor to build an order
     * @param itemBarcodesUsed used to idenify what content was used to fulfil the order
     * @param price total price for the order
     */
    public Order(List<String> itemBarcodesUsed, Integer price) {
        this.itemBarcodesUsed = itemBarcodesUsed;
        this.price = price;
    }

    public List<String> getItemBarcodesUsed() {
        return itemBarcodesUsed;
    }

    public void setItemBarcodesUsed(List<String> itemBarcodesUsed) {
        this.itemBarcodesUsed = itemBarcodesUsed;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
