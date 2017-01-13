package dbTables;

/**
 * Created by Damian on 2017-01-13.
 */

import org.jsoup.nodes.Element;

import java.util.List;

public class ProduktTable {
    private int idProduktu;
    private String rodzajProduktu;
    private String markaProduktu;
    private String modelProduktu;
    private String dodatkoweUwagi;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append("\n")
                .append("Id produktu=").append(idProduktu)
                .append("; rodzaj=").append(rodzajProduktu)
                .append("; marka=").append(markaProduktu)
                .append("; model=").append(modelProduktu)
                .append("; dodatkoweUwagi=").append(dodatkoweUwagi);
        return builder.toString();
    }

    public ProduktTable(Element element, String idProduktu) {
        List<Element> elements = element.getElementsByClass("breadcrumb");
        List<Element> nazwaProduktuList = element.getElementsByClass("product-name");
        this.idProduktu = Integer.valueOf(idProduktu);
        this.rodzajProduktu = elements.get(elements.size() - 1).text();
        this.dodatkoweUwagi = element.select(".ProductSublineTags").text();
        this.markaProduktu = nazwaProduktuList.get(0).text().substring(0, nazwaProduktuList.get(0).text().indexOf(' '));
        this.modelProduktu = nazwaProduktuList.get(0).text()
                .substring(nazwaProduktuList.get(0).text().indexOf(' '), nazwaProduktuList.get(0).text().length());
    }

    public int getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(int idProduktu) {
        this.idProduktu = idProduktu;
    }

    public String getRodzajProduktu() {
        return rodzajProduktu;
    }

    public void setRodzajProduktu(String rodzajProduktu) {
        this.rodzajProduktu = rodzajProduktu;
    }

    public String getMarkaProduktu() {
        return markaProduktu;
    }

    public void setMarkaProduktu(String markaProduktu) {
        this.markaProduktu = markaProduktu;
    }

    public String getModelProduktu() {
        return modelProduktu;
    }

    public void setModelProduktu(String modelProduktu) {
        this.modelProduktu = modelProduktu;
    }

    public String getDodatkoweUwagi() {
        return dodatkoweUwagi;
    }

    public void setDodatkoweUwagi(String dodatkoweUwagi) {
        this.dodatkoweUwagi = dodatkoweUwagi;
    }


}
