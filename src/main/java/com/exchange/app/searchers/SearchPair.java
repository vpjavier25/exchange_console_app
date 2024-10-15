package com.exchange.app.searchers;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.exchange.app.dtos.CurrencyConversorDto;
import com.exchange.app.models.CurrencyConversor;
import com.exchange.app.promts.inputs.TextInput;
import com.exchange.app.utils.Formatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class SearchPair {
    public  CurrencyConversor search(String base, String target, TextInput input) throws
            NumberFormatException,
            IOException,
            URISyntaxException,
            InterruptedException {
        //verificamos que se pueda convertir a double para verificar el formato
        //el formato que recibe la api es un String xxxx.xxxxx
        String amount;
        Date date = new Date();
        do{
            amount = input.execute();
            if(!isNumber(amount)){
                System.out.println("La cantidad ingresada no es valida");
            }
        } while(!isNumber(amount));


        URI uri = new URI("https://v6.exchangerate-api.com/v6/8053037d78acfea6bd92dfdb/pair/" + base + "/" + target + "/" + amount);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        if(!response.body().contains("success")){
            throw new RuntimeException("Existe un problema con el servidor, si el problema persiste contacte con el administrador");
        }
        CurrencyConversorDto resultado = gson.fromJson(response.body(), CurrencyConversorDto.class);
        CurrencyConversor currencyConversor = new CurrencyConversor(amount, Formatter.formatDate(date), resultado);
        addToHistory(currencyConversor);
        client.close();
        return currencyConversor;
    }

    public  List<String> readHistory() throws IOException {
        File file = new File("/home/javier/IdeaProjects/exchange_console_app/src/main/db/history.json");
        Gson gson = new Gson();
        List<String> historyItems = new ArrayList<>();
        StringBuilder json = new StringBuilder();
        String cadena;
        if (file.exists()) {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((cadena = bufferedReader.readLine()) != null) {
                json.append(cadena.trim());
            }
            fileReader.close();
            String jsonString = json.toString();
            if (!jsonString.isEmpty()) {

                TypeToken<List<String>> collectionType = new TypeToken<List<String>>() {
                };
                historyItems = gson.fromJson(jsonString, collectionType);
            }
            return historyItems;
        }
        return historyItems;

    }

    private  void addToHistory(CurrencyConversor cc){
        try {
            List<String> history = readHistory();
            FileWriter FileWriter = new FileWriter("/home/javier/IdeaProjects/exchange_console_app/src/main/db/history.json");
            PrintWriter printWriter = new PrintWriter(FileWriter);
            Gson gson = new Gson();
            if( history.size() <= 9){
                history.add(cc.toString());
            } else {
                history.addFirst(cc.toString());
                history.removeLast();
            }

            printWriter.println(gson.toJson(history));
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Boolean isNumber(String value){
        for(int i = 0; i < value.length(); i++){
            if(!Character.isDigit(value.charAt(i))){
                return false;
            }
        }
        return true;
    }
}

