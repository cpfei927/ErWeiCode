package com.cpfei.erweicode;

import com.google.zxing.WriterException;
import com.qianfeng.day36_erweicode.R;
import com.zxing.activity.CaptureActivity;
import com.zxing.encoding.EncodingHandler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tvResult;
	private EditText etInfo;
	private ImageView ivLogo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvResult = (TextView) findViewById(R.id.tv_result);
		etInfo = (EditText) findViewById(R.id.et_info);
		ivLogo = (ImageView) findViewById(R.id.iv_logo);
	}

	// 扫描二维码
	public void scan(View v) {

		Intent intent = new Intent(this, CaptureActivity.class);
		startActivityForResult(intent, 1);
	}

	// 得到扫描的结果
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {

			// 取出二维码中包含的结果
			String key = data.getExtras().getString("result");

			tvResult.setText("扫描的结果=" + key);
		}
	}

	// 生成二维码
	public void scan2(View v) {

		String info = etInfo.getText().toString();
		if (TextUtils.isEmpty(info)) {
			return;
		}

		try {
			// 1.要生成二维码的字符串的信息;2.二维码图片的宽和高
			// 返回一个二维码图片
			Bitmap bitmap = EncodingHandler.createQRCode(info, 350);
			ivLogo.setImageBitmap(bitmap);
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}
}
