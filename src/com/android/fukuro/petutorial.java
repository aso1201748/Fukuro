package com.android.fukuro;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class petutorial extends Activity {
	private RadioButton eraser;
	private RadioButton cutting;
	private RadioButton move;
	private AlertDialog.Builder alertDlg;
	private String path = null;
	private String picname = null;
	private String previousview=null;
	private String pen_mode=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.petutorial);

		Log.d("picture_edit", "onCreate");
		setTitle("画像編集");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent inte = getIntent();
		path = inte.getStringExtra("Fpath");
		picname = inte.getStringExtra("Fname");
		previousview=inte.getStringExtra("previousview");
		Log.d("picture","picname="+picname);
		if(previousview.equals("camera")){
			// 確認ダイアログの生成
	        alertDlg = new AlertDialog.Builder(this);
	        alertDlg.setTitle("画像を保存しますか");
	        //alertDlg.setMessage("");
	        alertDlg.setPositiveButton(
	            "OK",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {
	                    // 上書き ボタンクリック処理
	                }
	            });
	        alertDlg.setNegativeButton(
	        	"キャンセル",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {
	                    // キャンセル ボタンクリック処理
	                }
	            });
		}else{
			// 確認ダイアログの生成
	        alertDlg = new AlertDialog.Builder(this);
	        alertDlg.setTitle("画像を保存しますか");
	        //alertDlg.setMessage("");
	        alertDlg.setPositiveButton(
	            "上書き",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {
	                    // 上書き ボタンクリック処理
	                }
	            });
	        alertDlg.setNeutralButton(
	            "新規",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {
	                    // 新規 ボタンクリック処理
	                }
	            });
	        alertDlg.setNegativeButton(
	        	"キャンセル",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {
	                    // キャンセル ボタンクリック処理
	                }
	            });
		}
		File dir = new File("/data/data/com.android.fukuro/Item");
        if(dir.exists()){

            File file = new File(dir.getAbsolutePath()+"/Testo01.png");
            if (file.exists()) {
                    Bitmap bm2 = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bm3 = Bitmap.createScaledBitmap(bm2,230,250,false);
                    //((ImageView)findViewById(R.id.imageView2)).setImageBitmap(bm3);
            }else{
                //存在しない
            }
        }

		Button btn = (Button)findViewById(R.id.draw_back_btn);
		 btn.setOnClickListener(new View.OnClickListener() {
			 @Override
	        	public void onClick(View v) {

				 //バックボタンでの処理

			 	}
		 });

	     // ラジオグループのオブジェクトを取得
	        RadioGroup rg = (RadioGroup)findViewById(R.id.RadioGroup);
	        eraser = (RadioButton)findViewById(R.id.radioButton1);
	        cutting = (RadioButton)findViewById(R.id.radioButton2);
	        move = (RadioButton)findViewById(R.id.radioButton3);

	        // ラジオグループのチェック状態変更イベントを登録
	        rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

	            // チェック状態変更時に呼び出されるメソッド
	            public void onCheckedChanged(RadioGroup group, int checkedId) {
	                // チェック状態時の処理を記述
	                // チェックされたラジオボタンオブジェクトを取得
	                RadioButton radioButton = (RadioButton)findViewById(checkedId);
	                if(radioButton.getId() == eraser.getId()) {

	                	//消しゴムを押したときの処理
	                    Toast.makeText(petutorial.this, "撮影した写真の不要な部分をなぞることで削除することができます。",Toast.LENGTH_SHORT).show();

	                	pen_mode="eraser";
	                	Log.d("ラジオボタン","消しゴムを押した");

	                }else if(radioButton.getId()==cutting.getId()){

	                	//はさみを押したときの処理

	                	pen_mode="cutting";
	                	Log.d("ラジオボタン","ハサミを押した");

	                }else if(radioButton.getId()==move.getId()){

	                	//移動を押したときの処理

	                	pen_mode="move";
	                	Log.d("ラジオボタン","移動を押した");

	                }
	            }
	        });
	}


	@Override
	public void onDestroy(){
		super.onDestroy();
		path = null;
		picname = null;
		previousview=null;
		pen_mode=null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.picture_edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id == android.R.id.home){
            finish();
            return true;
		}else if (id == R.id.save) {
			// 表示
		     alertDlg.create().show();
			 //penview.saveBitmapToSd();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

