/*global define */
define(["jquery"], 
function($) {
	'use strict';

	return function() {
		this.data = {
		        "enter" : false,
				"x" : 0,
				"y" : 0
		};
		
		this.x = function(value) {
			if (value == undefined) return this.data.x;
		    this.data.x = value;
		};
		this.y = function(value) {
		    if (value == undefined) return this.data.y;
		    this.data.y = value;
		};
		this.enter = function(value) {
		    if (value == undefined) return this.data.enter;
		    this.data.enter = value;
		};
		this.xPercent = function() {
		    var screenWidth = $("#map").width();
		    return (this.data.x * 100) / screenWidth;
        };
        this.yPercent = function() {
            var screenHeight = $("#map").height();
            return (this.data.y * 100) / screenHeight;
        };
	};
});