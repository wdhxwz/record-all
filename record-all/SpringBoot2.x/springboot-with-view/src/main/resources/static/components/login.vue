<template>
    <div class="user-login">
        <div class="user-login-bg" :style="{'background-image':`url(${backgroundImage})`}"></div>
        <div class="content-wrapper2">
            <h2 class="slogan">
                <!-- 欢迎使用 <br/> XXXX 管理系统-->
            </h2>
            <div class="form-container">
                <h4 class="form-title">登录</h4>
                <!-- /.login-logo -->
                <div class="login-box-body">
                    <!--<form>-->
                    <div class="form-group has-feedback">
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                        <input v-model="userName" type="text" class="form-control" placeholder="登录账号">
                    </div>
                    <div class="form-group has-feedback">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        <input v-model="password" type="password" class="form-control" placeholder="登录密码">
                    </div>
                    <div class="row">
                        <div class="col-xs-8">
                            <div class="form-group has-feedback">
                                <input v-model="imgCode" type="text" class="form-control" placeholder="验证码">
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <img :src="imgSrc" v-on:click="refreshCaptcha"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-8">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" v-model="rememberMe">记住我
                                </label>
                            </div>
                        </div>
                        <!-- /.col -->
                        <div class="col-xs-4">
                            <button v-on:click="login"
                                    class="btn btn-success btn-block btn-flat">登录
                            </button>
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- </form>-->
                    <a href="forgetPassword.html">忘记密码</a><br>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    module.exports = {
        name: "login",
        data() {
            return {
                backgroundImage: "images/login_bg.png",
                user: {
                    username: '',
                    password: '',
                },
                userName: "",
                password: "",
                rememberMe: false,
                productName: "",
                imgCode: "",
                imgSrc: ""
            };
        },
        created: function () {
            this.imgSrc = "/captcha.jpg";
            let self = this;
            toastr.options = {
                positionClass: "toast-top-center",
                showDuration: 5,
                hideDuration: 0
            };
            axios.get("/product/info?productId=" + config.productId)
                .then(function (response) {
                    let data = response.data;
                    if (data && data.code === 200 && data.data) {
                        self.productName = data.data;
                    } else {
                        self.productName = config.defaultProductName;
                    }
                    localStorage.setItem(config.projectNameKey, self.productName);
                }).catch(function (error) {
            })
        },
        methods: {
            submitBtn() {
                this.$refs['form'].validate((valid) => {
                    if (valid) {
                        this.$message({
                            message: '登录成功',
                            type: 'success',
                        });
                    }
                });
            },
            login: function () {
                let self = this;
                if (!this.checkLoginParam()) {
                    return false;
                }
                axios.post("/user/login", {
                    userName: this.userName,
                    password: md5(this.password),
                    rememberMe: this.rememberMe,
                    imgCode: this.imgCode
                }).then(function (response) {
                    var data = response.data;
                    if (data && data.code === 200) {
                        window.location = "/index.html";

                        // 设置用户名称
                        localStorage.setItem(config.userNameKey, self.userName)
                    } else {
                        toastr.clear();
                        toastr.info(data.message);
                    }
                }).catch(function (error) {
                    console.error(error);
                });
            },
            checkLoginParam: function () {
                toastr.clear();
                if ($.trim(this.userName) === "") {
                    toastr.warning("请填写用户名");
                    return false;
                }
                if ($.trim(this.password) === "") {
                    toastr.warning("请输入登录密码");
                    return false;
                }
                if ($.trim(this.imgCode) === "") {
                    toastr.warning("请输入图片验证码");
                    return false;
                }
                return true;
            },
            refreshCaptcha: function () {
                this.imgSrc = "/captcha.jpg?id=" + new Date() + Math.floor(Math.random() * 24);
            }
        },
    };
</script>

<style>
    .user-login {
    }

    .user-login .user-login-bg {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-size: cover;
    }

    .user-login .el-checkbox__label {
        color: #999;
        font-size: 13px;
    }

    .user-login .content-wrapper2 {
        position: absolute;
        top: -100px;
        left: 0;
        right: 0;
        bottom: 0;
        max-width: 1080px;
        margin: 0 auto;
        display: flex;
        justify-content: space-around;
        align-items: center;
        height: 100%;
    }

    .user-login .slogan {
        text-align: center;
        color: #0f0f0f;
        font-size: 36px;
        letter-spacing: 2px;
        line-height: 48px;
    }

    .form-container {
        display: flex;
        justify-content: center;
        flex-direction: column;
        padding: 15px 15px;
        background-color: #fff;
        border-radius: 6px;
        box-shadow: 1px 1px 2px #eee;
    }

    .el-form-item2 {
        margin-bottom: 15px;
    }

    .form-Item {
        position: relative;
        flex-direction: column;
    }

    .form-line {
        position: relative;
        display: flex;
        align-items: center;
    }

    .form-line :after {
        content: '';
        position: absolute;
        bottom: 3px;
        left: 0;
        width: 100%;
        box-sizing: border-box;
        border-width: 1px;
        border-style: solid;
        border-top: 0;
        border-left: 0;
        border-right: 0;
        border-color: #dcdcdc;
        border-radius: 0;
    }

    .el-input {
        width: 240px;
    }

    input {
        border: none;
        margin: 0;
        padding-left: 10px;
        font-size: 13px;
    }

    .form-title {
        margin: 0 0 20px;
        text-align: center;
        color: #3080fe;
        letter-spacing: 12px;
        font-size: 25px;
    }

    .input-icon {
        color: #999;
    }

    .checkbox {
        margin-left: 5px;
    }

    .submit-btn {
        margin-bottom: 25px;
        width: 100%;
        background: #3080fe;
        border-radius: 28px;
    }

    .tips {
    }

    .link {
        color: #999;
        text-decoration: none;
        font-size: 13px;
    }

    .line {
        color: #dcd6d6;
        margin: 0 8px;
    }

    @media screen and (max-width: 720px) {
        .user-login {
        }

        .content-wrapper {
            margin: 20px auto;
            top: 40px;
            max-width: 300px;
            display: block;
        }

        .slogan {
            color: #666;
            font-size: 22px;
            line-height: 30px;
        }

    }
</style>