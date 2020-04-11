package hackovid2020.back.dto.product;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.dao.Product;

public class ProductResponseList {
	
	@JsonProperty
	private List<ProductDetailsResponse> productResponseList;
	
	@JsonCreator
	private ProductResponseList(List<ProductDetailsResponse> productResponseList) {
		this.productResponseList = productResponseList;
	}
	
	public static ProductResponseList ofProductList(List<Product> productList) {
		List<ProductDetailsResponse> prl = productList.stream()
				.map(ProductDetailsResponse::ofProduct)
				.collect(Collectors.toList());
		return new ProductResponseList(prl);
	}

}
