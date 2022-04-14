jQuery('#tim-tour-01').ready(() => {
    document.querySelector('#tim-tour-01 select[name=\"tax-tour_category\"]').addEventListener('change', (e) => {
        inputSeachAjax('cat', e.target);
    })
    document.querySelector('#tim-tour-01 select[name=\"tax-tour-destination\"]').addEventListener('change', (e) => {
        inputSeachAjax('destination', e.target);
    })
    document.querySelector('#tim-tour-01 select[name=\"duration\"]').addEventListener('change', (e) => {
        inputSeachAjax('duration', e.target);
    })
    document.querySelector('#tim-tour-01 select[name=\"month\"]').addEventListener('change', (e) => {
        inputSeachAjax('month', e.target);
    });
});
jQuery('.tourListDetail').ready(() => {
    addAnimationItem();
})


function inputSeachAjax(type, e) {
    let page = 0;
    let params = '';
    if (type != null && type != -1) {
        jQuery('#tim-tour-01 select, #tim-tour-01 input').not(jQuery(e)).val('').attr('current', 0);
        e.dataset.current == null ? 0 : e.dataset.current;
        params = '&type=' + type + '&value=' + e.value;
    }
    else {
        if (e.dataset.type != null) {
            params = '&type=' + e.dataset.type + '&value=' + e.dataset.value;
        }
        page = e.dataset.current;
    }

    let loadBTN = jQuery('#loadmoreItemDetail')[0];
    let fetchI = jQuery('#loadmoreItemDetail')[0].dataset.fetch;
    let start = Number(page) + 1;
    if (start >= Number(loadBTN.dataset.total)) {
        jQuery('.loadMoreItem').hide();
    }

    fetch('https://vntrekkingtour.vn/wp-admin/admin-ajax.php?action=getMoreTourShiki&pageTour=' + start + '&fetch=' + fetchI + params)
        .then(res => res.json())
        .then(data => {
            if (data.result == 'success') {
                if (type != null && type != -1) {
                    e.dataset.current = Number(page) + 1;
                    loadBTN.dataset.type = type;
                    loadBTN.dataset.value = e.value;
                    jQuery('.tourListDetail .tourItem').fadeOut().remove();
                    jQuery(data.content).fadeOut().insertBefore(jQuery('#tourKeep')).fadeIn();
                }
                else
                    jQuery(data.content).fadeOut().insertBefore(jQuery('#tourKeep')).fadeIn();
                if (data.content != '')
                    jQuery('.loadMoreItem').show();
                loadBTN.dataset.current = Number(page) + 1;
                loadBTN.dataset.total = data.total;
                addAnimationItem();
            }
        }).catch(err => console.log(err));
}

function addAnimationItem() {
    jQuery('#loadmoreItemDetail').click((e) => {
        inputSeachAjax(-1, e.target);
    });
    jQuery('.tourListDetail .exploreTour').click(function (e) {
        let parent = jQuery(this).parent().parent().parent().parent();
        exploreOpen(parent);
    });
    jQuery('.tourListDetail .backTourInfo').click(function (e) {
        let parent = jQuery(this).parent().parent().parent();
        exploreClose(parent)
    });
    jQuery('.tourListDetail .openTourBehind').click((e) => {
        let parent = jQuery(e.target.parentElement.parentElement);
        exploreOpen(parent);
    });
    jQuery('.tourListDetail .openTourInfront').click((e) => {
        let parent = jQuery(e.target.parentElement.parentElement);
        exploreClose(parent)
    });
};

function exploreOpen(parent) {
    let tourInfront = parent.find('.tourItemInFontOf');
    let tourItemBehind = parent.find('.tourItemBehind');

    tourInfront.removeClass('openTourItemDetail').toggleClass('closeTourItemDetail');
    tourItemBehind.removeClass('closeTourItemDetail').toggleClass('openTourItemDetail');
}
function exploreClose(parent) {
    let tourInfront = parent.find('.tourItemInFontOf');
    let tourItemBehind = parent.find('.tourItemBehind');

    tourItemBehind.removeClass('openTourItemDetail').toggleClass('closeTourItemDetail');
    tourInfront.removeClass('closeTourItemDetail').toggleClass('openTourItemDetail');
}