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
        brands: [],
        sortByOptions: ["Najnowszych", "Najtańszych", "Najdroższych"],
        items: [],
        page: 0
    }),
    methods: {
        loadNextPage() {
            this.page++
            let url;
            switch (this.sortBy) {
                case "Najnowszych":
                    url = `http://localhost:8080/${this.vehicleCategory}?page=${this.page}`;
                    break;
                case "Najtańszych":
                    url =
                        `http://localhost:8080/${this.vehicleCategory}?page=${this.page}&sort=price&mode=asc`;
                    break;
                default:
                    url =
                        `http://localhost:8080/${this.vehicleCategory}?page=${this.page}&sort=price&mode=desc`;
                    break;
            }
            if (this.priceLower != null) {
                url += `&minPrice=${this.priceLower}`
            }
            if (this.priceUpper != null) {
                url += `&maxPrice=${this.priceUpper}`
            }
            if (this.brand != null) {
                url += `&make=${this.brand}`
            }
            if (this.yearLower != null) {
                url += `&minYear=${this.yearLower}`
            }
            if (this.yearUpper != null) {
                url += `&maxYear=${this.yearUpper}`
            }
            if (this.mileageLower != null) {
                url += `&minMileage=${this.mileageLower}`
            }
            if (this.mileageUpper != null) {
                url += `&maxMileage=${this.mileageUpper}`
            }
            const fetchData = async () => {
                let response = await fetch(url);
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
            };

            fetchData();
        }
    },
    created() {
        const fetchBrands = async function(vm) {
            let searching = true
            let page = 1
            let brands = new Set()
            while (searching) {
                searching = false
                let response = await fetch(`http://localhost:8080/cars?page=${page}`)
                let vehicles = await response.json()
                for (let vehicle of vehicles) {
                    searching = true
                    brands.add(vehicle.make)
                }
                page++
            }
            vm.brands = Array.from(brands)
        }
        fetchBrands(this)
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
            let options
            switch (newSortBy) {
                case "Najnowszych":
                    url = `http://localhost:8080/${newVehicleCategory}`;
                    options = ""
                    break;
                case "Najtańszych":
                    url =
                        `http://localhost:8080/${newVehicleCategory}`;
                    options = "&sort=price&mode=asc"
                    break;
                default:
                    url =
                        `http://localhost:8080/${newVehicleCategory}`;
                    options = "&sort=price&mode=desc"
                    break;
            }
            if (newPriceLower != "null") {
                options += `&minPrice=${newPriceLower}`
            }
            if (newPriceUpper != "null") {
                options += `&maxPrice=${newPriceUpper}`
            }
            if (newBrand != "null") {
                options += `&make=${newBrand}`
            }
            if (newYearLower != "null") {
                options += `&minYear=${newYearLower}`
            }
            if (newYearUpper != "null") {
                options += `&maxYear=${newYearUpper}`
            }
            if (newMileageLower != "null") {
                options += `&minMileage=${newMileageLower}`
            }
            if (newMileageUpper != "null") {
                options += `&maxMileage=${newMileageUpper}`
            }
            fetch(url + "?page=1" + options)
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
            const fetchBrands = async function(vm) {
                let searching = true
                let page = 1
                let brands = new Set()
                while (searching && page <= 5) {
                    searching = false
                    let response = await fetch(url + `?page=${page}`+ options)
                    let vehicles = await response.json()
                    for (let vehicle of vehicles) {
                        searching = true
                        brands.add(vehicle.make)
                    }
                    page++
                }
                vm.brands = Array.from(brands)
            }
            fetchBrands(this)
        },
        vehicleCategory: function () {
            this.brand = null
        }
    }
};
</script>