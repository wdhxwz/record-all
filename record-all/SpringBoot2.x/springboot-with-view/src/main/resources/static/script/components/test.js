(function ($, Vue, axios) {
    'use strict';
    // 注册Vue组件
    Vue.component("test", function (resolve) {
        axios.get("test.html", {
            accept: 'text/html, text/plain'
        }).then(function (data) {
            resolve({
                template: data.data
            })
        }).catch(function (error) {
            console.log(error);
        })
    });
}(jQuery, Vue, axios));