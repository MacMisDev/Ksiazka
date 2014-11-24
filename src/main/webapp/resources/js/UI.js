/**
 * Created by Jarek on 2014-10-30.
 */



$(function () {

    //At page load after 0.5sec nice animation , pure native CSS !!!
    //$('#ribbon').hide();

    setTimeout(function () {
        $('#ribbon').addClass('animated tada');
        $('#loginBookLockedImage').addClass('animated bounce');
    },2000);

    // Sliding menu functionality, yep that only it, whole else is pure CSS :)
    $('#hideShowButton').click(function (e) {
        $('nav').toggleClass('navActive');
        $('#wrapper').toggleClass('wrapperActive');
    });


    console.log('dsfsf');

});