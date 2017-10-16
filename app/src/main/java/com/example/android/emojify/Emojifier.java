package com.example.android.emojify;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

/**
 * Created by Edward on 10/16/2017.
 */

public class Emojifier {
    public static void detectFaces(Context context, Bitmap image){

        FaceDetector faceDetector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        if (!faceDetector.isOperational()){
            new AlertDialog.Builder(context)
                    .setMessage("Could not set up the face detector!").show();
            return ;
        }

        Frame frame = new Frame.Builder().setBitmap(image).build();
        SparseArray<Face> faces = faceDetector.detect(frame);
        if (faces.size() == 0){
            Toast.makeText(context, "No faces has been detected.", Toast.LENGTH_SHORT).show();
        }

        faceDetector.release();
    }
}
