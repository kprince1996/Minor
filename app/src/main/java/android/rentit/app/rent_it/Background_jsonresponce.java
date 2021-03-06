package android.rentit.app.rent_it;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vipul dimri on 26-07-2017.
 */

public class Background_jsonresponce extends AsyncTask<Void,Void,String>
{


    String jsonurl;
    String line;

    AlertDialog alertDialog;
    Context ctx;


    static String Json_result;


    Background_jsonresponce(Context ctx)
    {
        this.ctx=ctx;

    }


    @Override
    protected void onPreExecute()
    {
        jsonurl="http://muscleuptk.000webhostapp.com/MinorProject/jsoncompletetable_complaint_info.php";

        ProgressDialog progressDialog=new ProgressDialog(ctx);
        progressDialog.setMessage("getting data");
        progressDialog.show();

    }

    @Override
    protected String doInBackground(Void... params) {



        try{
            URL url =  new URL(jsonurl);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();


            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));


            StringBuilder builder=new StringBuilder();

            while ((line=bufferedReader.readLine())!=null)
            {


                builder.append(line+"/n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return builder.toString().trim();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }






        return null;
    }






    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);

    }
    @Override
    protected void onPostExecute(String result)
    {




        Toast.makeText(ctx, "now click on get complaints lists", Toast.LENGTH_LONG).show();
        Json_result = result;




    }







}
