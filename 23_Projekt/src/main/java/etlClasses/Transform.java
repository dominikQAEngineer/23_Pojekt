package etlClasses;

/**
 * Created by Dominik on 2017-01-11.
 *
 */

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import dbTables.ProduktTable;
import dbTables.OpiniaTable;



public class Transform {
	public ArrayList<OpiniaTable> transformOpinie(ArrayList<Element> elements, String productId) {
		ArrayList<OpiniaTable> reviews = new ArrayList<OpiniaTable>();
		int licznik=0;
		for (Element e : elements) {
			reviews.add(new OpiniaTable(e, productId));
			licznik++;
		}
		System.out.println("Transformacji uleglo:"+licznik+" opinii");
		return reviews;
	}

	public ProduktTable transformProduct(Element element, String productId) {
		ProduktTable product = new ProduktTable(element, productId);
		return product;
	}
	
	public OpiniaTable parseReviewValues(OpiniaTable review){
		review.setWadyProduktu(review.getWadyProduktu().replace("ą", "a"));
		review.setWadyProduktu(review.getWadyProduktu().replace("ć", "c"));
		review.setWadyProduktu(review.getWadyProduktu().replace("ę", "e"));
		review.setWadyProduktu(review.getWadyProduktu().replace("ń", "n"));
		review.setWadyProduktu(review.getWadyProduktu().replace("ż", "z"));
		review.setWadyProduktu(review.getWadyProduktu().replace("ź", "z"));
		review.setWadyProduktu(review.getWadyProduktu().replace("ó", "o"));
		review.setWadyProduktu(review.getWadyProduktu().replace("ś", "s"));
		review.setWadyProduktu(review.getWadyProduktu().replace("ł", "l"));

		review.setZaletyProduktu(review.getZaletyProduktu().replace("ą", "a"));
		review.setZaletyProduktu(review.getZaletyProduktu().replace("ć", "c"));
		review.setZaletyProduktu(review.getZaletyProduktu().replace("ę", "e"));
		review.setZaletyProduktu(review.getZaletyProduktu().replace("ń", "n"));
		review.setZaletyProduktu(review.getZaletyProduktu().replace("ż", "z"));
		review.setZaletyProduktu(review.getZaletyProduktu().replace("ź", "z"));
		review.setZaletyProduktu(review.getZaletyProduktu().replace("ó", "o"));
		review.setZaletyProduktu(review.getZaletyProduktu().replace("ś", "s"));
		review.setZaletyProduktu(review.getZaletyProduktu().replace("ł", "l"));

		review.setPodsumowanie(review.getPodsumowanie().replace("ą", "a"));
		review.setPodsumowanie(review.getPodsumowanie().replace("ć", "c"));
		review.setPodsumowanie(review.getPodsumowanie().replace("ę", "e"));
		review.setPodsumowanie(review.getPodsumowanie().replace("ń", "n"));
		review.setPodsumowanie(review.getPodsumowanie().replace("ż", "z"));
		review.setPodsumowanie(review.getPodsumowanie().replace("ź", "z"));
		review.setPodsumowanie(review.getPodsumowanie().replace("ó", "o"));
		review.setPodsumowanie(review.getPodsumowanie().replace("ś", "s"));
		review.setPodsumowanie(review.getPodsumowanie().replace("ł", "l"));

		review.setAutor(review.getAutor().replace("ą", "a"));
		review.setAutor(review.getAutor().replace("ć", "c"));
		review.setAutor(review.getAutor().replace("ę", "e"));
		review.setAutor(review.getAutor().replace("ń", "n"));
		review.setAutor(review.getAutor().replace("ż", "z"));
		review.setAutor(review.getAutor().replace("ź", "z"));
		review.setAutor(review.getAutor().replace("ó", "o"));
		review.setAutor(review.getAutor().replace("ś", "s"));
		review.setAutor(review.getAutor().replace("ł", "l"));

		review.setAutor(review.getAutor().replace("'", " "));
		review.setPodsumowanie(review.getPodsumowanie().replace("'", " "));
		review.setZaletyProduktu(review.getZaletyProduktu().replace("'", " "));
		review.setWadyProduktu(review.getWadyProduktu().replace("'", " "));
		
		return review;
	}
	
	public ProduktTable parseProductValues(ProduktTable product){

		product.setRodzajProduktu(product.getRodzajProduktu().replace("ą", "a"));
		product.setRodzajProduktu(product.getRodzajProduktu().replace("ć", "c"));
		product.setRodzajProduktu(product.getRodzajProduktu().replace("ę", "e"));
		product.setRodzajProduktu(product.getRodzajProduktu().replace("ń", "n"));
		product.setRodzajProduktu(product.getRodzajProduktu().replace("ż", "z"));
		product.setRodzajProduktu(product.getRodzajProduktu().replace("ź", "z"));
		product.setRodzajProduktu(product.getRodzajProduktu().replace("ó", "o"));
		product.setRodzajProduktu(product.getRodzajProduktu().replace("ś", "s"));
		product.setRodzajProduktu(product.getRodzajProduktu().replace("ł", "l"));

		product.setMarkaProduktu(product.getMarkaProduktu().replace("ą", "a"));
		product.setMarkaProduktu(product.getMarkaProduktu().replace("ć", "c"));
		product.setMarkaProduktu(product.getMarkaProduktu().replace("ę", "e"));
		product.setMarkaProduktu(product.getMarkaProduktu().replace("ń", "n"));
		product.setMarkaProduktu(product.getMarkaProduktu().replace("ż", "z"));
		product.setMarkaProduktu(product.getMarkaProduktu().replace("ź", "z"));
		product.setMarkaProduktu(product.getMarkaProduktu().replace("ó", "o"));
		product.setMarkaProduktu(product.getMarkaProduktu().replace("ś", "s"));
		product.setMarkaProduktu(product.getMarkaProduktu().replace("ł", "l"));

		product.setModelProduktu(product.getModelProduktu().replace("ą", "a"));
		product.setModelProduktu(product.getModelProduktu().replace("ć", "c"));
		product.setModelProduktu(product.getModelProduktu().replace("ę", "e"));
		product.setModelProduktu(product.getModelProduktu().replace("ń", "n"));
		product.setModelProduktu(product.getModelProduktu().replace("ż", "z"));
		product.setModelProduktu(product.getModelProduktu().replace("ź", "z"));
		product.setModelProduktu(product.getModelProduktu().replace("ó", "o"));
		product.setModelProduktu(product.getModelProduktu().replace("ś", "s"));
		product.setModelProduktu(product.getModelProduktu().replace("ł", "l"));

		product.setDodatkoweUwagi(product.getDodatkoweUwagi().replace("ą", "a"));
		product.setDodatkoweUwagi(product.getDodatkoweUwagi().replace("ć", "c"));
		product.setDodatkoweUwagi(product.getDodatkoweUwagi().replace("ę", "e"));
		product.setDodatkoweUwagi(product.getDodatkoweUwagi().replace("ń", "n"));
		product.setDodatkoweUwagi(product.getDodatkoweUwagi().replace("ż", "z"));
		product.setDodatkoweUwagi(product.getDodatkoweUwagi().replace("ź", "z"));
		product.setDodatkoweUwagi(product.getDodatkoweUwagi().replace("ó", "o"));
		product.setDodatkoweUwagi(product.getDodatkoweUwagi().replace("ś", "s"));
		product.setDodatkoweUwagi(product.getDodatkoweUwagi().replace("ł", "l"));

		product.setDodatkoweUwagi(product.getDodatkoweUwagi().replace("'", " "));
		product.setModelProduktu(product.getModelProduktu().replace("'", " "));
		product.setMarkaProduktu(product.getMarkaProduktu().replace("'", " "));
		product.setRodzajProduktu(product.getRodzajProduktu().replace("'", " "));
		return product;
	}
}
