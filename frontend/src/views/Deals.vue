<template>
    <v-container style="max-width: 1200px">
        <v-row>
            <v-col cols="9">
                <v-list two-line flat>
                    <v-list-item-group>
                        <template v-for="(item, index) in items">
                            <v-list-item :key="item.title">
                                <!-- <template v-slot:default="{ active }"> -->
                                <v-list-item-avatar
                                    class="rounded"
                                    width="200"
                                    height="130"
                                >
                                    <v-img :src="item.imageSrc"></v-img>
                                </v-list-item-avatar>

                                <v-list-item-content>
                                    <v-list-item-title
                                        v-text="item.title"
                                    ></v-list-item-title>

                                    <v-list-item-subtitle
                                        class="text--primary"
                                        v-text="item.description"
                                    ></v-list-item-subtitle>

                                    <v-list-item-subtitle
                                        v-text="item.numbers"
                                    ></v-list-item-subtitle>
                                </v-list-item-content>

                                <v-list-item-action>
                                    <!-- <v-list-item-action-text
                            v-text="item.action"
                        ></v-list-item-action-text> -->
                                    <span style="opacity: 0.8; color: red; font-weight: bold; font-size: 22px;">{{ item.price }}</span>
                                </v-list-item-action>
                                <!-- </template> -->
                            </v-list-item>

                            <v-divider
                                v-if="index < items.length - 1"
                                :key="index"
                            ></v-divider>
                        </template>
                    </v-list-item-group>
                </v-list>
                <div v-intersect="loadNextPage"></div>
            </v-col>

            <v-col cols="3">
                <v-radio-group v-model="vehicleCategory">
                    <template v-slot:label>
                        <div>Kategoria:</div>
                    </template>
                    <v-radio
                        key="0"
                        label="Samochody osobowe"
                        value="cars"
                    ></v-radio>
                    <v-radio
                        key="1"
                        label="Motocykle"
                        value="motorcycles"
                    ></v-radio>
                </v-radio-group>

                <v-select
                    v-model="sortBy"
                    :items="sortByOptions"
                    label="Sortuj od"
                    dense
                ></v-select>
                <v-select
                    v-model="brand"
                    :items="brands"
                    label="Marka pojazdu"
                    dense
                ></v-select>
                Zakres cen:
                <v-container>
                    <v-row>
                        <v-col cols="6">
                            <v-text-field
                                v-model="priceLower"
                                single-line
                                label="Od"
                                dense
                                outlined
                                type="number"
                            />
                        </v-col>
                        <v-col cols="6">
                            <v-text-field
                                v-model="priceUpper"
                                single-line
                                label="Do"
                                dense
                                outlined
                                type="number"
                            />
                        </v-col>
                    </v-row>
                </v-container>
                Rok produkcji:
                <v-container>
                    <v-row>
                        <v-col cols="6">
                            <v-text-field
                                v-model="yearLower"
                                single-line
                                label="Od"
                                dense
                                outlined
                                type="number"
                            />
                        </v-col>
                        <v-col cols="6">
                            <v-text-field
                                v-model="yearUpper"
                                single-line
                                label="Do"
                                dense
                                outlined
                                type="number"
                            />
                        </v-col>
                    </v-row>
                </v-container>
                Przebieg pojazdu:
                <v-container>
                    <v-row>
                        <v-col cols="6">
                            <v-text-field
                                v-model="mileageLower"
                                single-line
                                label="Od"
                                dense
                                outlined
                                type="number"
                            />
                        </v-col>
                        <v-col cols="6">
                            <v-text-field
                                v-model="mileageUpper"
                                single-line
                                label="Do"
                                dense
                                outlined
                                type="number"
                            />
                        </v-col>
                    </v-row>
                </v-container>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    data: () => ({
        vehicleCategory: "cars",
        priceLower: null,
        priceUpper: null,
        yearLower: null,
        yearUpper: null,
        mileageLower: null,
        mileageUpper: null,
        sortBy: "Najnowszych",
        brand: null,
        brands: ["Volkswagen", "Toyota", "Citroën", "Peugeot", "BMW", "Renault"],
        sortByOptions: ["Najnowszych", "Najtańszych", "Najdroższych"],
        items: [],
        page: 1
    }),
    methods: {
        loadNextPage() {
            const fetchData = async () => {
                let response = await fetch(`http://localhost:8080/cars?page=${this.page}`);
                let vehicles = await response.json();

                for (let vehicle of vehicles) {
                    this.items.push({
                        title: vehicle.title,
                        description: vehicle.description,
                        numbers: `${vehicle.year} • ${vehicle.mileage} km`,
                        imageSrc: vehicle.images[0],
                        price: `${vehicle.price} zł`
                    });
                }
                this.page++
            };

            fetchData();
        }
    },
    computed: {
        filters() {
          return `${this.vehicleCategory}|${this.priceLower}|${this.priceUpper}|${this.sortBy}|${this.brand}|${this.yearLower}|${this.yearUpper}|${this.mileageLower}|${this.mileageUpper}`;
        },
    },
    watch: {
        filters: function (newValue) {
            this.page = 1
            const [newVehicleCategory, newPriceLower, newPriceUpper, newSortBy, newBrand, newYearLower, newYearUpper, newMileageLower, newMileageUpper] = newValue.split('|');
            let url;
            switch (newSortBy) {
                case "Najnowszych":
                    url = `http://localhost:8080/${newVehicleCategory}?page=1`;
                    break;
                case "Najtańszych":
                    url =
                        `http://localhost:8080/${newVehicleCategory}?page=1&sort=price&mode=asc`;
                    break;
                default:
                    url =
                        `http://localhost:8080/${newVehicleCategory}?page=1&sort=price&mode=desc`;
                    break;
            }
            if (newPriceLower != "null") {
                url += `&minPrice=${newPriceLower}`
            }
            if (newPriceUpper != "null") {
                url += `&maxPrice=${newPriceUpper}`
            }
            if (newBrand != "null") {
                url += `&make=${newBrand}`
            }
            if (newYearLower != "null") {
                url += `&minYear=${newYearLower}`
            }
            if (newYearUpper != "null") {
                url += `&maxYear=${newYearUpper}`
            }
            if (newMileageLower != "null") {
                url += `&minMileage=${newMileageLower}`
            }
            if (newMileageUpper != "null") {
                url += `&maxMileage=${newMileageUpper}`
            }
            fetch(url)
                .then(response => 
                    response.json()
                )
                .then(vehicles => {
                    this.items = []
                    for (let vehicle of vehicles) {
                        this.items.push({
                            title: vehicle.title,
                            description: vehicle.description,
                            numbers: `${vehicle.year} • ${vehicle.mileage} km`,
                            imageSrc: vehicle.images[0],
                            price: `${vehicle.price} zł`
                        });
                    }
                });
        },
    }
};
</script>