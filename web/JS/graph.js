let svg = document.querySelector("svg");
$('.svg').on('click', function () {
    if(checkR(y, r, x)) {
        let position = getMousePosition(svg, event);
        X = position.x;
        Y = position.y;
        setPointer(X, Y)
        X = (((X - 150) * r) / 100).toFixed(1);
        Y = (((150 - Y) * r) / 100).toFixed(1);
        $.ajax({
            type: "POST",
            url: "ControllerServlet",
            data: {Y: Y, X: X, R: r, xP: position.x, yP: position.y, com: 'svg'},
            success: function () {
                document.location.href = "result.jsp";
            }
        })
    }
});

function getMousePosition(svg, event) {
    let rect = svg.getBoundingClientRect();
    return {
        x: event.clientX - rect.left,
        y: event.clientY - rect.top
    };
}

function setPointer(x, y) {
    svg.insertAdjacentHTML("beforeend", `<circle class="smallDots" r="3" cx="${x}" cy="${y}" fill="white"></circle>`);
}