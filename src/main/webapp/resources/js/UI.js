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

        //Displays book list with data from passed JSON
        var fillBookList = function(data){
            //load template
            $('.spliterContent').load("/ksiazka/resources/partials/allBooks.html", function(){

                res = data.lastBooksAdded;

                $.each(res, function(i, el){
                    $('#bookList').append('<tr class="bookSelect" id=' + JSON.stringify(el.id) + '>' +
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
                    var j = i+1;
                    $('#booksPagination').append('<li class="booksPaginationBtn" id="' + i + '"><a>' + j + '</a></li>');
                }

            });
        };

        //Choosing book from book list:
        $(document).on("click", "tr.bookSelect", function(){
            var val = $(this).attr("id");
            $.ajax({
                url: '/ksiazka/book/' + val,
                type: "GET",
                dataType: "json",
                success: function(data){
                    console.log(JSON.stringify(data));
                    $('.spliterContent').load("/ksiazka/resources/partials/book.html", function(){

                        $('#description').val(JSON.stringify(data.description));
                        $('#title').val(JSON.stringify(data.title));
                        $('#author').val(JSON.stringify(data.author));
                        $('#publisher').val(JSON.stringify(data.publisher));
                        $('#year').val(JSON.stringify(data.publicationYear));
                        $('#isbn').val(JSON.stringify(data.isbn));

//                        $('.bookInfo').append(
//                           '<input value=' + JSON.stringify(data.title) + '></input>' +
//                           '<input value=' + JSON.stringify(data.author) + '></input>' +
//                           '<input value=' + JSON.stringify(data.publisher) + '></input>' +
//                           '<input value=' + JSON.stringify(data.publicationYear) + '></input>' +
//                           '<input value=' + JSON.stringify(data.isbn) + '></input>'
//                        );

                    });
                },
                error: function(err){
                    console.log(err);
                }
            });
        });

    });


});