package kata.Development.books.service;

import kata.Development.books.entity.DevelopmentBook;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.logging.Logger;

@Service
public class DevelopmentBookPricingServiceImpl {


    public BigDecimal computeDevelopmentBookBasketPrice (final Map<Integer, Integer> developmentBookBasketMap) {
        return evaluateDevelopmentBookBasketPrice(developmentBookBasketMap);
    }

    private BigDecimal evaluateDevelopmentBookBasketPrice(final Map<Integer, Integer> developmentBookBasketMap) {
        BigDecimal finalPrice = new BigDecimal(BigInteger.ZERO);
        final List<Integer> developmentBooksQuantity = new ArrayList<>(developmentBookBasketMap.values().stream().sorted(Comparator.reverseOrder()).toList());
        final boolean areDuplicatesPresent = developmentBooksQuantity.stream().anyMatch(q -> q > 1);

        if (areDuplicatesPresent) {
            List<Integer> tempDevelopmentBooksQuantity = new ArrayList<>();

            for (int i = 1; i <= developmentBooksQuantity.get(0); i++) {
                ListIterator<Integer> iterator = developmentBooksQuantity.listIterator();
                while (iterator.hasNext()) {
                    Integer currentValue = iterator.next();
                    if (currentValue > 1 && currentValue - i >= 0) {
                        tempDevelopmentBooksQuantity.add(currentValue);
                    } else if (currentValue <= 1) {
                        iterator.remove();
                        tempDevelopmentBooksQuantity.add(0);
                        break;
                    } else {
                        iterator.remove();
                        break;
                    }
                }
                finalPrice = computePriceWithDiscount(tempDevelopmentBooksQuantity, finalPrice);
                tempDevelopmentBooksQuantity.clear();
            }
        } else {
            return computePriceWithDiscount(developmentBooksQuantity, finalPrice);
        }
        return finalPrice;
    }

    private BigDecimal percentage(final BigDecimal base, final BigDecimal percent){
        return base.multiply(percent).divide(new BigDecimal(100), 2, RoundingMode.CEILING);
    }

    private BigDecimal computePriceWithDiscount (final List<Integer> consideredDevelopmentBooks, BigDecimal finalPrice) {
        BigDecimal developmentBookPrice = new BigDecimal(50);
        switch (consideredDevelopmentBooks.size()) {
            case 1 -> {
                finalPrice = finalPrice.add(developmentBookPrice);
            }
            case 2 -> {
                finalPrice = finalPrice.add(developmentBookPrice.multiply(new BigDecimal(2)).subtract(percentage(developmentBookPrice.multiply(new BigDecimal(2)), new BigDecimal(5))));
            }
            case 3 -> {
                finalPrice = finalPrice.add(developmentBookPrice.multiply(new BigDecimal(3)).subtract(percentage(developmentBookPrice.multiply(new BigDecimal(3)), new BigDecimal(10))));
            }
            case 4 -> {
                finalPrice = finalPrice.add(developmentBookPrice.multiply(new BigDecimal(4)).subtract(percentage(developmentBookPrice.multiply(new BigDecimal(4)), new BigDecimal(20))));
            }
            case 5 -> {
                finalPrice = finalPrice.add(developmentBookPrice.multiply(new BigDecimal(5)).subtract(percentage(developmentBookPrice.multiply(new BigDecimal(5)), new BigDecimal(25))));
            }
        }
        return finalPrice;
    }

    public Map<Integer, Integer> convertDevelopmentBookBasketMapToIntegerMap (Map<String, String> developmentBookBasketMap) {
        return convertSringMapToIntegerMap(developmentBookBasketMap);
    }

    private Map<Integer, Integer> convertSringMapToIntegerMap (Map<String, String> developmentBookBasketMap) {
        Map<Integer, Integer> convertedMap = new HashMap<>();
        if (developmentBookBasketMap != null && !developmentBookBasketMap.isEmpty()) {
            try {
                for (Map.Entry<String, String> i : developmentBookBasketMap.entrySet()) {
                    Integer id = Integer.parseInt(i.getKey());
                    Integer quantity = Integer.parseInt(i.getValue());
                    if (id <= 0 || id > 5) {
                        System.out.println("Development book with id " + id + " not found in the system.");
                    } else if (quantity < 0) {
                        System.out.println("This entry " + id + ":" + quantity + " will be discarded as operation is not possible with negative quantity.");
                    } else {
                        convertedMap.put(id, quantity);
                    }
                }
            } catch (NumberFormatException e) {
                throw e;
            }
        }
        return convertedMap;
    }

}
