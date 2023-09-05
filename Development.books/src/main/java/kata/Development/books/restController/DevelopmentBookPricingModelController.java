package kata.Development.books.restController;

import kata.Development.books.entity.DevelopmentBook;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

public class DevelopmentBookPricingModelController {

    public BigDecimal computeDevelopmentBookBasketPrice(List<DevelopmentBook> developmentBookBasket) {
        BigDecimal finalPrice = new BigDecimal(BigInteger.ZERO);
        BigDecimal developmentBookPrice = new BigDecimal(50);
        List<DevelopmentBook> distinctBooks = developmentBookBasket.stream().distinct().toList();
        if (distinctBooks.size() == developmentBookBasket.size()) {
            switch (developmentBookBasket.size()) {
                case 1 -> {
                    return developmentBookPrice;
                }
                case 2 -> {
                    return developmentBookPrice.multiply(new BigDecimal(4)).subtract(percentage(developmentBookPrice.multiply(new BigDecimal(2)), new BigDecimal(5)));
                }
                case 3 -> {
                    return developmentBookPrice.multiply(new BigDecimal(4)).subtract(percentage(developmentBookPrice.multiply(new BigDecimal(3)), new BigDecimal(10)));
                }
                case 4 -> {
                    return developmentBookPrice.multiply(new BigDecimal(4)).subtract(percentage(developmentBookPrice.multiply(new BigDecimal(4)), new BigDecimal(20)));
                }
                case 5 -> {
                    return developmentBookPrice.multiply(new BigDecimal(5)).subtract(percentage(developmentBookPrice.multiply(new BigDecimal(5)), new BigDecimal(25)));
                }
            }
        }
        return finalPrice;
    }


    public BigDecimal percentage(BigDecimal base, BigDecimal percent){
        return base.multiply(percent).divide(new BigDecimal(100), 2, RoundingMode.CEILING);
    }
}
