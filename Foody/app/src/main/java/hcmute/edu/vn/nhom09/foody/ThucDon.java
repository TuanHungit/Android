package hcmute.edu.vn.nhom09.foody;

public class ThucDon {
    public int id;
    public String menu_id;
    public String name;
    public String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ThucDon(int id, String menu_id, String name, String price) {
        this.id = id;
        this.menu_id = menu_id;
        this.name = name;
        this.price = price;
    }
}
