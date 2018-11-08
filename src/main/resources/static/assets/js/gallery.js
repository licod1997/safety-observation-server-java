;(function() {
  'use strict';
  var init = function (elem) {
    var activeImageIndex = 0;
    var thumbs = elem.querySelector('.thumbs').querySelectorAll('div:not([style*="display:none"])');
      var images = elem.querySelector('.images').querySelectorAll('div:not([style*="display:none"])');


      var activateImage = function(index) {
      if (!thumbs[index]) {
        return;
      }

      activeImageIndex = index;

      var toggleActiveClass = function(el, i) {
        if (i === index) {
          el.className = 'active';
        } else {
          el.className = '';
        }
      };

      for (var i=0; i < thumbs.length; i+=1) {
        toggleActiveClass(thumbs[i], i);
        toggleActiveClass(images[i], i); // Both `.thumbs` and `.images`
                                         // have the same number of elements
      }
    };
      // var rejectBtns = elem.querySelector('.btn-reject').querySelectorAll('div');
      // var onRejectClick = function (index) {
      //     if(index >= (images.length/2)  ){
      //
      //         showPrev();
      //     }else{
      //       showNext();
      //     }
      // };
      //
      // for (var i=0; i < rejectBtns.length; i+=1) {
      //     rejectBtns[i].addEventListener('click', onRejectClick, false);
      // }

    var showPrev = function () {
      activateImage(activeImageIndex - 1);
    };

    var showNext = function () {
      activateImage(activeImageIndex + 1);
    };

    elem.querySelector('span.left').addEventListener('click', showPrev, false);
    elem.querySelector('span.right').addEventListener('click', showNext, false);

    var imagesHammer = new Hammer(elem.querySelector('.images'));

    imagesHammer.on('swipeleft', showNext);
    imagesHammer.on('swiperight', showPrev);

    var onThumbClick = function () {
      for (var i=0; i < thumbs.length; i+=1) {
        if (thumbs[i] === this) {
          activateImage(i);
          return;
        }
      }
    };

    for (var i=0; i < thumbs.length; i+=1) {
      thumbs[i].addEventListener('click', onThumbClick, false);
    }
  };

  window.Gallery = {
    init: init
  };
}());
