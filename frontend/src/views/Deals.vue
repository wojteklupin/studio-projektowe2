<template>
    <v-list style="max-height: 100px" class="overflow-y-auto">
        <template v-for="user in users">
            <v-list-tile :key="user.id" avatar @click="alert('sdfsed')">
                <v-list-tile-content>
                    <v-list-tile-title v-text="user.name"></v-list-tile-title>
                </v-list-tile-content>
            </v-list-tile>
        </template>
    </v-list>
</template>