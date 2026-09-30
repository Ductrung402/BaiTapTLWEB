<<<<<<< HEAD
package murach.data;

import java.io.*;
import java.util.*;
import murach.business.Product;

public class ProductIO {

    public static Product getProduct(String productCode, String filepath) {
        try (BufferedReader in = new BufferedReader(new FileReader(filepath))) {
            String line = in.readLine();
            while (line != null) {
                String[] columns = line.split("\\|");
                if (columns.length >= 3 && productCode.equalsIgnoreCase(columns[0])) {
                    Product product = new Product();
                    product.setCode(columns[0]);
                    product.setDescription(columns[1]);
                    product.setPrice(Double.parseDouble(columns[2]));
                    return product;
                }
                line = in.readLine();
            }
            return null;
        } catch (IOException e) {
            System.err.println("Error reading products: " + e.getMessage());
            return null;
        }
    }

    public static ArrayList<Product> getProducts(String filepath) {
        ArrayList<Product> products = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filepath))) {
            String line = in.readLine();
            while (line != null) {
                String[] columns = line.split("\\|");
                if (columns.length >= 3) {
                    Product product = new Product();
                    product.setCode(columns[0]);
                    product.setDescription(columns[1]);
                    product.setPrice(Double.parseDouble(columns[2]));
                    products.add(product);
                }
                line = in.readLine();
            }
            return products;
        } catch (IOException e) {
            System.err.println("Error reading products list: " + e.getMessage());
            return null;
        }
    }
=======
package murach.data;

import java.io.*;
import java.util.*;
import murach.business.Product;

public class ProductIO {

    public static Product getProduct(String productCode, String filepath) {
        try (BufferedReader in = new BufferedReader(new FileReader(filepath))) {
            String line = in.readLine();
            while (line != null) {
                String[] columns = line.split("\\|");
                if (columns.length >= 3 && productCode.equalsIgnoreCase(columns[0])) {
                    Product product = new Product();
                    product.setCode(columns[0]);
                    product.setDescription(columns[1]);
                    product.setPrice(Double.parseDouble(columns[2]));
                    return product;
                }
                line = in.readLine();
            }
            return null;
        } catch (IOException e) {
            System.err.println("Error reading products: " + e.getMessage());
            return null;
        }
    }

    public static ArrayList<Product> getProducts(String filepath) {
        ArrayList<Product> products = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filepath))) {
            String line = in.readLine();
            while (line != null) {
                String[] columns = line.split("\\|");
                if (columns.length >= 3) {
                    Product product = new Product();
                    product.setCode(columns[0]);
                    product.setDescription(columns[1]);
                    product.setPrice(Double.parseDouble(columns[2]));
                    products.add(product);
                }
                line = in.readLine();
            }
            return products;
        } catch (IOException e) {
            System.err.println("Error reading products list: " + e.getMessage());
            return null;
        }
    }
>>>>>>> b1afbd999bd42958f471e97f7700f611520e5d6a
}