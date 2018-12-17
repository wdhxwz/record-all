(function ($, Vue, axios) {
    'use strict';
    // 注册Vue组件
    Vue.component("sidebar-menu", function (resolve) {
        axios.get("tpls/sidebar-menu.html", {
            accept: 'text/html, text/plain'
        }).then(function (data) {
            resolve(vueOpts(data.data));
        }).catch(function (error) {
            console.log(error);
        });

        function vueOpts(htmlTpl) {
            var options = {
                props: {
                    menus: {type: Array, required: true, default: []},
                },
                template: htmlTpl,
                // data: function () {
                //     return {
                //         currentUrl: ""
                //     }
                // },
                methods: {
                    getValue: function (param, prefix, defaultValue) {
                        if (param == null || param === '') {
                            return defaultValue;
                        }

                        return prefix + param;
                    }
                }
                // created: function () {
                //     var vModel = this;
                //     this.currentUrl = gutils.getHash();
                //     $.each(this.entitys, function (index, item) {
                //         activeTreeViewClass(vModel, item, null);
                //     });
                // }
            };

            return options;
        }
    });
}(jQuery, Vue, axios));