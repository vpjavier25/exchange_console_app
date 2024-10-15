import java.io.IOException;
import java.util.*;

import com.exchange.app.models.CurrencyConversor;
import com.exchange.app.promts.checkers.ContinuityChecker;
import com.exchange.app.promts.checkers.TextChecker;
import com.exchange.app.promts.checkers.keyChecker;
import com.exchange.app.promts.menus.BasicMenu;
import com.exchange.app.promts.menus.Menu;
import com.exchange.app.promts.menus.InteractiveMenu;
import com.exchange.app.promts.inputs.TextInput;
import com.exchange.app.searchers.SearchPair;
import com.exchange.app.utils.AnsiSupport;
import com.exchange.app.utils.Formatter;
import com.exchange.app.utils.Screen;
import org.fusesource.jansi.Ansi;

public class Main {
    static private final String[] mainMenuOptions = {"Convertir moneda", "Ver historial", "Salir"};
    static private final Map<String, String> currencies = new HashMap<>();
    static private final String[] currencyOptions1 = {
            "Dólar estadounidense",
            "Euro",
            "Libra esterlina",
            "Franco suizo",
            "Rublo ruso",
            "Dólar de Singapur",
            "Dólar neozelandés",
            "Dólar australiano",
            "Dólar de Brunéi",
            "Dólar de Hong Kong",
            "Dólar canadiense",
            "Won surcoreano",
            "Litecoin",
            "Peso argentino",
            "Peso colombiano",
            "Colón costarricense",
            "Peso chileno",
            "Guaraní paraguayo",
            "Real brasileño",
            "Sol peruano",
            "Peso uruguayo",
            "Atras"
    };

    public static void main(String[] args) {
        int option;
        Menu mainMenu;
        Menu menu1;
        Menu menu2;
        ContinuityChecker continuityChecker;
        if (AnsiSupport.checkAnsiSupport()) {
            mainMenu = new InteractiveMenu(mainMenuOptions, "Que desea hacer");
            menu1 = new InteractiveMenu(currencyOptions1, "Que moneda desea convertir: ");
            menu2 = new InteractiveMenu();
            continuityChecker = new keyChecker();
        } else {
            menu1 = new BasicMenu(currencyOptions1, "Que moneda desea convertir: ");
            menu2 = new BasicMenu();
            continuityChecker = new TextChecker();
            mainMenu = new BasicMenu(mainMenuOptions, "Que desea hacer");
        }

        try {
            do {
                option = mainMenu.display();
                switch (option) {
                    case 0:
                        option = excuteCurrencyConversion(menu1, menu2, continuityChecker);
                        break;
                    case 1:
                        SearchPair searchPair = new SearchPair();
                        List<String> history = searchPair.readHistory();
                        System.out.println("\nUltimas diez conversiones realizadas:\n");
                        for (int i = 0; i < history.size(); i++) {
                            System.out.println(Ansi.ansi().fgGreen().a(i+1 + ". ").reset().toString() + history.get(i));
                        }

                        option = continuityChecker.stop();
                        break;
                    case 2:
                        option = -1;
                        break;
                }
            } while (option != -1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void excuteHistory() {

    }

    public static int excuteCurrencyConversion(Menu menu1, Menu menu2, ContinuityChecker continuityChecker) {
        addCurrencies();
        int selectedOption1 = 0;
        int selectedOption2 = 0;
        int out = 0;
        String amount = "";
        CurrencyConversor result;
        SearchPair searchPair = new SearchPair();

        if (AnsiSupport.checkAnsiSupport()) {
            menu1 = new InteractiveMenu(currencyOptions1, "Que moneda desea convertir: ");
            menu2 = new InteractiveMenu();
            continuityChecker = new keyChecker();
        } else {
            menu1 = new BasicMenu(currencyOptions1, "Que moneda desea convertir: ");
            menu2 = new BasicMenu();
            continuityChecker = new TextChecker();
        }
        try {
            do {
                List<String> currencyOptions2List = new ArrayList<>();

                selectedOption1 = menu1.display();

                if (selectedOption1 == -1) {
                    break;
                }
                for (int i = 0; i < currencyOptions1.length - 1; i++) {
                    if (selectedOption1 != i) {
                        currencyOptions2List.add(currencyOptions1[i]);
                    }
                }
                currencyOptions2List.add("Atras");
                String[] currencyOptions2 = currencyOptions2List.toArray(new String[0]);

                menu2.setOptions(currencyOptions2);
                menu2.setQuestion("A que moneda desea convertir: ");
                selectedOption2 = menu2.display();

                if (selectedOption2 == -1) {
                    continue;
                }
                TextInput input = new TextInput("Convertir " +
                        currencyOptions1[selectedOption1] +
                        " a " +
                        currencyOptions2[selectedOption2] + ": "
                );
                Screen.cleanScreen();
                result = searchPair.search(
                        currencies.get(currencyOptions1[selectedOption1]),
                        currencies.get(currencyOptions2[selectedOption2]),
                        input
                );

                System.out.println(Formatter.formatConversionInfo(result));

                out = continuityChecker.stop();
                break;

            } while (true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return out;
    }

    private static void addCurrencies() {
        currencies.put("Dólar estadounidense", "USD");
        currencies.put("Euro", "EUR");
        currencies.put("Libra esterlina", "GBP");
        currencies.put("Franco suizo", "CHF");
        currencies.put("Rublo ruso", "RUB");
        currencies.put("Dólar de Singapur", "SGD");
        currencies.put("Dólar neozelandés", "NZD");
        currencies.put("Dólar australiano", "AUD");
        currencies.put("Dólar de Brunéi", "BND");
        currencies.put("Dólar de Hong Kong", "HKD");
        currencies.put("Dólar canadiense", "CAD");
        currencies.put("Won surcoreano", "KRW");
        currencies.put("Litecoin", "LTC");
        currencies.put("Peso argentino", "ARS");
        currencies.put("Peso colombiano", "COP");
        currencies.put("Colón costarricense", "CRC");
        currencies.put("Peso chileno", "CLP");
        currencies.put("Guaraní paraguayo", "PYG");
        currencies.put("Real brasileño", "BRL");
        currencies.put("Sol peruano", "PEN");
        currencies.put("Peso uruguayo", "UYU");

    }


}
