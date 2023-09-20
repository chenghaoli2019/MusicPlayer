package com.music.musicclient.Controller;
import com.music.musicclient.service.*;
import com.music.musicclient.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.math.BigInteger;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@org.springframework.stereotype.Controller
public class MusicController {
    @Autowired
    private final MusicService musicService;
    private final LyricService lyricService;
    MusicController(MusicService musicService,LyricService lyricService){
        this.musicService = musicService;
        this.lyricService = lyricService;

    }
    @GetMapping(value = {"/index", "/"})
    public String index(Model model){
        List<Music> playlist = musicService.GetMusic();
        model.addAttribute("musicList", playlist);
        //String audioPath = "/files/Shawn Mendes, Camila Cabello - Señorita.mp3"; // Replace with your logic to determine the audio file path
        //model.addAttribute("audioPath", audioPath);





        // Perform operations with the song ID as needed

        return "index";
    }

    @PostMapping("/index")
    public String processSongId(@RequestParam("id") String songId, Model model) {
        // Process the song ID as needed

        System.out.println("Received Song ID: " + songId);

        // Update the model attribute with the received song ID
        String url = "redirect:/player/" + songId;
        System.out.println(url);
        // Redirect to the GET method to update the page
        return url;
    }
    @GetMapping("/index/like/{id}")
    public String addLike(Model model, @PathVariable("id") BigInteger songId) {
        // Process the song ID as needed
        System.out.println("no");
        musicService.like(songId);
        // Redirect to the GET method to update the page
        return "redirect:/index";
    }

    @GetMapping("/download/{id}")
    public String login(Model model,@PathVariable("id") BigInteger id){
        Music newmusic = musicService.download(id);
        String uploadDirectory = "C:/Users/Alienware/Downloads/hello/";
        String sourceFilePath = newmusic.getMusicFiles();



        return "redirect:/index";
    }
    @GetMapping("/player/{id}")
    public String player(Model model, @PathVariable("id") BigInteger ID) {
        System.out.println("hello" + ID);
        Music newmusic = musicService.download(ID);
        model.addAttribute("audioPath2", "/files/" + newmusic.getMusicFiles());
        model.addAttribute("trackName",newmusic.getName());
        model.addAttribute("trackArtist",newmusic.getUploader());

        String filenameWithoutExtension = newmusic.getMusicFiles().replaceAll("\\.mp3$", ".png");
        String filenameWithoutExtension2 = newmusic.getMusicFiles().replaceAll("\\.mp3$", ".txt");
        model.addAttribute("visualPath2","/images/" + filenameWithoutExtension);
        String lyrics = lyricService.TxtReader("C:/Users/Alienware/Desktop/musicclient/src/main/resources/static/lyrics/" + filenameWithoutExtension2);
        model.addAttribute("Lyrics",lyrics);
        return "player";
    }

    @GetMapping("/upload")
    public String showUploadPage(Model model) {
        model.addAttribute("music", new Music());
        return "upload";
    }



    @PostMapping("/upload")
    public String handleUploadForm(Model model, Music music, @RequestParam("file") MultipartFile file,
                                   @RequestParam("name") String songName,
                                   @RequestParam("uploader") String username,
                                   @RequestParam("account") String account , @RequestParam("imageFile") MultipartFile image,
                                   @RequestParam(value = "textFile", required = false) MultipartFile text){
        System.out.println(songName + username + account);
        if (!file.isEmpty()) {
            try {
                // Get the original filename
                String originalFilename = file.getOriginalFilename();
                Path path = Paths.get(originalFilename);
                String fileName = path.getFileName().toString();
                Music uploadmusic = new Music();
                uploadmusic.setName(songName);
                uploadmusic.setUploader(username);
                uploadmusic.setAccount(account);
                uploadmusic.setLikes(0);
                uploadmusic.setViews(0);
                uploadmusic.setMusicFiles(fileName);
                musicService.upload(uploadmusic);
                // Specify the destination directory for saving the file
                String uploadDirectory = "C:/Users/Alienware/Desktop/musicclient/src/main/resources/static/files/";
                String uploadDirectory2 = "C:/Users/Alienware/Desktop/musicclient/src/main/resources/static/images/";
                String uploadDirectory3 = "C:/Users/Alienware/Desktop/musicclient/src/main/resources/static/lyrics/";

// Generate a unique file name or use the original file name
                String originalFilename2 = file.getOriginalFilename();
                Path path2 = Paths.get(originalFilename2);
                String fileName2 = path2.getFileName().toString();

// Change the suffix to ".png"
                String newFileName2 = fileName2.replaceFirst("\\.[^.]+$", ".png");
                String newFileName3 = fileName2.replaceFirst("\\.[^.]+$", ".txt");

// Combine the new file name with the upload directory
                Path newPath2 = Paths.get(uploadDirectory2, newFileName2);
                String newFilePath2 = newPath2.toString();
                Path newPath3 = Paths.get(uploadDirectory3, newFileName3);
                String newFilePath3 = newPath3.toString();


                // Save the file to the destination directory
                try {
                    FileCopyUtils.copy(file.getBytes(), new File(uploadDirectory + fileName));
                    FileCopyUtils.copy(image.getBytes(), new File( newFilePath2));
                    FileCopyUtils.copy(text.getBytes(), new File(newFilePath3 ));


                } catch (IOException e) {
                    // Handle the exception
                    e.printStackTrace();
                }

                // Process the file as needed (e.g., store it on disk, save it to a database)

                // Example: Save the file to disk
                // File savedFile = new File("path/to/save/" + originalFilename);
                // file.transferTo(savedFile);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception as needed
            }
        }

        return "redirect:/index";
    }


}
