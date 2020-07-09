package hcmute.edu.vn.nhom09.foody;


public class QuanAn {
    public int food_id;
    public String food_name;
    public String address;
    public String location;
    public String location_latitude;
    public String tinhthanh;
    public String price;
    public String gioMoCua;
    public String gioDongCua;
    public String menu_id;
    public String wifi;
    public String mkwifi;
    public byte[] image;
    public byte[] image2;
    public byte[] image3;
    public byte[] image4;

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation_latitude() {
        return location_latitude;
    }

    public void setLocation_latitude(String location_latitude) {
        this.location_latitude = location_latitude;
    }

    public String getTinhthanh() {
        return tinhthanh;
    }

    public void setTinhthanh(String tinhthanh) {
        this.tinhthanh = tinhthanh;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGioMoCua() {
        return gioMoCua;
    }

    public void setGioMoCua(String gioMoCua) {
        this.gioMoCua = gioMoCua;
    }

    public String getGioDongCua() {
        return gioDongCua;
    }

    public void setGioDongCua(String gioDongCua) {
        this.gioDongCua = gioDongCua;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getMkwifi() {
        return mkwifi;
    }

    public void setMkwifi(String mkwifi) {
        this.mkwifi = mkwifi;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage2() {
        return image2;
    }

    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }

    public byte[] getImage3() {
        return image3;
    }

    public void setImage3(byte[] image3) {
        this.image3 = image3;
    }

    public byte[] getImage4() {
        return image4;
    }

    public void setImage4(byte[] image4) {
        this.image4 = image4;
    }

    public QuanAn(int food_id, String food_name,
                  String address, String location,
                  String location_latitude, String tinhthanh,
                  String price, String gioMoCua, String gioDongCua,
                  String menu_id, String wifi, String mkwifi,
                  byte[] image, byte[] image2, byte[] image3, byte[] image4) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.address = address;
        this.location = location;
        this.location_latitude = location_latitude;
        this.tinhthanh = tinhthanh;
        this.price = price;
        this.gioMoCua = gioMoCua;
        this.gioDongCua = gioDongCua;
        this.menu_id = menu_id;
        this.wifi = wifi;
        this.mkwifi = mkwifi;
        this.image = image;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
    }
}
