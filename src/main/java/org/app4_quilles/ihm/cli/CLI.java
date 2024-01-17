package org.app4_quilles.ihm.cli;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

import org.app4_quilles.ihm.cli.menu.MenuOption;

public class CLI {

    CLI(InputStream scanner) throws Exception {
        throw new Exception();
    }
    public int getInputInt(String promptMsg, int min, int max) {
        return 0;
    }
    public int getInputInt(String promptMsg) {
        return 0;
    }
    public String getInputString(String promptMsg, Function<String, Boolean> validityFunction) {
        return "";
    }
    public String getInputString(String promptMsg) {
        return "";    
    }
    public int showMenu(String title, ArrayList<MenuOption> options, int page) {
        return 0;
    }
    public int showMenu(String title, ArrayList<MenuOption> options) {
        return 0;
    }
    public static void pressEnterToConfirm(String promptMsg) {
        
    }
    public void pressEnterToConfirm() {
    }
}






//     /**
//      * tests
//      * @param args
//      */
//     public  void main(String[] args) {
//         int v = getInputInt("test");
//         System.out.println("=> " + v);

//         int w = getInputInt("test", -2, 2);
//         System.out.println("=> " + w);

//         String s = getInputString("test str");
//         System.out.println("=> " + s);

//         String s2 = getInputString("test str length of 3", (str) -> {return str.length() >= 3;});
//         System.out.println("=> " + s2);

//         pressEnterToConfirm("back");

//         try {
//             showMenu(null, null);
//         } catch (Exception e) {
//             // it should throw if no option is given (not for a null title)
//             int result = showMenu("yes or no ?", new ArrayList<>(Arrays.asList(
//                 new MenuOption("yes", () -> {
//                     System.out.println("it's a yes.");
//                 }),
//                 new MenuOption("no", () -> {
//                     System.out.println("it's a no.");
//                 })
//             )));
//             System.out.println("it was " + result);

//             ArrayList<MenuOption> options = new ArrayList<>();
//             for (int i = 0; i < 70; i++) {
//                 final int index = i;
//                 options.add(new MenuOption("do " + i, () -> {
//                     System.out.println("it is " + index);
//                 }));
//             }
//             int result2 = showMenu("a lot of stuff", options);
//             System.out.println(result2 + ", it was.");
//         }
//     }
// }