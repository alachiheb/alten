package com.alten.management.product.exception;


public enum ProductExceptionMessage {

    PRODUCT_NOT_FOUND( "Ce produit n'existe pas", 1);

    private final String description;
    private final String code;

    ProductExceptionMessage(String description, int code) {
        this.code = String.valueOf(code);
        this.description = description;
    }

    public String toErrorMessage() {
        return String.join(" : ", this.code,this.description);
    }
}
