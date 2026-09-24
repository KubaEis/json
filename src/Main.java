import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Path cesta = Path.of("data", "pizzeria.json");
        String obsah = Files.readString(cesta);

        JSONObject root = new JSONObject(obsah);
        JSONArray pizzy = root.getJSONArray("pizzy");

        List<JSONObject> list = new ArrayList<>();
        for(int i = 0; i < pizzy.length(); i++) {
            list.add(pizzy.getJSONObject(i));
        }


        boolean beh = true;
        while (beh) {
            System.out.println("\n-------MENU-------");
            System.out.println("1. jidelni listek");
            System.out.println("2. nejlevnejsi pizza");
            System.out.println("3. nejdrazsi pizza");
            System.out.println("4. prumer cena");
            System.out.println("5. pizzy v cenovem rozmezi");
            System.out.println("6. pizzy s vybranou ingredienci");
            System.out.println("7. pizzy od nejlevnejsi po nejdrazsi");
            System.out.println("8. konec");
            System.out.print("Vase volba: ");

            int vyber = sc.nextInt();
            sc.nextLine();

            switch (vyber) {
                case 1:
                    for (int i = 0; i < pizzy.length(); i++) {
                        JSONObject pizza = pizzy.getJSONObject(i);
                        System.out.println(pizza.getString("nazev") + " – " + pizza.getInt("cena") + " Kč");
                    }
                    break;
                case 2:
                    String nejlevnejsiJmeno = "";
                    int nejlC = Integer.MAX_VALUE;
                    for (int i = 0; i < pizzy.length(); i++) {
                        JSONObject pizza = pizzy.getJSONObject(i);
                        if (pizza.getInt("cena") < nejlC) {
                            nejlC = pizza.getInt("cena");
                            nejlevnejsiJmeno = pizza.getString("nazev");
                        }
                    }
                    System.out.println("Nejlevnejsi pizza: " + nejlevnejsiJmeno + " cena: " + nejlC + " Kč");
                    break;
                case 3:
                    String nejdrazsiJmeno = "";
                    int nejdC = 0;
                    for (int i = 0; i < pizzy.length(); i++) {
                        JSONObject pizza = pizzy.getJSONObject(i);
                        if (pizza.getInt("cena") > nejdC) {
                            nejdC = pizza.getInt("cena");
                            nejdrazsiJmeno = pizza.getString("nazev");
                        }
                    }
                    System.out.println("Nejdrazsi pizza: " + nejdrazsiJmeno + " cena: " + nejdC + " Kč");
                    break;
                case 4:
                    int soucet = 0;
                    for (int i = 0; i < pizzy.length(); i++) {
                        soucet += pizzy.getJSONObject(i).getInt("cena");
                    }
                    double prumer = (double) soucet / pizzy.length();
                    System.out.printf("Prumer cen: "+ prumer);
                    break;
                case 5:
                    System.out.println("Nizsi cena: ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Vyssi cena: ");
                    int v = sc.nextInt();
                    sc.nextLine();
                    if (n>v){
                        int i = n;
                        n = v;
                        v = i;
                    }
                    for (int i = 0; i < pizzy.length(); i++) {
                        JSONObject pizza = pizzy.getJSONObject(i);
                        if (pizza.getInt("cena") > n && pizza.getInt("cena") < v) {
                            int cena = pizza.getInt("cena");
                            String jmeno = pizza.getString("nazev");
                            System.out.println(jmeno + " cena: " + cena + " Kč");
                        }
                    }
                    break;
                case 6:
                    System.out.println("Ingredience: ");
                    String ingredience = sc.nextLine();
                    for (int i = 0; i < pizzy.length(); i++) {
                        JSONObject pizza = pizzy.getJSONObject(i);
                        if (pizza.get("ingredience").toString().contains(ingredience)) {
                            int cena = pizza.getInt("cena");
                            String jmeno = pizza.getString("nazev");
                            System.out.println(jmeno + " cena: " + cena + " Kč");
                        }
                    }
                    break;
                case 7:

                    String jmeno = "";
                    int cena = Integer.MAX_VALUE;
                    for(int j = 0; j < list.size(); j++) {
                        JSONObject pizza = list.get(j);
                        for (int i = 0; i < list.size(); i++) {
                            if (pizza.getInt("cena") < cena) {
                                    nejlC = pizza.getInt("cena");
                                    nejlevnejsiJmeno = pizza.getString("nazev");
                            }
                        }
                        System.out.println(pizza.getString("nazev") + " cena: " + pizza.getInt("cena") + " Kč");
                        list.remove(j);
                    }
                    /*
                    String jmeno = "";
                    int cena = Integer.MAX_VALUE;
                    for (int i = 0; i < pizzy.length(); i++) {
                        JSONObject pizza = pizzy.getJSONObject(i);
                        if (pizza.getInt("cena") < cena) {
                            cena = pizza.getInt("cena");
                            jmeno = pizza.getString("nazev");
                        }
                    }
                    System.out.println(jmeno + " cena: " + cena + " Kč");
                    int c = Integer.MAX_VALUE;
                    for(int i = 0; i < pizzy.length(); i++){
                        for (int j = 0; j < pizzy.length(); j++) {
                            JSONObject pizza = pizzy.getJSONObject(i);
                            if (pizza.getInt("cena") > cena && pizza.getInt("cena") < c) {
                                c = pizza.getInt("cena");
                                jmeno = pizza.getString("nazev");
                            }
                        }
                        cena = c;
                        System.out.println(jmeno + " cena: " + cena + " Kč");
                    }*/
                    break;
                default:
                    System.out.println("Neplatna volba, zkuste to znovu.");
                    break;
            }
        }
    }
}