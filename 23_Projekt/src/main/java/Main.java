
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dbTables.OpiniaTable;
import dbTables.ProduktTable;
import org.jsoup.nodes.Element;

import etlClasses.Extract;
import etlClasses.Load;
import etlClasses.Transform;

public class Main {
	public static void wyczyscEkran() throws InterruptedException, IOException {
		System.out.print("\f");
	}
	public static int menu(String productID) throws InterruptedException, IOException {

		int wybranaPozycja;
		Scanner input = new Scanner(System.in);

		wyczyscEkran();
		System.out.println("Wybrane ID: "+productID);
		System.out.println("Wybierz, co chcesz zrobic z danym produktem:");
		System.out.println("_____________________________________________\n");
		System.out.println("1) Pozyskaj dane ze zrodla (E)");
		System.out.println("2) Przeksztalc dane do postaci modelu danych (T)");
		System.out.println("3) Zaladuj dane do hurtowni (L)");
		System.out.println("4) Pelny proces ETL");
		System.out.println("5) Wyswietl opinie z bazy danych");
		System.out.println("6) Eksportuj opinie do pliku csv");
		System.out.println("7) Eksportuj wybrana opinie do pliku csv");
		System.out.println("8) Usun wszystkie opinie z bazy danych");
		System.out.println("9) Wybierz inne ID");
		System.out.println("10) Zakoncz program");
		System.out.println(" ");
		System.out.print("Podaj numer z menu: ");
		wybranaPozycja = input.nextInt();

		return wybranaPozycja;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Extract extract = new Extract();
		Transform transform = new Transform();
		Load load = new Load();

		String temp;
		final String tak = "t";
		ProduktTable prod = null;
		Element pobranyProdukt = null;
		ArrayList<Element> pobraneOpinie = null;
		ArrayList<OpiniaTable> przetransformowaneOpinie = null;

		Scanner in = new Scanner(System.in);
		System.out.print("Podaj numer produktu: ");

		String productID = in.nextLine();
		while(!productID.matches("[0-9]+") || extract.extractProduct(productID)==null) {
			System.out.println("Nie ma takiego produktu w bazie, podaj inny nr:");
			productID = in.nextLine();
		}

		String opiniaID = null;
		while(true){
			switch (menu(productID)) {
	        case 1:
				wyczyscEkran();
	        	System.out.println("Pobranie danych ze zrodla dla produktu: "+productID+"...");
	        	System.out.println("===========================================");
	        	pobraneOpinie = extract.extractReviews(productID);
				pobranyProdukt = extract.extractProduct(productID);
	        	System.out.print("Chcesz zobaczyc pobrane dane? ('t' jesli tak): ");
	        	temp = in.next();
	        	if(temp.equals(tak)){
					System.out.println(pobranyProdukt);
	        		System.out.println(pobraneOpinie);
	        		System.out.println("Nacisnij dowolny przycisk aby powrocic do Menu");
	        		System.in.read();
	        	}else{
	        		System.out.println("Powrot do menu...");
	        	}
	            break;
	        case 2:
				wyczyscEkran();
	        	System.out.println("Transformacja pobranych danych...");
	        	System.out.println("===========================================");
	        	if(pobraneOpinie == null || pobraneOpinie.size() == 0){
	        		System.out.println("Musisz pobrac opinie przed ich transformacja lub brak opinii dla danego przedmiotu");
	        		System.out.println("Nacisnij dowolny przycisk aby powrocic do Menu");
	        		System.in.read();
	        	}else{
	        		przetransformowaneOpinie = transform.transformOpinie(pobraneOpinie, productID);
					prod = transform.transformProduct(pobranyProdukt,productID);
	        		System.out.println("Dane zostaly transformowane");
	        		System.out.print("Chcesz zobaczyc transformowane dane? ('t' jesli tak): ");
	        		temp = in.next();
	        		if(temp.equals(tak)){
						System.out.println(prod);
	        			System.out.println(przetransformowaneOpinie);
	        			System.out.println("Nacisnij dowolny przycisk aby powrocic do Menu");
	        			System.in.read();
	        		}else{
	        			System.out.println("Powrot do menu...");
	        		}
	        	}
	            break;
	        case 3:
				wyczyscEkran();
				//load.wyczyscOpinieDlaID(productID);
	        	System.out.println("Zasilenie hurtowni danych opiniami po transformacji");
	        	System.out.println("===========================================");
	        	if(przetransformowaneOpinie == null || przetransformowaneOpinie.size() == 0){
	        		System.out.println("Aby załadować dane musisz je najpierw pobrać (E) a następnie transformować(T)");
	        		System.out.println("Nacisnij dowolny przycisk aby powrocic do Menu");
	        		System.in.read();
	        	}else{
	        		load.wstawProduktDoBazy(prod);
	        		load.wstawOpinieDoBazy(przetransformowaneOpinie);
	        		pobraneOpinie.clear();
		        	przetransformowaneOpinie.clear();
	        		System.out.println("Nacisnij dowolny przycisk aby powrocic do Menu");
	        		System.in.read();
	        	}
	            break;
			case 4:
				wyczyscEkran();
				System.out.println("Pobranie danych ze zrodla dla produktu: "+productID+"...");
				pobraneOpinie = extract.extractReviews(productID);
				pobranyProdukt = extract.extractProduct(productID);
				System.out.println("Dane zostaly pobrane!");
				System.out.println("Transformacja pobranych danych do odpowiedniego modelu...");
				prod = transform.transformProduct(pobranyProdukt,productID);
				przetransformowaneOpinie = transform.transformOpinie(pobraneOpinie, productID);
				System.out.println("Zrobione!");
				System.out.println("Zasilenie hurtowni danych...");
				//load.wyczyscOpinieDlaID(productID);
				load.wstawProduktDoBazy(prod);
				load.wstawOpinieDoBazy(przetransformowaneOpinie);
				System.out.println("Dane zostaly umieszczone w Hurtowni");
				//CZYSZCZENIE OPINII
				pobraneOpinie.clear();
				przetransformowaneOpinie.clear();

				System.out.println("\n");
				System.out.println("Nacisnij dowolny przycisk aby powrocic do Menu");
				System.in.read();
					break;
	        case 5:
				wyczyscEkran();
	        	System.out.println("Otrzymywanie wszystkich opinii z bazy danych dla produktu: "+productID);
	        	System.out.println("===========================================");
	        	load.pokazWszystkieOpinieDlaID(productID);
	        	System.out.println("Nacisnij dowolny przycisk aby powrocic do Menu");
	        	System.in.read();
	            break;
	        case 6:
				wyczyscEkran();
				System.out.println("Wgrywanie do pliku wszystkich opinii dla produktu nr: "+productID);
				System.out.println("===========================================");
				load.zapiszWszystkieOpinieDoPlikuDlaID(productID);
				System.out.println("Nacisnij dowolny przycisk aby powrocic do Menu");
				System.in.read();
				break;
			case 7:
				wyczyscEkran();
				System.out.print("Podaj numer Opinii, ktora chcesz eksportowac do pliku: ");
				opiniaID = in.nextLine();
				while(!opiniaID.matches("[0-9]+")) {
					System.out.println("Musisz podac nr:");
					opiniaID = in.nextLine();
				}
				System.out.println("Wgrywanie do pliku wybranej opinii dla produktu nr: "+productID);
				System.out.println("===========================================");
				load.zapiszJednaOpinieDoPlikuDlaID(productID,opiniaID);
				System.out.println("Nacisnij dowolny przycisk aby powrocic do Menu");
				System.in.read();
				break;
	        case 8:
				wyczyscEkran();
	        	System.out.println("Ta operacja trwale usunie wszystkie opinie dla danego produktu.");
	        	System.out.println("Czy na pewno chcesz to zrobic? ('t' jesli tak): ");
	        	temp = in.next();
        		if(temp.equals(tak)){
        			System.out.println("===========================================");
        			System.out.println("Usuwanie wszystkich opinii z bazy danych");
	        		load.wyczyscOpinie();
	        		System.out.println("Zrobione!");
	        	}
	        	System.out.println("Nacisnij dowolny przycisk aby powrocic do Menu");
	        	System.in.read();
	            break;
	        case 9:
				wyczyscEkran();
	    		System.out.print("Podaj numer produktu: ");
	        	productID = in.nextLine();
				while(!productID.matches("[0-9]+") || extract.extractProduct(productID)==null) {
					System.out.println("Nie ma takiego produktu w bazie, podaj inny nr:");
					productID = in.nextLine();
				}
	        	if(pobraneOpinie != null){
	        		pobraneOpinie.clear();
	        	}
	        	if(przetransformowaneOpinie != null){
	        		przetransformowaneOpinie.clear();
	        	}
	            break;
	        case 10:
				System.exit(1);
	            break;
	        default:
				wyczyscEkran();
	        	System.out.println("Nie znaleziono takiego produktu, powrot do Menu");
        		System.out.println("Nacisnij dowolny przycisk aby kontynuowac...");
	        	System.in.read();
			}
		}
	}
}
