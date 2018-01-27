var App = {
    register: function () {
        $('#login-btn').on('click', function () {
            $.ajax({
                url: '/wiki/api/doRegister',
                data: $('#login-form').serialize(),
                method: 'POST',
                dataType: 'json',
                success: function (data) {
                    console.log(data)
                    if (data.success == true) {
                        alert(data.msg)
                        location.href = "/"
                    } else {
                        alert(data.msg)
                    }
                },
                error: function (error) {
                    alert("error：" + JSON.stringify(error))
                }
            })
        })
    },
    doRegister: function () {
        App.register()
    }
}

$(App.doRegister())