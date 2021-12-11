package agh.studio.service;

import agh.studio.entity.CarOffer;
import agh.studio.repository.CarsOffersRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class WebScrappingService {

    private CarsOffersRepository carsOffersRepository;
    private String baseUrl = "https://sprzedajemy.pl/motoryzacja/samochody-osobowe";

    public WebScrappingService(CarsOffersRepository carsOffersRepository) {
        this.carsOffersRepository = carsOffersRepository;
    }

    public void scrapCarOffers() {
        scrapCarOffersSinglePage();
    }

    public void scrapCarOffersSinglePage() {
        try {
            Document doc = Jsoup.connect(baseUrl).get();
            Elements offersLists = doc.getElementsByClass("offers");
            if (offersLists.size() >= 2) {
                for(Element offer: offersLists.get(1).getElementsByClass("details")) {
                    CarOffer carOffer = new CarOffer();
                    Elements title = offer.getElementsByClass("title");
                    if (title.size() > 0)
                        carOffer.setTitle(title.get(0).text());

                    Elements price = offer.getElementsByClass("price");
                    if (price.size() > 0)
                        carOffer.setPrice(Double.parseDouble(getNumberFromString(price.get(0).text())));

                    Elements details = offer.getElementsByClass("attribute");
                    if (details.size() > 0)
                        carOffer.setYear(Integer.parseInt(getNumberFromString(details.get(0).text())));
                    if (details.size() > 1)
                        carOffer.setMileage(Long.parseLong(getNumberFromString(details.get(1).text())));

                    Elements href = offer.getElementsByClass("offerLink");
                    if (href.size() > 0)
                        scrapCarOfferDetailsPage(href.get(0).attr("href"), carOffer);

                    carOffer.setNumberOfRates(0.0);
                    carOffer.setSumOfRates(0.0);

                    carsOffersRepository.save(carOffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrapCarOfferDetailsPage(String href, CarOffer carOffer) throws IOException {
        Document doc = Jsoup.connect(baseUrl + "/" + href).get();
        Elements gallery = doc.getElementsByClass("offerGallery");
        if (gallery.size() > 0)
            carOffer.setImages(gallery.get(0).getElementsByClass("js-gallerySlide")
                    .stream()
                    .filter(image -> image.hasAttr("src"))
                    .map(image -> image.attr("src"))
                    .collect(Collectors.toList()));

        Elements attributes = doc.getElementsByClass("item");
        if (attributes.size() > 1) {
            if (attributes.get(1).getElementsByTag("strong").size() > 0)
                carOffer.setMake(attributes.get(1).getElementsByTag("strong").get(0).text());
        }
        if (attributes.size() > 2) {
            if (attributes.get(2).getElementsByTag("strong").size() > 0)
                carOffer.setModel(attributes.get(2).getElementsByTag("strong").get(0).text());
        }

        Elements description = doc.getElementsByClass("offerDescription");
        if (description.size() > 0)
            carOffer.setDescription(description.get(0).text());

        Elements voivodship = doc.getElementsByClass("region");
        if (voivodship.size() > 0)
            carOffer.setVoivodship(voivodship.get(0).text());
    }

    private String getNumberFromString(String input) {
        return input.replaceAll("[^0-9]","");
    }
}
