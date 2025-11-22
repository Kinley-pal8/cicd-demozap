package sg.edu.nus.iss.cicddemo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

/**
 * REST API Controller providing health check, version, and random data endpoints.
 * Uses javafaker library to generate fake but realistic data.
 */
@RestController
public class DataController {

    private static final int NATIONS_COUNT = 10;
    private static final int CURRENCIES_COUNT = 20;
    private static final String HEALTH_STATUS = "HEALTH CHECK OK!";
    private static final String VERSION = "The actual version is 1.0.0";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Faker faker = new Faker();

    /**
     * Health check endpoint for monitoring application status.
     * @return health status message
     */
    @GetMapping("/")
    public String healthCheck() {
        return HEALTH_STATUS;
    }

    /**
     * Application version endpoint.
     * @return application version string
     */
    @GetMapping("/version")
    public String version() {
        return VERSION;
    }

    /**
     * Get random nations data.
     * @return JsonNode array containing nationality, capital city, flag, and language
     */
    @GetMapping("/nations")
    public JsonNode getRandomNations() {
        var nations = objectMapper.createArrayNode();
        for (int i = 0; i < NATIONS_COUNT; i++) {
            var nation = faker.nation();
            nations.add(objectMapper.createObjectNode()
                    .put("nationality", nation.nationality())
                    .put("capitalCity", nation.capitalCity())
                    .put("flag", nation.flag())
                    .put("language", nation.language()));
        }
        return nations;
    }

    /**
     * Get random currencies data.
     * @return JsonNode array containing currency name and code
     */
    @GetMapping("/currencies")
    public JsonNode getRandomCurrencies() {
        var currencies = objectMapper.createArrayNode();
        for (int i = 0; i < CURRENCIES_COUNT; i++) {
            var currency = faker.currency();
            currencies.add(objectMapper.createObjectNode()
                    .put("name", currency.name())
                    .put("code", currency.code()));
        }
        return currencies;
    }

}
