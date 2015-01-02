/**
 * Created by Jarek on 2015-01-01.
 */
(function () {

    var animation = 'animated shake';
    var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';

    $(document).ready(function () {

        if ($('#registerForm').length) {

            $('#registerForm').parsley().subscribe('parsley:field:error', function (parsleyField) {

                parsleyField.$element.focusout(function () {
                    if (!parsleyField.isValid())
                        parsleyField.$element.addClass(animation).one(animationEnd, function () {
                            parsleyField.$element.removeClass(animation);
                        });
                });

            });
        } else {
            console.log("no #registerForm found");
        }
    });
}());
