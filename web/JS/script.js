$(function () {
    $('.resultFromPhp').remove();
    $('.smallDots').remove();
    $.ajax({
        type: "POST",
        url: "ControllerServlet",
        data: {com: 'old'},
        success: function (answer) {
            $('.result').append(answer);
        }
    })
    $.ajax({
        type: "POST",
        url: "ControllerServlet",
        data: {com: 'oldG'},
        success: function (answer) {
            $('.svg-graph').append(answer);
        }
    })
    $('.game').on('click', function () {
        setTimeout(func, 20000)
        let res = 0;
        $('#point').on('click', function () {
            x1 = randomInteger(-1.5, 1.5);
            y1 = randomInteger(-1.5, 1.5);
            r1 = randomInteger(1, 2);
            setPoint(y1, x1, r1);
            res += 1;
        })

        function func() {
            alert(res);
            $('#point').attr('onclick', '').unbind('click');
        }
    })
    $('#send').on('click', function () {
        xyr();
        if (checkY(y, r, x) && checkR(y, r, x)) {
            $.ajax({
                type: "POST",
                url: "ControllerServlet",
                data: {Y: y, X: x, R: r, com: 'send'},
                success: function () {
                    document.location.href = "result.jsp";
                }
            })
        }
    })
    $('#clear').on('click', function () {
        $.ajax({
            type: "POST",
            url: "ControllerServlet",
            data: {com: 'clear'},
            success: function () {
                $('.resultFromPhp').remove();
                $('.smallDots').remove();
            }
        })
    })
    $('.x-button').on('click', function () {
        xyr()
        checkY(y, r, x)
        checkR(y, r, x)
    })
    $('.yInput').on('input', function () {
        xyr()
        checkY(y, r, x)
        checkR(y, r, x)
    })
    $('.rInput').on('input', function () {
        xyr()
        checkY(y, r, x)
        checkR(y, r, x)
    })
})

function xyr() {
    y = $('.yInput').val()
    r = $('.rInput').val()
    $('.x-button').each(function () {
        if ($(this).hasClass('x-button-active')) {
            x = $(this).find('span').text();
        }
    });
}

function checkY(y, r, x) {
    if (!y) {
        showErrorY('<br>Вы не ввели Y')
        setPoint(0, 0, 1)
        return false
    } else if (y <= -3 || y >= 3) {
        showErrorY('<br>Y не принадлежит [-3:3]')
        setPoint(0, 0, 1)
        return false
    } else if (isNaN(y)) {
        showErrorY('<br>Y должен быть числом')
        setPoint(0, 0, 1)
        return false
    } else {
        $('#error2').html('')
        return true
    }
}

function checkR(y, r, x) {
    if (r == 0) {
        showErrorR('<br>R не выбран')
        setPoint(0, 0, 1)
        return false
    } else if (checkY(y, r, x)) {
        $('#error1').html('')
        setPoint(y, x, r)
        return true
    } else {
        $('#error1').html('')
        setPoint(0, 0, 1)
        return true
    }
}

function showErrorR(message) {
    $('#error1').css({'color': 'white', 'font-size': 'medium'}).html(message)
}

function showErrorY(message) {
    $('#error2').css({'color': 'white', 'font-size': 'medium'}).html(message)
}

function setPoint(y, x, r) {
    $('#point').attr("cx", (x * 100 / r + 150))
        .attr("cy", (y * -100 / r + 150));
}

function randomInteger(min, max) {
    return Math.random() * (max - min) + min;
}