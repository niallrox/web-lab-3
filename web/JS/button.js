$('.x-button').each(function () {
    this.addEventListener('click', function () {

        $('.x-button').each(function () {
            if ($(this).hasClass("x-button-active")){
                $(this).toggleClass("x-button-active");
            }
        });
        $(this).toggleClass("x-button-active");
    })
})