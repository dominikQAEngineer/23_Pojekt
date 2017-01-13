package etlClasses;

/**
 * Created by Damian on 2017-01-13.
 */

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Extract {

    public static String GLOBALURL = "http://www.ceneo.pl";

    private ArrayList<Element> extractReviewsFromPage(Document document) {
        return document.select(".product-review");
    }

    private boolean hasNextPage(Document document) {
        return !document.select(".page-arrow.arrow-next").isEmpty();
    }

    private String getUrlToNextPage(Document document) {
        return GLOBALURL + document.select(".page-arrow.arrow-next").select("a[href]").attr("href");
    }

    public Element extractProduct(String productId) throws IOException {
        String currentURL = GLOBALURL + "/" + productId;
        Document doc = null;
        try {
            doc = Jsoup.connect(currentURL).get();
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            return null;
        }

        return doc.select(".main-content").get(0);
    }

    public ArrayList<Element> extractReviews(String productId) throws IOException {
        ArrayList<Element> allElements = new ArrayList<Element>();
        Document doc = null;
        String currentURL = GLOBALURL + "/" + productId + "#tab=reviews";
        try {
            doc = Jsoup.connect(currentURL).get();
            int count = 0;
            while (true) {
                count++;
                allElements.addAll(extractReviewsFromPage(doc));
                if (!hasNextPage(doc)) {
                    break;
                }
                currentURL = getUrlToNextPage(doc);
                doc = Jsoup.connect(currentURL).get();
            }
            System.out.println("Liczba wszystkich opinii: " + allElements.size());
        } catch (Exception e) {
            System.out.println("ERROR:" + e);
            return null;
        }
        return allElements;
    }

}
