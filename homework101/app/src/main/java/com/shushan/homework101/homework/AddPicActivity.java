package com.shushan.homework101.homework;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.shushan.homework101.Constants;
import com.shushan.homework101.HttpHelper.service.entity.homework.JobCheck;
import com.shushan.homework101.HttpHelper.service.entity.homework.JobTutorship;
import com.shushan.homework101.HttpHelper.service.presenter.JobCheckPresenter;
import com.shushan.homework101.HttpHelper.service.presenter.JobTutorshipPresenter;
import com.shushan.homework101.HttpHelper.service.view.JobCheckView;
import com.shushan.homework101.HttpHelper.service.view.JobTutorshipView;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.adapter.HomeworkGridImageAdapter;
import com.shushan.homework101.base.BaseActivity;
import com.shushan.homework101.homework.clip.TakePhoteActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.shushan.homework101.Constants.HOMEWORK_ADD_REQUEST_CODE;
import static com.shushan.homework101.Constants.HOMEWORK_BACK_REQUEST_CODE;

public class AddPicActivity extends BaseActivity implements View.OnClickListener,HomeworkGridImageAdapter.OnDeleteClickListener{
    @Bind(R.id.iv_add_pic_back)
    ImageView iv_add_pic_back;
    @Bind(R.id.tv_add_pic_right)
    TextView tv_add_pic_right;
    @Bind(R.id.recycler_add_pic)
    RecyclerView recycler_add_pic;
    @Bind(R.id.ll_add_pic)
    LinearLayout ll_add_pic;

    private int themeId;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<LocalMedia> selectListTotal = new ArrayList<>();
    private int maxSelectNum = 100;
    private HomeworkGridImageAdapter adapter;
    private String path;
    private int width;
    private int height;
    private int type;
    private HomeworkCheckPopupWindow homeworkCheckPopupWindow;
    private int homework_type;
    private String userid;
    private String token;
    private JobCheckPresenter jobCheckPresenter;
    private JobCheck mJobCheck;
    private String[] picArrays;
    private JobTutorshipPresenter jobTutorshipPresenter;

    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_add_pic);
    }
    @Override
    protected void initData() {
        themeId = R.style.picture_default_style;
        Intent intent=getIntent();
        if(intent!=null){
            type = intent.getIntExtra("type",0);
            selectListTotal=intent.getParcelableArrayListExtra("selectList");
            if(selectListTotal!=null&&selectListTotal.size()>0){
                selectList.addAll(selectListTotal);
            }
            if(type== Constants.TYPE_CAMERA){
                path = intent.getStringExtra("path");
                width = intent.getIntExtra("width", 0);
                height = intent.getIntExtra("height", 0);
                LocalMedia localMedia=new LocalMedia();
                localMedia.setPath(path);
                localMedia.setWidth(width);
                localMedia.setHeight(height);
                selectList.add(0,localMedia);

                picArrays=new String[selectList.size()];

                for (int i = 0; i <selectList.size() ; i++) {
                    picArrays[i]=selectList.get(i).getPath();
                }

                adapter.notifyDataSetChanged();
            }else if(type==Constants.TYPE_ALBUM) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void initViews() {
        SharedPreferences sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
        homework_type = sharedPreferences.getInt("homework_type",0);
        userid = sharedPreferences.getString("userid","");
        token = sharedPreferences.getString("token","");
        LogUtils.d("userid:"+userid+"\ntoken:"+token);
        jobCheckPresenter = new JobCheckPresenter(this);
        jobCheckPresenter.onCreate(Constants.BASE_URL);
        jobCheckPresenter.attachView(jobCheckView);
        jobTutorshipPresenter = new JobTutorshipPresenter(this);
        jobTutorshipPresenter.onCreate(Constants.BASE_URL);
        jobTutorshipPresenter.attachView(jobTutorshipView);

        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recycler_add_pic.setLayoutManager(manager);
        adapter = new HomeworkGridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recycler_add_pic.setAdapter(adapter);
    }

    @Override
    protected void initEvents() {
        iv_add_pic_back.setOnClickListener(this);
        tv_add_pic_right.setOnClickListener(this);
        adapter.setDeleteClickListener(this);
        adapter.setOnItemClickListener(new HomeworkGridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(AddPicActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(AddPicActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(AddPicActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
// 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(AddPicActivity.this);
                } else {
                    Toast.makeText(AddPicActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    private JobTutorshipView jobTutorshipView=new JobTutorshipView() {
        @Override
        public void onSuccess(JobTutorship jobTutorship) {
            if(jobTutorship!=null&&jobTutorship.getError()==0){

                LogUtils.d(jobTutorship.toString());

                homeworkCheckPopupWindow = new HomeworkCheckPopupWindow(AddPicActivity.this,ll_add_pic,homework_type,jobTutorship,picArrays);
                if (homeworkCheckPopupWindow != null) {
                    if (homeworkCheckPopupWindow.isShowing()) {
                        homeworkCheckPopupWindow.dismiss();
                    }
                    homeworkCheckPopupWindow.showAsDropDown(ll_add_pic);
                }
            }
        }

        @Override
        public void onError(String result) {

        }
    };
    private JobCheckView jobCheckView = new JobCheckView() {
        @Override
        public void onSuccess(JobCheck jobCheck) {
            if(jobCheck!=null&&jobCheck.getError()==0){

                LogUtils.d(jobCheck.toString());
                mJobCheck=jobCheck;
                homeworkCheckPopupWindow = new HomeworkCheckPopupWindow(AddPicActivity.this,ll_add_pic,homework_type,jobCheck,picArrays);
                if (homeworkCheckPopupWindow != null) {
                    if (homeworkCheckPopupWindow.isShowing()) {
                        homeworkCheckPopupWindow.dismiss();
                    }
                    homeworkCheckPopupWindow.showAsDropDown(ll_add_pic);
                }
            }
        }

        @Override
        public void onError(String result) {
            LogUtils.d(result.toString());

        }
    };

    private HomeworkGridImageAdapter.onAddPicClickListener onAddPicClickListener = new HomeworkGridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册 以下是例子：不需要的api可以不写
            if(type==Constants.TYPE_ALBUM){
                PictureSelector.create(AddPicActivity.this)
                        .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(maxSelectNum)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(3)// 每行显示个数
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .previewVideo(true)// 是否可预览视频
                        .enablePreviewAudio(true) // 是否可播放音频
                        .isCamera(false)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        //.compressSavePath(getPath())//压缩图片保存地址
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .selectionMedia(selectList)// 是否传入已选图片
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

            }else{
                Intent intent=new Intent(AddPicActivity.this,TakePhoteActivity.class);
                intent.putParcelableArrayListExtra("selectList",(ArrayList)selectList);
                startActivityForResult(intent, HOMEWORK_ADD_REQUEST_CODE);
            }
        }

    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_pic_back:
                Intent intent = new Intent(AddPicActivity.this, TakePhoteActivity.class);
                intent.putParcelableArrayListExtra("selectList", (ArrayList) selectList);
                startActivityForResult(intent,HOMEWORK_BACK_REQUEST_CODE);
                break;
            case R.id.tv_add_pic_right:
                if(!TextUtils.isEmpty(userid)&&!TextUtils.isEmpty(token)&&picArrays!=null&&picArrays.length>=1){
                    if(homework_type==1){
                        jobCheckPresenter.getJobCheckMsg(userid,token);
                    }else{
                        jobTutorshipPresenter.getJobTutorshipMsg(userid,token);
                    }
                }
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        //Log.i("图片-----》", media.getPath());
                        LogUtils.d(selectList.toString());
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();

                    picArrays=new String[selectList.size()];

                    for (int i = 0; i <selectList.size() ; i++) {
                        picArrays[i]=selectList.get(i).getPath();
                    }
                    break;
                case HOMEWORK_BACK_REQUEST_CODE:
                    if(data!=null){
                        ArrayList<LocalMedia> selectList=data.getParcelableArrayListExtra("selectList");
                        if(selectList!=null&&selectList.size()>0){
                            LogUtils.d(selectList.toString());

                            picArrays=new String[selectList.size()];
                            for (int i = 0; i <selectList.size() ; i++) {
                                picArrays[i]=selectList.get(i).getPath();
                            }
                        }
                    }
                    break;
                case HOMEWORK_ADD_REQUEST_CODE:
                    if(data!=null){
                        ArrayList<LocalMedia> selectList=data.getParcelableArrayListExtra("selectList");
                        if(selectList!=null&&selectList.size()>0){
                            LogUtils.d(selectList.toString());

                            picArrays=new String[selectList.size()];
                            for (int i = 0; i <selectList.size() ; i++) {
                                picArrays[i]=selectList.get(i).getPath();
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("onDestroy");

    }

    @Override
    public void onDeleteClick(int position) {
        LogUtils.d("selectList.size():"+selectList.size());
        if(picArrays!=null&&picArrays.length>0){
            List<String> picList=Arrays.asList(picArrays);
            ArrayList<String> arrayPicList=new ArrayList<>(picList);
            arrayPicList.remove(position);
            picArrays=new String[arrayPicList.size()];
            for (int i = 0; i <arrayPicList.size() ; i++) {

                picArrays[i]= arrayPicList.get(i);
            }
            LogUtils.d("picArrays.size():"+Arrays.toString(picArrays));
        }
    }
}
