package com.android.fukuro;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class OutputActivity extends Activity {

	private DBHelper dbHelper = new DBHelper(this);

	public static SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);

		db = dbHelper.getWritableDatabase();

	}

}
