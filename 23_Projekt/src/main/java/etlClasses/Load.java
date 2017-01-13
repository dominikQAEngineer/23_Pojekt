package etlClasses;

/**
 * Created by Dominik on 2017-01-05.
 *
 */

import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import CSVClasses.CSVUtils;
import dbTables.ProduktTable;
import dbTables.OpiniaTable;

public class Load {
	private String url = "jdbc:mysql://localhost:3306/opinieceneodb?characterEncoding=utf8";
	private String user = "root";
	private String pass = "";

	private Statement statement = null;	
	private Transform transform = new Transform();
	public void wstawProduktDoBazy(ProduktTable product){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url,user,pass);
			String sql;
			statement = con.createStatement();
			product = transform.parseProductValues(product);

			sql = "INSERT IGNORE INTO produkt_table VALUES ('" + product.getIdProduktu()+
					"','"+ product.getRodzajProduktu() +
					"','"+ product.getMarkaProduktu() +
					"','"+ product.getModelProduktu() +
					"','"+ product.getDodatkoweUwagi() +"' )";
			statement.executeUpdate(sql);
			System.out.println("Produkt wstawiony do Bazy danych (INSERT IGNORE)");
			con.close();
		}catch(Exception e){
			System.out.println("ERROR: Cannot insert into DB - Error:"+e);
		}
	}
	public void wstawOpinieDoBazy(ArrayList<OpiniaTable> reviews ){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url,user,pass);
			String sql;

			int counter = 0;
			for (OpiniaTable review : reviews) {
				counter++;
				review = transform.parseReviewValues(review);
				statement = con.createStatement();

				sql = "INSERT IGNORE INTO opinia_table VALUES ('" + review.getIdOpinii() +
						"','"+ review.getWadyProduktu() +
						"','"+ review.getZaletyProduktu() +
						"','"+ review.getPodsumowanie() +
						"','"+ review.getIloscGwiazdek() +
						"','"+ review.getAutor() +
						"','"+ review.getDataWystawienia() +
						"','"+ review.getProductID() +
						"','"+ review.getRekomendacja() +
						"','"+ review.getOpiniaPrzydatna() +
						"','"+ review.getOpiniaNieprzydatna() +
						"')";
				statement.executeUpdate(sql);
			}
			System.out.println("Opinie wstawione do Bazy Danych (INSERT IGNORE");
			System.out.println("Wstawione wiersze: "+ counter);
			con.close();
		}catch(Exception e){
			System.out.println("ERROR: Cannot insert into DB - Error:"+e);
		}
	}
	public void pokazWszystkieOpinieDlaID(String productID){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url,user,pass);
			
			System.out.println("Database is connected !");
			
			statement = con.createStatement();

			ResultSet rs = statement.executeQuery("SELECT * FROM opinia_table WHERE ID_PRODUKTU="+productID);
			
			while (rs.next()) {
				int idProduktu = rs.getInt("ID_PRODUKTU");
				int idOpinii = rs.getInt("ID_OPINII");
				String wady = rs.getString("WADY_PRODUKTU");
				String zalety = rs.getString("ZALETY_PRODUKTU");
				String podsumowanie = rs.getString("PODSUMOWANIE");
				String gwiazdki = rs.getString("OCENA");
				String autor = rs.getString("AUTOR");
				String dataWystawienia = rs.getString("DATA_WYSTAWIENIA");
				String rekomendacja = rs.getString("REKOMENDACJA");
				int opiniaPrzydatna = rs.getInt("OPINIA_PRZYDATNA");
				int opiniaNieprzydatna = rs.getInt("OPINIA_NIEPRZYDATNA");
				
	            System.out.println("\nOpiniaTable ["+idOpinii + "][" + idProduktu + "][" + wady + "][" + zalety +
	            		"][" + podsumowanie + "][" + gwiazdki + "][" + autor + "][" + dataWystawienia + "][" +
						rekomendacja +"]["+opiniaPrzydatna+"]["+opiniaNieprzydatna+"]\n");
	        }
			con.close();
			
		}catch(Exception e){
			System.out.println("ERROR: Cannot connect to DB - Error:"+e);
		}
	}

	public void zapiszWszystkieOpinieDoPlikuDlaID(String productID){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url,user,pass);
			System.out.println("Database is connected !");
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM produkt_table WHERE ID_PRODUKTU="+productID);
			rs.next();

			String csvFile = "./PlikiTekstowe/produktNr"+productID+"WszystkieOpinie.csv";
			FileWriter writer = new FileWriter(csvFile);

			CSVUtils.writeLine(writer, Arrays.asList(
					"\nId Produktu:"+productID,
					"\nRodzaj Produktu:"+rs.getString("RODZAJ_PRODUKTU"),
					"\nMarka Produktu:"+rs.getString("MARKA_PRODUKTU"),
					"\nModel Produktu:"+rs.getString("MODEL_PRODUKTU"),
					"\nDodatkowe uwagi:"+rs.getString("DODATKOWE_UWAGI")
					));

			writer.flush();

			rs = statement.executeQuery("SELECT * FROM opinia_table WHERE ID_PRODUKTU="+productID);

			while (rs.next()) {
				CSVUtils.writeLine(writer, Arrays.asList(
						"\nId Opinii: "+rs.getInt("ID_OPINII"),
						"\nWady produktu: "+rs.getString("WADY_PRODUKTU"),
						"\nZalety produktu: "+rs.getString("ZALETY_PRODUKTU"),
						"\nPodsumowanie: "+rs.getString("PODSUMOWANIE"),
						"\nOcena: "+rs.getString("OCENA"),
						"\nAutor: "+rs.getString("AUTOR"),
						"\nData Wystawienia: "+rs.getString("DATA_WYSTAWIENIA"),
						"\nRekomendacja: "+rs.getString("REKOMENDACJA"),
						"\nOpinia przydatna: "+rs.getInt("OPINIA_PRZYDATNA"),
						"\nOpinia nieprzydatna: "+rs.getInt("OPINIA_NIEPRZYDATNA")));
			}
			con.close();

			writer.flush();
			writer.close();

			System.out.println("Dane zostaly zapisane do pliku: ./PlikiTekstowe/produktNr"+productID+"WszystkieOpinie.csv");

		}catch(Exception e){
			System.out.println("ERROR: Cannot connect to DB - Error:"+e);
		}
	}

	public void zapiszJednaOpinieDoPlikuDlaID(String productID, String opiniaID){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url,user,pass);
			System.out.println("Database is connected !");
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM produkt_table WHERE ID_PRODUKTU="+productID);
			rs.next();

			String csvFile = "./PlikiTekstowe/produktNr"+productID+"OpiniaNr"+opiniaID+".csv";
			FileWriter writer = new FileWriter(csvFile);
			CSVUtils.writeLine(writer, Arrays.asList(
					"\nId Produktu:"+String.valueOf(productID),
					"\nRodzaj Produktu:"+rs.getString("RODZAJ_PRODUKTU"),
					"\nMarka Produktu:"+rs.getString("MARKA_PRODUKTU"),
					"\nModel Produktu:"+rs.getString("MODEL_PRODUKTU"),
					"\nDodatkowe uwagi:"+rs.getString("DODATKOWE_UWAGI")
			));

			writer.flush();
			rs = statement.executeQuery("SELECT * FROM opinia_table WHERE ID_PRODUKTU="+productID+" AND ID_OPINII="+opiniaID);

			rs.next();
				CSVUtils.writeLine(writer, Arrays.asList(
						"\nId Opinii: "+rs.getInt("ID_OPINII"),
						"\nWady produktu: "+rs.getString("WADY_PRODUKTU"),
						"\nZalety produktu: "+rs.getString("ZALETY_PRODUKTU"),
						"\nPodsumowanie: "+rs.getString("PODSUMOWANIE"),
						"\nOcena: "+rs.getString("OCENA"),
						"\nAutor: "+rs.getString("AUTOR"),
						"\nData Wystawienia: "+rs.getString("DATA_WYSTAWIENIA"),
						"\nRekomendacja: "+rs.getString("REKOMENDACJA"),
						"\nOpinia przydatna: "+rs.getInt("OPINIA_PRZYDATNA"),
						"\nOpinia nieprzydatna: "+rs.getInt("OPINIA_NIEPRZYDATNA")));
			con.close();

			writer.flush();
			writer.close();

			System.out.println("Dane zostaly zapisane do pliku: ./PlikiTekstowe/produktNr"+productID+"OpiniaNr"+opiniaID+".csv");

		}catch(Exception e){
			System.out.println("ERROR: Cannot connect to DB - Error:"+e);
		}
	}

	public void wyczyscOpinie(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url,user,pass);
			String sql;
			statement = con.createStatement();
			
			sql = "TRUNCATE opinia_table";
			statement.execute(sql);
			
			System.out.println("Wszystkie opinie usuniete!");
			con.close();
		}catch(Exception e){
			System.out.println("ERROR: Cannot remove remove records from DB - Error:"+e);
		}
	}
	public void wyczyscOpinieDlaID(String idProduktu){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url,user,pass);
			String sql;
			statement = con.createStatement();
			sql = "DELETE FROM opinia_table WHERE ID_PRODUKTU="+idProduktu;
			statement.execute(sql);
			con.close();
		}catch(Exception e){
			System.out.println("ERROR: Cannot remove remove records from DB - Error:"+e);
		}
	}
}
