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
                                    <v-img
                                        src="https://ireland.apollo.olxcdn.com/v1/files/eyJmbiI6Im54a280YjI3ZGQ3NTMtT1RPTU9UT1BMIiwidyI6W3siZm4iOiJ3ZzRnbnFwNnkxZi1PVE9NT1RPUEwiLCJzIjoiMTYiLCJwIjoiMTAsLTEwIiwiYSI6IjAifV19.Q4JsgAH7aVrN3Cc9aVHRJXLNnXHjBPJ4ebaQO0y7OgE/image;s=1080x720"
                                    ></v-img>
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
                                    <div class="rate-wrapper">
                                        <v-icon> mdi-star-outline </v-icon>
                                        {{ item.rate }}
                                    </div>
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
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    data: () => ({
        vehicleCategory: "cars",
        items: [
            {
                title: "Toyota ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
                rate: "4,2",
            },
            {
                title: "Volkswagen ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
                rate: "4,2",
            },
            {
                title: "Audi ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
                rate: "4,2",
            },
            {
                title: "Opel ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
                rate: "4,2",
            },
            {
                title: "Citroen ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
                rate: "4,2",
            },
            {
                title: "Renault ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
                rate: "4,2",
            },
            {
                title: "Fiat ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
                rate: "4,2",
            },
            {
                title: "Maluch ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
                rate: "4,2",
            },
            {
                title: "Polonez ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
                rate: "4,2",
            },
            {
                title: "Mitsubishi ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
                rate: "4,2",
            },
        ],
    }),
};
</script>

<style>
.rate-wrapper {
    height: 24px;
}
</style>