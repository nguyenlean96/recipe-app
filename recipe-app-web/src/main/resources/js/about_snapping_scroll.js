const $ = selector => document.querySelectorAll(selector);

window.onload = slider_activate();

let swiper;

function slider_activate() {
    if ($('.teresahaidong_slider').length > 0) {
        swiper = new Swiper(
            '.teresahaidong_slider',
            {
                direction: 'vertical',
                sliderPerView: 1,
                spaceBetween: 0,
                mousewheel: true,
                pagination: {
                    el: '.swiper-pagination',
                    type: 'progressbar',
                },
            }
        );
    }
}