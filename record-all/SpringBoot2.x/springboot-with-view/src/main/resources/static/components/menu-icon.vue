<template>
    <i :class="getClassName"></i>
</template>

<script>
    module.exports = {
        name: "menu-icon",
        data() {
            return {
                defaultName: "fa fa-circle-o"
            }
        },
        props: {
            iconName: {
                type: String,
                required: false
            }
        },
        computed: {
            getClassName: function () {
                console.log(this.iconName);
                if (this.iconName === undefined || this.iconName === "") {
                    return this.defaultName;
                }
                return this.iconName
            }
        }
    }
</script>

<style>
    .hello {
        background-color: #ffe;
    }
</style>