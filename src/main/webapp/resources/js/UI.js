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

        $('#menuBooks').click(function () {
            $.ajax({
                url: '/ksiazka/book/list',
                type: 'GET',
                dataType: 'json',
                success: function(res) {
                    $('.spliterContent').html("");
                    data = res.lastBooksAdded;
                    $.each(data, function(i, el){
                        $('.spliterContent').append($('<div>',{
                            text: (JSON.stringify(el))
                        }));
                        console.log(JSON.stringify(el.author));
                    });
                },
                error: function(err){
                    console.log(err);
                }
            });
        });
    });


});