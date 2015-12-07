package eyeem.com.samplenav;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import eyeem.com.router.Assets;
import eyeem.com.router.Router;
import eyeem.com.router.RouterLoader;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      com.eyeem.router.RouterConstants rc = new com.eyeem.router.RouterConstants();

      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               .setAction("Action", test).show();
         }
      });
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_main, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();

      //noinspection SimplifiableIfStatement
      if (id == R.id.action_settings) {
         return true;
      }

      return super.onOptionsItemSelected(item);
   }

   public static View.OnClickListener test = new View.OnClickListener() {
      @Override public void onClick(View v) {
         Router r = RouterLoader
            .with(v.getContext())
            .plugin(new RequestPlugin())
            .plugin(new DecoratorsPlugin())
            .load(Assets._from(v.getContext(), "map.yaml"));

         String id = "me";

         Bundle bundle = r.bundleFor("u/"+id+"?isNotSelf=true");


      }
   };

   public static class DecoratorsPlugin extends RouterLoader.Plugin {
      public DecoratorsPlugin() { super("decorators"); }

      @Override public void bundleFor(Router.RouteContext context, Object config, Bundle bundle) {
         Log.d(DecoratorsPlugin.class.getSimpleName(), config.toString());
      }
   }

   public static class RequestPlugin extends RouterLoader.Plugin {
      public RequestPlugin() { super("request"); }

      @Override public void bundleFor(Router.RouteContext context, Object config, Bundle bundle) {
         Log.d(RequestPlugin.class.getSimpleName(), config.toString());
      }
   }
}