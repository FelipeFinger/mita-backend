package br.com.felipefinger.mita.service;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.FileCredentialStore;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.google.common.collect.Lists;

public class UploadVideoService {

  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private static final JsonFactory JSON_FACTORY = new JacksonFactory();
  private static YouTube youtube;
  private static String VIDEO_FILE_FORMAT = "video/*";
  
  public static String uploadVideo(File video, String titulo, String descricao){
	  
	  List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload");

	    try {
	      Credential credential = authorize(scopes);

	      youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(
	          "youtube-cmdline-uploadvideo-sample").build();

	      File videoFile = video;
	      System.out.println("Iniciando upload do vídeo: " + titulo);

	      Video videoObjectDefiningMetadata = new Video();

	      VideoStatus status = new VideoStatus();
	      status.setPrivacyStatus("public");
	      videoObjectDefiningMetadata.setStatus(status);

	      VideoSnippet snippet = new VideoSnippet();

	      snippet.setTitle(titulo);
	      snippet.setDescription(descricao);

	      List<String> tags = new ArrayList<String>();
	      tags.add("mita");
	      tags.add("mirror");
	      tags.add("therapy");
	      tags.add("assistant");
	      tags.add("vr");
	      tags.add("terapia");
	      tags.add("espelho");
	      tags.add("assistente");
	      tags.add("realidade virtual");
	      snippet.setTags(tags);

	      videoObjectDefiningMetadata.setSnippet(snippet);

	      InputStreamContent mediaContent = new InputStreamContent(
	          VIDEO_FILE_FORMAT, new BufferedInputStream(new FileInputStream(videoFile)));
	      mediaContent.setLength(videoFile.length());

	      YouTube.Videos.Insert videoInsert = youtube.videos()
	          .insert("snippet,statistics,status", videoObjectDefiningMetadata, mediaContent);

	      MediaHttpUploader uploader = videoInsert.getMediaHttpUploader();

	      uploader.setDirectUploadEnabled(false);

	      MediaHttpUploaderProgressListener progressListener = new MediaHttpUploaderProgressListener() {
	        public void progressChanged(MediaHttpUploader uploader) throws IOException {
	          switch (uploader.getUploadState()) {
	            case INITIATION_STARTED:
	              System.out.println("Inicializando serviços..");
	              break;
	            case INITIATION_COMPLETE:
	              System.out.println("Serviços nicializados.");
	              break;
	            case MEDIA_IN_PROGRESS:
	              System.out.println("Upload em progresso");
	              System.out.println("Status upload: " + uploader.getProgress());
	              break;
	            case MEDIA_COMPLETE:
	              System.out.println("Upload concluído!");
	              break;
	            case NOT_STARTED:
	              System.out.println("Upload não iniciado!");
	              break;
	          }
	        }
	      };
	      uploader.setProgressListener(progressListener);

	      Video returnedVideo = videoInsert.execute();

	      System.out.println("\n================== Informações do vídeo ==================\n");
	      System.out.println("  - Link: https://www.youtube.com/watch?v=" + returnedVideo.getId());
	      System.out.println("  - Título: " + returnedVideo.getSnippet().getTitle());
	      System.out.println("  - Tags: " + returnedVideo.getSnippet().getTags());
	      System.out.println("  - Privavidade: " + returnedVideo.getStatus().getPrivacyStatus());

	      return ("https://www.youtube.com/embed/" + returnedVideo.getId());
	      
	    } catch (GoogleJsonResponseException e) {
	    	
	      System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
	          + e.getDetails().getMessage());
	      e.printStackTrace();
	    } catch (IOException e) {
	    	
	      System.err.println("IOException: " + e.getMessage());
	      e.printStackTrace();
	    } catch (Throwable t) {
	    	
	      System.err.println("Throwable: " + t.getMessage());
	      t.printStackTrace();
	    }	  
	    
	    return null;
  }

  private static Credential authorize(List<String> scopes) throws Exception {

    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
        JSON_FACTORY, UploadVideoService.class.getResourceAsStream("/client_secret.json"));

    if (clientSecrets.getDetails().getClientId().startsWith("Enter")
        || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
      System.out.println(
          "Enter Client ID and Secret from https://console.developers.google.com/project/_/apiui/credential"
          + "into youtube-cmdline-uploadvideo-sample/src/main/resources/client_secrets.json");
      System.exit(1);
    }

    FileCredentialStore credentialStore = new FileCredentialStore(
        new File(System.getProperty("user.home"), ".credentials/youtube-api-uploadvideo.json"),
        JSON_FACTORY);

    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, scopes).setCredentialStore(credentialStore)
        .build();

    LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(8081).build();

    return new AuthorizationCodeInstalledApp(flow, localReceiver).authorize("user");
  }

  public static boolean isValidIntegerSelection(String input, int max) {
    if (input.length() > 9) return false;

    boolean validNumber = false;
    // Only accepts positive numbers of up to 9 numbers.
    Pattern intsOnly = Pattern.compile("^\\d{1,9}$");
    Matcher makeMatch = intsOnly.matcher(input);

    if (makeMatch.find()) {
      int number = Integer.parseInt(makeMatch.group());
      if ((number >= 0) && (number < max)) {
        validNumber = true;
      }
    }
    return validNumber;
  }
}