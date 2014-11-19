package com.android.fukuro;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class RankShowActivity extends Activity implements DownloadListTaskCallback {

	private DBHelper dbHelper = new DBHelper(this);

	public static SQLiteDatabase db;

	Bitmap myBitmap = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);

		db = dbHelper.getWritableDatabase();

		setContentView(R.layout.ranklist);

	}

    @Override
    protected void onResume(){
        super.onResume();
        DownloadImage();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }


	public void DownloadImage(){
		DownloadListTask task = new DownloadListTask(this, this);
		Log.d("check","DL発動");
	    task.execute("");
	}

	@Override
	public void onSuccessDownloadList(String result) {
		// TODO 自動生成されたメソッド・スタブ
		ArrayList<String> imgList = new ArrayList<String>();

		try {
			JSONObject rootObject = new JSONObject(result);
			JSONArray itemArray = rootObject.getJSONArray("item");
			for (int i = 0; i < itemArray.length(); i++) {
			    JSONObject jsonObject = itemArray.getJSONObject(i);
			 // アイテムを追加します
			    imgList.add(jsonObject.getString("ranking_item"));
			    Log.d("json",jsonObject.getString("ranking_item"));
			}
		} catch (JSONException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}


			URL url = null;


			try {
				url = new URL("http://koyoshi.php.xdomain.jp/Item/" + imgList);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.connect();
				InputStream input = connection.getInputStream();
				Bitmap myBitmap = BitmapFactory.decodeStream(input);
			} catch (MalformedURLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		ImageView imgView = (ImageView)findViewById(R.id.imageView1);
	    // アダプターを設定します
	    imgView.setImageBitmap(myBitmap);
	}

	@Override
	public void onFailedDownloadList() {
		// TODO 自動生成されたメソッド・スタブ

	}


}
