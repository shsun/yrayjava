// $(function () {
//
//
//     var objUrl;
//     var img_html;
//     $("#adv_photo_value2").change(function () {
//         var img_div = $(".img_div");
//         var filepath = $("input[name='image_file']").val();
//
//         for (var i =0; i< $('#adv_photo_value2')[0].files.length; i++){
//             console.log($('#adv_photo_value2')[0].files[i])
//         }
//         for (var i = 0; i < this.files.length; i++) {
//             objUrl = getObjectURL(this.files[i]);
//
//             var extStart = filepath.lastIndexOf(".");
//             var ext = filepath.substring(extStart, filepath.length).toUpperCase();
//             /*
//              作者：z@qq.com
//             时间：2016-12-10
//             描述：鉴定每个图片上传尾椎限制
//             * */
//             if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
//                 $(".shade").fadeIn(500);
//                 $(".text_span").text("图片限于bmp,png,gif,jpeg,jpg格式");
//                 this.value = "";
//                 $(".img_div").html("");
//                 return false;
//             } else {
//                 /*
//                  若规则全部通过则在此提交url到后台数据库
//                  * */
//                 img_html = "<div class='isImg'><img src='" + objUrl + "' onclick='javascript:lookBigImg(this)' style='height: 100%; width: 100%;' /><button class='removeBtn' onclick='javascript:removeImg(this)'>x</button></div>";
//                 img_div.append(img_html);
//             }
//         }
//         return true;
//     });
//
//     /*
//      作者：z@qq.com
//     时间：2016-12-10
//     描述：鉴定每个浏览器上传图片url 目前没有合并到Ie
//      * */
//     function getObjectURL(file) {
//         var url = null;
//         if (window.createObjectURL != undefined) { // basic
//             url = window.createObjectURL(file);
//         } else if (window.URL != undefined) { // mozilla(firefox)
//             url = window.URL.createObjectURL(file);
//         } else if (window.webkitURL != undefined) { // webkit or chrome
//             url = window.webkitURL.createObjectURL(file);
//         }
//         //console.log(url);
//         return url;
//     }
// });
//
// /*
//  作者：z@qq.com
//  时间：2016-12-10
//   描述：上传图片附带删除 再次地方可以加上一个ajax进行提交到后台进行删除
//  * */
// function removeImg(r) {
//     $(r).parent().remove();
// }
//
