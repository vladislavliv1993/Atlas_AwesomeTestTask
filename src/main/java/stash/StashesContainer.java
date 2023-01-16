package stash;


public final class StashesContainer {

    private static final ThreadLocal<Stash> stashes = new ThreadLocal<>();

    private static Stash getStash() {
        Stash current = stashes.get();
        if (current == null) {
            Stash stash = new Stash();
            stashes.set(stash);
            return stash;
        }
        return current;
    }

    public static void clearStash() {
        stashes.set(null);
    }

    public static void put(String key, Object value) {
        getStash().put(key, value);
    }

    public static Object get(String key) {
        return getOrDefault(key, null);
    }

    public static void clear() {
        getStash().clear();
    }

    public static Object getOrDefault(String key, Object defaultValue) {
        return getStash().getOrDefault(key, defaultValue);
    }
}
