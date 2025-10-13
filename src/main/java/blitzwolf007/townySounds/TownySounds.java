package blitzwolf007.townySounds;

import co.aikar.commands.PaperCommandManager;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class TownySounds extends JavaPlugin implements Listener {

    private EventExecutor executor;
    private static TownySounds instance;
    private final Set<Class<?>> registeredEvents = new HashSet<>();
    private final Map<Class<?>, Entry> eventConfigMap = new HashMap<>();

    private static class Entry {
        SoundTriplet st;
        String notifier;
        String path;

        Entry(SoundTriplet st, String notifier, String path) {
            this.st = st;
            this.notifier = notifier;
            this.path = path;
        }
    }

    @Override
    public void onEnable() {
        instance = this;
        executor = (listener, event) -> {
            getLogger().info("event triggered: " + event.getClass().getSimpleName());
            Entry entry = eventConfigMap.get(event.getClass());
            if (entry == null) {
                getLogger().info("no config entry found for event: " + event.getClass().getSimpleName());
                return;
            }
            if (entry.st.sound() == null) {
                getLogger().info("sound disabled (set to 'none') for event: " + event.getClass().getSimpleName());
                return;
            }

            String notifier = entry.notifier.toLowerCase();
            Object target = null;
            if (!notifier.equals("everyone")) {
                if (entry.path == null || entry.path.isEmpty()) {
                    getLogger().warning("path missing for " + event.getClass().getName());
                    return;
                }
                try {
                    target = reflectGet(event, entry.path);
                } catch (Exception ex) {
                    getLogger().warning("reflection failed for " + event.getClass().getName() + ": " + ex.getMessage());
                    return;
                }
                if (target == null) {
                    return;
                }
            }

            boolean played = switch (notifier) {
                case "everyone" -> {
                    Utils.notifyEveryone(entry.st);
                    yield true;
                }
                case "town" -> {
                    if (target instanceof Town t) {
                        Utils.notifyTown(t, entry.st);
                        yield true;
                    } else {
                        getLogger().warning("target not instance of Town for " + event.getClass().getName());
                        yield false;
                    }
                }
                case "nation" -> {
                    if (target instanceof Nation n) {
                        Utils.notifyNation(n, entry.st);
                        yield true;
                    } else {
                        getLogger().warning("target not instance of Nation for " + event.getClass().getName());
                        yield false;
                    }
                }
                default -> {
                    getLogger().warning("unknown notifier '" + notifier + "' for " + event.getClass().getName());
                    yield false;
                }
            };

            if (played)
                getLogger().info("sound playback completed for event: " + event.getClass().getSimpleName());
        };

        new PaperCommandManager(this).registerCommand(new ReloadCommand());
        saveDefaultConfig();
        reloadConfig();

        Bukkit.getGlobalRegionScheduler().runDelayed(this, task -> loadConfig(), 2L);
    }

    public void loadConfig() {
        eventConfigMap.clear();
        registeredEvents.clear();
        var config = getConfig();
        var eventsSec = config.getConfigurationSection("events");

        int loadedCount = 0;
        for (String eventKey : eventsSec.getKeys(false)) {
            getLogger().info("processing event key: " + eventKey);
            String sound = eventsSec.getString(eventKey + ".sound");
            if (sound == null || sound.equalsIgnoreCase("none"))
                continue;

            float volume = (float) eventsSec.getDouble(eventKey + ".volume", 1.0);
            float pitch = (float) eventsSec.getDouble(eventKey + ".pitch", 1.0);
            String notifier = eventsSec.getString(eventKey + ".notifier", "everyone");
            String path = eventsSec.getString(eventKey + ".path", "");

            SoundTriplet st = new SoundTriplet(sound, volume, pitch);

            Class<? extends Event> eventClass = null;
            if (!eventKey.contains(".")) {
                String[] prefixes = {
                        "com.palmergames.bukkit.towny.event.",
                        "com.palmergames.bukkit.towny.event.nation.",
                        "com.palmergames.bukkit.towny.event.town."
                };
                for (String prefix : prefixes) {
                    try {
                        Class<?> clazz = Class.forName(prefix + eventKey);
                        if (Event.class.isAssignableFrom(clazz)) {
                            eventClass = (Class<? extends Event>) clazz;
                            break;
                        }
                    } catch (ClassNotFoundException e) {
                        getLogger().warning("Class not found for " + prefix + eventKey + ": " + e.getMessage());
                    }
                }
            } else {
                // full name was configd
                try {
                    Class<?> clazz = Class.forName(eventKey);
                    if (Event.class.isAssignableFrom(clazz)) {
                        eventClass = (Class<? extends Event>) clazz;
                    }
                } catch (ClassNotFoundException e) {
                    getLogger().warning("event class not found: " + eventKey + ": " + e.getMessage());
                    continue;
                }
            }

            if (eventClass == null) {
                getLogger().warning(
                        "could not resolve event class for key: " + eventKey + " (check name and Towny version)");
                continue;
            }

            eventConfigMap.put(eventClass, new Entry(st, notifier, path));
            getLogger().info("loaded config for event: " + eventClass.getSimpleName() + " (notifier: " + notifier
                    + ", path: '" + path + "')");

            if (!registeredEvents.contains(eventClass)) {
                getServer().getPluginManager().registerEvent(eventClass, this, EventPriority.MONITOR, executor, this,
                        true);
                registeredEvents.add(eventClass);
                getLogger().info("registered Bukkit event listener for: " + eventClass.getSimpleName());
            }
            loadedCount++;
        }
        getLogger().info("config loading complete. loaded " + loadedCount + " events. total registered: "
                + registeredEvents.size());
    }

    private static Object reflectGet(Object obj, String path) throws Exception {
        String[] parts = path.split("\\.");
        for (String part : parts) {
            Method method = obj.getClass().getMethod(part);
            obj = method.invoke(obj);
            if (obj == null) {
                return null;
            }
        }
        return obj;
    }

    public static TownySounds getInstance() {
        return instance;
    }
}