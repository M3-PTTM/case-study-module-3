<script>
    document.addEventListener('DOMContentLoaded', function () {
        var buyNowCarousel = document.querySelectorAll('.carousel .buy-now');
        buyNowCarousel.forEach(function (button) {
            button.addEventListener('click', handleBuyNowClick);
        });
        document.getElementById('content').addEventListener('click', function (event) {
            if (event.target.classList.contains('buy-now')) {
                event.preventDefault();
                handleBuyNowClick.call(event.target);
            }
        });

        function handleBuyNowClick() {
            var id = this.getAttribute('data-id');
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "/home?action=add-to-cart&id=" + id, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        var notification = document.getElementById('notification');
                        notification.style.display = 'block';
                        window.scrollTo({top: 0, behavior: 'smooth'});
                        setTimeout(function () {
                            notification.style.display = 'none';
                        }, 3000);
                    } else {
                        window.location.href = 'http://localhost:8080/login.jsp';
                    }
                }
            };
            xhr.send();
            console.log("ok");
        }
    });
</script>