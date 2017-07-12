/*global define */
define(["jquery"], 
function($) {
	'use strict';

	return function() {
		this.el = $(".camera");
	    
	    this.data = {
                "zoom" : 1
		};
		
		this.zoom = function(value) {
            if (value == undefined) return this.data.zoom;
            this.data.zoom += value;
            this.zoomCorrect();
            
            $(".camera").css({
                "zoom": this.data.zoom,
                "-moz-transform": "scale("+this.data.zoom+")",
                "-o-transform": "scale("+this.data.zoom+")",
                "-webkit-transform": "scale("+this.data.zoom+")",
                "transform": "scale("+this.data.zoom+")",
                "-moz-transform-origin": "0 0",
                "-o-transform-origin": "0 0",
                "-webkit-transform-origin": "0 0",
                "transform-origin": "0 0"
            });
        };
		this.zoomCorrect = function() {
		    if (this.data.zoom > 2) this.data.zoom = 2;
            if (this.data.zoom < 0.125) this.data.zoom = 0.125;
		};
	};
});