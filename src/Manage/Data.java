package Manage;
import javax.swing.*;

import java.io.*;
import java.util.Stack;

public class Data {
    String line;
    String fileFlavors = "src/File/flavors.txt";
    String filePrices = "src/File/prices.txt";
    String fileCocktails = "src/File/cocktails.txt";

    CustomLinkedList cocktailList;

    public Data() {
        cocktailList = new CustomLinkedList();
        try {
            File file = new File(fileCocktails);
            if (!file.exists()) file.createNewFile();
            BufferedReader readCocktails = new BufferedReader(new FileReader(fileCocktails));
            while ((line = readCocktails.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                try {
                    String[] p1 = line.split(" - ");
                    String ing = p1[0].trim();
                    String[] p2 = p1[1].split(" \\(");
                    String flav = p2[1].replace(")","").trim();
                    String[] p3 = p2[0].split(" ", 2);
                    String price = p3[0].trim();
                    String name = p3[1].trim();
                    cocktailList.add(new Cocktail(ing, price, name, flav));
                } catch (Exception ex) {}
            }
            readCocktails.close();
        } catch (IOException i) { i.printStackTrace(); }
    }

    public void addCocktails(String ingredient, String name, String flavor, String price) {
        try {
            BufferedWriter bwCocktails = new BufferedWriter(new FileWriter(fileCocktails, true));
            bwCocktails.write(ingredient + " - " + price + " " + name + " (" + flavor + ")");
            bwCocktails.newLine();
            bwCocktails.close();
        } catch (IOException i) { i.printStackTrace(); }
        cocktailList.add(new Cocktail(ingredient, price, name, flavor));
        JOptionPane.showMessageDialog(null, "Data Added Success!!\n>> " + ingredient + " - " + name + " " + price + " (" + flavor + ")");
    }

    public DefaultListModel listDataCocktails() {
        return cocktailList.toListModel();
    }

    public DefaultListModel listDataFlavors() {
        DefaultListModel<String> dataFlavors = new DefaultListModel<>();
        try {
            File f = new File(fileFlavors);
            if(!f.exists()) f.createNewFile();
            BufferedReader readFlavors = new BufferedReader(new FileReader(fileFlavors));
            while ((line = readFlavors.readLine()) != null) {
                if(!line.trim().isEmpty()) dataFlavors.addElement(line);
            }
            readFlavors.close();
        } catch (IOException i) { i.printStackTrace(); }
        return dataFlavors;
    }

    public DefaultListModel listDataPrices() {
        DefaultListModel<String> dataPrices = new DefaultListModel<>();
        try {
            File f = new File(filePrices);
            if(!f.exists()) f.createNewFile();
            BufferedReader readPrices = new BufferedReader(new FileReader(filePrices));
            while ((line = readPrices.readLine()) != null) {
                if(!line.trim().isEmpty()) dataPrices.addElement(line);
            }
            readPrices.close();
        } catch (IOException i) { i.printStackTrace(); }
        return dataPrices;
    }

    public void replaceDataCocktails(String oldData, String newIng, String newName, String newPrice, String newFlav) {
        String replace = newIng + " - " + newPrice + " " + newName + " (" + newFlav + ")";
        if (newPrice != null && newFlav != null) {
            String forReadData = "";
            try {
                BufferedReader readCocktails = new BufferedReader(new FileReader(fileCocktails));
                line = readCocktails.readLine();
                while (line != null) {
                    forReadData = forReadData + line + System.lineSeparator();
                    line = readCocktails.readLine();
                }
                String changeData = forReadData.replace(oldData, replace);
                BufferedWriter writeCocktails = new BufferedWriter(new FileWriter(fileCocktails));
                writeCocktails.write(changeData);
                writeCocktails.close();
                readCocktails.close();
                cocktailList.update(oldData, new Cocktail(newIng, newPrice, newName, newFlav));
            } catch (IOException i) { i.printStackTrace(); }
            JOptionPane.showMessageDialog(null, "Data Edit Successfully !!\n");
        } else {
            JOptionPane.showMessageDialog(null, "Edit Failed !! - Try Again");
        }
    }

    public void replaceDataFlavors(String oldData, String newReplace) {
        String forReadData = "", changeData;
        try {
            BufferedReader readFlavors = new BufferedReader(new FileReader(fileFlavors));
            line = readFlavors.readLine();
            while (line != null) {
                forReadData = forReadData + line + System.lineSeparator();
                line = readFlavors.readLine();
            }
            changeData = forReadData.replace(oldData, newReplace);
            BufferedWriter writeFlavors = new BufferedWriter(new FileWriter(fileFlavors));
            writeFlavors.write(changeData);
            writeFlavors.close();
            readFlavors.close();
        } catch (IOException i) { i.printStackTrace(); }
        JOptionPane.showMessageDialog(null, "Data Edit Successfully !!\n" + oldData + " -> " + newReplace);
    }

    public void replaceDataPrices(String oldData, String newReplace) {
        String forReadData = "", changeData;
        try {
            BufferedReader readPrices = new BufferedReader(new FileReader(filePrices));
            line = readPrices.readLine();
            while (line != null) {
                forReadData = forReadData + line + System.lineSeparator();
                line = readPrices.readLine();
            }
            changeData = forReadData.replace(oldData, newReplace);
            BufferedWriter writePrices = new BufferedWriter(new FileWriter(filePrices));
            writePrices.write(changeData);
            writePrices.close();
            readPrices.close();
        } catch (IOException i) { i.printStackTrace(); }
        JOptionPane.showMessageDialog(null, "Data Edit Successfully !!\n" + oldData + " -> " + newReplace);
    }

    public void delPrices(String selected) {
        String forReadData = "", changeData;
        try {
            BufferedReader readPrices = new BufferedReader(new FileReader(filePrices));
            line = readPrices.readLine();
            while (line != null) {
                forReadData = forReadData + line + System.lineSeparator();
                line = readPrices.readLine();
            }
            changeData = forReadData.replace(selected + System.lineSeparator(), "");
            changeData = changeData.replace(selected, "");
            BufferedWriter writePrices = new BufferedWriter(new FileWriter(filePrices));
            writePrices.write(changeData);
            writePrices.close();
            readPrices.close();
        } catch (IOException i) { i.printStackTrace(); }
        JOptionPane.showMessageDialog(null, "Delete [ Prices - " + selected + " ] Success !!");
    }

    public void delFlavors(String selected) {
        String forReadData = "", changeData;
        try {
            BufferedReader readFlavors = new BufferedReader(new FileReader(fileFlavors));
            line = readFlavors.readLine();
            while (line != null) {
                forReadData = forReadData + line + System.lineSeparator();
                line = readFlavors.readLine();
            }
            changeData = forReadData.replace(selected + System.lineSeparator(), "");
            changeData = changeData.replace(selected, "");
            BufferedWriter writeFlavors = new BufferedWriter(new FileWriter(fileFlavors));
            writeFlavors.write(changeData);
            writeFlavors.close();
            readFlavors.close();
        } catch (IOException i) { i.printStackTrace(); }
        JOptionPane.showMessageDialog(null, "Delete [ Flavors - " + selected + " ] Success !!");
    }

    public void delCocktails(String selected) {
    	deletedStack.push(selected);
        String forReadData = "", changeData;
        try {
            BufferedReader readCocktails = new BufferedReader(new FileReader(fileCocktails));
            line = readCocktails.readLine();
            while (line != null) {
                forReadData = forReadData + line + System.lineSeparator();
                line = readCocktails.readLine();
            }
            changeData = forReadData.replace(selected + System.lineSeparator(), "");
            changeData = changeData.replace(selected, "");
            BufferedWriter writeCocktails = new BufferedWriter(new FileWriter(fileCocktails));
            writeCocktails.write(changeData);
            writeCocktails.close();
            readCocktails.close();
            cocktailList.removeByString(selected);
        } catch (IOException i) { i.printStackTrace(); }
        JOptionPane.showMessageDialog(null, "Delete [ Cocktails - " + selected + " ] Success !!");
    }
    
    
    /*
     * Author: Yen-Chia Chiu
     * Feature: switch DefaultListModel to（String[]）
     */
    public String[] getCocktailsArray() {
        DefaultListModel<String> model = listDataCocktails();
        String[] a = new String[model.size()];
        for (int i = 0; i < model.size(); i++) {
            a[i] = model.getElementAt(i);
        }
        return a;
    }
    
    /*
     * Author: Yen-Chia Chiu
     * Feature: let "price" be a "double"
     */
    private double extractPrice(String record) {
        try {
            String[] p1 = record.split(" - ");
            if (p1.length < 2) 
            	return 0;
            
            String remain = p1[1].trim();
            String[] parts = remain.split(" ", 2);
            
            String price = parts[0].trim();
            price = price.replaceAll("[^0-9.]", "");
            
            return Double.parseDouble(price);
            
        } catch (Exception e) {
            return 0;
        }
    }
    
    /*
     * Author: Yen-Chia Chiu
     * Feature: use Merge Sort to sort the price
     */
    public void mergeSortByPrice(String[] tempArray, int first, int last) {
        if (first < last) {
        	int mid = first + (last - first) / 2; 
            mergeSortByPrice(tempArray, first, mid);
            mergeSortByPrice(tempArray, mid + 1, last);
            
            mergeByPrice(tempArray, first, mid, last);
        }
    }
    
    /*
     * Author: Yen-Chia Chiu
     * Feature: Combine two sorted data sets, and sorted data set by comparing prices.
     */
    private void mergeByPrice(String[] a, int first, int mid, int last) {
        int beginHalf1 = first;
        int endHalf1 = mid;
        int beginHalf2 = mid + 1;
        int endHalf2 = last;
        
        String[] tempArray = new String[a.length];
        int index = beginHalf1;
        while (beginHalf1 <= endHalf1 && beginHalf2 <= endHalf2) {
            if (extractPrice(a[beginHalf1]) <= extractPrice(a[beginHalf2])) {
                tempArray[index] = a[beginHalf1];
                beginHalf1++;
            } else {
                tempArray[index] = a[beginHalf2];
                beginHalf2++;
            }
            index++;
        }
        
        while (beginHalf1 <= endHalf1) {
            tempArray[index] = a[beginHalf1];
            beginHalf1++;
            index++;
        }
        
        while (beginHalf2 <= endHalf2) {
            tempArray[index] = a[beginHalf2];
            beginHalf2++;
            index++;
        }
        
        for (index = first; index <= last; index++) {
            a[index] = tempArray[index];
        }
    }
    
    /*
     * Author: Yen-Chia Chiu
     * Feature: Use Stack to track deletion 
     */
    private static Stack<String> deletedStack = new Stack<>();
    
    /*
     * Author: Yen-Chia Chiu
     * Feature: Return the last deleted item
     */
    public String peekLastDeleted() {
        if (deletedStack.isEmpty()) {
            return null;
        }
        return deletedStack.peek();
    }
}