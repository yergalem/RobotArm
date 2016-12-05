import java.util.List;

/**
 * Created by roger on 05.12.16.
 */
public class Order {
    private List<String> itemBarcodesUsed;
    private Integer price;

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
