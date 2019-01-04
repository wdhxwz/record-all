(function ($, Vue, axios) {
    'use strict';
    // 注册Vue组件
    Vue.component("basic-container", function (resolve) {
        axios.get("tpls/basic-container.html", {
            accept: 'text/html, text/plain'
        }).then(function (data) {
            resolve(vueOpts(data.data));
        }).catch(function (error) {
            console.log(error);
        });

        function vueOpts(htmlTpl) {
            let options = {
                template: htmlTpl
            };

            return options;
        }
    });
}(jQuery, Vue, axios));