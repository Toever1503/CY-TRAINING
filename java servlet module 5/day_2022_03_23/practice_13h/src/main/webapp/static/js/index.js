jQuery(document).ready(function ($) {
    // $('#closeBtn').click(function () {
    //     $('#detailProduct').hide();
    // });
    document.querySelectorAll('.listItems .card .card-img-top').forEach(function (item) {
        $(item).click(function () {
            console.log(item.parent().parent());
            // $('#detailProduct img')[0].src = $(item).find('img')[0].src;
            // $('#detailProduct h3.text-center').text($(item).find('.card-body .text-center').text());
            // $('#detailProduct').css('display', 'block');
        });
    });
    // $('#detailProduct').click(() => {
    //     $('#detailProduct').hide();
    // })
    // $('#detailProduct .productInfor').click(function (e) {
    //     e.stopPropagation();
    // });
})