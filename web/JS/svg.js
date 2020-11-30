$(function () {
    let color
    const svg = document.querySelector('svg')
    let XG = document.querySelector(".invX");
    let YG = document.querySelector(".invY");
    let RG = document.querySelector(".invR");
    let RGG = document.querySelector('.Rlabel');
    $('#fu').on('click', function () {
        if(RG.value) {
            let position = getMousePosition(svg, event);
            X = position.x;
            Y = position.y;
            RG.value = 4
            XG.value = (((X - 150) * RG.value) / 100).toFixed(1);
            YG.value = (((150 - Y) * RG.value) / 100).toFixed(1);
            if(result(XG.value, YG.value, RG.value) === "True"){
                color = 'green'
            } else {
                color = 'red'
            }
            setPointer(X, Y)
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
        svg.insertAdjacentHTML("beforeend", `<circle r="3" cx="${x}" cy="${y}" fill="${color}"></circle>`);
    }
    function result(x, y, r) {
        if (x >= 0 && y >= 0 && y <= (-0.5 * x + r / 2)) {
            return "True";
        }
        if (x <= 0 && y >= 0 && (x * x + y * y) <= (r * r) / 4) {
            return "True";
        }
        if (y <= 0 && y >= -r && x >= 0 && x <= r / 2) {
            return "True";
        }
        return "False";
    }
})