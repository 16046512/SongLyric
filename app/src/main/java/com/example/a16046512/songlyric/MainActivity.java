package com.example.a16046512.songlyric;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etArtist,etSongName;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etArtist = (EditText)findViewById(R.id.etArtist);
        etSongName = (EditText)findViewById(R.id.etName);
        search = (Button) findViewById(R.id.btnSearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] info = {etArtist.getText().toString(),etSongName.getText().toString()};
                Intent i = new Intent(MainActivity.this,Lyric.class);
                i.putExtra("info", info);
                startActivity(i);
            }
        });
    }
}
