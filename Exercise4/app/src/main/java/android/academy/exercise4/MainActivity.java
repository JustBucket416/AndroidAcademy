package android.academy.exercise4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final Object lock = new Object();
    private boolean rightLeg = true;
    private boolean isRunning = true;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    @Override
    public void onStart() {
        super.onStart();
        new Thread(new LeftLeg()).start();
        new Thread(new RightLeg()).start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }

    private class LeftLeg implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                try {
                    while (isRunning) {
                        if (rightLeg) lock.wait();
                        else {
                            rightLeg = true;
                            lock.notify();
                            textView.post(new SetTextAction("Left step"));
                            System.out.println("Left step");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class RightLeg implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                try {
                    while (isRunning) {
                        if (!rightLeg) lock.wait();
                        else {
                            rightLeg = false;
                            lock.notify();
                            textView.post(new SetTextAction("Right step"));
                            System.out.println("Right step");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class SetTextAction implements Runnable {

        private final String text;

        SetTextAction(String text) {
            this.text = text;
        }

        @Override
        public void run() {
            textView.setText(text);
        }
    }

}
