package org.stackwizards.contentmanagerhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.stackwizards.contentmanager.FileStorageUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileStorageUtils.SaveDataToExternalDirectory(this, "outer\\inner\\text.txt", "Lorem ipsum ...");

        String data = FileStorageUtils.GetStoredData(this,"outer\\inner\\text.txt");
        Log.d("HELPER_UTILS", data);
    }

}
