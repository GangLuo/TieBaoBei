package com.zhixiangzhonggong.tiebaobei.CustomizedClass;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.zhixiangzhonggong.tiebaobei.model.CarInformation;
import com.zhixiangzhonggong.tiebaobei.myInterface.saveImageAsyncTaskListener;
import com.zhixiangzhonggong.tiebaobei.webrequest.AppController;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SaveImageToMemory extends AsyncTask< HashMap<String,Bitmap>, Void, ArrayList<String> > {
   Bitmap image;
    String imageName;
    String imagePath;
    CarInformation carInformation;
    HashMap<String,Bitmap> nameAndPicture;
    private Context context = null;
    private ArrayList<String> storePictureUrl;
    private saveImageAsyncTaskListener eventListener;
    private ArrayList<String> imagePaths;
    public SaveImageToMemory(HashMap<String,Bitmap> nameAndPicture, CarInformation carInformation) {
        super();
       // this.image = image;
       // this.imageName = imageName;
        this.carInformation = carInformation;
        this.nameAndPicture=nameAndPicture;
    }

    @Override
    protected ArrayList<String> doInBackground(HashMap<String,Bitmap>... params) {
        HashMap<String,Bitmap> nameAndPicture=params[0];
        imagePaths = new ArrayList<String>();
        storePictureUrl= new ArrayList<>();
        for(Map.Entry<String,Bitmap> entry :nameAndPicture.entrySet()){
             imageName=entry.getKey();
             image=entry.getValue();
            imagePath = saveImageInternalMemory(image, imageName);
            imagePaths.add(imagePath+ "/" +imageName);
        }


        return imagePaths;
    }

    @Override
    protected void onPostExecute(ArrayList<String> result) {
        Log.d("News reader", "Feed downloaded");
        eventListener.onImagePathsReady(imagePaths);
       // carInformation.setCarPictureLocalUrl(imagePath);
       // saveToDatabase(carInformation, imagePath);
    }

    public String saveImageInternalMemory (Bitmap bitmapImage, String imageName){
        ContextWrapper cw = new ContextWrapper(AppController.getInstance().getApplicationContext());
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

    public void setEventListener(saveImageAsyncTaskListener listener){
        this.eventListener=listener;
    }

}

