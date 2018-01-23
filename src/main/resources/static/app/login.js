var App = {
    login: function () {
        $('#login-btn').on('click', function () {
            $.ajax({
                url: '/doLogin',
                data: $('#login-form').serialize(),
                method: 'POST',
                success: function (data) {
                    console.log(data)
                    if (data.success == true) {
                        alert("success:" + data.msg)
                        location.href = data.redirectUrl
                    } else {
                        alert("something maybe wrong:" + data.msg)
                    }
                },
                error: function (error) {
                    alert("error：" + JSON.stringify(error))
                }
            })
        })
    },
    doLogin: function () {
        App.login()
    }
}

$(App.doLogin())