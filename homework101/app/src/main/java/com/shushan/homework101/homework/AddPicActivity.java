package com.shushan.homework101.homework;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.adapter.HomeworkGridImageAdapter;
import com.shushan.homework101.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AddPicActivity extends BaseActivity implements View.OnClickListener {
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

    protected void initContentView() {
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
                adapter.notifyDataSetChanged();
            }else if(type==Constants.TYPE_ALBUM) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void initViews() {
        homeworkCheckPopupWindow = new HomeworkCheckPopupWindow(AddPicActivity.this,ll_add_pic);
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
                startActivity(intent);
            }
        }

    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_pic_back:
                Intent intent = new Intent(AddPicActivity.this, TakePhoteActivity.class);
                intent.putParcelableArrayListExtra("selectList", (ArrayList) selectList);
                startActivity(intent);
                break;
            case R.id.tv_add_pic_right:
                if (homeworkCheckPopupWindow != null) {
                    if (homeworkCheckPopupWindow.isShowing()) {
                        homeworkCheckPopupWindow.dismiss();
                    }
                    homeworkCheckPopupWindow.showAsDropDown(ll_add_pic);
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
        /*if(selectList!=null&&selectList.size()>0){
            selectList.clear();
        }*/
    }
}
