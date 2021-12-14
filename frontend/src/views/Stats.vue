<template>
    <v-container style="max-width: 1200px">
        <v-row>
            <v-col cols="10">
                <v-container>
                    <v-row>
                        <v-col cols="6">
                            <GChart
                                type="BarChart"
                                :data="voivodshipChartData"
                                :options="voivodshipChartOptions"
                            />
                        </v-col>
                        <v-col cols="6">
                            <GChart
                                type="Histogram"
                                :data="capacityChartData"
                                :options="capacityChartOptions"
                            />
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="12">
                            <GChart
                                type="ColumnChart"
                                :data="makeChartData"
                                :options="makeChartOptions"
                            />
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="6">
                            <GChart
                                type="Histogram"
                                :data="priceChartData"
                                :options="priceChartOptions"
                            />
                        </v-col>
                        <v-col cols="6">
                            <GChart v-if="vehicleCategory == 'cars'"
                                type="PieChart"
                                :data="bodyChartData"
                                :options="bodyChartOptions"
                            />
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="6">
                            <GChart v-if="vehicleCategory == 'cars'"
                                type="PieChart"
                                :data="fuelChartData"
                                :options="fuelChartOptions"
                            />
                            
                        </v-col>
                        <v-col cols="6">
                            <GChart v-if="vehicleCategory == 'cars'"
                                type="Histogram"
                                :data="powerChartData"
                                :options="powerChartOptions"
                            />
                        </v-col>
                    </v-row>
                </v-container>
            </v-col>

            <v-col cols="2">
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
        brand: null,
        brands: [],
        voivodshipChartData: [
            ["Województwo", "Ilość ogłoszeń"]
        ],
        voivodshipChartOptions: {
            title: "Ilość ogłoszeń według województwa",
            legend: "none"
        },
        fuelChartData: [
            ["Paliwo", "Ilość ogłoszeń"]
        ],
        fuelChartOptions: {
            title: "Ilość ogłoszeń według rodzaju paliwa"
        },
        bodyChartData: [
            ["Nadwozie", "Ilość ogłoszeń"]
        ],
        bodyChartOptions: {
            title: "Ilość ogłoszeń według rodzaju nadwozia"
        },
        makeChartData: [
            ["Marka", "Ilość ogłoszeń"]
        ],
        makeChartOptions: {
            title: "Ilość ogłoszeń według marki",
            legend: "none"
        },
        powerChartData: [
            ["Moc silnika [KM]"]
        ],
        powerChartOptions: {
            title: "Ilość ogłoszeń według mocy silnika [KM]",
            legend: "none"
        },
        capacityChartData: [
            ["Pojemność silnika [cm3]"]
        ],
        capacityChartOptions: {
            title: "Ilość ogłoszeń według pojemności silnika [cm^3]",
            legend: "none"
        },
        priceChartData: [
            ["Cena"]
        ],
        priceChartOptions: {
            title: "Ilość ogłoszeń według ceny",
            legend: "none"
        },
    }),
    created() {
        const fetchStats = async function(vm) {
            let response = await fetch(`http://localhost:8080/cars/stats`)
            let stats = await response.json()
            for (let item of stats.voivodship) {
                vm.voivodshipChartData.push([item.label, item.count])
            }
            for (let item of stats.make) {
                vm.makeChartData.push([item.label, item.count])
            }
            for (let item of stats.capacity) {
                vm.capacityChartData.push([item])
            }
            for (let item of stats.price) {
                vm.priceChartData.push([item])
            }
            for (let item of stats.body) {
                vm.bodyChartData.push([item.label, item.count])
            }
            for (let item of stats.fuel) {
                vm.fuelChartData.push([item.label, item.count])
            }
            for (let item of stats.power) {
                vm.powerChartData.push([item])
            }
        }
        fetchStats(this)
    },
    computed: {
        filters() {
          return `${this.vehicleCategory}|${this.priceLower}|${this.priceUpper}|${this.brand}|${this.yearLower}|${this.yearUpper}|${this.mileageLower}|${this.mileageUpper}`;
        },
    },
    watch: {
        filters: function (newValue) {
            this.page = 1
            const [newVehicleCategory, newPriceLower, newPriceUpper, newBrand, newYearLower, newYearUpper, newMileageLower, newMileageUpper] = newValue.split('|');
            let url = `http://localhost:8080/${newVehicleCategory}/stats?dummy=true`;
            let url2 = `http://localhost:8080/${newVehicleCategory}?dummy=true`
            
            if (newPriceLower != "null") {
                url += `&minPrice=${newPriceLower}`
                url2 += `&minPrice=${newPriceLower}`
            }
            if (newPriceUpper != "null") {
                url += `&maxPrice=${newPriceUpper}`
                url2 += `&maxPrice=${newPriceUpper}`
            }
            if (newBrand != "null") {
                url += `&make=${newBrand}`
                url2 += `&make=${newBrand}`
            }
            if (newYearLower != "null") {
                url += `&minYear=${newYearLower}`
                url2 += `&minYear=${newYearLower}`
            }
            if (newYearUpper != "null") {
                url += `&maxYear=${newYearUpper}`
                url2 += `&maxYear=${newYearUpper}`
            }
            if (newMileageLower != "null") {
                url += `&minMileage=${newMileageLower}`
                url2 += `&minMileage=${newMileageLower}`
            }
            if (newMileageUpper != "null") {
                url += `&maxMileage=${newMileageUpper}`
                url2 += `&maxMileage=${newMileageUpper}`
            }
            const fetchStats = async function(vm) {
                let response = await fetch(url)
                let stats = await response.json()

                vm.voivodshipChartData = [vm.voivodshipChartData[0]]
                vm.makeChartData = [vm.makeChartData[0]]
                vm.capacityChartData = [vm.capacityChartData[0]]
                vm.priceChartData = [vm.priceChartData[0]]

                for (let item of stats.voivodship) {
                    vm.voivodshipChartData.push([item.label, item.count])
                }
                for (let item of stats.make) {
                    vm.makeChartData.push([item.label, item.count])
                }
                for (let item of stats.capacity) {
                    vm.capacityChartData.push([item])
                }
                for (let item of stats.price) {
                    vm.priceChartData.push([item])
                }
                if (vm.vehicleCategory == "cars") {
                    vm.fuelChartData = [vm.fuelChartData[0]]
                    vm.bodyChartData = [vm.bodyChartData[0]]
                    vm.powerChartData = [vm.powerChartData[0]]
                    for (let item of stats.fuel) {
                        vm.fuelChartData.push([item.label, item.count])
                    }
                    for (let item of stats.body) {
                        vm.bodyChartData.push([item.label, item.count])
                    }
                    for (let item of stats.power) {
                        vm.powerChartData.push([item])
                    }
                }
            }
            fetchStats(this)

            const fetchBrands = async function(vm) {
                let searching = true
                let page = 1
                let brands = new Set()
                while (searching && page <= 5) {
                    searching = false
                    let response = await fetch(url2 + `&page=${page}`)
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