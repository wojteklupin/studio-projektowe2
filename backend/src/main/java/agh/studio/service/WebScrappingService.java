package agh.studio.service;

import agh.studio.entity.CarOffer;
import agh.studio.entity.MotorcycleOffer;
import agh.studio.repository.CarsOffersRepository;
import agh.studio.repository.MotorcyclesOffersRepository;
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
    private MotorcyclesOffersRepository motorcyclesOffersRepository;
    private String baseCarUrl = "https://sprzedajemy.pl/motoryzacja/samochody-osobowe";
    private String baseMotorcycleUrl = "https://sprzedajemy.pl/motoryzacja/motocykle-skutery-quady";

    public WebScrappingService(CarsOffersRepository carsOffersRepository, MotorcyclesOffersRepository motorcyclesOffersRepository) {
        this.carsOffersRepository = carsOffersRepository;
        this.motorcyclesOffersRepository = motorcyclesOffersRepository;
    }

    public void scrapCarOffers() {
        scrapCarOffersSinglePage();
    }

    public void scrapMotorcycleOffers() {
        scrapMotorcycleOffersSinglePage();
    }

    public void scrapCarOffersSinglePage() {
        try {
            Document doc = Jsoup.connect(baseCarUrl).get();
            Elements offersLists = doc.getElementsByClass("offers");
            if (offersLists.size() >= 2) {
                for(Element offer: offersLists.get(1).getElementsByClass("details")) {
                    scrapSingleCarOffer(offer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrapMotorcycleOffersSinglePage() {
        try {
            Document doc = Jsoup.connect(baseMotorcycleUrl).get();
            Elements offersLists = doc.getElementsByClass("offers");
            if (offersLists.size() >= 2) {
                for(Element offer: offersLists.get(1).getElementsByClass("details")) {
                    scrapSingleMotorcycleOffer(offer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrapSingleCarOffer(Element offer) {
        try {
            CarOffer carOffer = new CarOffer();
            Elements title = offer.getElementsByClass("title");
            if (title.size() > 0)
                carOffer.setTitle(title.get(0).text());

            Elements price = offer.getElementsByClass("price");
            if (price.size() > 0) {
                carOffer.setPrice(Double.parseDouble(getNumberFromString(price.get(0).text())));
                if (carOffer.getPrice() != null)
                carOffer.setPriceCategory((int) (carOffer.getPrice() / 10000));
            }

            Elements details = offer.getElementsByClass("attribute");
            if (details.size() > 0)
                carOffer.setYear(Integer.parseInt(getNumberFromString(details.get(0).text())));
            if (details.size() > 1)
                carOffer.setMileage(Long.parseLong(getNumberFromString(details.get(1).text())));

            Elements href = offer.getElementsByClass("offerLink");
            if (href.size() > 0)
                scrapCarOfferDetailsPage(href.get(0).attr("href"), carOffer);

            carsOffersRepository.save(carOffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrapSingleMotorcycleOffer(Element offer) {
        try {
            MotorcycleOffer motorcycleOffer = new MotorcycleOffer();
            Elements title = offer.getElementsByClass("title");
            if (title.size() > 0)
                motorcycleOffer.setTitle(title.get(0).text());

            Elements price = offer.getElementsByClass("price");
            if (price.size() > 0) {
                motorcycleOffer.setPrice(Double.parseDouble(getNumberFromString(price.get(0).text())));
                if (motorcycleOffer.getPrice() != null)
                    motorcycleOffer.setPriceCategory((int) (motorcycleOffer.getPrice() / 10000));
            }

            Elements details = offer.getElementsByClass("attribute");
            if (details.size() > 0)
                motorcycleOffer.setYear(Integer.parseInt(getNumberFromString(details.get(0).text())));
            if (details.size() > 1)
                motorcycleOffer.setMileage(Long.parseLong(getNumberFromString(details.get(1).text())));

            Elements href = offer.getElementsByClass("offerLink");
            if (href.size() > 0)
                scrapMotorcycleOfferDetailsPage(href.get(0).attr("href"), motorcycleOffer);

            motorcyclesOffersRepository.save(motorcycleOffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrapCarOfferDetailsPage(String href, CarOffer carOffer) throws IOException {
        Document doc = Jsoup.connect(baseCarUrl + "/" + href).get();
        Elements gallery = doc.getElementsByClass("offerGallery");
        if (gallery.size() > 0)
            carOffer.setImages(gallery.get(0).getElementsByClass("js-gallerySlide")
                    .stream()
                    .filter(image -> image.hasAttr("src"))
                    .map(image -> image.attr("src"))
                    .collect(Collectors.toList()));

        Elements attributes = doc.getElementsByClass("item");
        for(Element attribute: attributes) {
            if (attribute.getElementsByTag("span").get(0).text().equals("Marka"))
                carOffer.setMake(attribute.getElementsByTag("strong").get(0).text());

            else if (attribute.getElementsByTag("span").get(0).text().equals("Model"))
                carOffer.setModel(attribute.getElementsByTag("strong").get(0).text());

            else if (attribute.getElementsByTag("span").get(0).text().equals("Rodzaj nadwozia"))
                carOffer.setBody(attribute.getElementsByTag("strong").get(0).text());

            else if (attribute.getElementsByTag("span").get(0).text().equals("Pojemność silnika")) {
                carOffer.setEngineCapcacity(getIntegerOrNull(attribute.getElementsByTag("strong").get(0).text()));
                if (carOffer.getEngineCapcacity() != null)
                    carOffer.setEngineCapacityCategory(carOffer.getEngineCapcacity() / 100);
            }

            else if (attribute.getElementsByTag("span").get(0).text().equals("Moc")) {
                carOffer.setEnginePower(getIntegerOrNull(attribute.getElementsByTag("strong").get(0).text()));
                if (carOffer.getEnginePower() != null)
                    carOffer.setEnginePowerCategory(carOffer.getEnginePower() / 10);
            }

            else if (attribute.getElementsByTag("span").get(0).text().equals("Paliwo"))
                carOffer.setFuelType(attribute.getElementsByTag("strong").get(0).text());
        }

        Elements description = doc.getElementsByClass("offerDescription");
        if (description.size() > 0)
            carOffer.setDescription(description.get(0).text());

        Elements voivodship = doc.getElementsByClass("region");
        if (voivodship.size() > 0)
            carOffer.setVoivodship(voivodship.get(0).text());
    }

    public void scrapMotorcycleOfferDetailsPage(String href, MotorcycleOffer motorcycleOffer) throws IOException {
        Document doc = Jsoup.connect(baseMotorcycleUrl + "/" + href).get();
        Elements gallery = doc.getElementsByClass("offerGallery");
        if (gallery.size() > 0)
            motorcycleOffer.setImages(gallery.get(0).getElementsByClass("js-gallerySlide")
                    .stream()
                    .filter(image -> image.hasAttr("src"))
                    .map(image -> image.attr("src"))
                    .collect(Collectors.toList()));

        Elements attributes = doc.getElementsByClass("item");
        for(Element attribute: attributes) {
            if (attribute.getElementsByTag("span").get(0).text().equals("Marka"))
                motorcycleOffer.setMake(attribute.getElementsByTag("strong").get(0).text());

            else if (attribute.getElementsByTag("span").get(0).text().equals("Pojemność silnika")) {
                motorcycleOffer.setEngineCapcacity(getIntegerOrNull(attribute.getElementsByTag("strong").get(0).text()));
                if (motorcycleOffer.getEngineCapcacity() != null)
                    motorcycleOffer.setEngineCapacityCategory(motorcycleOffer.getEngineCapcacity() / 100);
            }

            else if (attribute.getElementsByTag("span").get(0).text().equals("Moc")) {
                motorcycleOffer.setEnginePower(getIntegerOrNull(attribute.getElementsByTag("strong").get(0).text()));
                if (motorcycleOffer.getEnginePower() != null)
                    motorcycleOffer.setEnginePowerCategory(motorcycleOffer.getEnginePower());
            }

        }

        Elements description = doc.getElementsByClass("offerDescription");
        if (description.size() > 0)
            motorcycleOffer.setDescription(description.get(0).text());

        Elements voivodship = doc.getElementsByClass("region");
        if (voivodship.size() > 0)
            motorcycleOffer.setVoivodship(voivodship.get(0).text());
    }

    private Integer getIntegerOrNull(String input) {
        String processed = getNumberFromString(input);
        if (processed.equals(""))
            return null;
        else
            return Integer.parseInt(processed);
    }

    private String getNumberFromString(String input) {
        return input.replaceAll("[^0-9]","");
    }
}
