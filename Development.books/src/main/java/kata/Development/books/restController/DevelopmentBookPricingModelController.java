package kata.Development.books.restController;

import kata.Development.books.service.DevelopmentBookPricingServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping(path = "/development-books")
public class DevelopmentBookPricingModelController {
    @GetMapping("/compute-pricing")
    private BigDecimal getDevelopmentBooksPricing (@RequestParam Map<String, String> developmentBookBasketMap ) {
        DevelopmentBookPricingServiceImpl developmentBookPricingService = new DevelopmentBookPricingServiceImpl();
        final Map<Integer, Integer> convertedMap = developmentBookPricingService.convertDevelopmentBookBasketMapToIntegerMap(developmentBookBasketMap);
        return developmentBookPricingService.computeDevelopmentBookBasketPrice(convertedMap);
    }

    //TODO: Add proper REST error and exceptions handling

}
