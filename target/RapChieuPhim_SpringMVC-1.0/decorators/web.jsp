<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ</title>
    <link href="<c:url value='/template/web/css/style1.css' />" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
</head>

<body class="bg-dark">
    <%@ include file="/common/web/header.jsp" %>
    <dec:body />
    <%@ include file="/common/web/footer.jsp" %>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const prevButton = document.getElementById('prevButton');
            const nextButton = document.getElementById('nextButton');
            const carousels = document.querySelectorAll('.showing .carousel');
            let isNextButtonClicked = false;

            carousels.forEach((carousel, index) => {
                carousel.addEventListener('slide.bs.carousel', function (event) {
                    const direction = event.direction;
                    const slideTo = event.to;

                    if (isNextButtonClicked) {
                        if (direction === 'right') {
                            carousels.forEach((otherCarousel, otherIndex) => {
                                if (otherIndex !== index) {
                                    const carouselInstance = bootstrap.Carousel.getInstance(otherCarousel);
                                    carouselInstance.next();
                                }
                            });
                        } else {
                            carousels.forEach((otherCarousel, otherIndex) => {
                                if (otherIndex !== index) {
                                    const carouselInstance = bootstrap.Carousel.getInstance(otherCarousel);
                                    carouselInstance.prev();
                                }
                            });
                        }
                    }

                    isNextButtonClicked = false;
                });
            });

            prevButton.addEventListener('click', function () {
                carousels.forEach(carousel => {
                    const carouselInstance = bootstrap.Carousel.getInstance(carousel);
                    carouselInstance.prev();
                });
            });

            nextButton.addEventListener('click', function () {
                carousels.forEach(carousel => {
                    const carouselInstance = bootstrap.Carousel.getInstance(carousel);
                    carouselInstance.next();
                });
            });
        });

        //Hover vào 1 trong 3 carouselslide thì 2 cái còn lại dừng chạy
        document.addEventListener("DOMContentLoaded", function() {
            const hoverableItems = document.querySelectorAll(".hoverable");

            hoverableItems.forEach(function(item) {
                item.addEventListener("mouseenter", function() {
                    pauseAllCarousels();
                });
                item.addEventListener("mouseleave", function() {
                    startAllCarousels();
                });
            });

            function pauseAllCarousels() {
                const carousels = document.querySelectorAll(".carousel");
                carousels.forEach(function(carousel) {
                    const carouselInstance = bootstrap.Carousel.getInstance(carousel);
                    if (carouselInstance) {
                        carouselInstance.pause();
                    }
                });
            }

            function startAllCarousels() {
                const carousels = document.querySelectorAll(".carousel");
                carousels.forEach(function(carousel) {
                    const carouselInstance = bootstrap.Carousel.getInstance(carousel);
                    if (carouselInstance) {
                        carouselInstance.cycle();
                    }
                });
            }
        });

        document.getElementById('next').onclick = function() {
            let lists = document.querySelectorAll('.commingsoon-container .commingsoon-item');
            document.getElementById('commingsoon-slide').appendChild(lists[0]);
        }
        document.getElementById('prev').onclick = function() {
            let lists = document.querySelectorAll('.commingsoon-container .commingsoon-item');
            document.getElementById('commingsoon-slide').prepend(lists[lists.length - 1]);
        }
    </script>
</body>

</html>
