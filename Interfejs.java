
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Interfejs {

    static Scanner inputScanner = new Scanner(System.in);

    static KotDAO kotDao = new KotDAO();

    public static void main(String[] args) {
        String userChoice;
        do {
            System.out.println();
            System.out.println("Wybierz, co chcesz zrobić, a następnie zatwierdź enterem:");
            System.out.println("[1] Dodaj nowego kota");
            System.out.println("[2] Pokaż wszystkie koty");
            System.out.println("[x] Zakończ");
            userChoice = getUserInput();
            if (userChoice.equals("1")) {
                addCats();
            } else if (userChoice.equals("2")) {
                showCats();
            }
        } while (!userChoice.equalsIgnoreCase("x"));
    }

    public static void showCats(){
        System.out.println("!--- LISTA KOTÓW ---!");

        Kot kot;
        for (int i=0; i<kotDao.getCats().size(); i++) {
            kot = kotDao.getCats().get(i);
            System.out.println(i + ": " + kot.getName());
        }
        System.out.println();
        Pattern numberPattern = Pattern.compile("[0-9]+");
        String loadNumber;
        do {
            System.out.print("Którego kota chcesz poznać bliżej? ");
            loadNumber = getUserInput();
        } while (!numberPattern.matcher(loadNumber).matches());

        Integer catNumber = Integer.parseInt(loadNumber);

        if (kotDao.getCats().size()>catNumber) {
            Kot wybranyKot = kotDao.getCats().get(catNumber);
            System.out.println("Wybrany kot ma na imie "+wybranyKot.getName()+", waży "+wybranyKot.getWeight()+", urodził się "+wybranyKot.getDateOfBirth().toString()+", a opiekuje się nim "+wybranyKot.getGuardianName());
        } else {
            System.out.println("Niestety, nie znalazłem kota o wybranym numerze :( Sprobój ponownie lub go dodaj!");
        }
    }

    public static void addCats() {
        System.out.println("!---DODAJ KOTA---!");
        Kot kot = new Kot();

        System.out.println("Podaj imię kota ");
        kot.setName(getUserInput());

        System.out.print("Podaj, imie opiekuna kota: ");
        kot.setGuardianName(getUserInput());

        Pattern datePattern = Pattern.compile("[0-9]{4}.[0-1]?[0-9].[0-3]?[0-9]");
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd");
        String dateOfBirthload;
        do{
            System.out.print("Podaj datę urodzenia kota w formacie RRRR.MM.DD: ");
            dateOfBirthload = getUserInput();
            if(datePattern.matcher(dateOfBirthload).matches()){
                try {
                    kot.setDateOfBirth(date.parse(dateOfBirthload));
                } catch (ParseException pe) {
                    System.out.println("Coś jest nie tak z formatem daty! Przykładowa data: 2021.03.30");
                }
            }
        }while (kot.getDateOfBirth() == null);

        Pattern weightPattern = Pattern.compile("[0-9]+(\\.[0-9]+)?");
        String weightload;
        do {
            System.out.print("Podaj wagę kota: ");
            weightload =getUserInput();
            if (weightPattern.matcher(weightload).matches()) {
                kot.setWeight(Float.valueOf(weightload));
            }
        } while (kot.getWeight() == null);

        kotDao.addCat(kot);

        System.out.println("Dziękuję, teraz już wiem prawie wszystko o kocie!");
    }

    public static String getUserInput() {
        return inputScanner.nextLine().trim();
    }
}
