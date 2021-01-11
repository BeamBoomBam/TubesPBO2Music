package sample.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.DAO.MusicDAO;
import sample.DAO.PlaylistDAO;
import sample.Main.Main;
import sample.Model.Music;
import sample.Model.Playlist;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField SearchBar;
    public Button Search;
    public Button Account;
    public TableView<Music> TabelLagu;
    public TableColumn<Music, String> KolomJudul;
    public TableColumn<Music, String> KolomAlbum;
    public TableColumn<Music, String> KolomArtist;
    public ImageView CoverLagu;
    public Label JudulLagu;
    public Label ArtisLagu;
    public Label SisaWaktu;
    public static Label static_labelJudul;
    public static Label static_labelArtis;
    public static Label static_labelSisawaktu;
    public Button ShuffleButton;
    public Button PrevButton;
    public Button PlayButton;
    public Button NextButton;
    public Button RepeatButton;
    public Label ProgressLagu;
    public Slider VolumeSlider;
    public MenuItem addButton;
    public ListView<Playlist> Playlists;
    public MenuItem delPlaylist;
    public AnchorPane ChildPane;
    private ObservableList<Music> mList;
    public ObservableList<Playlist> pList;
    Stage new_stage;
    private Playlist playlist;
    private PlaylistDAO playlistDAO;
    private Playlist selectedPlaylist;

    private final ObservableList<Music> musicList = FXCollections.observableArrayList();

    public PlaylistDAO getPlaylistDAO() {
        if (playlistDAO == null) {
            playlistDAO = new PlaylistDAO();
        }
        return playlistDAO;
    }

    public ObservableList<Playlist> getpList(){
        if (pList == null){
            pList= FXCollections.observableArrayList();
            pList.addAll(getPlaylistDAO().showData());
        }
        return pList;
    }

    public void initialize (URL location, ResourceBundle resources){
        Playlists.setItems(this.getpList());
        MusicDAO mDao = new MusicDAO();
        ObservableList<Music> mList = (ObservableList<Music>) mDao.showData();
        TabelLagu.setItems(mList);
        KolomJudul.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getJudul()));
        KolomAlbum.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getAlbum()));
        KolomArtist.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getPenyanyi()));
        Music music = TabelLagu.getSelectionModel().getSelectedItem();
        System.out.println(music);
        Playlists.refresh();
        static_labelJudul = JudulLagu;
        static_labelArtis = ArtisLagu;
        static_labelSisawaktu = SisaWaktu;

        musicList.addAll(mDao.showData());
        FilteredList<Music> filteredList = new FilteredList<>(musicList, m -> true);
        SearchBar.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(Music ->{
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Music.getJudul().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (Music.getAlbum().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else return Music.getPenyanyi().toLowerCase().contains(lowerCaseFilter);
            });
        }));
        SortedList<Music> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind((TabelLagu.comparatorProperty()));
        TabelLagu.setItems(sortedList);


    }

    public void addPlaylist(ActionEvent ex) throws IOException {
        Playlist newplay = new Playlist();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Playlist name :");
        Optional<String> result = dialog.showAndWait();
        newplay.setNama(dialog.getDefaultValue());
        PlaylistDAO pDao = new PlaylistDAO();
        if (result.isPresent()){
//            newplay.setIdPlaylist(0);
            newplay.setIdUser(1);
            newplay.setNama(result.get());
//            int id = 0;
//            int idmusic = 0;
//            int iduser = 1;
//            String nama = result.get();
            //pDao.addData(new Playlist(id, nama, idmusic, iduser));
        }
        getPlaylistDAO().addData(newplay);
        getpList().add(newplay);
    }

    public void playSound(){
        String filepath = "../Sounds/12DaysOfChristmas.mp3";

        try {
            File musicPath = new File(filepath);

            if (musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else {
                System.out.println("File tidak ditemukan.");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deletePlaylist(ActionEvent actionEvent) {
        selectedPlaylist = Playlists.getSelectionModel().getSelectedItem();
        System.out.println(selectedPlaylist.getIdPlaylist());
        if(playlistDAO.delData(selectedPlaylist)==0){
            selectedPlaylist = Playlists.getSelectionModel().getSelectedItem();
            playlistDAO.delData(selectedPlaylist);
        }

        getpList().clear();
        getpList().addAll(playlistDAO.showData());
        Playlists.setItems(this.getpList());
    }

    public void selectplaylist(MouseEvent mouseEvent) {
        selectedPlaylist = Playlists.getSelectionModel().getSelectedItem();
    }

    public void selectPlaylist(WindowEvent windowEvent) {
        selectedPlaylist = Playlists.getSelectionModel().getSelectedItem();
    }

    public void playsong(ActionEvent actionEvent) {
        static_labelJudul.setText(TabelLagu.getSelectionModel().getSelectedItem().getJudul());
        static_labelArtis.setText(TabelLagu.getSelectionModel().getSelectedItem().getAlbum());
        static_labelSisawaktu.setText(TabelLagu.getSelectionModel().getSelectedItem().getWaktu());
    }

    public void Logout(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../View/LoginPage.fxml"));
        ChildPane.getChildren().setAll(pane);
    }
}
