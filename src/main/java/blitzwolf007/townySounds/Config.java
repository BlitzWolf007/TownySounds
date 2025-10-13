package blitzwolf007.townySounds;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Config {

    private static JavaPlugin pl;
    private static FileConfiguration config;

    public static void init(JavaPlugin plugin) {
        pl = plugin;
        config = plugin.getConfig();

        createEventFields(NationEvents.class);
        createEventFields(OtherEvents.class);
        createEventFields(TownEvents.class);

        config.options().copyDefaults(true);
        pl.saveConfig();
    }

    public static void reload()
    {
        pl.reloadConfig();
        config = pl.getConfig();

        createEventFields(NationEvents.class);
        createEventFields(OtherEvents.class);
        createEventFields(TownEvents.class);

        config.options().copyDefaults(true);
        pl.saveConfig();
    }

    public static SoundTriplet getSoundTriplet(Class cls, Class eventCls) {
        SoundTriplet st = new SoundTriplet();

        String path = cls.getSimpleName() + "." + eventCls.getSimpleName();
        String soundName = config.getString(path + ".sound");
        float volume = (float) config.getDouble(path + ".volume");
        float pitch = (float) config.getDouble(path + ".pitch");

        if (soundName.equalsIgnoreCase("none"))
            st.sound = null;
        else
            st.sound = Sound.valueOf(soundName);
        st.volume = volume;
        st.pitch = pitch;

        return  st;
    }

    private static void createEventFields(Class cls) {
        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length == 1 && Event.class.isAssignableFrom(params[0])) {
                    String path = cls.getSimpleName() + "." + params[0].getSimpleName();
                    config.addDefault(path + ".sound", "ENTITY_PLAYER_LEVELUP");
                    config.addDefault(path + ".volume", 1f);
                    config.addDefault(path + ".pitch", 1f);
                }
            }
        }
    }
}
