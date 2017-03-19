$(document).ready(function() {

	$(function() {
		$("#sortable").sortable({
			revert : true
		});

		$("ul, li").disableSelection();
	});

	$("input[type=checkbox]").switchButton({
		on_label : 'ON',
		off_label : 'OFF',
		width : "6",
		height : "1.8",
		button_width : "3",
		size_unit : "em",
	});

	$("input[type=checkbox]").each(function() {
		var input = this;
		var result;
		$.ajax({
			url : "heating/equimpent/switches/" + input.id,
			success : function(data) {
				result = data;
				$("input#" + input.id).switchButton({
					off_callback : function() {
						$.ajax({
							url : result.offUri,
						});
					},
					on_callback : function() {
						$.ajax({
							url : result.onUri,
						});
					}
				});

			}
		});
	});

});

/* RADIO Functions */

function playRadio(id) {
	$.get("/radio/play?id=" + id);
}

function stopRadio() {
	$.get("/radio/stop");
}

var Timer_Started = true;
var Timer = setTimeout(DoThis, 200);
var Volume = 0.5;

function DoThis() {
	Timer_Started = false;
	$.ajax({
		url : "/radio/setVolume?volume=" + Volume
	});
}

function setVolume(val) {

	Volume = val;

	if (!Timer_Started) {
		Timer_Started = true;
		Timer = setTimeout(DoThis, 200);
	}
}

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
			else {
				return 0.5;
			}
		}
	};
})();

$(document).ready(
		function() {

			$('.deleteRadio').click(
					function() {

						$.get('/radio/delete?id='
								+ this.parentNode.parentNode.id,
								function(data) {
									console.log(data);
								}, 'json' // I expect a JSON response
						);
					});

			$('#submitNewRadio').click(
					function() {
						$.post('/radio/add', $('form#radioForm').serialize(),
								function(data) {
									console.log(data);
								}, 'json' // I expect a JSON response
						);
					});

			$.get("/radio/", function(data) {
				data.forEach(function(entry) {
					$('.dropdown-radio').append(
							"<a href='#' onclick='playRadio(" + entry.id
									+ ");'>" + entry.name + "</a>");
				});

				$('.dropdown-radio').append(
						"<a href='#' onclick='stopRadio();'>Stop Radio</a>");
			});

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
		});
