<template>
    <ul class="sidebar-menu" data-widget="tree">
        <li v-for="menu in menus"
            :key="menu.id"
            :class="{treeview:menu.children && menu.children.length > 0}">
            <router-link :to="menu.path">
                <menu-icon :icon-name="menu.icon"></menu-icon>
                <span>{{ menu.name }}</span>
                <span v-if="menu.children && menu.children.length > 0" class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                </span>
            </router-link>
            <ul class="treeview-menu" v-if="menu.children && menu.children.length > 0">
                <li v-for="submenu in menu.children">
                    <router-link :to="submenu.path">
                        <menu-icon :icon-name="submenu.icon"></menu-icon>
                        {{ submenu.name }}
                    </router-link>
                </li>
            </ul>
        </li>
    </ul>
</template>

<script>
    module.exports = {
        name: "sidebar-menu",
        props: {
            menus: {type: Array, required: true, default: []},
        },
        methods: {
            getValue: function (param, prefix, defaultValue) {
                if (param == null || param === '') {
                    return defaultValue;
                }

                return prefix + param;
            }
        }
    }
</script>

<style scoped>

</style>