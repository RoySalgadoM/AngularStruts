package model.product;

import model.category.Category;

public class Product {
    private int id;
    private String name;
    private String content;
    private int stock;
    private String description;
    private double cost;
    private Category category;

    public Product(int id, String name, String content, int stock, String description, double cost, Category category) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.stock = stock;
        this.description = description;
        this.cost = cost;
        this.category = category;
    }

    public Product(String name, String content, int stock, String description, double cost, Category category) {
        this.name = name;
        this.content = content;
        this.stock = stock;
        this.description = description;
        this.cost = cost;
        this.category = category;
    }

    public Product() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
