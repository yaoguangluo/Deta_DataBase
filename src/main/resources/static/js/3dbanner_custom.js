$(function() {
			var Page = (function() {
				var $navArrows = $('#nav-arrows').hide(), slicebox = $('#sb-slider').slicebox({
					onReady : function() {
						$navArrows.show();
					},
					orientation : 'r',
					cuboidsRandom : true,
					disperseFactor : 30
				}), init = function() {
					initEvents();
				}, initEvents = function() {
					// add navigation events
					$navArrows.children(':first').on('click', function() {
						if (slicebox!=null) {
							slicebox.next();
							return false;
						}
						return false;
					});
					$navArrows.children(':last').on('click', function() {
						if (slicebox!=null) {
							slicebox.previous();
							return false;
						}
						return false;
					});
				};
				return {
					init : init
				};
			})();
			Page.init();
		});