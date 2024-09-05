<script src="/man/js/jquery.min.js"></script>
<script src="/man/js/popper.min.js"></script>
<script src="/man/js/bootstrap.bundle.min.js"></script>
<script src="/man/js/jquery-3.0.0.min.js"></script>
<script src="/man/js/plugin.js"></script>
<script src="/man/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="/man/js/custom.js"></script>
<script src="/man/js/owl.carousel.js"></script>
<script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- MDB -->
<script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.2/mdb.umd.min.js"
></script>
<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
        document.getElementById("main").style.marginLeft = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("main").style.marginLeft = "0";
    }

    $("#main").click(function () {
        $("#navbarSupportedContent").toggleClass("nav-normal")
    })

    function showMore() {
        var amount = document.getElementsByClassName("product").length;
        $.ajax({
            url: "/load",
            type: "get",
            data: {
                exits: amount
            },
            success: function (data) {
                var row = document.getElementById("content");
                row.innerHTML += data;
            },
            error: function (xhr) {
            }
        });
    }

    function searchByNameOrModel(param) {
        var keyword =param.value;
        $.ajax({
            url: "/search",
            type: "get",
            data: {
                keyword: keyword
            },
            success: function (data) {
                var row = document.getElementById("content");
                row.innerHTML = data;
            },
            error: function (xhr) {
            }
        });
    }
</script>
