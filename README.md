# Specyfikacja zapytań - REST API

# Dla samochodów osobowych:
## Zakładka "Ogłoszenia":

```
/cars?sort=newest // domyślnie
/cars?sort=price,asc
/cars?sort=price,desc
```

```
/cars?offset=51 // podaje 10 kolejnych wyników, zaczynając od 51
```

### Filtry:
```
/cars?priceLower=5000
/cars?priceUpper=10000
```

```
/cars?brand=Audi,Opel,Toyota
```
...

### Przykład odpowiedzi:
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