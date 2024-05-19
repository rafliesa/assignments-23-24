package assignments.assignment3;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Restaurant {
    private String nama;
    private ArrayList<Menu> menu;

    public Restaurant(String nama) {
        this.nama = nama;
        this.menu = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public void addMenu(Menu newMenu) {
        menu.add(newMenu);
    }

    public ArrayList<Menu> getMenu() {
        return menu;
    }

    private ArrayList<Menu> sortMenu() {
        Menu[] menuArr = new Menu[menu.size()];
        for (int i = 0; i < menu.size(); i++) {
            menuArr[i] = menu.get(i);
        }
        int n = menuArr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (menuArr[j].getHarga() > menuArr[j + 1].getHarga()) {

                    Menu temp = menuArr[j];
                    menuArr[j] = menuArr[j + 1];
                    menuArr[j + 1] = temp;
                }
            }
        }
        return new ArrayList<>(Arrays.asList(menuArr));
    }

    public String printMenu() {
        StringBuilder menuString = new StringBuilder("Menu:\n");
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('\u0000');
        decimalFormat.setDecimalFormatSymbols(symbols);
        int menuNumber = 1;
        for (Menu menuItem : sortMenu()) {
            menuString.append(menuNumber).append(". ").append(menuItem.getNamaMakanan()).append(" ")
                    .append(decimalFormat.format(menuItem.getHarga())).append("\n");
            menuNumber++;
        }
        if (menuString.length() > 0) {
            menuString.deleteCharAt(menuString.length() - 1);
        }
        return menuString.toString();
    }

    public boolean menuExist(String namaMakanan){
        for (Menu makanan : menu) {
            if (makanan.getNamaMakanan().equalsIgnoreCase(namaMakanan)) {
                return true;
            }
        }
        return false;
    }

    public String[] getMenuName(){
        ArrayList<String> menuName = new ArrayList<String>();
        for (Menu namaMakanan : menu) {
            menuName.add(namaMakanan.getNamaMakanan());
        }
        return menuName.toArray(new String[0]);
    }

    public Menu getMenuByName(String name) {
        Optional<Menu> menuMatched = menu.stream()
                .filter(menu -> menu.getNamaMakanan().equalsIgnoreCase(name)).findFirst();
        if (menuMatched.isPresent()) {
            return menuMatched.get();
        }
        return null;
    }

}
