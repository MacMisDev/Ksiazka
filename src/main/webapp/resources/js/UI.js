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

                    fillBookList(res);

                },
                error: function(err){
                    console.log(err);
                }
            });
        });

        //BOOK LIST PAGINATION
        $(document).on("click", 'div.pagination li.booksPaginationBtn', function(){

            var val = $(this).attr("id");

            $.ajax({
                url: '/ksiazka/book/list',
                type: "POST",
                data: JSON.stringify({lastBooksAddedPage: val }),
                contentType: "application/json",
                success: function(data){
                    fillBookList(data);
                }
            });
        });

        var fillBookList = function(data){
            //load template
            $('.spliterContent').load("/ksiazka/resources/partials/allBooks.html", function(){

                res = data.lastBooksAdded;

                $.each(res, function(i, el){
                    $('#bookList').append('<tr class="bookSelect" id=' + JSON.stringify(el.isbn) + '>' +
                            '<td>' + JSON.stringify(el.title) +
                            '<td>' + JSON.stringify(el.author) +
                            '<td>' + JSON.stringify(el.publisher) +
                            '<td>' + JSON.stringify(el.publicationYear) +
                            '<td>' + JSON.stringify(el.isbn) +
                            '</tr>'
                    );
                });

                res = data.maxPages;
                //temporary pagination (lists all pages)
                for( var i = 0; i <= res; i++ ){
                    $('#booksPagination').append('<li class="booksPaginationBtn" id="' + i + '"><a>' + i + '</a></li>');
                }

            });
        };

    });


});