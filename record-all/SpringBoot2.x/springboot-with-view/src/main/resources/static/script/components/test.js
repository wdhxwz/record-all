(function ($, Vue, axios) {
    'use strict';
    // 注册Vue组件
    Vue.component("test-a", function (resolve) {
        axios.get("tpls/test.html", {
            accept: 'text/html, text/plain'
        }).then(function (data) {
            console.log(data.data);
            resolve({
                template: data.data
            })
        }).catch(function (error) {
            console.log(error);
        })
    });
}(jQuery, Vue, axios));