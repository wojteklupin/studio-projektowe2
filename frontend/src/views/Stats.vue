<template>
    <v-container style="max-width: 1200px">
        <v-row>
            <v-col cols="10">
                <v-container>
                    <v-row>
                        <v-col cols="6">
                            <GChart
                                type="ColumnChart"
                                :data="chartData"
                                :options="chartOptions"
                            />
                        </v-col>
                        <v-col cols="6">
                            <GChart
                                type="ColumnChart"
                                :data="chartData"
                                :options="chartOptions"
                            />
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="6">
                            <GChart
                                type="ColumnChart"
                                :data="chartData"
                                :options="chartOptions"
                            />
                        </v-col>
                        <v-col cols="6">
                            <GChart
                                type="ColumnChart"
                                :data="chartData"
                                :options="chartOptions"
                            />
                        </v-col>
                    </v-row>
                </v-container>
            </v-col>

            <v-col cols="2"> Filtry: </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    data: () => ({
        chartData: [
            ["Year", "Sales", "Expenses", "Profit"],
            ["2014", 1000, 400, 200],
            ["2015", 1170, 460, 250],
            ["2016", 660, 1120, 300],
            ["2017", 1030, 540, 350],
        ],
        chartOptions: {
            chart: {
                title: "Company Performance",
                subtitle: "Sales, Expenses, and Profit: 2014-2017",
            },
        },
    }),
};
</script>