package mcsq.nxa.progressview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;


public class MainActivity extends Activity implements Runnable {
    private mcsq.nxa.android.ProgressView cpu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.main_activity);

        super.findViewById(R.id.main_activity_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(MainActivity.this).start();
            }
        });

        this.cpu = super.findViewById(R.id.main_activity_cpu);
        //开始旋转的角度
        this.cpu.setStartAngle(new Random().nextInt(360));
        //模拟动画
        new Thread(this).start();
    }


    @Override
    public void run() {
        int value = new Random().nextInt(90) + 1;//模拟内存占用

        for (int i = 0; i < value; i++) {
            this.cpu.setProgress(i + 1, "CPU " + (i + 1) + "%");

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
