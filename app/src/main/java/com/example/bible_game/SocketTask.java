package com.example.bible_game;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
// import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

public class SocketTask extends AsyncTask<JSONObject, Void, JSONObject>
{

    private final static String IP_ADDRESS = "10.100.102.15"; // My home Socket
    private final static int PORT = 8820;       // HTTP port
    private Socket socket;
    private String sendingStr="";
    private String receivingStr="";
    BufferedReader reader;
    private JSONObject jsonRcv;
    private JSONObject jsonSnd;

    public SocketTask(JSONObject json1)
    {
        this.jsonSnd = json1;
    }

    //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected JSONObject doInBackground(JSONObject... jsonObjects)
    {
        try {
            this.socket = new Socket(IP_ADDRESS, PORT);
            send();
            receive();
            this.socket.close();
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
        return this.jsonRcv;
    }
    // @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void send()
    {
        try {
            byte[] privateKeyBytes = Files.readAllBytes(Paths.get("private_key.pem"));
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
            PublicKey publicKey = keyFactory.generatePublic(privateKeySpec);
            OutputStreamWriter writer = new OutputStreamWriter(this.socket.getOutputStream(), StandardCharsets.UTF_8); // outputStreamWriter creating
            //String sendingStr = this.jsonSnd.toString();
            System.out.println(this.jsonSnd.toString());
           byte[] sendingStr = encryption.encrypt(this.jsonSnd.toString().getBytes(StandardCharsets.UTF_8),publicKey);
            writer.write(Arrays.toString(sendingStr));
            writer.flush();
            Log.d("Result", "sent");
        }
        catch (Exception e) {
            Log.e("Exception", e.toString());
        }
    }
    // @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void receive()
    {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataInputStream in = new DataInputStream(socket.getInputStream());
            byte[] encryptedData = new byte[in.available()];
            in.readFully(encryptedData);
            byte[] pemBytes = encryption.readBytesFromFile("private_key.pem");
            X509EncodedKeySpec spec = new X509EncodedKeySpec(pemBytes);
            PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(spec);
            //char[] charBuffer = new char[1024];
            //StringBuilder stringBuilder = new StringBuilder();
            //reader.read(charBuffer);
            //stringBuilder.append(charBuffer);
            //reader.close();
            //String str = stringBuilder.toString();
            String str = new String(encryption.decrypt(encryptedData,privateKey));
            this.jsonRcv = new JSONObject(str);

        }
        catch (IOException | JSONException e)
        {
            Log.e("Exception", e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


