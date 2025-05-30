package jthecoder12.flappyobjects.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import jthecoder12.flappyobjects.Main;

/** Launches the GWT application. */
public final class GwtLauncher extends GwtApplication {
        @Override
        public GwtApplicationConfiguration getConfig () {
            return new GwtApplicationConfiguration(0, 0);
        }

        @Override
        public ApplicationListener createApplicationListener () {
            return new Main();
        }
}
