const eyes = document.querySelectorAll('.eyes span')
const hand = document.querySelector('.hands span:first-child')
const mouse = document.querySelector('.mouth')
let smoke = document.querySelector('.smoke')
const h = document.querySelector('#hours')
const m = document.querySelector('#minutes')
const s = document.querySelector('#seconds')
const dayy = document.querySelector('#day')
const mess = document.querySelector('#mes')
const godd = document.querySelector('#god')
const svet = document.querySelector('#svet')
const jiga = document.querySelector('.jiga')
let svett = true
let hh
let mm
let ss
let dayNow
let mes
let year

time()
pokur()
setInterval(time, 5000);
light()
pokur()
close();
chirk();

function pokur() {
    document.querySelector('#pokur').addEventListener('click', function () {
        jiga.classList.add('jigaEst');
        document.querySelector('#chirk').style.display = 'block'
        document.querySelector('#close').style.display = 'block'
        document.querySelector('#pokur').style.display = 'none'
    })
}

function close() {
    document.querySelector('#close').addEventListener('click', function () {
        document.querySelector('.jiga').classList.remove('jigaEst')
        document.querySelector('#pokur').style.display = 'block'
    })
}

function chirk() {
    document.querySelector('#chirk').addEventListener('click', function jig() {
        document.querySelector('#ysk').src = "content/khuy.jpg"
        setTimeout(function () {
            if (getRandomInRange(1, 5) === 1) {
                document.querySelector('#chirk').style.display = 'none'
                document.querySelector('#close').style.display = 'none'
                document.querySelector('#ysk').src = "content/khuy_3.jpg"
                setTimeout(function () {
                    pokuring()
                    document.querySelector('.jiga').classList.remove('jigaEst')
                }, 2000)
            } else {
                document.querySelector('#ysk').src = "content/khuy_2.jpg"
                setTimeout(function (){
                    document.querySelector('#ysk').src = "content/khuy.jpg"
                }, 200)
            }
        }, 50)
    })
}

function time() {
    let day = new Date()
    hh = (day.getHours() < 10) ? '0' + day.getHours() + ':' : day.getHours() + ':';
    mm = (day.getMinutes() < 10) ? '0' + day.getMinutes() + ':' : day.getMinutes() + ':';
    ss = (day.getSeconds() < 10) ? '0' + day.getSeconds() : day.getSeconds();
    dayNow = (day.getDate() < 10) ? '0' + day.getDate() + '/' : day.getDate() + '/';
    mes = (day.getMonth() < 10) ? '0' + day.getMonth() + '/' : day.getMonth()+1 + '/';
    year = day.getFullYear();
    h.innerHTML = hh
    m.innerHTML = mm
    s.innerHTML = ss
    dayy.innerHTML = dayNow;
    mess.innerHTML = mes;
    godd.innerHTML = year;
}

function light() {
    svet.addEventListener('click', function () {
        document.querySelector('.jiga').classList.remove('jigaEst')
        if (svett) {
            document.querySelector('body').style.backgroundColor = 'black'
            document.querySelector('.ghost').style.display = 'block'
            document.querySelector('#pokur').style.display = 'block'
            document.querySelector('.cap').style.backgroundColor = 'darkgray'
            document.querySelector('.cap').style.color = 'black'
            document.querySelector('.clock').style.boxShadow = '0 0 10px 15px #fc6b01'
            document.querySelector('.clock').style.color = '#fc6b01'
            svett = false
        } else {
            document.querySelector('body').style.backgroundColor = 'white'
            document.querySelector('.ghost').style.display = 'none'
            document.querySelector('#pokur').style.display = 'none'
            document.querySelector('.cap').style.color = 'white'
            document.querySelector('.cap').style.backgroundColor = '#900012'
            document.querySelector('.clock').style.boxShadow = 'none'
            document.querySelector('.clock').style.color = 'black'
            svett = true
        }
    })
}

function getRandomInRange(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

function pokuring() {
    hand.style.transform = "translate(60px, -17px)";
    mouse.style.width = "10px";
    mouse.style.height = "10px";
    mouse.style.borderRadius = "20px";
    eyes[0].style.height = "10px";
    eyes[1].style.height = "10px";
    setTimeout(function () {
        hand.style.transform = "translate(0px, 0px)";
        mouse.style.transform = "translateX(-50px)";
        smoke.style.display = 'block';
    }, 2000)
    setTimeout(function () {
        mouse.style.width = "40px";
        mouse.style.height = "20px";
        mouse.style.transform = "translateX(0px)"
        mouse.style.borderRadius = "0 0 20px 20px";
        eyes[0].style.height = "24px";
        eyes[1].style.height = '24px';
        smoke.style.display = 'none';
    }, 3000)
    setTimeout(()=>{
        clearInterval(pok)
        document.querySelector('#ysk').src = "content/khuy.jpg"
        document.querySelector('#pokur').style.display = 'block'
    }, 20000)
    let pok = setInterval(function () {
        hand.style.transform = "translate(60px, -17px)";
        mouse.style.width = "10px";
        mouse.style.height = "10px";
        mouse.style.borderRadius = "20px";
        eyes[0].style.height = "10px";
        eyes[1].style.height = "10px";
        setTimeout(function () {
            hand.style.transform = "translate(0px, 0px)";
            mouse.style.transform = "translateX(-50px)";
            smoke.style.display = 'block';
        }, 2000)
        setTimeout(function () {
            mouse.style.width = "40px";
            mouse.style.height = "20px";
            mouse.style.transform = "translateX(0px)"
            mouse.style.borderRadius = "0 0 20px 20px";
            eyes[0].style.height = "24px";
            eyes[1].style.height = '24px';
            smoke.style.display = 'none';
        }, 3000)
    }, 5000)
}