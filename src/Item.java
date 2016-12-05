/**
 * Created by roger on 02.12.16.
 */
public class Item {

    private String barcode;
    private String name;
    private char symbol;
    private Integer volume;
    private Integer price;
    private ItemType type;


    public Item(String barcode, String name, char symbol, Integer volume, Integer price, ItemType type) {
        this.barcode = barcode;
        this.name = name;
        this.symbol = symbol;
        this.volume = volume;
        this.price = price;
        this.type = type;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
