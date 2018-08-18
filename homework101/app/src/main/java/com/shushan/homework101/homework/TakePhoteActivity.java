package com.shushan.homework101.homework;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luck.picture.lib.entity.LocalMedia;
import com.shushan.homework101.Constants;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.shushan.homework101.Constants.GALLERY_REQUEST_CODE;
import static com.shushan.homework101.Constants.REQUEST_STORAGE_READ_ACCESS_PERMISSION;

/**
 * @Class: TakePhoteActivity
 * @Description: take pictures activity
 * @author: liming
 */
public class TakePhoteActivity extends BaseActivity implements CameraPreview.OnCameraStatusListener,
        SensorEventListener {
    private static final String TAG = "TakePhoteActivity";
    public static final Uri IMAGE_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    public static final String PATH = Environment.getExternalStorageDirectory()
            .toString() + "/AndroidMedia/";
    CameraPreview mCameraPreview;
    CropImageView mCropImageView;
    RelativeLayout mTakePhotoLayout;
    LinearLayout mCropperLayout;
    private String flashMode="auto";
    private ImageView iv_flash;
    private FocusView focusView;
    private List<LocalMedia> selectList = new ArrayList<>();
    @Override
    protected void initContentView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_take_phote);
    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        if(intent!=null){
            selectList=intent.getParcelableArrayListExtra("selectList");
        }
    }

    @Override
    protected void initViews() {
        mCropImageView = (CropImageView) findViewById(R.id.CropImageView);
        mCameraPreview = (CameraPreview) findViewById(R.id.cameraPreview);
        focusView = (FocusView) findViewById(R.id.view_focus);
        mTakePhotoLayout = (RelativeLayout) findViewById(R.id.take_photo_layout);
        mCropperLayout = (LinearLayout) findViewById(R.id.cropper_layout);
        iv_flash = (ImageView) findViewById(R.id.iv_flash);
        mSensorManager = (SensorManager) getSystemService(Context.
                SENSOR_SERVICE);
        mAccel = mSensorManager.getDefaultSensor(Sensor.
                TYPE_ACCELEROMETER);
    }

    @Override
    protected void initEvents() {
        mCameraPreview.setFocusView(focusView);
        mCameraPreview.setOnCameraStatusListener(this);
        mCropImageView.setGuidelines(2);
    }
    boolean isRotated = false;

    @Override
    protected void onResume() {
        super.onResume();
        if(!isRotated) {
            final TextView hint_tv = (TextView) findViewById(R.id.hint);
            ObjectAnimator animator = ObjectAnimator.ofFloat(hint_tv, "rotation", 0f, 90f);
            animator.setStartDelay(800);
            animator.setDuration(2000);
            animator.setInterpolator(new LinearInterpolator());
            animator.start();
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }
                @Override
                public void onAnimationEnd(Animator animator) {
                    hint_tv.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            View view =  findViewById(R.id.crop_hint);
            AnimatorSet animSet = new AnimatorSet();
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "rotation", 0f, 90f);
            ObjectAnimator moveIn = ObjectAnimator.ofFloat(view, "translationX", 0f, -50f);
            animSet.play(animator1).before(moveIn);
            animSet.setDuration(10);
            animSet.start();
            isRotated = true;
        }
        mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtils.e("onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    public void takePhoto(View view) {
        if(mCameraPreview != null) {
            mCameraPreview.takePicture();
        }
    }
   public void selectPic(View view){
       //pickFromGallery();
       Intent intent=new Intent(this,AddPicActivity.class);
       intent.putExtra("type", Constants.TYPE_ALBUM);
       intent.putParcelableArrayListExtra("selectList",(ArrayList)selectList);
       startActivity(intent);
   }
    public void close(View view) {
        finish();
    }
    public void flashClick(View view){
      if(flashMode.equals("auto")){
          flashMode="on";
          iv_flash.setImageResource(R.drawable.homework_flash_on);
          if(mCameraPreview!=null){
              mCameraPreview.setFlashMode(flashMode);
          }
          return;
      }if(flashMode.equals("on")){
            flashMode="off";
            iv_flash.setImageResource(R.drawable.homework_flash_off);
            if(mCameraPreview!=null){
                mCameraPreview.setFlashMode(flashMode);
            }
            return;
        }else{
            flashMode="auto";
            iv_flash.setImageResource(R.drawable.homework_flash_auto);
            if(mCameraPreview!=null){
                mCameraPreview.setFlashMode(flashMode);
            }
            return;
        }
    }
    private void pickFromGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(TakePhoteActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.permission_read_storage_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
            //if you restrict the type of images that upload to the server,you can write as follows
            pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(pickIntent, GALLERY_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    LogUtils.d("== select pic finish==");
                    Uri source = data.getData();
                    //ready to screenshot
                    try {
                        mCropImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), source));
                    } catch (IOException e) {
                        LogUtils.e(e.getMessage());
                    }
                    showCropperLayout();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * close the screenshot activity
     * @param view
     */
    public void closeCropper(View view) {
        showTakePhotoLayout();
    }

    /**
     * ready to screenshot and save the picture
     * @param view
     */
    public void startCropper(View view) {
        //get the screenshot and rotate it 90 degree
        CropperImage cropperImage = mCropImageView.getCroppedImage();
        LogUtils.e(cropperImage.getX() + "," + cropperImage.getY());
        LogUtils.e(cropperImage.getWidth() + "," + cropperImage.getHeight());
        //Bitmap bitmap = Utils.rotate(cropperImage.getBitmap(), -90);
        //Bitmap bitmap = mCropImageView.getCroppedImage();
        Bitmap bitmap=cropperImage.getBitmap();
        // system time
        long dateTaken = System.currentTimeMillis();
        // picture name
        String filename = DateFormat.format("yyyy-MM-dd kk.mm.ss", dateTaken)
                .toString() + ".jpg";
        Uri uri = insertImage(getContentResolver(), filename, dateTaken, PATH,
                filename, bitmap, null);
        cropperImage.getBitmap().recycle();
        cropperImage.setBitmap(null);
        Intent intent = new Intent(this,AddPicActivity.class);
        intent.setData(uri);
        intent.putExtra("path", PATH + filename);
        intent.putExtra("width", bitmap.getWidth());
        intent.putExtra("height", bitmap.getHeight());
        intent.putExtra("cropperImage", cropperImage);
        intent.putExtra("type", Constants.TYPE_CAMERA);
        if(selectList!=null&&selectList.size()>0){
            intent.putParcelableArrayListExtra("selectList",(ArrayList)selectList);
        }
        startActivity(intent);
        bitmap.recycle();
        finish();
        super.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
//        doAnimation(cropperImage);
    }

    private void doAnimation(CropperImage cropperImage) {
        ImageView imageView = new ImageView(this);
        View view = LayoutInflater.from(this).inflate(
                R.layout.image_view_layout, null);
        ((RelativeLayout) view.findViewById(R.id.root_layout)).addView(imageView);
        RelativeLayout relativeLayout = ((RelativeLayout) findViewById(R.id.root_layout));
//        relativeLayout.addView(imageView);
        imageView.setX(cropperImage.getX());
        imageView.setY(cropperImage.getY());
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = (int)cropperImage.getWidth();
        lp.height = (int) cropperImage.getHeight();
        imageView.setLayoutParams(lp);
        imageView.setImageBitmap(cropperImage.getBitmap());
        try {
            getWindow().addContentView(view, lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(this, "translationX", cropperImage.getX(), 0);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", cropperImage.getY(), 0);*/

        TranslateAnimation translateAnimation = new TranslateAnimation(
                0, -cropperImage.getX(), 0, -(Math.abs(cropperImage.getHeight() - cropperImage.getY())));
        RotateAnimation rotateAnimation = new RotateAnimation(0, -90,
                Animation.ABSOLUTE, cropperImage.getX() , Animation.ABSOLUTE, cropperImage.getY());
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.setFillAfter(true);
        animationSet.setDuration(2000L);
        imageView.startAnimation(animationSet);
//        finish();
    }

    /**
     * Callback after photo is taken successfully
     * save images and display screenshot interface
     * @param data
     */
    @Override
    public void onCameraStopped(byte[] data) {
        LogUtils.i("==onCameraStopped==");
        // create the image
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        // system time
        long dateTaken = System.currentTimeMillis();
        // image name
        String filename = DateFormat.format("yyyy-MM-dd kk.mm.ss", dateTaken)
                .toString() + ".jpg";
        // save the image
        Uri source = insertImage(getContentResolver(), filename, dateTaken, PATH,
                filename, bitmap, data);
        //ready to screenshot
        try {
            mCropImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), source));
//            mCropImageView.rotateImage(90);
        } catch (IOException e) {
            LogUtils.e(e.getMessage());
        }
        showCropperLayout();
    }

    /**
     * store images and add information to the media database
     */
    private Uri insertImage(ContentResolver cr, String name, long dateTaken,
                            String directory, String filename, Bitmap source, byte[] jpegData) {
        OutputStream outputStream = null;
        String filePath = directory + filename;
        try {
            File dir = new File(directory);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(directory, filename);
            if (file.createNewFile()) {
                outputStream = new FileOutputStream(file);
                if (source != null) {
                    source.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                } else {
                    outputStream.write(jpegData);
                }
            }
        } catch (FileNotFoundException e) {
            LogUtils.e(e.getMessage());
            return null;
        } catch (IOException e) {
            LogUtils.e(e.getMessage());
            return null;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable t) {
                }
            }
        }
        ContentValues values = new ContentValues(7);
        values.put(MediaStore.Images.Media.TITLE, name);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
        values.put(MediaStore.Images.Media.DATE_TAKEN, dateTaken);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.DATA, filePath);
        return cr.insert(IMAGE_URI, values);
    }

    private void showTakePhotoLayout() {
        mTakePhotoLayout.setVisibility(View.VISIBLE);
        mCropperLayout.setVisibility(View.GONE);
    }

    private void showCropperLayout() {
        mTakePhotoLayout.setVisibility(View.GONE);
        mCropperLayout.setVisibility(View.VISIBLE);
        mCameraPreview.start();
    }


    private float mLastX = 0;
    private float mLastY = 0;
    private float mLastZ = 0;
    private boolean mInitialized = false;
    private SensorManager mSensorManager;
    private Sensor mAccel;
    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        if (!mInitialized){
            mLastX = x;
            mLastY = y;
            mLastZ = z;
            mInitialized = true;
        }
        float deltaX  = Math.abs(mLastX - x);
        float deltaY = Math.abs(mLastY - y);
        float deltaZ = Math.abs(mLastZ - z);

        if(deltaX > 0.8 || deltaY > 0.8 || deltaZ > 0.8){
            mCameraPreview.setFocus();
        }
        mLastX = x;
        mLastY = y;
        mLastZ = z;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
