

public class Content {

    private String barcode;
    private String name;
    private char symbol;
    private Integer volume;
    private Double price;
    private ContentType type;


    /**
     * Constructor for a content which gets stored in the freezer
     * @param barcode barcode used for the content, usually printed on the tube and can be used with barcode scanners
     * @param name name given to the content, human readable
     * @param symbol abbreviation of a single character to represent the content (useful for console printing as example)
     * @param volume volume of liquid contained in the content
     * @param price price for the content, this is the total price for the current volume being stored, not the price per volume
     * @param type the type this content can be classified as
     */
    public Content(String barcode, String name, char symbol, Integer volume, Double price, ContentType type) {
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

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + symbol;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (symbol != other.symbol)
			return false;
		if (type != other.type)
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}
    
    
    
    
}
