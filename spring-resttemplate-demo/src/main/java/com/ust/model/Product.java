package com.ust.model;

import lombok.Data;

//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@RequiredArgsConstructor
//@EqualsAndHashCode
@Data
public class Product {

	private int productId;
	private String productName;
//	private final @Getter(lazy = true) int quantityOnHand = 0;
	private int quantityOnHand;
	private int price;
}
