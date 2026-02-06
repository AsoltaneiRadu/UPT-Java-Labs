package org.example;

import com.google.gson.*;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Map<Integer, Produs> map = incarcaProduse("src/main/resources/produse.json");
        map.forEach((id, p) -> System.out.println(id + "->" + p));
    }
    public static Map<Integer, Produs> incarcaProduse(String file) {
        Map<Integer, Produs> map = new HashMap<>();
        Gson gson = new Gson();
        try {
            JsonObject root = JsonParser.parseReader(new FileReader(file)).getAsJsonObject();
            JsonArray produse = root.getAsJsonArray("produse");
            for (JsonElement e : produse) {
                JsonObject obj = e.getAsJsonObject();
                int id = obj.get("id").getAsInt();
                String tip = obj.get("tip").getAsString();
                String nume = obj.get("nume").getAsString();
                double pret = obj.get("pret").getAsDouble();
                Produs produs;
                if (tip.equalsIgnoreCase("carte")) {
                    String autor = obj.get("autor").getAsString();
                    int anAparitie = obj.get("anAparitie").getAsInt();
                    produs = new Carte(nume, pret, autor, anAparitie);
                } else if (tip.equalsIgnoreCase("joc")) {
                    String platforma = obj.get("platforma").getAsString();
                    String gen = obj.get("gen").getAsString();
                    produs = new Joc(nume, pret, Console.valueOf(platforma), gen);

                } else {
                    continue;
                }
                map.put(id, produs);

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
        return map;
    }
}