<html>

<script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js">
</script>
<script src="http://www.veryhuo.com/uploads/Common/js/jQuery.md5.js">
</script>

<body>
<button>ssss</button>
<h2>Hello World!</h2>
<a href="index_2.jsp">index_2</a>
</body>

<script>
    $(function(){

        $("button").click(function(){
            $.ajax({
                url:"/user/login.do",
                type:"POST",
                data:{username: "kaibing", password: $.md5("123456")},
                success: function (data) {
                    alert(data);
                },
                error: function () {
                    alert(data);
                }
            });
        });

    });

</script>
</html>
