package com.example.interviewpreparation;

import static androidx.test.InstrumentationRegistry.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.interviewpreparation.model.Model_Video;

import java.util.ArrayList;
import java.util.HashSet;

public class NewActivity extends AppCompatActivity {

    ArrayList<Model_Video> al_video = new ArrayList<>();
    private static final int REQUEST_PERMISSIONS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        String str = Environment.getExternalStorageDirectory()+"/Shani";
        Log.i("Path:",str);
        String video_url = "android.resource://" + getPackageName() + "/" + R.raw.shani;
       // Uri videoUri = Uri.parse(video_url);
        //Uri videoUri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/Shani.mp4");
      //  Uri videoUri = Uri.parse(Environment.getExternalStorageDirectory()+"/SD card/Shani.mp4");
       // String video_url = Environment.getExternalStorageDirectory()+"/SD card/Shani.mp4";
        Uri videoUri = Uri.parse(str);
        VideoView videoView = findViewById(R.id.videoView);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
       // videoView.setVideoURI(videoUri);
      //  videoView.setVideoPath("/storage/emulated/0/Download/Shani.mp4");
        videoView.setVideoPath("/storage/0403-0201/Shani.mp4");
        videoView.requestFocus();
        videoView.start();
        System.out.println("Hello world");
       // getAllMedia();
        fn_checkpermission();
    }

    private void fn_checkpermission(){
        /*RUN TIME PERMISSIONS*/

        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(NewActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(NewActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {

            } else {
                ActivityCompat.requestPermissions(NewActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        }else {
            Log.e("Else","Else");
            fn_video();
        }
    }


    public void fn_video() {

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name,column_id,thum;

        /*Cursor cur = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);*/

        String absolutePathOfImage = null;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Uri uri1 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;//Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME,MediaStore.Video.Media._ID,MediaStore.Video.Thumbnails.DATA};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
        column_id = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
        thum = cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);

        while (cursor.moveToNext()) {

            try{
                absolutePathOfImage = cursor.getString(column_index_data);
                Log.e("Column", absolutePathOfImage);
                Log.e("absolutePath:", absolutePathOfImage);
                Log.e("Folder", cursor.getString(column_index_folder_name));
                Log.e("column_id", cursor.getString(column_id));
                Log.e("thum", cursor.getString(thum));

                Model_Video obj_model = new Model_Video();
                obj_model.setBoolean_selected(false);
                obj_model.setStr_path(absolutePathOfImage);
                obj_model.setStr_thumb(cursor.getString(thum));

                al_video.add(obj_model);
            }catch (NullPointerException e) {

            }

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        fn_video();
                    } else {
                        Toast.makeText(NewActivity.this, "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

    }

    public ArrayList<String> getAllMedia() {
        HashSet<String> videoItemHashSet = new HashSet<>();
        String[] projection = { MediaStore.Video.VideoColumns.DATA ,MediaStore.Video.Media.DISPLAY_NAME};
        Cursor cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        try {
            cursor.moveToFirst();
            do{
                videoItemHashSet.add((cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))));
            }while(cursor.moveToNext());

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> downloadedList = new ArrayList<>(videoItemHashSet);
        for(int i=0;i< downloadedList.size();i++) {
            Log.i("Videoname:",downloadedList.get(i));
        }
        return downloadedList;
    }


}