<<<<<<< HEAD
package murach.data;

import java.io.*;
import murach.business.User;

public class UserIO {

    public static boolean add(User user, String filepath) {
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(
                new FileWriter(filepath, true)))) {
            out.println(user.getEmail() + "|"
                    + user.getFirstName() + "|"
                    + user.getLastName());
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public static User getUser(String email, String filepath) {
        try (BufferedReader in = new BufferedReader(
                new FileReader(filepath))) {
            String line = in.readLine();
            while (line != null) {
                String[] columns = line.split("\\|");
                if (columns.length >= 3 && email.equalsIgnoreCase(columns[0])) {
                    User user = new User();
                    user.setEmail(columns[0]);
                    user.setFirstName(columns[1]);
                    user.setLastName(columns[2]);
                    return user;
                }
                line = in.readLine();
            }
            return null;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
=======
package murach.data;

import java.io.*;
import murach.business.User;

public class UserIO {

    public static boolean add(User user, String filepath) {
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(
                new FileWriter(filepath, true)))) {
            out.println(user.getEmail() + "|"
                    + user.getFirstName() + "|"
                    + user.getLastName());
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public static User getUser(String email, String filepath) {
        try (BufferedReader in = new BufferedReader(
                new FileReader(filepath))) {
            String line = in.readLine();
            while (line != null) {
                String[] columns = line.split("\\|");
                if (columns.length >= 3 && email.equalsIgnoreCase(columns[0])) {
                    User user = new User();
                    user.setEmail(columns[0]);
                    user.setFirstName(columns[1]);
                    user.setLastName(columns[2]);
                    return user;
                }
                line = in.readLine();
            }
            return null;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
>>>>>>> b1afbd999bd42958f471e97f7700f611520e5d6a
}