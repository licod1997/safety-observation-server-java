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
    datatable active
    ==================================*/
    if ( $( '#dataTable' ).length ) {
        $( '#dataTable' ).DataTable( {
            responsive: true

        } );
    }
    if ( $( '#dataTable2' ).length ) {
        firstNotificationId = $( '.feedback-id' ).first().text();
        lastNotificationId = $( '.feedback-id' ).last().text();
        var table = $( '#dataTable2' );
        var carousel = $( '#carouselFeedbackPhoto .carousel-inner' );
        var modalTimeField = $( '#modal-feedback-time' );
        var modalDescriptionField = $( '#modal-feedback-description' );
        $( table ).find( 'thead' ).hide();
        var interval = setInterval( function () {
            $.ajax( {
                url: 'http://localhost:8080/phan-hoi-moi?firstNotificationId=' + firstNotificationId,
                method: 'POST',
                success: function ( xhr ) {
                    if ( xhr.trim() ) {
                        $( table ).find( 'tbody' ).prepend( xhr.trim() );
                        firstNotificationId = $( '.feedback-id' ).first().text();
                        $.showDetail();
                    }
                },
                error: function () {
                    clearInterval( interval );
                }
            } );
        }, 5000 );

        $.fn.hasScrollBar = function () {
            return this.get( 0 ).scrollHeight > this.height();
        }

        $.loadMore = function () {
            if ( $( window ).scrollTop() + $( window ).height() == $( document ).height() ) {
                $.ajax( {
                    url: 'http://localhost:8080/phan-hoi-cu?lastNotificationId=' + lastNotificationId,
                    method: 'POST',
                    success: function ( xhr ) {
                        if ( xhr.trim() ) {
                            $( table ).find( 'tbody' ).append( xhr.trim() );
                            lastNotificationId = $( '.feedback-id' ).last().text();
                            $.showDetail();
                        }
                    },
                    error: function () {
                    }
                } );
            }
        }

        $( window ).resize( function () {
            if ( !$( window ).hasScrollBar() ) {
                $.loadMore();
            }
        } );

        if ( !$( window ).hasScrollBar() ) {
            $.loadMore();
        }

        $( window ).scroll( function () {
            $.loadMore();
        } );

        $.showDetail = function () {
            $( table ).find( 'tr' ).click( function () {
                $( carousel ).empty();
                $( this ).find( '.feedback-photo-url' ).each( function ( i, el ) {
                    if ( i == 0 ) {
                        var tab = '<div class="carousel-item img-fit active">\n' +
                            '   <img class="d-block w-100" src="' + $( this ).text() + '">\n' +
                            '</div>';
                        $( carousel ).append( tab );
                    } else {
                        var tab = '<div class="carousel-item img-fit">\n' +
                            '   <img class="d-block w-100" src="' + $( this ).text() + '">\n' +
                            '</div>';
                        $( carousel ).append( tab );
                    }
                } );
                $( modalTimeField ).text( $( this ).find( '.feedback-time' ).text() );
                $( modalDescriptionField ).text( $( this ).find( '.feedback-description' ).text() );

                var tr = $( this );
                if ( tr.hasClass( 'feedback-unread' ) ) {
                    $.ajax( {
                        url: 'http://localhost:8080/da-xem-phan-hoi?currentNotificationId=' + tr.find( '.feedback-id' ).text(),
                        method: 'POST',
                        success: function ( xhr ) {
                            tr.addClass( 'feedback-read' ).removeClass( 'feedback-unread' );
                        },
                        error: function () {
                        }
                    } );
                }
            } );
        }




    }
    if ( $( '#dataTable3' ).length ) {
        $( '#dataTable3' ).DataTable( {
            responsive: true
        } );
    }


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

    /*================================
    Download uploadfile
    ==================================*/
    $('#multipleUploadForm').submit(function(event) {

        var formData = new FormData(this);
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "http://localhost:8080/uploadMultipleTrainFiles",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                alert("Tải lên thành công!");
                window.location.reload(true);
            },
            error: function (error) {
                alert("Tải lên thất bại!");
                console.log(error);
            }
        });

        event.preventDefault();
    });


    //============apporve feeback onclick======
    $( '.btn-feedback-approve' ).on( 'click', function () {
        feedbackId = $('.feedbackId').text();
        $.ajax( {
            url: 'http://localhost:8080/set-reject-feedback?feedbackId='+feedbackId + '&optionFeedback=false',
            method: 'GET', success: function (response) {
                alert("Approve successfully");
                console.log(response)
                $('.feedback-controler').addClass('gone');
            },
            error: function (error) {
                alert("Failed to Approve ");
                console.log(error);
            }
        } );
    } );

    //============reject feeback onclick======
    $( '.btn-feedback-reject' ).on( 'click', function () {
        feedbackId = $('.feedbackId').text();
        $.ajax( {
            url: 'http://localhost:8080/set-reject-feedback?feedbackId='+feedbackId + '&optionFeedback=true',
            method: 'GET', success: function (response) {
                window.location.href = 'http://localhost:8080/'
                console.log(response)
            },
            error: function (error) {
                alert("Failed to Reject ");
                console.log(error);
            }
        } );
    } );


    /*================================
    Upload File to Train
    ==================================*/
    $('#multipleUploadTrainForm').submit(function(event) {

        var formData = new FormData(this);
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "http://localhost:8080/uploadMultipleTrainFiles",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                alert("Tải lên thành công!");
                window.location.reload(true);
            },
            error: function (error) {
                alert("Tải lên thất bại!");
                console.log(error);
            }
        });

        event.preventDefault();
    });


})( jQuery );