package com.example.tharani.externalstorage;
/*import is libraries imported for writing the code
* AppCompatActivity is base class for activities
* Bundle handles the orientation of the activity
*/
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    /*onCreate is the first method in the life cycle of an activity
     savedInstance passes data to super class,data is pull to store state of application
   * setContentView is used to set layout for the activity
   *R is a resource and it is auto generate file
   * activity_main assign an integer value*/
    //declaring variables
    ImageView imageview;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview= findViewById(R.id.imageView);
        button= findViewById(R.id.button);
        //returning reference to the view
        /*final is a keyword once we created as final cannot be changes
        * creating object ByteArrayOutputStream using new
        * ByteArrayOutputStream class stream creates a buffer in memory and all data sent to the stream in stored in buffer*/
        final ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        button.setOnClickListener(new View.OnClickListener() {//here we are creating the onclick method
            @Override
            public void onClick(View view) {
                /* ResourcesCompat.getDrawable() return a drawable object associated with a particular resource ID and styled for the specified theme
                * Bitmap is representation of bitmap image*/
                Drawable drawable= ResourcesCompat.getDrawable(getResources(),R.drawable.image,null);
                Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();//by suing get method giving reference to bitmap
                bitmap.compress(Bitmap.CompressFormat.PNG,50,byteArrayOutputStream);
                // Bitmap.CompressFormat specifies the known formats a bitmap can be compressed into. enum, Bitmap.Config
                // finding SD Card path,gets ExternalStorageDirectory
                File filepath=new File(Environment.getExternalStorageDirectory()+"/Image File/");
                // using try & catch block
                try {//The try block contains set of statements where an exception can occur. A try block is always followed by a catch block,
                    Log.e("path", "path= "+ new File( Environment.getExternalStorageDirectory()
                            + "/SampleImage.png").getCanonicalPath());
                } catch (IOException e) {// A try block must be followed by catch blocks and catches IOException
                    e.printStackTrace();//printStackTrace used to print the description of exception
                }
                try {
                    //creation of new new file
                    filepath.createNewFile();
                   /* creating object of FileOutputStream
                   *FileOutputStream used to create a file and write data into it */
                    FileOutputStream fileOutputStream=new FileOutputStream(filepath);
                    //converting the data into byte
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    //This class implements an output stream in which the data is written into a byte array
                    //closing the  fileOutputStream
                    fileOutputStream.close();

                } catch (FileNotFoundException e) {
                    //catches the FileNotFoundException which is checked exception that is compile time exception
                    e.printStackTrace();//printStackTrace used to print the description of FileNotFoundException
                } catch (IOException e) { //catches the IOException which is checked exception that is compile time exception
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();
                /*A toast is a view containing a quick little message for the user.
                * LENGTH_LONG display toast message in long period of time*/

            }
        });


    }
}