//test

$(document).ready(function(){

    $('#addBook').submit(function(event) {

        var ISBN = $('#ISBN').val();
        var author = $('#author').val();
        var description = $('#description').val();
        var pages = $('#pages').val();
        var publicationYear = $('#publicationYear').val();
        var publisher = $('#publisher').val();
        var title = $('#title').val();
        var json = { "ISBN" : ISBN, "author" : author, "description" : description, "pages" : pages, "publicationYear" : publicationYear, "publisher" : publisher, "title" : title };

        $.ajax({
            url: $("#addBook").attr("action"),
            data: JSON.stringify(json),
            type: "POST",
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
/*            beforeSend: function(xhr){
              xhr.setRequestHeader("Accept", "application/json");
              xhr.setRequestHeader("Content-Type", "application/json");
            },*/
            success: function(book) {
                var respContent = "";

                respContent += "<span class='success'>Dodano ";
                respContent += book.title;
                respContent += " do listy ksiazek oczekujacych na zatwierdzenie!</span>";

                $("#bookResponse").html(respContent);
            }
        });
        event.preventDefault();
    });
});