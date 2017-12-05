function initMap(){
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 17,
		center: {lat: -23.589, lng: -46.631},
		gestureHandling: 'cooperative'
	});
	var geocoder = new google.maps.Geocoder();
	geocodeAddress(geocoder, map);
}

function geocodeAddress(geocoder, map) {
	var address = document.getElementById('endereco_estabelecimento').value;

	if(address != 'Endereço não cadastrado'){
		geocoder.geocode({'address': address}, function(results, status){
			if (status === 'OK') {
				map.setCenter(results[0].geometry.location);

				var contentString = '<div id="content">'+
					'<h3>'+$('#nome_estabelecimento').html()+'</h3>'+
					'<div id="bodyContent">'+
					'<p><strong>Categoria:</strong> '+document.getElementById('categoria_estabelecimento').value+'<br>'+
					'<strong>Endereço:</strong> '+results[0].formatted_address+'</p>'+
					'</div>'+
					'</div>';

				var infowindow = new google.maps.InfoWindow({
					content: contentString
				});

				var marker = new google.maps.Marker({
					map: map,
					position: results[0].geometry.location,
					title: $('#nome_estabelecimento').html()
				});
				infowindow.open(map, marker);
			}
		});
	}
}