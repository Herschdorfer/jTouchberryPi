$(document).ready(
		function() {

			$.get("/radio/", function(data) {
				data.forEach(function(entry) {
					console.log(entry);
					$('.dropdown-radio').append(
							"<a href='#' onclick='playRadio(" + entry.id
									+ ");'>" + entry.name + "</a>");
				});
				$('.dropdown-radio').append("<a href='#' onclick='stopRadio();'>Stop Radio</a>");
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