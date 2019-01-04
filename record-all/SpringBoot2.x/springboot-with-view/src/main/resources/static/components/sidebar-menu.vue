<template>
    <ul class="sidebar-menu" data-widget="tree">
        <li v-for="menu in menus"
            :key="menu.menuId"
            :class="{active:menu.open,treeview:menu.list && menu.list.length > 0}">
            <a :href="getValue(menu.url,'','#')">
                <i :class="getValue(menu.icon,'fa fa-','fa fa-circle-o')"></i>
                <span>{{ menu.name }}</span>
                <span v-if="menu.list && menu.list.length > 0" class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                </span>
            </a>
            <ul class="treeview-menu" v-if="menu.list && menu.list.length > 0">
                <li v-for="submenu in menu.list">
                    <a :href="getValue(submenu.url,'','#')">
                        <menu-icon icon-name="fa fa-share"></menu-icon>
                        {{ submenu.name }}
                    </a>
                </li>
                <li>
                    <router-link to="/test">
                        <i class="fa fa-circle-o"></i>
                        首页
                    </router-link>
                </li>
                <li>
                    <router-link to="/test2">
                        <i class="fa fa-circle-o"></i>
                        首页2
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