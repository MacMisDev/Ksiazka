/**
 * Created by Jarek on 2014-10-30.
 */

$(function () {
    $(document).ready(function () {
        /*   setTimeout(function () {
         //At page load after 0.5sec nice animation , pure native CSS !!!
         // $('#ribbon').addClass('animated tada');
         // $('#loginBookLockedImage').addClass('animated bounce');
         },2000);*/

        // Sliding menu functionality, yep that only it, whole else is pure CSS :)
        $('#hideShowButton').click(function (e) {
            $('nav').toggleClass('navActive');
            $('#wrapper').toggleClass('wrapperActive');
        });


        $('#editName').click(function () {
            $('#editName').toggleClass('editActive');
            $("#name").prop('disabled', !$("#name").prop('disabled'));
            $('#saveName').toggle('slow', function () {
                //Buttons shown, yay!
            });
        });

        $('#editUsername').click(function () {
            $('#editUsername').toggleClass('editActive');
            $("#username").prop('disabled', !$("#username").prop('disabled'));
            $('#saveUsername').toggle('slow', function () {
            });
        });

        $('#editMail').click(function () {
            $('#editMail').toggleClass('editActive');
            $("#email").prop('disabled', !$("#email").prop('disabled'));
            $('#saveMail').toggle('slow', function () {
            });
        });
    });


});