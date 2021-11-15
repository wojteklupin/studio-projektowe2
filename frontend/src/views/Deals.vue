<template>
    <v-list two-line flat>
        <v-list-item-group>
            <template v-for="(item, index) in items">
                <v-list-item :key="item.title">
                    <!-- <template v-slot:default="{ active }"> -->
                    <v-list-item-avatar class="rounded" width="200" height="130">
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
                        <div>
                            <v-icon>
                                mdi-star-outline
                            </v-icon>
                            4,2
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
</template>

<script>
export default {
    data: () => ({
        items: [
            {
                title: "Toyota ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
            },
            {
                title: "Volkswagen ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
            },
            {
                title: "Audi ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
            },
            {
                title: "Opel ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
            },
            {
                title: "Citroen ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
            },
            {
                title: "Renault ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
            },
            {
                title: "Fiat ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
            },
            {
                title: "Maluch ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
            },
            {
                title: "Polonez ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
            },
            {
                title: "Mitsubishi ...",
                description: "W doskonałym stanie, bez usterek.",
                numbers: `2004 • 248 km`,
            },
        ],
    }),
};
</script>