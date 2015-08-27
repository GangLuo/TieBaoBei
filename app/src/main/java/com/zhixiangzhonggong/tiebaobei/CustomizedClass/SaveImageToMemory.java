package com.zhixiangzhonggong.tiebaobei.CustomizedClass;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.zhixiangzhonggong.tiebaobei.model.CarInformation;
import com.zhixiangzhonggong.tiebaobei.webrequest.AppController;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class SaveImageToMemory extends AsyncTask< Void, Void, ArrayList<String> > {
    Bitmap image;
    String imageName;
    String imagePath;
    CarInformation carInformation;
    private Context context = null;
    private ArrayList<String> storePictureUrl;

    public SaveImageToMemory(Bitmap image, String imageName, CarInformation carInformation) {
        super();
        this.image = image;
        this.imageName = imageName;
        this.carInformation = carInformation;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... params) {
        storePictureUrl= new ArrayList<>();
        imagePath = saveImageInternalMemory(image, imageName);

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<String> result) {
        Log.d("News reader", "Feed downloaded");
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


}

