// import Swiper from 'https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.esm.browser.min.js';
const $ = selector => document.querySelectorAll(selector);

window.onload = slider_activate();

let swiper;

function slider_activate() {
    if ($('.about_slider').length > 0) {
        swiper = new Swiper(
            '.about_slider',
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