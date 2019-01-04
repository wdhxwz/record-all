const test2 = {template: '<div>foo2</div>'};
const routes = [
    {path: '/test', component: httpVueLoader("view/test.vue")},
    {path: '/test2', component: test2}
];
const router = new VueRouter({
    routes: routes
});

router.beforeEach((to, from, next) => {
    console.log(to);
    console.log(from);
    console.log(next);
    next();
})