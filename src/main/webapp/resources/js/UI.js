/**
 * Created by Jarek on 2014-10-30.
 */



$(function () {

    //At page load nice animation , pure native CSS !!!
    $('#ribbon').addClass('animated zoomIn');
    $('#loginBookLockedImage').addClass('animated flipInY');
    // Sliding menu functionality, yep that only it, whole else is pure CSS :)
    $('#hideShowButton').click(function (e) {
        $('nav').toggleClass('navActive');
        $('#wrapper').toggleClass('wrapperActive');
    });


    console.log('dsfsf');

});