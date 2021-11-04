# Specyfikacja zapytań - REST API

## Dla samochodów osobowych:

```
/cars?sort=newest // domyślnie
/cars?sort=price,asc
/cars?sort=price,desc
```

```
/cars?offset=51 // podaj 10 kolejnych wyników, zaczynając od 51
```

### Filtry
```
/cars?priceLower=5000
/cars?priceUpper=10000
```

```
/cars?brand=Audi,Opel,Toyota
```
...

Przykład:
```js
[
    {
        "image": "strona.com/url/do/obrazka",
        "title": "Nazwa pojazdu",
        "description": "Krótki opis pojazdu",
        "year": rok_produkcji,
        "mileage": przebieg_samochodu_km,
        "rate": ocena_wyliczona_przez_backend_w_skali_od_1_do_5,
    },
    {
        "image": "strona.com/url/do/obrazka",
        "title": "Nazwa pojazdu",
        "description": "Krótki opis pojazdu",
        "year": rok_produkcji,
        "mileage": przebieg_samochodu_km,
        "rate": ocena_wyliczona_przez_backend_w_skali_od_1_do_5,
    }
]
```

### Statystyki:
```
/cars/stats
```
