$(function () {
    drawPoints();
    $('input').on('input', function () {
        setTimeout(function () {
            xyr()
            setPoint(x, y, r)
        }, 1000)
    })
    $('.commandLinks').on('click', function () {
        setTimeout(function () {
            xyr()
            setPoint(x, y, r)
            $(".smallDots").remove()
            drawPoints()
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

    function drawPoints() {
        let rows = document.getElementById('table_data').childNodes;
        rows.forEach(row => {
            let a = row.childNodes;
            let b = a[0].innerHTML;
            let c = a[1].innerHTML;
            let d = document.querySelector('.Rlabel').innerHTML;
            let res;
            if (a[3].innerHTML === "True") {
                res = "green";
            } else {
                res = "red";
            }
            b = (b * 100 / d + 150)
            c = (c * -100 / d + 150)
            let svg = document.querySelector("svg");
            svg.insertAdjacentHTML("beforeend", `<circle class="smallDots" r="3" cx="${b}" cy="${c}" fill="${res}"></circle>`);

        })
    }

    function randomInteger(min, max) {
        return Math.random() * (max - min) + min;
    }
})