<template>
    <v-app>
        <v-app-bar app color="primary" dark dense>
            <v-tabs v-model="tab" align-with-title>
                <v-tabs-slider color="yellow"></v-tabs-slider>

                <v-tab v-for="item in items" :key="item.title" :to="item.url">
                    {{ item.title }}
                </v-tab>
            </v-tabs>
        </v-app-bar>

        <v-main>
            <v-container fluid>
                <router-view></router-view>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
export default {
    name: "App",

    components: {},

    data: () => ({
        items: [
            { title: "Ogłoszenia", url: "/" },
            { title: "Statystyki", url: "/stats" },
            { title: "Rekomendacje", url: "/recommendations" },
        ],
        tab: null,
    }),
};
</script>
