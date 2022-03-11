jQuery(document).ready(function ($) {
    $('#closeBtn').click(function () {
        $('#detailProduct').hide();
    });
    document.querySelectorAll('.listItems .card').forEach(function (item) {
        $(item).click(function () {
            $('#detailProduct img')[0].src = $(item).find('img')[0].src;
            $('#detailProduct h3.text-center').text($(item).find('.card-body .text-center').text());
            $('#detailProduct').css('display', 'block');
        });
    });
    $('#detailProduct').click(() => {
        $('#detailProduct').hide();
    })
    $('#detailProduct .productInfor').click(function (e) {
        e.stopPropagation();
    });
})