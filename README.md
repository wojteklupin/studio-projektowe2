# Specyfikacja zapytań - REST API

# Dla samochodów osobowych:
## Zakładka "Ogłoszenia":

```
/cars - zapytanie o oferty samochodów
```
Dostępne parametry zapytania
```
page - numer strony wyszukiwania począwszy od 1 - parametr obowiązkowy!
make - marka (parametr opcjonalny)
minPrice - cena minimalna (parametr opcjonalny)
maxPrice - cena maksymalna (parametr opcjonalny)
minYear - rok minimalny (parametr opcjonalny)
maxYear - rok maksyamlny (parametr opcjonalny)
minMileage - przebieg minimalny (parametr opcjonalny)
maxMileage - przebieg minimalny (parametr opcjonalny)
sort - sortowanie (parametr opcjonalny - gdy brak, sortuje od najnowszych ogłoszeń, dla wartości parametru price sortuje według ceny)
mode - tryb sortowania (parametr opcjonalny - używany, gdy podany parametr sort z wartością price, dla wartości desc sortuje malejąco, w przeciwnym wypadku sortuje rosnąco)
```


### Przykład odpowiedzi:
```js
[
    {
        "id": identyfikator ogłoszenia
        "make": makra pojazdu,
        "model": model pojazdu,
        "title": tytuł ogłoszenia,
        "description": opis pojazdu,
        "price": cena,
        "year": rok produkcji,
        "mileage": przebieg samochodu w km,
        "images": lista linków do zdjęć,
        "rate": ocena_wyliczona_przez_backend_w_skali_od_1_do_5_z_jednym_miejscem_po_przecinku,
    },
    {
        "id": identyfikator ogłoszenia
        "make": makra pojazdu,
        "model": model pojazdu,
        "title": tytuł ogłoszenia,
        "description": opis pojazdu,
        "price": cena,
        "year": rok produkcji,
        "mileage": przebieg samochodu w km,
        "images": lista linków do zdjęć,
        "rate": ocena_wyliczona_przez_backend_w_skali_od_1_do_5_z_jednym_miejscem_po_przecinku,
    }
]
```

## Zakładka "Statystyki":
```
/cars/stats
```

### Filtry:
Podobnie jak powyżej

### Przykład odpowiedzi:
```js
{
    "pricesHistogram": {
        "start": poczatkowa_wartosc_na_osi_x,
        "stop": koncowa_wartosc_na_osi_x,
        "step": szerokosc_slupkow_histogramu_na_osi_x,
        "values": [100, 200, 500, 400, 150] /* kolejne wartości na osi Y*/
    },
    "enginesPowerHistogram": {
        "start": poczatkowa_wartosc_na_osi_x,
        "stop": koncowa_wartosc_na_osi_x,
        "step": szerokosc_slupkow_histogramu_na_osi_x,
        "values": [100, 200, 500, 400, 150] /* kolejne wartości na osi Y */
    },
    "dealsByVoivodshipBarChart": [ /* ilosc ogloszen wedlug wojewodztwa (wykres słupkowy, posortowany) */
        {
            "voivodshipName": "małopolskie",
            "value": 235
        },
        {
            "voivodshipName": "lubelskie",
            "value": 123
        }
        ...
    ],
    "averageRateByBrandBarChart": [ /* średnia ocena jakość/cena, wyliczona, w skali od 1 do 5 (wykres słupkowy, posortowany) */
        {
            "brandName": "Opel",
            "value": 4.4
        },
        {
            "brandName": "Toyota",
            "value": 4.2
        }
        ...
    ]
}
```

## Zakładka "Rekomendacje":
Obowiązkowe query parameters: ***priceLower*** i ***priceUpper***
```
/cars/recommendations?priceLower=5000&priceUpper=10000
```

### Dodatkowe filtry:
Podobnie jak powyżej

### Przykład odpowiedzi (identycznie jak w zakładce "Ogłoszenia"):
```js
[
    {
        "image": "http://localhost:8080/url/do/obrazka",
        "title": "Nazwa pojazdu",
        "description": "Krótki opis pojazdu",
        "year": rok_produkcji,
        "mileage": przebieg_samochodu_km,
        "rate": ocena_wyliczona_przez_backend_w_skali_od_1_do_5_z_jednym_miejscem_po_przecinku,
    },
    {
        "image": "http://localhost:8080/url/do/obrazka",
        "title": "Nazwa pojazdu",
        "description": "Krótki opis pojazdu",
        "year": rok_produkcji,
        "mileage": przebieg_samochodu_km,
        "rate": ocena_wyliczona_przez_backend_w_skali_od_1_do_5_z_jednym_miejscem_po_przecinku,
    }
]
```

# Dla motocykli:
TODO, ale pewnie bardzo podobnie.
