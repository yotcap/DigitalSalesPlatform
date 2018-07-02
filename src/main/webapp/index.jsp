<html>

<script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js">
</script>

<body>
<button>ssss</button>
<h2>Hello World!</h2>
</body>

<script>
    $(function(){

        $("button").click(function(){
            alert("sss");
            $.ajax({
                url:"/hello.do",
                type:"POST",
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
