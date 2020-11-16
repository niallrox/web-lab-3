$(function () {
    $('input').on('input', function (){
        setTimeout(function () {
            xyr()
            setPoint(x, y, r)
        }, 1000)
    })
    $('.commandLinks').on('click', function () {
        setTimeout(function () {
            xyr()
            setPoint(x, y, r)
        }, 1000)
    })
    $('.ui-slider-handle').on('click', function () {
        setTimeout(function () {
            xyr()
            setPoint(x, y, r)
        }, 1000)
    })
    $('.ui-slider').on('click', function () {
        setTimeout(function () {
            xyr()
            setPoint(x, y, r)
        }, 2000)
    })



    function xyr() {
        x = document.querySelector('.Xlabel').innerHTML
        y = document.querySelector('.Ylabel').innerHTML
        r = document.querySelector('.Rlabel').innerHTML
    }

    function setPoint(x, y, r) {
        $('#point').attr("cx", (x * 100 / r + 150))
            .attr("cy", (y * -100 / r + 150));
    }

    function randomInteger(min, max) {
        return Math.random() * (max - min) + min;
    }
})