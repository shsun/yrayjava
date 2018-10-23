function getCookie(name) {
    var cookieValue = null;
    if (document.cookie && document.cookie !== '') {
        var cookies = document.cookie.split(';');
        for (var i = 0; i < cookies.length; i++) {
            var cookie = jQuery.trim(cookies[i]);
            // Does this cookie string begin with the name we want?
            if (cookie.substring(0, name.length + 1) === (name + '=')) {
                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                break;
            }
        }
    }
    return cookieValue;
}


$(function () {
    var url_admin = '/xadmin/black5/advertisement/';
    if (window.location.pathname == url_admin) {
        var field_landing_url = $('#result_list .field-landing_url');
        field_landing_url.css('color', '#447e9b');

        // var maxwidth=15;
        // if (field_landing_url.text().length > maxwidth) {
        //     field_landing_url.text().substring(0, maxwidth);
        //     field_landing_url.html(field_landing_url.html() + '..');
        // }

    }

    $(document).on("click", '#result_list .field-landing_url', function () {
        var open_url = $(this).text();
        console.log(open_url);
        window.open(open_url);

    });
});


