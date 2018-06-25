/**
 * 
 */

function toggleSound(isSoundPlaying){
	isSoundPlaying ? pause() : play() ;
}

function play(){
    audio.play();
}

function pause(){
    audio.pause();
}

$('document').ready(function(){
	
	audio = document.getElementById('audio');
	
	$(".iptGuess").keypress(function( event ) {
		if ( event.which == 13 ) {
			event.preventDefault();
			$(".btnGuess").click();
		}
	});
    
});

