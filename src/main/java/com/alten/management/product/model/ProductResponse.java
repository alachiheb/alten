package com.alten.management.product.model;

import com.alten.management.product.util.InventoryStatus;
import lombok.Builder;

import java.util.Date;


@Builder
public record ProductResponse(
         String name
        , String code
        , String description
        , String image
        , String category
        , Long price
        , Long quantity
        , String internalReference
        , Integer shellId
        , InventoryStatus inventoryStatus
        , long rating
        , Date createdAt
        , Date updatedAt)
{

}
