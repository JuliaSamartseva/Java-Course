package com.example.labyrinth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final Button generateSolution = findViewById(R.id.generateSolution);
    final LabyrinthView view = findViewById(R.id.view);
    generateSolution.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (!view.showSolution) view.showSolution();
          }
        });
  }
}
