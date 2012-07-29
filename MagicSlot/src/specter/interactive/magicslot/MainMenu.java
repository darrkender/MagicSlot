package specter.interactive.magicslot;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainMenu extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        // set full screen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        
        // set actions to buttons
        // exit button
        final Button bExit = (Button) findViewById(R.id.b_exit);
        bExit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				finish();
			}
        });
        
        // exit button
        final Button bPlay = (Button) findViewById(R.id.b_play);
        bPlay.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
		        Intent playIntent = new Intent(view.getContext(), PlayActivity.class);
		        startActivity(playIntent);
				finish();
			}
        });
   

    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    
}
