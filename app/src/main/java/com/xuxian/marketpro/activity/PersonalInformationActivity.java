package com.xuxian.marketpro.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.util.AbDateUtil;
import com.ab.util.AbFileUtil;
import com.ab.util.AbPreferenceUtils;
import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.xuxian.marketpro.R;
import com.xuxian.marketpro.activity.supers.SuperSherlockActivity;
import com.xuxian.marketpro.libraries.util.ActivityUtil;
import com.xuxian.marketpro.libraries.util.AddPhoto;
import com.xuxian.marketpro.libraries.util.monitor.PhoneMonitor;
import com.xuxian.marketpro.presentation.View.widght.CircleImageView;
import com.xuxian.marketpro.presentation.View.widght.pop.OperationPopupWindow;
import com.xuxian.marketpro.presentation.application.MyApplication;
import com.xuxian.marketpro.presentation.db.UserDb;
import com.xuxian.marketpro.presentation.entity.UserEntity;

import java.io.File;
import java.util.Calendar;

import static android.widget.LinearLayout.HORIZONTAL;

/**
 * Created by youarenotin on 16/8/23.
 */
public class PersonalInformationActivity extends SuperSherlockActivity implements View.OnClickListener {
    private static final int CAMERA_CROP_DATA = 3022;
    private static final int CAMERA_WITH_DATA = 3023;
    public static final int CITY = 3024;
    private static final int DATE_DIALOG_ID = 1;
    public static final int IMAGE = 3;
    public static final int LOVE = 3025;
    private static final int PHOTO_PICKED_WITH_DATA = 3021;
    public static final int TO_SELECT_PHOTO = 3;
    protected static final int TO_UPLOAD_FILE = 1;
    protected static final int UPLOAD_FILE_DONE = 2;
    private static final int UPLOAD_INIT_PROCESS = 4;
    private static final int UPLOAD_IN_PROCESS = 5;
    private File PHOTO_DIR;
    private Button btn_cancel;
    private Button btn_photo_albums;
    private Button btn_system_head;
    private Button btn_taking_pictures;
    private EditText ed_email;
    private EditText ed_name;
    private EditText ed_new_paw;
    private EditText ed_old_paw;
    private Handler handler;
    private String image;
    /**
     * 头像
     */
    private CircleImageView iv_head_pic;
    private String love;
    private File mCurrentPhotoFile;
    private OnDateSetListener mDateSetListener;
    private int mDay;
    private String mFileName;
    private int mMonth;
    private int mYear;
    public View popView;
    public OperationPopupWindow popWindow;
    final String[] professionalItems;
    private ProgressDialog progressDialog;
    /**
     * 生日
     */
    private RelativeLayout rl_birthday;
    /**
     * 许鲜币
     */
    private RelativeLayout rl_data_point;
    /**
     * 邮箱
     */
    private RelativeLayout rl_update_email;
    /**
     * 爱好
     */
    private RelativeLayout rl_update_love;
    /**
     * 会员名
     */
    private RelativeLayout rl_update_name;
    /**
     * 修改密码
     */
    private RelativeLayout rl_update_password;
    /**
     * 修改电话
     */
    private RelativeLayout rl_update_phone;
    /**
     * 修改职业
     */
    private RelativeLayout rl_update_professional;
    /**
     * '
     * 修改学校
     */
    private RelativeLayout rl_update_school;
    /**
     * 修改性别
     */
    private RelativeLayout rl_update_sex;

    private final String[] sexItems;
    /**
     * 生日
     */
    private TextView tv_birthday;
    /**
     * 许鲜币
     */
    private TextView tv_data_point;
    /**
     * 邮箱
     */
    private TextView tv_update_email;
    /**
     * 爱好
     */
    private TextView tv_update_love;
    /**
     * 会员名
     */
    private TextView tv_update_name;
    /**
     * 密码
     */
    private TextView tv_update_password;
    /**
     * 电话
     */
    private TextView tv_update_phone;
    /**
     * 职业
     */
    private TextView tv_update_professional;
    /**
     * 学校
     */
    private TextView tv_update_school;
    /**
     * 性别
     */
    private TextView tv_update_sex;
    private String type;
    //    private NetworkAsyncTask updateAsyncTask;
//    private NetworkAsyncTask updateSystemImageAsyncTask;
//    private NetworkAsyncTask updatemodifyPwdOrUnameAsyncTask;
    private UserDb userDb;
    private UserEntity userEntity;

    public PersonalInformationActivity() {
        sexItems = new String[]{"男", "女"};
        professionalItems = new String[]{"学生", "在职", "退休", "自由职业"};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information_layout);
        initTitleBar();
        initFindViewById();
        initPicPopWindow();
        setListener();
        init();
    }

    private void initPicPopWindow() {
        popView = View.inflate(getActivity(), R.layout.choose_picture_dialog, null);
        btn_taking_pictures = (Button) this.popView.findViewById(R.id.btn_taking_pictures);
        btn_system_head = (Button) this.popView.findViewById(R.id.btn_system_head);
        btn_photo_albums = (Button) this.popView.findViewById(R.id.btn_photo_albums);
        btn_cancel = (Button) this.popView.findViewById(R.id.btn_cancel);
        popWindow = new OperationPopupWindow(this.popView, getActivity(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popWindow.setAnimationStyle(R.style.AnimBottom);
        popWindow.setBackgroundDrawable(new ColorDrawable(0x50000000));
        popWindow.setOutsideTouchable(true);
        this.btn_system_head.setOnClickListener(new View.OnClickListener() {//启动系统相册选择照片
            public void onClick(View v) {
                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }
                ActivityUtil.startSystemImageActivity(getActivity());
            }
        });
        this.btn_taking_pictures.setOnClickListener(new View.OnClickListener() {//拍照
            public void onClick(View v) {
                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }
                String photo_dir = AbFileUtil.getImageDownloadDir(getActivity());
                if (AbStrUtil.isEmpty(photo_dir)) {
                    AbToastUtil.showToast(getActivity(), "存储卡不存在");
                    return;
                }
                PHOTO_DIR = new File(photo_dir);
                doPickPhotoAction();
            }
        });
        this.btn_photo_albums.setOnClickListener(new View.OnClickListener() {//相册选取照片
            public void onClick(View v) {
                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }
                try {
                    Intent intent = new Intent("android.intent.action.GET_CONTENT", null);
                    intent.setType("image/*");
                    startActivityForResult(intent, PersonalInformationActivity.PHOTO_PICKED_WITH_DATA);
                } catch (ActivityNotFoundException e) {
                    AbToastUtil.showToast(getActivity(), "没有找到照片");
                }
            }
        });
        this.btn_cancel.setOnClickListener(new View.OnClickListener() {//取消
            public void onClick(View v) {
                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                }
            }
        });
    }

    private void doPickPhotoAction() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            doTakePhoto();
        } else {
            AbToastUtil.showToast(getActivity(), "没有可用的存储卡");
        }
    }

    protected void doTakePhoto() {
        try {
            this.mFileName = System.currentTimeMillis() + ".jpg";
            this.mCurrentPhotoFile = new File(this.PHOTO_DIR, this.mFileName);
            AbPreferenceUtils.savePrefString(getActivity(), "path", this.mCurrentPhotoFile.getPath());
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE", null);
            intent.putExtra("output", Uri.fromFile(this.mCurrentPhotoFile));
            startActivityForResult(intent, CAMERA_WITH_DATA);
        } catch (Exception e) {
            AbToastUtil.showToast(getActivity(), "未找到系统相机程序");
        }
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void init() {
        userDb = new UserDb(getActivity());
        userEntity = (UserEntity) getIntent().getBundleExtra(SuperSherlockActivity.INTENT_BUNDLE).get(SuperSherlockActivity.INTENT_OBJECT);
        if (this.userEntity != null) {
            this.tv_update_name.setText(this.userEntity.getUsername());
            this.tv_data_point.setText(this.userEntity.getPoint() + "");
            if (this.userEntity.getSex() == 0) {
                this.tv_update_sex.setText("女");
            } else {
                this.tv_update_sex.setText("男");
            }
            if (AbStrUtil.isEmpty(this.userEntity.getHead_ico())) {
                this.iv_head_pic.setBackgroundResource(R.drawable.pintuan_default_head_icon);
            } else {
                ImageLoader.getInstance().displayImage(this.userEntity.getHead_ico(), this.iv_head_pic, MyApplication.getInstance().getSampleOptions(R.drawable.pintuan_default_head_icon));
            }
            this.tv_birthday.setText(this.userEntity.getBirthday());
            this.tv_update_school.setText(this.userEntity.getSchool());
            this.tv_update_professional.setText(this.userEntity.getOccupation());
            this.tv_update_phone.setText(this.userEntity.getPhone());
            this.tv_update_email.setText(this.userEntity.getEmail());
            this.tv_update_love.setText(this.userEntity.getInterest());
            if (!AbStrUtil.isEmpty(this.tv_birthday.getText().toString())) {
                Calendar c1 = Calendar.getInstance();
                c1.setTime(AbDateUtil.getDateByFormat(this.tv_birthday.getText().toString(), AbDateUtil.dateFormatYMD));
                this.mYear = c1.get(TO_UPLOAD_FILE);
                this.mMonth = c1.get(UPLOAD_FILE_DONE);
                this.mDay = c1.get(UPLOAD_IN_PROCESS);
            }
        }
    }

    @Override
    protected void initTitleBar() {
        setTitle("个人信息");
        getTitleLeftClick().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    int sex;
                    if (tv_update_sex.getText().toString().equals("女")) {
                        sex = 0;
                    } else {
                        sex = PersonalInformationActivity.TO_UPLOAD_FILE;
                    }
                    if (!(userEntity.getPhone().equals(tv_update_phone.getText().toString()) && userEntity.getSex() == sex && userEntity.getBirthday().equals(tv_birthday.getText().toString()) && userEntity.getOccupation().equals(tv_update_professional.getText().toString()) && userEntity.getSchool().equals(tv_update_school.getText().toString()) && userEntity.getEmail().equals(tv_update_email.getText().toString()) && userEntity.getInterest().equals(tv_update_love.getText().toString()))) {
//                       updateAsyncTask = new NetworkAsyncTask(getActivity(), null, 0);
//                       updateAsyncTask.execute(new Object[]{userEntity.getUserid(), userEntity.getToken(), Integer.valueOf(sex), tv_update_email.getText().toString(), tv_birthday.getText().toString(), tv_update_professional.getText().toString(), tv_update_school.getText().toString(), tv_update_love.getText().toString()});
                    }
                } catch (Exception e) {
                }
                getActivity().finish();
            }
        });
    }

    @Override
    protected void initFindViewById() {
        iv_head_pic = (CircleImageView) findViewById(R.id.iv_head_pic);
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        tv_update_name = (TextView) findViewById(R.id.tv_update_name);
        tv_update_password = (TextView) findViewById(R.id.tv_update_password);
        tv_data_point = (TextView) findViewById(R.id.tv_data_point);
        tv_update_school = (TextView) findViewById(R.id.tv_update_school);
        tv_update_professional = (TextView) findViewById(R.id.tv_update_professional);
        tv_update_phone = (TextView) findViewById(R.id.tv_update_phone);
        tv_update_love = (TextView) findViewById(R.id.tv_update_love);
        tv_update_email = (TextView) findViewById(R.id.tv_update_email);
        tv_update_sex = (TextView) findViewById(R.id.tv_update_sex);
        rl_data_point = (RelativeLayout) findViewById(R.id.rl_data_point);
        rl_update_phone = (RelativeLayout) findViewById(R.id.rl_update_phone);
        rl_birthday = (RelativeLayout) findViewById(R.id.rl_birthday);
        rl_update_sex = (RelativeLayout) findViewById(R.id.rl_update_sex);
        rl_update_professional = (RelativeLayout) findViewById(R.id.rl_update_professional);
        rl_update_email = (RelativeLayout) findViewById(R.id.rl_update_email);
        rl_update_school = (RelativeLayout) findViewById(R.id.rl_update_school);
        rl_update_love = (RelativeLayout) findViewById(R.id.rl_update_love);
        rl_update_name = (RelativeLayout) findViewById(R.id.rl_update_name);
        rl_update_password = (RelativeLayout) findViewById(R.id.rl_update_password);
    }

    @Override
    protected void setListener() {
        this.iv_head_pic.setOnClickListener(this);
        this.rl_birthday.setOnClickListener(this);
        this.rl_data_point.setOnClickListener(this);
        this.rl_update_phone.setOnClickListener(this);
        this.rl_update_sex.setOnClickListener(this);
        this.rl_update_professional.setOnClickListener(this);
        this.rl_update_email.setOnClickListener(this);
        this.rl_update_school.setOnClickListener(this);
        this.rl_update_love.setOnClickListener(this);
        this.rl_update_name.setOnClickListener(this);
        this.rl_update_password.setOnClickListener(this);
        this.rl_birthday.setBackgroundResource(R.drawable.light_yellow_selector);
        this.rl_data_point.setBackgroundResource(R.drawable.light_yellow_selector);
        this.rl_update_phone.setBackgroundResource(R.drawable.light_yellow_selector);
        this.rl_update_sex.setBackgroundResource(R.drawable.light_yellow_selector);
        this.rl_update_professional.setBackgroundResource(R.drawable.light_yellow_selector);
        this.rl_update_email.setBackgroundResource(R.drawable.light_yellow_selector);
        this.rl_update_school.setBackgroundResource(R.drawable.light_yellow_selector);
        this.rl_update_love.setBackgroundResource(R.drawable.light_yellow_selector);
        this.rl_update_name.setBackgroundResource(R.drawable.light_yellow_selector);
        this.rl_update_password.setBackgroundResource(R.drawable.light_yellow_selector);
        PhoneMonitor.getInstance().register(PersonalInformationActivity.class.getName(), new PhoneMonitor.PhoneMonitorCallback() {
            @Override
            public void appOperation(String phone) {
                tv_update_phone.setText(phone);
            }
        });
    }

    private DatePickerDialog dialog;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head_pic:
                popWindow.showAtLocation(findViewById(R.id.personal_information), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.rl_update_name:
                initNameDialog();
                break;
            case R.id.rl_update_password:
                initPasswordDialog();
                break;
            case R.id.rl_update_phone:
                ActivityUtil.startVerificationActivity(getActivity());
                break;
            case R.id.rl_update_sex:
                initSexDialog();
                break;
            case R.id.rl_birthday:
                Calendar calendar = Calendar.getInstance();
                dialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                tv_birthday.setText(year + "/" + monthOfYear + "/"
                                        + dayOfMonth);
                            }
                        }, calendar.get(Calendar.YEAR), calendar
                        .get(Calendar.MONTH), calendar
                        .get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            case R.id.rl_update_professional:
                initPrefessionalDialog();
                break;
            case R.id.rl_update_school:
                ActivityUtil.startSchoolCityActivity(getActivity());
                break;
            case R.id.rl_update_love:
                ActivityUtil.startLoveActivity(getActivity(), this.tv_update_love.getText().toString());
                break;
            case R.id.rl_update_email:
                initEmailDialog();
                break;

        }
    }

    private void initPasswordDialog() {
        Builder builder = new Builder(getActivity());
        LinearLayout.LayoutParams params_editText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        this.ed_old_paw = new EditText(getActivity());
        this.ed_new_paw = new EditText(getActivity());
        this.ed_old_paw.setHint("请输入旧密码");
        this.ed_new_paw.setHint("请输入新密码");
        this.ed_old_paw.setSingleLine(true);
        this.ed_new_paw.setSingleLine(true);
        this.ed_old_paw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        this.ed_new_paw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        linearLayout.addView(this.ed_old_paw, params_editText);
        linearLayout.addView(this.ed_new_paw, params_editText);
        builder.setTitle("修改密码");
        builder.setView(linearLayout);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (userEntity != null) {
                    type = "paw";
//                    updatemodifyPwdOrUnameAsyncTask = new NetworkAsyncTask(getActivity(), null, PersonalInformationActivity.UPLOAD_FILE_DONE);
//                    NetworkAsyncTask access$1100 = updatemodifyPwdOrUnameAsyncTask;
//                    Object[] objArr = new Object[PersonalInformationActivity.UPLOAD_IN_PROCESS];
//                    objArr[0] = userEntity.getUserid();
//                    objArr[PersonalInformationActivity.TO_UPLOAD_FILE] = IceUdpTransportPacketExtension.PWD_ATTR_NAME;
//                    objArr[PersonalInformationActivity.UPLOAD_FILE_DONE] = ed_old_paw.getText().toString();
//                    objArr[PersonalInformationActivity.TO_SELECT_PHOTO] = ed_new_paw.getText().toString();
//                    objArr[PersonalInformationActivity.UPLOAD_INIT_PROCESS] = Long.valueOf(System.currentTimeMillis());
//                    access$1100.execute(objArr);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void initSexDialog() {
        Builder builder = new Builder(getActivity());
        builder.setTitle("性别选择");
        builder.setItems(this.sexItems, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                PersonalInformationActivity.this.tv_update_sex.setText(PersonalInformationActivity.this.sexItems[which]);
            }
        });
        builder.create().show();
    }

    private void initPrefessionalDialog() {
        Builder builder = new Builder(getActivity());
        builder.setTitle("职业选择");
        builder.setItems(this.professionalItems, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                PersonalInformationActivity.this.tv_update_professional.setText(PersonalInformationActivity.this.professionalItems[which]);
            }
        });
        builder.create().show();
    }

    private void initEmailDialog() {
        Builder builder = new Builder(getActivity());
        LinearLayout.LayoutParams params_editText = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(0);
        this.ed_email = new EditText(getActivity());
        this.ed_email.setText(this.tv_update_email.getText().toString());
        linearLayout.addView(this.ed_email, params_editText);
        builder.setTitle("修改邮箱");
        builder.setView(linearLayout);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                PersonalInformationActivity.this.tv_update_email.setText(PersonalInformationActivity.this.ed_email.getText().toString());
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void initNameDialog() {
        Builder builder = new Builder(getActivity());
        LinearLayout.LayoutParams params_editText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(HORIZONTAL);
        this.ed_name = new EditText(getActivity());
        this.ed_name.setSingleLine(true);
        this.ed_name.setHint("请输入昵称");
        linearLayout.addView(this.ed_name, params_editText);
        this.ed_name.setText(this.tv_update_name.getText().toString());
        builder.setTitle("\u4fee\u6539\u6635\u79f0");
        builder.setView(linearLayout);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (userEntity != null) {
//                    type = UserDao.COLUMN_NAME_ID;
//                    updatemodifyPwdOrUnameAsyncTask = new NetworkAsyncTask(getActivity(), null, PersonalInformationActivity.UPLOAD_FILE_DONE);
//                    NetworkAsyncTask access$1100 = updatemodifyPwdOrUnameAsyncTask;
//                    Object[] objArr = new Object[PersonalInformationActivity.UPLOAD_IN_PROCESS];
//                    objArr[0] = userEntity.getUserid();
//                    objArr[PersonalInformationActivity.TO_UPLOAD_FILE] = UserDao.COLUMN_NAME_ID;
//                    objArr[PersonalInformationActivity.UPLOAD_FILE_DONE] = tv_update_name.getText().toString();
//                    objArr[PersonalInformationActivity.TO_SELECT_PHOTO] = ed_name.getText().toString();
//                    objArr[PersonalInformationActivity.UPLOAD_INIT_PROCESS] = Long.valueOf(System.currentTimeMillis());
//                    access$1100.execute(objArr);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent mIntent) {
        super.onActivityResult(requestCode, resultCode, mIntent);
        if (resultCode == -1) {
            switch (requestCode) {
                case TO_SELECT_PHOTO /*3*/:
                    this.image = mIntent.getStringExtra("IMAGE");
                    ImageLoader.getInstance().displayImage(this.image, this.iv_head_pic, MyApplication.getInstance().getSampleOptions(R.drawable.pintuan_default_head_icon));
                    if (this.userEntity != null) {
//                        this.updateSystemImageAsyncTask = new NetworkAsyncTask(getActivity(), null, TO_UPLOAD_FILE);
//                        NetworkAsyncTask networkAsyncTask = this.updateSystemImageAsyncTask;
//                        Object[] objArr = new Object[UPLOAD_IN_PROCESS];
//                        objArr[0] = this.userEntity.getUserid();
//                        objArr[TO_UPLOAD_FILE] = Integer.valueOf(UPLOAD_FILE_DONE);
//                        objArr[UPLOAD_FILE_DONE] = this.userEntity.getToken();
//                        objArr[TO_SELECT_PHOTO] = null;
//                        objArr[UPLOAD_INIT_PROCESS] = this.image;
//                        networkAsyncTask.execute(objArr);
                    }
                case PHOTO_PICKED_WITH_DATA /*3021*/:
                    String currentFilePath = AddPhoto.getPath(getActivity(), mIntent.getData());
                    if (AbStrUtil.isEmpty(currentFilePath)) {
                        AbToastUtil.showToast(getActivity(), "未在存储卡中找到这个文件");
                        return;
                    }
                    Intent intent1 = new Intent(this, CropImageActivity.class);
                    intent1.putExtra("PATH", currentFilePath);
                    startActivityForResult(intent1, CAMERA_CROP_DATA);
                case CAMERA_CROP_DATA /*3022*/:
                    String path = mIntent.getStringExtra("PATH");
                    if (this.userEntity == null) {
                        return;
                    }
                    if (AbStrUtil.isEmpty(path)) {
                        Toast.makeText(this, "上传的文件路径出错", Toast.LENGTH_LONG).show();
                        return;
                    }
                    ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(path), this.iv_head_pic, MyApplication.getInstance().getSampleOptions(R.drawable.pintuan_default_head_icon));
                    Message msg = Message.obtain();
                    msg.obj = path;
                    msg.what = TO_UPLOAD_FILE;
                    this.handler.sendMessage(msg);
                case CAMERA_WITH_DATA /*3023*/:
                    Intent intent2 = new Intent(this, CropImageActivity.class);
                    intent2.putExtra("PATH", AbPreferenceUtils.loadPrefString(getActivity(), "path"));
                    startActivityForResult(intent2, CAMERA_CROP_DATA);
                case CITY /*3024*/:
                    this.tv_update_school.setText(mIntent.getStringExtra("SCHOOL"));
                case LOVE /*3025*/:
                    this.love = mIntent.getStringExtra("LOVE");
                    this.tv_update_love.setText(this.love);
                default:
            }
        }
    }
}
