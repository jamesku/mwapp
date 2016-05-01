package com.memwall.www.memwall;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by james_000 on 4/24/2016.
 */
public class MainFragment extends Fragment implements View.OnClickListener{

    private Uri fileUri;
    Intent fotoIntent;
    Intent videoIntent;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    int RESULT_OK;
    int RESULT_CANCELED;
    MainActivity mainActivity;
    static final int MY_PERMISSIONS =200;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);


        /*Add the 3 FAB buttons and onclick listeners*/
        FloatingActionButton myFab = (FloatingActionButton) rootView.findViewById(R.id.picture);
        myFab.setOnClickListener(this);
        FloatingActionButton myFab2 = (FloatingActionButton) rootView.findViewById(R.id.video);
        myFab2.setOnClickListener(this);
        FloatingActionButton myFab3 = (FloatingActionButton) rootView.findViewById(R.id.text);
        myFab3.setOnClickListener(this);


        /*Camera Intents */
        fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        return rootView;

    }

    /*what happens when one of the FAB buttons is clicked*/
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video:
                checkPermissions();
                doVideo();
                break;
            case R.id.text:
                 //doText
                break;
            case R.id.picture:
                checkPermissions();
                //doFoto();
                break;
        }
    }

    /* Not sure why this is here */
    public static MainFragment newInstance(String text) {

        MainFragment main = new MainFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);
        main.setArguments(b);

        return main;
    }

    /*when user clicks on photo*/
    public void doFoto(){

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        fotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(fotoIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    public void doVideo() {

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);  // create a file to save the video
        videoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name
        videoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video image quality to high
        // start the Video Capture Intent
        startActivityForResult(videoIntent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
    }


    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {

                // Image captured and saved to fileUri specified in the Intent

            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }

        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Video captured and saved to fileUri specified in the Intent

            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the video capture
            } else {
                // Video capture failed, advise user
            }
        }
    }*/


    private  Uri getOutputMediaFileUri(int type){
            return Uri.fromFile(getOutputMediaFile(type));
            }

    /** Create a File for saving an image or video */
    private  File getOutputMediaFile(int type){
            // To be safe, you should check that the SDCard is mounted
            // using Environment.getExternalStorageState() before doing this.


            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES)+"/MemWall");
            boolean success = mediaStorageDir.mkdir();
        Log.d("MemWall", ""+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
            // This location works best if you want the created images to be shared
            // between applications and persist after your app has been uninstalled.

            // Create the storage directory if it does not exist
            if (! mediaStorageDir.exists()){
            Log.d("MemWall", "failed to create directory");
            }


            String hashTag = ((MainActivity) getActivity()).getHashTag();
            // Create a media file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File mediaFile;
            if (type == MEDIA_TYPE_IMAGE){
                mediaFile = new File(mediaStorageDir.getPath(),
                ""+hashTag+"_"+"User" + "_"+timeStamp+".jpg");
            } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath(),
                    ""+hashTag+"_"+"User"+ "_"+timeStamp+".mp4");
            } else {
            return null;
            }

            return mediaFile;
            }

    private void checkPermissions(){
        if (ContextCompat.checkSelfPermission(getActivity(),
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS);
            }
      /*  if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS);
            }*/
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case (MY_PERMISSIONS): {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast toast = Toast.makeText(null, "Permission granted", Toast.LENGTH_LONG);
                    toast.show();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}
