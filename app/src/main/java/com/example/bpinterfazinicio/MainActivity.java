package com.example.bpinterfazinicio;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    FrameLayout panel;
    View front, back;
    Button btnFlip;
    boolean isFront = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        panel = findViewById(R.id.panelPrincipal);
        front = findViewById(R.id.cardFront);
        back = findViewById(R.id.cardBack);
        btnFlip = findViewById(R.id.btnFlip);

        btnFlip.setOnClickListener(v -> flipCard());
    }

    private void flipCard() {
        panel.animate()
                .rotationY(90)
                .setDuration(300)
                .withEndAction(() -> {

                    front.setVisibility(isFront ? View.GONE : View.VISIBLE);
                    back.setVisibility(isFront ? View.VISIBLE : View.GONE);
                    isFront = !isFront;

                    panel.setRotationY(-90);
                    panel.animate()
                            .rotationY(0)
                            .setDuration(300)
                            .start();
                })
                .start();
    }
}
