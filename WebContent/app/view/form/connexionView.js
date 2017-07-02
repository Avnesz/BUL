/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils",
        "app/utils/viewUtils",
        "app/utils/messageUtils",
        "text!app/template/form/connexion.html",
        "app/model/form/connexionModel",
        "app/view/form/inscriptionView",
        "app/view/game/gameView"], 
function($, _, Utils, ViewUtils, MessageUtils, page, Model, InscriptionView, Game) {
	'use strict';

	return function(parent) {
		this.init = function(parent) {
			this.el = $("#form");
			this.model = new Model();
			this.parent = parent;
		};

		this.render = function() {
			_.templateSettings.variable = "data";
			var template = _.template(page);
			var templateData = {};
			this.el.html(template(templateData));
			ViewUtils.verticalCenter();
			
			this.checkEvents();
		};
		
		this.checkEvents = function() {
			var that = this;
			$("#connexion").click(function() {
				MessageUtils.hide();
				that.model.send(function(data) {
					if (data.codeRetour != 0) {
						MessageUtils.show(data.message, "danger");
					}else {
						that.game = new Game();
					}
				});
			});
			$("#inscription").click(function() {
				if (!that.inscriptionView) {
					that.inscriptionView = new InscriptionView(that);
				}
				that.inscriptionView.show();
			});
		};
		
		this.show = function(message) {
			Utils.load("track", {"where" : "Menu de connexion"}, function(data) {}, "POST");
			this.render();
			if (message) {
				MessageUtils.show(message, "success");
			}
		};
		
		this.init(parent);
	};
});