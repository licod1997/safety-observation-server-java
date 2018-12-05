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
    sidebar menu
    ==================================*/
    $( "#menu" ).metisMenu();

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
    form bootstrap validation
    ==================================*/
    $( '[data-toggle="popover"]' ).popover()

    /*------------- Start form Validation -------------*/
    window.addEventListener( 'load', function () {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName( 'needs-validation' );
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call( forms, function ( form ) {
            form.addEventListener( 'submit', function ( event ) {
                if ( form.checkValidity() === false ) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add( 'was-validated' );
            }, false );
        } );
    }, false );

    /*================================
    Slicknav mobile menu
    ==================================*/
    $( 'ul#nav_menu' ).slicknav( {
        prependTo: "#mobile_menu"
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

    var form_error = $( '#form_error' );
    var form_success = $( '#form_success' );
    form_error.hide();
    form_success.hide();

    $( '#form_submit' ).click( function () {
        var error = [];
        if ( $( '#inputUsername' ).val().length < 5 || $( '#inputUsername' ).val().length > 20 ) {
            error.push( 'Xin vui lòng đặt tài khoản dài từ 5-20 ký tự.' )
        }
        if ( $( '#inputPassword' ).val().length < 5 || $( '#inputPassword' ).val().length > 20 ) {
            error.push( 'Xin vui lòng đặt mật khẩu dài từ 5-20 ký tự.' );
        }
        if ( $( '#inputRePassword' ).val() != $( '#inputPassword' ).val() ) {
            error.push( 'Mật khẩu nhập lại không trùng với mật khẩu.' );
        }
        if ( error.length > 0 ) {
            form_error.empty();
            for ( var index = 0, len = error.length; index < len; index++ ) {
                form_error.append( '<p><strong>' + error[index] + '</strong></p>' )
            }
            form_error.show();
            return;
        }
        form_success.hide();
        form_error.hide();
        $.ajax( {
            url: host + '/tao-tai-khoan?' + token
                + '&username=' + $( '#inputUsername' ).val()
                + '&password=' + $( '#inputPassword' ).val()
            ,
            method: 'POST',
            success: function ( xhr ) {
                form_success.empty();
                form_success.append( '<p><strong>' + xhr + '</strong></p>' )
                form_success.show();
            },
            error: function ( xhr ) {
                form_error.empty();
                form_error.append( '<p><strong>' + xhr.responseText + '</strong></p>' )
                form_error.show();
            }
        } )
    } );

    /*================================
    Fullscreen Page
    ==================================*/

    if ( $( '#full-view' ).length ) {

        var requestFullscreen = function ( ele ) {
            if ( ele.requestFullscreen ) {
                ele.requestFullscreen();
            } else if ( ele.webkitRequestFullscreen ) {
                ele.webkitRequestFullscreen();
            } else if ( ele.mozRequestFullScreen ) {
                ele.mozRequestFullScreen();
            } else if ( ele.msRequestFullscreen ) {
                ele.msRequestFullscreen();
            } else {
                console.log( 'Fullscreen API is not supported.' );
            }
        };

        var exitFullscreen = function () {
            if ( document.exitFullscreen ) {
                document.exitFullscreen();
            } else if ( document.webkitExitFullscreen ) {
                document.webkitExitFullscreen();
            } else if ( document.mozCancelFullScreen ) {
                document.mozCancelFullScreen();
            } else if ( document.msExitFullscreen ) {
                document.msExitFullscreen();
            } else {
                console.log( 'Fullscreen API is not supported.' );
            }
        };

        var fsDocButton = document.getElementById( 'full-view' );
        var fsExitDocButton = document.getElementById( 'full-view-exit' );

        fsDocButton.addEventListener( 'click', function ( e ) {
            e.preventDefault();
            requestFullscreen( document.documentElement );
            $( 'body' ).addClass( 'expanded' );
        } );

        fsExitDocButton.addEventListener( 'click', function ( e ) {
            e.preventDefault();
            exitFullscreen();
            $( 'body' ).removeClass( 'expanded' );
        } );


    }


})( jQuery );