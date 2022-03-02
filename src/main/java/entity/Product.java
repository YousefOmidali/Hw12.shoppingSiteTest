package entity;

public class Product {

    private int id;
    private String name;
    private String description;
    private Category category;
    private int qty;
    private int price;

    public Product(String name, String description, Category category, int qty, int price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.qty = qty;
        this.price = price;
    }

    public Product(int id, String name, String description,int price , int qty, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.qty = qty;
        this.price = price;
    }


    public Product(Product product) {
    }

    public Product(String name, String description, int price, int qty) {
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.price = price;
    }

    public Product(int id,String name, String description, int price, int qty, int category_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = new Category(category_id,"","");
        this.qty = qty;
        this.price = price;
    }

    public Product(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
