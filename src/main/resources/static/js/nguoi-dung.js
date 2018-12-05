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
    Init variables
    ==================================*/
    var host = 'http://localhost:8080';
    var token = $.getCookie( 'auth' );

    /*================================
    Datatable active
    ==================================*/
    if ( $( '#dataTable2' ).length ) {
        $( '#dataTable2' ).DataTable( {
            responsive: true,
            language: {
                search: "Tìm kiếm:",
                lengthMenu: "Hiển thị _MENU_ kết quả",
                zeroRecords: "Không có kết quả nào được tìm thấy",
                paginate: {
                    first: "Trang Đầu",
                    last: "Trang Cuối",
                    next: "Sau",
                    previous: "Trước"
                },
                info: "Hiện thị _START_-_END_ trong tổng số _TOTAL_ kết quả",
                infoEmpty: "Hiển thị 0-0 trong tổng số 0 kết quả",
                infoFiltered: "(được lọc từ _MAX_ kết quả)"
            },
            searching: true,
            pagingType: "full_numbers"
        } );

        $( ".switch input" ).change( function ( e ) {
            var checkbox = $( this );
            var userId = $( $( '.user-id' )[$( this ).index()] ).text();
            var enable = this.checked;
            $.ajax( {
                url: host + '/cap-nhat-tai-khoan?' + token + '&userId=' + userId + '&enable=' + enable,
                method: 'POST',
                async: false,
                success: function ( data, textStatus, xhr ) {

                },
                error: function ( xhr, textStatus, error ) {
                    checkbox.prop( 'checked', !enable );
                }
            } );
        } );
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

    $( '.need-auth-url' ).each( function () {
        if ( $( this ).attr( 'href' ).indexOf( '?' ) > -1 ) {
            $( this ).attr( 'href', $( this ).attr( 'href' ) + '&' + token );
        } else {
            $( this ).attr( 'href', $( this ).attr( 'href' ) + '?' + token );
        }
    } );


})( jQuery );