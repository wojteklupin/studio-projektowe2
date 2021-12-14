<template>
    <v-container style="max-width: 1200px">
        <v-row>
            <v-col cols="10">
                <v-container>
                    <v-row>
                        <v-col cols="6">
                            <div id="voivodshipChart"></div>
                        </v-col>
                        <v-col cols="6">
                            <!-- <GChart
                                type="ColumnChart"
                                :data="fuelChartData"
                                :options="fuelChartOptions"
                            /> -->
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="6">
                            <!-- <GChart
                                type="ColumnChart"
                                :data="bodyChartData"
                                :options="bodyChartOptions"
                            /> -->
                        </v-col>
                        <v-col cols="6">
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
                                type="ColumnChart"
                                :data="powerChartData"
                                :options="powerChartOptions"
                            />
                        </v-col>
                        <v-col cols="6">
                            <GChart
                                type="ColumnChart"
                                :data="capacityChartData"
                                :options="capacityChartOptions"
                            />
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="12">
                            <GChart
                                type="ColumnChart"
                                :data="priceChartData"
                                :options="priceChartOptions"
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
        voivodshipChartData: [
            ["Województwo", "Ilość ogłoszeń"]
        ],
        voivodshipChartOptions: {
            chart: {
                title: "Company Performance"
            },
            height: 400,
        },
        fuelChartData: [
            ["Paliwo", "Ilość ogłoszeń"]
        ],
        fuelChartOptions: {
            chart: {
                title: "Company Performance",
                subtitle: "Sales, Expenses, and Profit: 2014-2017",
            },
        },
        bodyChartData: [
            ["Nadwozie", "Ilość ogłoszeń"]
        ],
        bodyChartOptions: {
            chart: {
                title: "Company Performance",
                subtitle: "Sales, Expenses, and Profit: 2014-2017",
            },
        },
        makeChartData: [
            ["Marka", "Ilość ogłoszeń"]
        ],
        makeChartOptions: {
            chart: {
                title: "Company Performance",
                subtitle: "Sales, Expenses, and Profit: 2014-2017",
            },
        },
        powerChartData: [
            ["Moc silnika [KM]", "Ilość ogłoszeń"]
        ],
        powerChartOptions: {
            chart: {
                title: "Company Performance",
                subtitle: "Sales, Expenses, and Profit: 2014-2017",
            },
        },
        capacityChartData: [
            ["Pojemność silnika [cm3]", "Ilość ogłoszeń"]
        ],
        capacityChartOptions: {
            chart: {
                title: "Company Performance",
                subtitle: "Sales, Expenses, and Profit: 2014-2017",
            },
        },
        priceChartData: [
            ["Cena", "Ilość ogłoszeń"]
        ],
        priceChartOptions: {
            chart: {
                title: "Company Performance",
                subtitle: "Sales, Expenses, and Profit: 2014-2017",
            },
        },
    }),
    created() {
        const fetchStats = async function(vm) {
            let response = await fetch(`http://localhost:8080/cars/stats`)
            let stats = await response.json()
            for (let item of stats.voivodship) {
                vm.voivodshipChartData.push([item.label, item.count])
            }
            for (let item of stats.fuel) {
                vm.fuelChartData.push([item.label, item.count])
            }
            for (let item of stats.body) {
                vm.bodyChartData.push([item.label, item.count])
            }
            for (let item of stats.make) {
                vm.makeChartData.push([item.label, item.count])
            }
            for (let item of stats.power) {
                vm.powerChartData.push([item.label, item.count])
            }
        }
        fetchStats(this)
    }
};
</script>