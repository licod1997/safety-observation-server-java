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
    sidebar collapsing
    ==================================*/
    $( '.nav-btn' ).on( 'click', function () {
        $( '.page-container' ).toggleClass( 'sbar_collapsed' );
    } );

    /*================================
    Start Footer resizer
    ==================================*/
    var e = function () {
        var e = (window.innerHeight > 0 ? window.innerHeight : this.screen.height) - 5;
        (e -= 67) < 1 && (e = 1), e > 67 && $( ".main-content" ).css( "min-height", e + "px" )
    };
    $( window ).ready( e ), $( window ).on( "resize", e );

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
    sidebar collapsing
    ==================================*/
    $( '.nav-btn' ).on( 'click', function () {
        $( '.page-container' ).toggleClass( 'sbar_collapsed' );
    } );

    /*================================
    Start Footer resizer
    ==================================*/
    var e = function () {
        var e = (window.innerHeight > 0 ? window.innerHeight : this.screen.height) - 5;
        (e -= 67) < 1 && (e = 1), e > 67 && $( ".main-content" ).css( "min-height", e + "px" )
    };
    $( window ).ready( e ), $( window ).on( "resize", e );

    /*================================
    sidebar menu
    ==================================*/
    $( "#menu" ).metisMenu();
    $( "#menu2" ).metisMenu();

    /*================================
    slimscroll activation
    ==================================*/
    $( '.menu-inner' ).slimScroll( {
        height: 'auto'
    } );
    $( '.nofity-list' ).slimScroll( {
        height: '435px'
    } );
    $( '.timeline-area' ).slimScroll( {
        height: '500px'
    } );
    $( '.recent-activity' ).slimScroll( {
        height: 'calc(100vh - 114px)'
    } );
    $( '.settings-list' ).slimScroll( {
        height: 'calc(100vh - 158px)'
    } );

    /*================================
    stickey Header
    ==================================*/
    $( window ).on( 'scroll', function () {
        var scroll = $( window ).scrollTop(),
            mainHeader = $( '#sticky-header' ),
            mainHeaderHeight = mainHeader.innerHeight();

        // console.log(mainHeader.innerHeight());
        if ( scroll > 1 ) {
            $( "#sticky-header" ).addClass( "sticky-menu" );
        } else {
            $( "#sticky-header" ).removeClass( "sticky-menu" );
        }
    } );

    /*================================
    Slicknav mobile menu
    ==================================*/
    $( 'ul#nav_menu' ).slicknav( {
        prependTo: "#mobile_menu"
    } );

    /*================================
    slider-area background setting
    ==================================*/
    $( '.settings-btn, .offset-close' ).on( 'click', function () {
        $( '.offset-area' ).toggleClass( 'show_hide' );
        $( '.settings-btn' ).toggleClass( 'active' );
    } );

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
    var token = $.getCookie( 'auth' );

    $( '.need-auth-url' ).each( function () {
        if ( $( this ).attr( 'href' ).indexOf( '?' ) > -1 ) {
            $( this ).attr( 'href', $( this ).attr( 'href' ) + '&' + token );
        } else {
            $( this ).attr( 'href', $( this ).attr( 'href' ) + '?' + token );
        }
    } );

    $( '#multipleUploadForm' ).submit( function ( e ) {
        e.preventDefault();
        var formData = new FormData( this );
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/tai-len-nhieu-tap-tin-xml?" + token,
            data: formData,
            processData: false,
            contentType: false,
            success: function ( response ) {
                alert( "Tải lên thành công!" );
            },
            error: function ( error ) {
                alert( "Tải lên thất bại!" );
                console.log( error );
            }
        })
    } );
})( jQuery );