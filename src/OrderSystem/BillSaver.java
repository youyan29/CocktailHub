package OrderSystem;
import java.io.*;
import java.util.HashMap;

public class BillSaver {

    private static int billCounter = loadLastBillNumber();

    public static void save(HashMap<String, Item> order) {

        try {
            File dir = new File("src/File");
            if (!dir.exists()) dir.mkdirs();

            File file = new File(dir, "Bill_Record.txt");

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.write("===== BILL #" + billCounter + " =====\n");

            double subtotal = 0;

            for (Item item : order.values()) {
                double price = item.price * item.quantity;
                subtotal += price;

                writer.write(item.name + " x" + item.quantity + " $" + price + "\n");
            }

            double tax = subtotal * 0.06;
            double total = subtotal + tax;

            writer.write("Tax: $" + String.format("%.2f", tax) + "\n");
            writer.write("Total: $" + String.format("%.2f", total) + "\n\n");

            writer.close();

            billCounter++;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static int loadLastBillNumber() {
        File file = new File("src/File/Bill_Record.txt");

        if (!file.exists()) return 1;

        int lastNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("===== BILL #")) {
                    try {
                        String num = line.replaceAll("[^0-9]", "");
                        lastNumber = Integer.parseInt(num);
                    } catch (NumberFormatException e) {

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }

        return lastNumber + 1;
    }
}