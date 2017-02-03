$(document).ready(
		function() {

			$.get("/radio/", function(data) {
				data.forEach(function(entry) {
					console.log(entry);
					$('.dropdown-radio').append(
							"<a href='#' onclick='playRadio(" + entry.id
									+ ");'>" + entry.name + "</a>");
				});
				$('.dropdown-radio').append(
						"<a href='#' onclick='stopRadio();'>Stop Radio</a>");
			});
		});

var dataStore = (function() {
	var xml;

	$.ajax({
		url : "/radio/getVolume",
		success : function(data) {
			xml = data;
		}
	});

	return {
		getXml : function() {
			if (xml)
				return xml;
			// else show some error that it isn't loaded yet;
		}
	};
})();

$(document).ready(function() {

	Volume = dataStore.getXml();

	$("#volume").slider({
		min : 0,
		max : 100,
		value : 0,
		range : "min",
		animate : true,
		slide : function(event, ui) {
			setVolume((ui.value) / 100);
		}
	});

	$("#volume").slider('value', Volume * 100);

	$("input[type=checkbox]").switchButton({
		on_label : 'ON',
		off_label : 'OFF',
		width : "6",
		height : "1.8",
		button_width : "3",
		size_unit : "em",
	});

	var test = $('#relais1').switchButton({
		off_callback : function(data) {
			$.ajax({
				url : "http://192.168.188.46/relay_1_off",
			});
		},
		on_callback : function(data) {
			$.ajax({
				url : "http://192.168.188.46/relay_1_on",
			});
		}
	});

	var test = $('#relais2').switchButton({
		off_callback : function(data) {
			$.ajax({
				url : "http://192.168.188.46/relay_2_off",
			});
		},
		on_callback : function(data) {
			$.ajax({
				url : "http://192.168.188.46/relay_2_on",
			});
		}
	});

});

function playRadio(id) {
	$.get("/radio/play?id=" + id, function(data) {

	});
};

function stopRadio(id) {
	$.get("/radio/stop", function(data) {

	});
};

var Timer_Started = true;
var Timer = setTimeout(DoThis, 200);
var Volume;

function DoThis() {
	Timer_Started = false;
	$.ajax({
		url : "/radio/setVolume?volume=" + Volume
	});
}

function setVolume(val) {

	Volume = val;

	if (Timer_Started) {

	} else {
		Timer_Started = true;
		Timer = setTimeout(DoThis, 200);
	}
}