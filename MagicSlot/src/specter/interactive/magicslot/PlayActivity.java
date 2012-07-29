package specter.interactive.magicslot;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class PlayActivity extends Activity {

	private double speed = 0;
	private boolean isSound = true;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        
        // set full screen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        
        // buttons
        // start button
        final Button bPlay = (Button) findViewById(R.id.b_start);
        bPlay.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View view, MotionEvent me) {
		        Start();
		        bPlay.setEnabled(false);
		        bPlay.setBackgroundResource(R.drawable.play_b_start_dis);
				return false;
			}
        });
        
        
        // sound button
        final Button bSound = (Button) findViewById(R.id.b_sound);
        bSound.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				bSound.setBackgroundResource(isSound ? R.drawable.play_sound_off : R.drawable.play_sound_on);
                // TODO sound
                isSound = !isSound;	
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_play, menu);
        return true;
    }
    
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new  Intent(this, MainMenu.class));
            finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
    
    // start rolling bars
    private void Start(){
    	speed = 1;
    	int counter = 0;
    	
    	Bitmap[] slotSymbols = new Bitmap[7];
    	slotSymbols[0] = BitmapFactory.decodeResource(getResources(), R.drawable.play_slot_s1);
    	slotSymbols[1] = BitmapFactory.decodeResource(getResources(), R.drawable.play_slot_s2);
    	slotSymbols[2] = BitmapFactory.decodeResource(getResources(), R.drawable.play_slot_s3);
    	slotSymbols[3] = BitmapFactory.decodeResource(getResources(), R.drawable.play_slot_s4);
    	slotSymbols[4] = BitmapFactory.decodeResource(getResources(), R.drawable.play_slot_s5);
    	slotSymbols[5] = BitmapFactory.decodeResource(getResources(), R.drawable.play_slot_s6);
    	slotSymbols[6] = BitmapFactory.decodeResource(getResources(), R.drawable.play_slot_s7);
    
    	
    	while (speed > 0){
    		if (speed < 100)
    			speed += 10;
    		
    		ImageView iv = (ImageView) this.findViewById(R.id.i_1bar);
    		Bitmap bmp =  BitmapFactory.decodeResource(getResources(), R.drawable.play_slot_bar);
    		Canvas canvas = new Canvas(bmp);
    		
    		int actSymbol = (int)Math.round((counter % 31) * 0.01);
    		float prtH = (float) (bmp.getHeight() * 0.25);
    		float prtW = (float) ((bmp.getWidth() - slotSymbols[1].getWidth()) * 0.5);
    		
    		for(int i = 0; i < 5; i++){
    			int smbl = actSymbol + i;
    			if (smbl > 6)
    				smbl = 6;
    			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    			canvas.drawBitmap(slotSymbols[smbl], prtW, prtH * i + counter, paint);
    		}
    		
    		iv.draw(canvas);
    		
    		
    		counter++;
    		if (counter > 100)
    			return;
    		/*try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
    	}
    }

    
}
