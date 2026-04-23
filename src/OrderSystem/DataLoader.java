package OrderSystem;

import java.io.*;
import java.util.*;

public class DataLoader {

    public static List<Item> loadItems(String path) {

        List<Item> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(" - ");
                String base_liquor = parts[0];

                String right = parts[1];

                int dollarIndex = right.indexOf("$");
                int spaceAfterPrice = right.indexOf(" ", dollarIndex);

                double price = Double.parseDouble(
                        right.substring(dollarIndex + 1, spaceAfterPrice)
                );

                String namePart = right.substring(spaceAfterPrice + 1);

                int typeStart = namePart.indexOf("(");
                int typeEnd = namePart.indexOf(")");

                String name = namePart.substring(0, typeStart).trim();
                String flavor = namePart.substring(typeStart + 1, typeEnd);

                String fullDesc = "Flavor: " + flavor + "\n"
                		+ "Base liquor: " + base_liquor;

                Item item = new Item(name, price, fullDesc);

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}