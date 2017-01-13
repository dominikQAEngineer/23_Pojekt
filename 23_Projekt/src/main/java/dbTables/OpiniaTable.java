package dbTables;

/**
 * Created by Aleksandra on 2017-01-01.
 *
 */

import org.jsoup.nodes.Element;

public class OpiniaTable {

	private int idProduktu;
	private int idOpinii;
	private String zaletyProduktu;
	private String wadyProduktu;
	private String podsumowanie;
	private String iloscGwiazdek;
	private String autor;
	private String dataWystawienia;
	private String rekomendacja;
	private int opiniaPrzydatna;
	private int opiniaNieprzydatna;

	public int getProductID() {
		return this.idProduktu;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n")
				.append("id produktu=").append(idProduktu)
				.append("; id opinii=").append(idOpinii)
				.append("; zalety=").append(zaletyProduktu)
				.append("; wady=").append(wadyProduktu)
				.append("; tresc opinii=").append(podsumowanie)
				.append("; ilosc Gwiazdek=").append(iloscGwiazdek)
				.append("; autor=").append(autor)
				.append("; data=").append(dataWystawienia)
				.append("; rekomendacja=").append(rekomendacja)
				.append("; glosy na Tak=").append(opiniaPrzydatna)
				.append("; glosy na Nie=").append(opiniaNieprzydatna);
		return builder.toString();
	}

	public OpiniaTable(Element element, String productId) {
		this.idProduktu = Integer.valueOf(productId);
		this.idOpinii = Integer.parseInt(element.select("button[data-review-id]").attr("data-review-id"));
		this.zaletyProduktu = element.select(".pros-cell").text();
		this.wadyProduktu = element.select(".cons-cell").text();
		this.podsumowanie = element.select(".product-review-body").text();
		this.iloscGwiazdek = element.select(".review-score-count").text();
		this.autor = element.select(".product-reviewer").text();
		this.dataWystawienia = element.select("time[datetime]").attr("datetime");
		this.rekomendacja = element.select(".product-review-summary").text();
		this.opiniaPrzydatna = Integer.parseInt(element.getElementById("votes-yes-" + this.idOpinii).text());
		this.opiniaNieprzydatna = Integer.parseInt(element.getElementById("votes-no-" + this.idOpinii).text());
	}

	public String getRekomendacja() {
		return rekomendacja;
	}

	public void setRekomendacja(String rekomendacja) {
		this.rekomendacja = rekomendacja;
	}

	public void setIdProduktu(int idProduktu) {
		this.idProduktu = idProduktu;
	}

	public int getIdOpinii() {
		return idOpinii;
	}

	public void setIdOpinii(int idOpinii) {
		this.idOpinii = idOpinii;
	}

	public String getZaletyProduktu() {
		return zaletyProduktu;
	}

	public void setZaletyProduktu(String zaletyProduktu) {
		this.zaletyProduktu = zaletyProduktu;
	}

	public String getWadyProduktu() {
		return wadyProduktu;
	}

	public void setWadyProduktu(String wadyProduktu) {
		this.wadyProduktu = wadyProduktu;
	}

	public String getPodsumowanie() {
		return podsumowanie;
	}

	public void setPodsumowanie(String podsumowanie) {
		this.podsumowanie = podsumowanie;
	}

	public String getIloscGwiazdek() {
		return iloscGwiazdek;
	}

	public void setIloscGwiazdek(String iloscGwiazdek) {
		this.iloscGwiazdek = iloscGwiazdek;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDataWystawienia() {
		return dataWystawienia;
	}

	public void setDataWystawienia(String dataWystawienia) {
		this.dataWystawienia = dataWystawienia;
	}


	public int getOpiniaPrzydatna() {
		return opiniaPrzydatna;
	}

	public void setOpiniaPrzydatna(int opiniaPrzydatna) {
		this.opiniaPrzydatna = opiniaPrzydatna;
	}

	public int getOpiniaNieprzydatna() {
		return opiniaNieprzydatna;
	}

	public void setOpiniaNieprzydatna(int opiniaNieprzydatna) {
		this.opiniaNieprzydatna = opiniaNieprzydatna;
	}

}
