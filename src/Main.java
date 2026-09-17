import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

        boolean beh = true;
        while (beh) {
            System.out.println("\n-------MENU-------");
            System.out.println("1. jidelni listek");
            System.out.println("2. nejlevnejsi pizza");
            System.out.println("3. nejdrazsi pizza");
            System.out.println("4. prumer cena");
            System.out.println("5. konec");
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
                    System.out.printf("Prumer cen: %.2f Kč%n", prumer);
                    break;
                case 5:
                    beh = false;
                    System.out.println("Konec programu.");
                    break;
                default:
                    System.out.println("Neplatna volba, zkuste to znovu.");
                    break;
            }
        }
    }
}