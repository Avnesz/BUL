/*global define */
define(["app/utils/utils"], 
function(Utils) {
	'use strict';

	return function() {
		this.data = {
				"login" : "login",
				"mail" : "mail",
				"verifMail" : "verifMail",
				"mdp" : "mdp",
				"verifMdp" : "mdp"
		};
		
		this.validate = function() {
			var mdp = $("#mdp").val();
			
			// Est remplie
			if (!mdp) {
				return "Veuillez remplir le champ mot de passe.";
			}
			
			// Contient une majuscule
			if (!/^(?=.*[A-Z]).*$/.test(mdp)) {
				return "Votre mot de passe doit contenir au moins une majuscule.";
			}
			// Contient une minuscule
			if (!/^(?=.*[a-z]).*$/.test(mdp)) {
				return "Votre mot de passe doit contenir au moins une minuscule.<br/>";
			}
			// Contient un chiffre
			if (!/^(?=.*\d).*$/.test(mdp)) {
				return "Votre mot de passe doit contenir au moins un chiffre.<br/>";
			}
			// Fait minimum 10 caracteres
			if (mdp.length < 10) {
				return "Votre mot de passe doit faire au minimum 10 caracteres.";
			}
			
			return null;
		};
		
		this.send = function(successFunc) {
			this.data.login = $("#login").val();
			this.data.mail = $("#mail").val();
			this.data.verifMail = $("#verifMail").val();
			this.data.mdp = Utils.hash($("#mdp").val());
			this.data.verifMdp = Utils.hash($("#verifMdp").val());
			Utils.load("../Bebel/subscribe", this.data, successFunc);
		};
	};
});