package com.example.user.oumobileapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nick Furlo on 3/29/2018.
 */

public class BusTracker extends Activity {

    ImageView bus1= (ImageView) findViewById(R.id.bus1);
    ImageView bus2= (ImageView) findViewById(R.id.bus2);
    ImageView bus3= (ImageView) findViewById(R.id.bus3);
    ImageView bus4= (ImageView) findViewById(R.id.bus4);
    ImageView bus5= (ImageView) findViewById(R.id.bus5);
    ImageView bus6= (ImageView) findViewById(R.id.bus6);


    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bustracker);
        bus1.setVisibility(View.INVISIBLE);
        bus2.setVisibility(View.INVISIBLE);
        bus3.setVisibility(View.INVISIBLE);
        bus4.setVisibility(View.INVISIBLE);
        bus5.setVisibility(View.INVISIBLE);
        bus6.setVisibility(View.INVISIBLE);
        getBus();
    }

    //Method to check PHP for which bus to display
    public void getBus(){
      String type = params[0];
      String login_url = "http://www.secs.oakland.edu/~ksmith/Bustracker.php";
      if (type.equals("login")) {
          try {
              String useremail = params[1];
              String password = params[2];
              URL url = new URL(login_url);
              HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
              httpURLConnection.setRequestMethod("POST");
              httpURLConnection.setDoOutput(true);
              httpURLConnection.setDoInput(true);
              OutputStream outputStream = httpURLConnection.getOutputStream();
              BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
              String post_data = URLEncoder.encode("useremail", "UTF-8") + "=" + URLEncoder.encode(useremail, "UTF-8") + "&"
                      + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
              bufferedWriter.write(post_data);
              bufferedWriter.flush();
              bufferedWriter.close();
              outputStream.close();
              InputStream inputStream = httpURLConnection.getInputStream();
              BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
              String result = "";
              String line = "";
              while ((line = bufferedReader.readLine()) != null) {
                  result += line;
              }
              bufferedReader.close();
              inputStream.close();
              httpURLConnection.disconnect();
              return result;
          } catch (MalformedURLException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }


      return null;
  }
    }


}
