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
    var url_admin = '/xadmin/account_entry/h5accountentry/';
    if (window.location.pathname == url_admin) {
        $.get('/admin_h5accountentry/', {}, function (data) {
            console.log(data);
            $('#all_sy_release_money').text(data[0]['sum_all_sy_release_money'])
            $('#all_sy_gift_money').text(data[0]['sum_all_sy_gift_money'])
            $('#chenmo_sy_release_money').text(data[0]['chenmo_sy_release_money'])
            $('#chenmo_sy_gift_money').text(data[0]['chenmo_sy_gift_money'])
        });
    }
});


