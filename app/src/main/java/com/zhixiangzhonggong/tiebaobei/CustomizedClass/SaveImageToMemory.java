package com.zhixiangzhonggong.tiebaobei.CustomizedClass;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.zhixiangzhonggong.tiebaobei.model.CarInformation;

import java.io.File;
import java.io.FileOutputStream;


public class SaveImageToMemory extends AsyncTask<Void, Void, Void> {
    Bitmap image;
    String imageName;
    String imagePath;
    CarInformation carInformation;
    private Context context = null;

    public SaveImageToMemory(Bitmap image, String imageName, CarInformation carInformation) {
        super();
        this.image = image;
        this.imageName = imageName;
        this.carInformation = carInformation;
    }

    @Override
    protected Void doInBackground(Void... params) {
        imagePath = saveImageInternalMemory(image, imageName);
        carInformation.setCarPictureLocalUrl(imagePath);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        Log.d("News reader", "Feed downloaded");
       // saveToDatabase(carInformation, imagePath);
    }

    public String saveImageInternalMemory (Bitmap bitmapImage, String imageName){
        ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory, imageName);
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }

 /*   public void saveToDatabase(CarInformation carInformation, String mediaPath)
    {
        carInformation.setCarPictureLocalUrl(mediaPath);

        if(isUpdate){
            mediaDB.updateMedia(media);

            String logString = new String(currentMedia + " pictures have been updated." +
                    mediaList.getMediaArray().get(currentMedia).getMediaUrl());
            Log.d(TAG, logString);

            if(currentMedia < totalMedias - 1 ){
                getTheNextMediaImgOrVid(currentMedia);
                currentMedia++;
            }
            else{
                currentMedia = 0;

                int currentActivity = sharedPref.getInt(Constants.KEY_CURRENT_ACTIVITY, Constants.CURRENT_NO_NEED_TO_UPDATE);
                if(currentActivity == Constants.CURRENT_MEDIA_SHOW_ACTIVITY){
                    AppController.getInstance().generalActivity.refreshActivityContent();
                    Log.d(TAG, "content updated");
                }
            }
        }else{
            mediaDB.insertMedia(media);

            String logString = new String(currentMedia+" pictures have been saved." +
                    mediaList.getMediaArray().get(currentMedia).getMediaUrl());
            Log.d(TAG, logString);

            if(currentMedia < totalMedias - 1){
                getTheNextMediaImgOrVid(currentMedia);
                currentMedia++;
            }
            else{
                currentMedia = 0;
            }
        }
    }*/

}

