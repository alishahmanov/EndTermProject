package controllers;

import exceptions.ShoeNotFoundException;
import models.Shoes;
import services.interfaces.IShoesService;
import models.enums.Role;

public class ShoesController {
    private final IShoesService service;

    public ShoesController(IShoesService service) {
        this.service = service;
    }

    public String addShoe(String brand, String material, String color, String seasonOfShoes,
                          boolean gender, int size, int price, String availability, Role role) {
        if (brand == null || brand.trim().isEmpty()) return "Brand cannot be empty!";
        if (material == null || material.trim().isEmpty()) return "Material cannot be empty!";
        if (color == null || color.trim().isEmpty()) return "Color cannot be empty!";
        if (seasonOfShoes == null || seasonOfShoes.trim().isEmpty()) return "Season cannot be empty!";
        if (availability == null || availability.trim().isEmpty()) return "Availability cannot be empty!";
        if (size <= 0) return "Size must be greater than 0!";
        if (price <= 0) return "Price must be greater than 0!";
        if (role != Role.SUPPLIER) return "Only suppliers can add shoes!";

        boolean created = service.addShoe(brand, material, color, seasonOfShoes, gender, size, price, availability, role);
        return created ? "Shoe added successfully!" : "Failed to add shoe.";
    }

    public String getAllShoes() {
        return service.getAllShoes();
    }


    public Shoes getShoe(int id) {
        return service.getShoeById(id)
                .orElseThrow(() -> new ShoeNotFoundException(id));
    }
}
