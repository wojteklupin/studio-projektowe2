import Vue from "vue";
import VueRouter from "vue-router";
import Deals from "../views/Deals.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        component: Deals,
    },
    {
        path: "/stats",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import(/* webpackChunkName: "about" */ "../views/Stats.vue"),
    }
];

const router = new VueRouter({
    routes,
});

export default router;
