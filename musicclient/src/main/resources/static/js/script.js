$(document).ready(function() {
    var audioPlayer = document.getElementById('audioPlayer');
    var audioSource = document.getElementById('audioSource');
    var playlistItems = document.querySelectorAll('#playlist li');
    var progress = document.getElementById('progress');

    var currentSongIndex = 0;

    function loadSong(index) {
        var song = playlistItems[index];
        var songUrl = song.getAttribute('data-url');
        var songName = song.innerText;
        var songId = song.getAttribute('data-url'); // Get the song ID

        audioSource.src = songUrl;
        audioPlayer.load();
        audioPlayer.play();

        // Highlight the current song in the playlist
        playlistItems.forEach(function(item) {
            item.classList.remove('active');
        });
        song.classList.add('active');

        // Update the progress bar continuously
        audioPlayer.ontimeupdate = function() {
            var progressPercent = (audioPlayer.currentTime / audioPlayer.duration) * 100;
            progress.style.width = progressPercent + '%';
        };

        // Update the song name in the player
        document.title = 'Music Player - ' + songName;

        console.log("Clicked Song ID:", songId); // Print the song ID
        // You can perform further actions with the song ID here

    }

    // Load the first song
    loadSong(currentSongIndex);

    // Event listeners for buttons
    $('#prevBtn').click(function() {
        if (currentSongIndex > 0) {
            currentSongIndex--;
        } else {
            currentSongIndex = playlistItems.length - 1;
        }
        loadSong(currentSongIndex);
    });

    $('#playPauseBtn').click(function() {
        if (audioPlayer.paused) {
            audioPlayer.play();
            $(this).text('Pause');
        } else {
            audioPlayer.pause();
            $(this).text('Play');
        }
    });

    $('#nextBtn').click(function() {
        if (currentSongIndex < playlistItems.length - 1) {
            currentSongIndex++;
        } else {
            currentSongIndex = 0;
        }
        loadSong(currentSongIndex);
    });

    // Event listener for clicking on a playlist item
    playlistItems.forEach(function(item, index) {
        console.log("hello");
        item.addEventListener('click', function() {
            currentSongIndex = index;
            loadSong(currentSongIndex);
        });
    });
});