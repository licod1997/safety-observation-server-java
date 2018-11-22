(function ( $ ) {
    "use strict";

    /*================================
    Preloader
    ==================================*/
    var preloader = $( '#preloader' );
    $( window ).on( 'load', function () {
        preloader.fadeOut( 'slow', function () {
            $( this ).hide();
        } );
    } );

    /*================================
    login form
    ==================================*/
    $( '.form-gp input' ).on( 'focus', function () {
        $( this ).parent( '.form-gp' ).addClass( 'focused' );
    } );
    $( '.form-gp input' ).on( 'focusout', function () {
        if ( $( this ).val().length === 0 ) {
            $( this ).parent( '.form-gp' ).removeClass( 'focused' );
        }
    } );

    /*================================
    Set cookies
    ==================================*/
    $.setCookie = function ( cname, cvalue, expire ) {
        var d = new Date();
        d.setTime( expire );
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    /*================================
    Get cookies
    ==================================*/
    $.getCookie = function ( cname ) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent( document.cookie );
        var ca = decodedCookie.split( ';' );
        for ( var i = 0; i < ca.length; i++ ) {
            var c = ca[i];
            while ( c.charAt( 0 ) == ' ' ) {
                c = c.substring( 1 );
            }
            if ( c.indexOf( name ) == 0 ) {
                return c.substring( name.length, c.length );
            }
        }
        return null;
    }

    /*================================
    Init variables
    ==================================*/
    var host = 'http://localhost:8080';
    var form_error = $( '#form_error' );
    form_error.hide();

    $( '#form_submit' ).click( function () {
        var username = $( '#input_username' ).val();
        var password = $( '#input_password' ).val();

        $.ajax( {
            url: host + '/token/generate-token',
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify( {
                username: username,
                password: password
            } ),
            success: function ( data, status, xhr ) {
                $.setCookie( 'auth', data['token'], data['expire'] );

                var token = $.getCookie( 'auth' );
                if ( token != null ) {
                    window.location.href = host + '/phan-hoi?' + token;
                }
            },
            error: function ( xhr, status, thrown ) {
                form_error.show();
            }
        } )

    } );

})( jQuery );