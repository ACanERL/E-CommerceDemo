package com.example.aceshop.model;

public class Items {
String itemname;
int itemimage;
public Items(){

}
public Items(String itemName,int itemImage){
    this.itemname=itemName;
    this.itemimage=itemImage;
}

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getItemimage() {
        return itemimage;
    }

    public void setItemimage(int itemimage) {
        this.itemimage = itemimage;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemname='" + itemname + '\'' +
                ", itemimage=" + itemimage +
                '}';
    }
}
