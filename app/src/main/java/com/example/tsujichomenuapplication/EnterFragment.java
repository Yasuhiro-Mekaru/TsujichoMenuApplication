package com.example.tsujichomenuapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnterFragment extends Fragment {

    //Buttonインスタンスの活性化を判定するフラグ
    private boolean _buttonFlag = false;

    //Buttonクラスのインスタンスを格納する変数
    Button _buttonJp, _buttonEn, _buttonCh, _buttonChange, _buttonStart;
    TextView _tvTitle, _tvParagraph;

    //TextViewに表示する言葉を変数に格納
    private final String _englishTitle = "Welcome to Tsujicho";
    private final String _englishParagraph = "Select Your Language";
    private final String _englishChange = "Change";
    private final String _englishStart = "Start";
    private final String _chineseTitle = "歡迎來到 Tsujicho";
    private final String _chineseParagraph = "選擇你的語言";
    private final String _chineseChange = "更改";
    private final String _chineseStart = "開始";

    //Buttonインスタンスの背景色の色を格納する変数
    private final int _defaultColor = Color.YELLOW;
    private final int _selectedColor = Color.RED;
    private final int _unableColor = Color.GRAY;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TextViewクラスのインスタンスを生成
        _tvTitle = view.findViewById(R.id.tvEnterTitle);
        _tvParagraph = view.findViewById(R.id.tvEnterParagraph);

        //Buttonクラスのインスタンスを生成
        _buttonJp = view.findViewById(R.id.btnEnterJa); //日本語選択ボタン
        _buttonEn = view.findViewById(R.id.btnEnterEn); //英語選択ボタン
        _buttonCh = view.findViewById(R.id.btnEnterCh); //中国語選択ボタン
        _buttonChange = view.findViewById(R.id.btnEnterChange); //変更ボタン
        _buttonStart = view.findViewById(R.id.btnEnterStart); //startボタン

        //Buttonクラスのインスタンスの背景色をセット
        _buttonJp.setBackgroundColor(_defaultColor);
        _buttonEn.setBackgroundColor(_defaultColor);
        _buttonCh.setBackgroundColor(_defaultColor);
        _buttonChange.setBackgroundColor(_unableColor);
        _buttonStart.setBackgroundColor(_unableColor);

        //Buttonクラスのインスタンスの活性化をセット
        _buttonChange.setEnabled(_buttonFlag);
        _buttonStart.setEnabled(_buttonFlag);

        //Button用のリスナークラスをインスタンス化し、各Buttonインスタンスにセット
        SelectButtonListener selectButtonListener = new SelectButtonListener();
        _buttonJp.setOnClickListener(selectButtonListener);
        _buttonEn.setOnClickListener(selectButtonListener);
        _buttonCh.setOnClickListener(selectButtonListener);
        _buttonChange.setOnClickListener(selectButtonListener);
    }


    //Button押下時のリスナークラス
    private class SelectButtonListener implements View.OnClickListener{
        //DBにリクエストする際のlanguageIdを格納する変数
        private String languageId;

        @Override
        public void onClick(View view) {
            //押下されたButtonのid値を取得し分岐
            int id = view.getId();
            switch (id){
                case R.id.btnEnterJa: //日本語選択ボタン
                    languageId = "1";
                    //DBへアクセスする処理
                    GetMenuData getMenuData = new GetMenuData();
                    getMenuData.execute(languageId);

                    _buttonJp.setBackgroundColor(_selectedColor);
                    _buttonEn.setBackgroundColor(_unableColor);
                    _buttonCh.setBackgroundColor(_unableColor);
                    _buttonChange.setBackgroundColor(_defaultColor);
                    _buttonStart.setBackgroundColor(_defaultColor);

                    _buttonJp.setEnabled(_buttonFlag);
                    _buttonEn.setEnabled(_buttonFlag);
                    _buttonCh.setEnabled(_buttonFlag);

                    _buttonFlag = true;
                    _buttonChange.setEnabled(_buttonFlag);
                    _buttonStart.setEnabled(_buttonFlag);
                    break;
                case R.id.btnEnterEn: //英語選択ボタン
                    languageId = "2";
                    //DBへアクセスする処理
                    getMenuData = new GetMenuData();
                    getMenuData.execute(languageId);

                    _buttonEn.setBackgroundColor(_selectedColor);
                    _buttonJp.setBackgroundColor(_unableColor);
                    _buttonCh.setBackgroundColor(_unableColor);
                    _buttonChange.setBackgroundColor(_defaultColor);
                    _buttonStart.setBackgroundColor(_defaultColor);

                    _buttonJp.setEnabled(_buttonFlag);
                    _buttonEn.setEnabled(_buttonFlag);
                    _buttonCh.setEnabled(_buttonFlag);

                    _buttonFlag = true;
                    _buttonChange.setEnabled(_buttonFlag);
                    _buttonStart.setEnabled(_buttonFlag);

                    _tvTitle.setText(_englishTitle);
                    _tvParagraph.setText(_englishParagraph);
                    _buttonChange.setText(_englishChange);
                    _buttonStart.setText(_englishStart);
                    break;
                case R.id.btnEnterCh: //中国語選択ボタン
                    languageId = "3";
                    //DBへアクセスする処理
                    getMenuData = new GetMenuData();
                    getMenuData.execute(languageId);

                    _buttonCh.setBackgroundColor(_selectedColor);
                    _buttonJp.setBackgroundColor(_unableColor);
                    _buttonEn.setBackgroundColor(_unableColor);
                    _buttonChange.setBackgroundColor(_defaultColor);
                    _buttonStart.setBackgroundColor(_defaultColor);

                    _buttonJp.setEnabled(_buttonFlag);
                    _buttonEn.setEnabled(_buttonFlag);
                    _buttonCh.setEnabled(_buttonFlag);

                    _buttonFlag = true;
                    _buttonChange.setEnabled(_buttonFlag);
                    _buttonStart.setEnabled(_buttonFlag);

                    _tvTitle.setText(_chineseTitle);
                    _tvParagraph.setText(_chineseParagraph);
                    _buttonChange.setText(_chineseChange);
                    _buttonStart.setText(_chineseStart);
                    break;
                case R.id.btnEnterChange: //変更ボタン
                    _buttonJp.setBackgroundColor(_defaultColor);
                    _buttonEn.setBackgroundColor(_defaultColor);
                    _buttonCh.setBackgroundColor(_defaultColor);
                    _buttonChange.setBackgroundColor(_unableColor);
                    _buttonStart.setBackgroundColor(_unableColor);

                    _buttonJp.setEnabled(_buttonFlag);
                    _buttonEn.setEnabled(_buttonFlag);
                    _buttonCh.setEnabled(_buttonFlag);

                    _buttonFlag = false;
                    _buttonChange.setEnabled(_buttonFlag);
                    _buttonStart.setEnabled(_buttonFlag);
                    break;
                case R.id.btnEnterStart: //スタートボタン
                    //Todo 画面遷移
                    break;
            }
        }
    }


    //DBからmenuデータを取得するクラス
    private class GetMenuData extends AsyncTask<String, String, String>{
        //DBのテーブル名を変数に格納
        private final String table = "menuMaster";
        //DBのlanguageIdを格納する変数
        private int languageId = 0;

        @Override
        protected String doInBackground(String... strings) {
            //引数で渡されたlanguageIdをint型に変換
            languageId = Integer.parseInt(strings[0]);

            HashMap<String, Object> jsonInnerData = new HashMap<>();
            jsonInnerData.put("table", table);
            jsonInnerData.put("languageId", languageId);

            HashMap<String, Object> jsonOuterData = new HashMap<>();
            jsonOuterData.put("data", jsonInnerData);

            //WebサーバーのURLを変数に格納
            String serverUrl = "https://tsujicho.herokuapp.com/get_db";
            Log.i("Logging", "EnterFragment GetMenuData url: " + serverUrl);

            HttpURLConnection connection = null;
            InputStream inputStream;
            String response = "";

            try{
                URL url = new URL(serverUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent", "Android");
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                connection.connect();

                OutputStream outputStream = connection.getOutputStream();
                if(jsonOuterData.size() > 0){
                    Log.i("Logging", "EnterFragment GetMenuData doInBackground if");
                    JSONObject jsonObject = new JSONObject(jsonOuterData);
                    String jsonText = jsonObject.toString();
                    Log.i("Logging", "EnterFragment GetMenuData doInBackground jsonText: " + jsonText);
                    PrintStream printStream = new PrintStream(connection.getOutputStream());
                    printStream.print(jsonText);
                    printStream.close();
                }
                outputStream.close();

                int status = connection.getResponseCode();
                if(status == 200){
                    Log.i("Logging", "EnterFragment GetMenuData doInBackground SUCCESS");
                }
                else {
                    Log.i("Logging", "EnterFragment GetMenuData doInBackground ERROR" + String.valueOf(status));
                }
                inputStream = connection.getInputStream();
                response = is2String(inputStream);
                Log.i("Logging", "EnterFragment GetMenuData doInBackground response: " + response);

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(connection != null){
                    connection.disconnect();
                }
            }

            return response;
        }

        //UI側スレッドでの処理
        //引数response はdoInBackgroundメソッドのreturn値
        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            Log.i("Logging", "EnterFragment GetMenuData onPostExecute response: " + response);

            try{
                //引数で渡されたStringインスタンスをJSONオブジェクトに変更
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                int len = jsonArray.length();
                Log.i("Logging", "EnterFragment GetMenuData onPostExecute len: " + len);

                //JSONArrayインスタンスに格納された値をHashmapオブジェクトにし、Arraylistに格納
                ArrayList<HashMap<String, Object>> menuDatas = new ArrayList<>();
                for(int i=0; i<len; i++){
                    JSONObject menuObject = jsonArray.getJSONObject(i);
                    int menuCategory = menuObject.getInt("category");
                    int menuType = menuObject.getInt("type");
                    String menuName = menuObject.getString("name");
                    int menuPrice = menuObject.getInt("price");
                    String menuDescription = menuObject.getString("description");

                    HashMap<String, Object> menuData = new HashMap<>();
                    menuData.put("category", menuCategory);
                    menuData.put("type", menuType);
                    menuData.put("name", menuName);
                    menuData.put("price", menuPrice);
                    menuData.put("description", menuDescription);

                    menuDatas.add(menuData);
                }
                int arrayLength = menuDatas.size();
                Log.i("Logging", "EnterFragment GetMenuData onPostExecute menuDatas: " + arrayLength);

                Bundle bundle = new Bundle();
                //Bundleインスタンスに格納するデータはglobalで持っておく -> 「変更」ボタン押下時に値をクリアするため
                //Bundleクラスオブジェクトへのデータの代入の方法に関しては後ほど検討する
                //Stringインスタンスとして渡して、遷移先でHashmapインスタンスにするかどうか

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        //InputStreamに入ってきたデータをString型に変換する処理
        private String is2String(InputStream is) throws IOException{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            char[] b = new char[2048];
            int line;
            while (0 <= (line = reader.read(b))){
                stringBuffer.append(b, 0, line);
            }
            return stringBuffer.toString();
        }
    }
}
